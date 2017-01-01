package com.chnye.common.fsm;

import java.util.Collection;


public interface ITransition<ST,ET,A,G> {
	ST getSource();
	ST getTarget();
	ET getEvent();
	G  getGuard();
	Collection<A> getActions();
}
