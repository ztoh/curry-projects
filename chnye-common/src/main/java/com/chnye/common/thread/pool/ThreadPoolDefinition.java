package com.chnye.common.thread.pool;

public class ThreadPoolDefinition {

	private int minSize;
	private int maxSize;
	private long keepAliveTime;
	private int queueSize;
	private String rejectedPolicy;
	public int getMinSize() {
		return minSize;
	}
	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public long getKeepAliveTime() {
		return keepAliveTime;
	}
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	public String getRejectedPolicy() {
		return rejectedPolicy;
	}
	public void setRejectedPolicy(String rejectedPolicy) {
		this.rejectedPolicy = rejectedPolicy;
	}
	
	
}
