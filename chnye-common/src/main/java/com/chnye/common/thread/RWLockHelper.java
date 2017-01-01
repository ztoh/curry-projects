package com.chnye.common.thread;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.marketcetera/core/2.4.2/org/marketcetera/core/LockHelper.java#LockHelper

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockHelper {
	
	private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public <T> T executeWrite( Callable<T> inBlock ) throws Exception{
		try {
			rwLock.writeLock().lock();
			return inBlock.call();
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public void executeWrite( Runnable inBlock ) {
		try {
			rwLock.writeLock().lock();
			inBlock.run();
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	public <T> T executeRead( Callable<T> inBlock ) throws Exception{
		try {
			rwLock.readLock().lock();
			return inBlock.call();
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	
	public void executeRead( Runnable inBlock ) {
		try {
			rwLock.readLock().lock();
			inBlock.run();
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	
	
}
