package com.chnye.common.eventbus;

import java.util.EventListener;

public interface IEventListener<E> extends EventListener {
	void onEvent( E event );
}
