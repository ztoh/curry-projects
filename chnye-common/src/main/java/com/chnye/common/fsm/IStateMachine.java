package com.chnye.common.fsm;

import java.util.List;

public interface IStateMachine<E,A, G,C,ST,ET> {

	ST getInitialState();
	ST getCurrentState();
	ST fire( E event, C context );
	List<ITransition<ST, ET, A,G>> getTransitions();
	
}
