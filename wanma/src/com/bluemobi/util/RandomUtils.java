package com.bluemobi.util;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int n) {
        return random.nextInt(n);
    }

    public static int nextInt(int start, int end) {
        return (start + random.nextInt(end - start));
    }

    public static int nextPercent() {
        return random.nextInt(100) + 1;
    }

    public static boolean checkPercent(int rate) {
        return random.nextInt(100) < rate;
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    public static <T> T getRandomOne(List<T> list) {
        if (list.size() == 0) {
            return null;
        }
        return list.get(nextInt(list.size()));
    }
}