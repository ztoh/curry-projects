package com.chnye.common.utils;

/*
 * 如何使用
 class Dispatch{
   private WatcherList<IHandler> handlers = WatcherList.create();
   
 }
 */

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WatcherList<L> {

	private CopyOnWriteArrayList<L>  listenerLists = new CopyOnWriteArrayList<L>();
	
	public static <L> WatcherList<L> create(){
		return new WatcherList<L>();
	}
	
	public static <L> WatcherList<L> create( L listener ){
		WatcherList<L>  watchLists = WatcherList.<L>create();
		watchLists.add( listener );
		return watchLists;
	}
	
	public WatcherList<L> add( L listener ){
		listenerLists.add( listener );
		return this;
	}
	
	public WatcherList<L> remove( L listener ){
		listenerLists.remove( listener );
		return this;
	}
	
	public List<L>  getList(){
		return listenerLists;
	}
	
}
