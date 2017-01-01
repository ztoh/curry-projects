package com.chnye.common.fsm;

public interface IStateChangeListener<ST,E> {
	void stateChanged( ST from, ST to, E event );
}
