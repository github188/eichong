package com.bluemobi.product.cache;

public class CacheStatistics {
	private long cacheSize ;
	private long diskSize ;
	private long heapSize ;
	private long writerQueneLength ;
	private long hitCount ;
	private long missCount ;
	private long cachePutCount ;
	private long cacheRemoveCount ;
	public long getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}
	public long getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}
	public long getHeapSize() {
		return heapSize;
	}
	public void setHeapSize(long heapSize) {
		this.heapSize = heapSize;
	}
	public long getWriterQueneLength() {
		return writerQueneLength;
	}
	public void setWriterQueneLength(long writerQueneLength) {
		this.writerQueneLength = writerQueneLength;
	}
	public long getHitCount() {
		return hitCount;
	}
	public void setHitCount(long hitCount) {
		this.hitCount = hitCount;
	}
	public long getMissCount() {
		return missCount;
	}
	public void setMissCount(long missCount) {
		this.missCount = missCount;
	}
	public long getCachePutCount() {
		return cachePutCount;
	}
	public void setCachePutCount(long cachePutCount) {
		this.cachePutCount = cachePutCount;
	}
	public long getCacheRemoveCount() {
		return cacheRemoveCount;
	}
	public void setCacheRemoveCount(long cacheRemoveCount) {
		this.cacheRemoveCount = cacheRemoveCount;
	}
	
}
