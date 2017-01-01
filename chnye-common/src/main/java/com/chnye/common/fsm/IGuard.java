package com.chnye.common.fsm;

public interface IGuard<C> {

	boolean allowed(C context );
}
