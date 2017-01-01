package com.chnye.common.eventdispatch;


@SuppressWarnings("rawtypes")
public interface EventDispatcher {
	void dispatch( Event event );
	void register( Class<? extends Enum> eventType, EventHandler handler );
	void unregister( Class<? extends Enum> eventType, EventHandler handler );
}
