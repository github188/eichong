package com.bluemobi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSON;

public abstract class RankingTree<T> {
    private final AtomicInteger version = new AtomicInteger(0);
    private final Comparator<T> comparator;
    protected final TreeMap<T, Integer> map;

    public RankingTree(Collection<T> objects, Comparator<T> comparator) {
        this.comparator = comparator;
        this.map = new TreeMap<T, Integer>(comparator);

        for (Iterator iterator = objects.iterator(); iterator.hasNext(); ) {
            Object o = iterator.next();
            this.map.put((T) o, this.version.incrementAndGet());
        }

        int ranking = 0;
        for (Map.Entry<T, Integer> entry : this.map.entrySet()) {
            entry.setValue(++ranking);
            postUpdate(ranking, entry.getKey(), false);
        }
    }

    protected abstract T preUpdate(T paramT, int paramInt);

    protected abstract T postUpdate(int paramInt, T paramT, boolean paramBoolean);

    public synchronized boolean containsKey(T o) {
        return this.map.containsKey(o);
    }

    public synchronized int add(T o) {
        int ranking;
        NavigableMap<T, Integer> tailMap;
        if (this.map.containsKey(o)) {
            throw new AssertionError(JSON.toJSONString(o));
        }
        int version = this.version.incrementAndGet();
        this.map.put(o, version);
        Map.Entry lowerEntry = this.map.lowerEntry(o);
        if (lowerEntry == null) {
            ranking = 0;
            tailMap = this.map;
        } else {
            ranking = (Integer) lowerEntry.getValue();
            tailMap = this.map.tailMap((T) lowerEntry.getKey(), false);
        }
        for (Map.Entry<T, Integer> entry : tailMap.entrySet()) {
            entry.setValue(++ranking);
            postUpdate(ranking, entry.getKey(), entry.getKey() == o);
        }
        return ranking;
    }

    public synchronized boolean remove(T o) {
        if (this.map.remove(o) == null) {
            return false;
        }
        Map.Entry lowerEntry = this.map.lowerEntry(o);
        if (lowerEntry == null) {
            return true;
        }
        int ranking = (Integer) lowerEntry.getValue();
        NavigableMap<T, Integer> tailMap = this.map.tailMap((T) lowerEntry.getKey(), false);
        for (Map.Entry<T, Integer> entry : tailMap.entrySet()) {
            entry.setValue(++ranking);
            postUpdate(ranking, entry.getKey(), entry.getKey() == o);
        }
        this.version.incrementAndGet();
        return true;
    }

    public synchronized int update(T o, int value) {
        int ranking;
        NavigableMap<T, Integer> subMap;
        Map.Entry<T, Integer> lowerEntry = this.map.lowerEntry(o);
        Map.Entry<T, Integer> higherEntry = this.map.higherEntry(o);

        this.map.remove(o);
        preUpdate(o, value);
        this.map.put(o, -this.version.incrementAndGet());

        Map.Entry<T, Integer> newLowerEntry = this.map.lowerEntry(o);

        if ((lowerEntry != null) && (newLowerEntry != null)) {
            int cmp = this.comparator.compare(lowerEntry.getKey(), newLowerEntry.getKey());
            if (cmp == 0) {
                ranking = lowerEntry.getValue() + 1;
                this.map.put(o, ranking);
                return ranking;
            }
            if (cmp < 0) {
                ranking = lowerEntry.getValue();
                subMap = this.map.subMap(lowerEntry.getKey(), false, o, true);
            } else {
                ranking = newLowerEntry.getValue();
                if (higherEntry == null)
                    subMap = this.map.tailMap(newLowerEntry.getKey(), false);
                else
                    subMap = this.map.subMap(newLowerEntry.getKey(), false, higherEntry.getKey(), false);
            }
        } else {
            if ((lowerEntry == null) && (newLowerEntry == null)) {
                this.map.put(o, 1);
                return 1;
            }
            if (lowerEntry == null) {
                ranking = 0;
                subMap = this.map.headMap(o, true);
            } else {
                ranking = 0;
                if (higherEntry == null) {
                    subMap = this.map;
                } else {
                    subMap = this.map.headMap(higherEntry.getKey(), false);
                }
            }
        }
        int myRanking = 0;
        for (Map.Entry<T, Integer> entry : subMap.entrySet()) {
            entry.setValue(++ranking);
            System.out.println("rankingTree ranking:" + ranking);
            postUpdate(ranking, entry.getKey(), entry.getKey() == o);
            if (entry.getKey() == o) {
                myRanking = ranking;
            }
        }
        return myRanking;
    }

    public synchronized ArrayList<T> getTop(int n) {
        ArrayList<T> list = new ArrayList<T>(n);
        int i = 0;
        for (Map.Entry<T, Integer> entry : this.map.entrySet()) {
            list.add(entry.getKey());
            if (++i >= n) {
                break;
            }
        }
        return list;
    }

    public synchronized void clear() {
        this.map.clear();
    }

    public int size() {
        return map.size();
    }
}