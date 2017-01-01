package com.chnye.common.fsm;

public interface IAction<ST, E, C> {
	void execute( ST from, ST to, E event, C context );
}
