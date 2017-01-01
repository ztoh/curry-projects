package com.chnye.framework.lifecycle;

/*
 * http://www.grepcode.com/file/repo1.maven.org/maven2/org.azeckoski/reflectutils/0.9.20/org/azeckoski/reflectutils/LifecycleManager.java#LifecycleManager
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.chnye.common.able.ICloseable;
import com.chnye.common.lifecycle.ILifecycle;
import com.chnye.common.lifecycle.LifecycleException;

//public class LifecycleManager implements ICloseable{
public class LifecycleManager implements ILifecycle{

	private  boolean isActive = false;
	
	private  List<ILifecycle>  lists = new ArrayList<ILifecycle>();
	
	private  Lock  lock = new ReentrantLock();
	
	public  boolean isActive(){
		return isActive;
	}
	public  void setActive( boolean isActive ){
		isActive = isActive;
	}
	
	public  void register( ILifecycle lifecycle ){
		if( isActive ){
			lock.lock();
			try{
				lists.add( lifecycle );
			} finally {
				lock.unlock();
			}
		}
	}
	
	public void unregister( ILifecycle lifecycle ){
		if( isActive ){
			lock.lock();
			try{
				lists.remove( lifecycle );
			} finally {
				lock.unlock();
			}
		}
	}
	
	public List<ILifecycle> getLifecycles(){
		return lists;
	}
	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.init();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void reInit() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.reInit();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.start();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void reStart() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.reStart();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public boolean isStart() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				lifecycle.run();
			}
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.stop();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public boolean isStop() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void lifecycleDestroy() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.lifecycleDestroy();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void lifecycleClear() throws LifecycleException {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			for( ILifecycle lifecycle : lists ){
				try{
					lifecycle.lifecycleClear();
				} catch( LifecycleException e ){
					e.printStackTrace();
				}
			}
			
			lists.clear();
		} finally {
			lock.unlock();
		}
	}
	
	
	
//	@Override
//	public void close() {
//		// TODO Auto-generated method stub
//		
//	}



}
