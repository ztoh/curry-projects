package com.chnye.common.fsm;

import java.util.Collection;
import java.util.LinkedList;

public class Transition<ST extends Enum<?>, ET extends Enum<?>, A,G > implements ITransition<ST, ET, A,G> {

	private ST sourceStateType;
	private ST targetStateType;
	private ET eventType;
	private G guard;
	private Collection<A> actions;
	
	public Transition( ST source, ST target, ET eventType, A action, G guard ){
		this.sourceStateType = source;
		this.targetStateType = target;
		this.eventType = eventType;
		if( actions == null ){
			actions = new LinkedList<A>();
		}
		actions.add( action );
		this.guard = guard;
	}
	
	public Transition( ST source, ST target, ET eventType, Collection<A> actions, G guard ){
		this.sourceStateType = source;
		this.targetStateType = target;
		this.eventType = eventType;
		this.actions = actions;
		this.guard = guard;
	}
	
	@Override
	public ST getSource() {
		// TODO Auto-generated method stub
		return sourceStateType;
	}

	@Override
	public ST getTarget() {
		// TODO Auto-generated method stub
		return targetStateType;
	}

	@Override
	public ET getEvent() {
		// TODO Auto-generated method stub
		return eventType;
	}

	@Override
	public Collection<A> getActions() {
		// TODO Auto-generated method stub
		return actions;
	}

	@Override
	public G getGuard() {
		// TODO Auto-generated method stub
		return guard;
	}

}
