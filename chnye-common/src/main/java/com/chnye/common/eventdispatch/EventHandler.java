package com.chnye.common.eventdispatch;


@SuppressWarnings("rawtypes")
public interface EventHandler<T extends Event> {
	boolean accept(T event );
	void handle(T eventT );
}
