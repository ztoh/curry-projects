package com.chnye.common.fsm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateMachine<E extends IEvent<ET>,A extends IAction<ST,E,C>,G extends IGuard<C>, C,ST extends Enum<?>,ET extends Enum<?>> 
implements IStateMachine<E,A,G,C,ST,ET> , 
ILifeCycle, 
IStateListenable<IStateChangeListener<ST, E>>{

	private ST initialStateType;
	private ST currentStateType;
	private List<ITransition<ST, ET, A,G>> transitions ;
	private C context;
	private List<IStateChangeListener<ST, E>> listeners;
	private ST lastStateEventType;		//上一次状态 
	
	public StateMachine( ST initialStateType, C context, List<ITransition<ST, ET, A,G>> transitions ){
		this.initialStateType = initialStateType;
		this.currentStateType = this.initialStateType;
		this.context = context;
		this.transitions = transitions;
		listeners = new ArrayList<>();
	}
	
	@Override
	public ST getInitialState() {
		// TODO Auto-generated method stub
		return initialStateType;
	}

	@Override
	public ST getCurrentState() {
		// TODO Auto-generated method stub
		return currentStateType;
	}

	@Override
	public ST fire(E event, C context) {
		// TODO Auto-generated method stub
		
		ITransition<ST, ET, A,G> transition = getTransition( currentStateType, event.getType() );
		if( transition != null ){
			if( transition.getGuard() != null && !transition.getGuard().allowed(context) ){
				System.out.println( "StateMachine.fire: matched but guard false, eventType[" + event.getType() + "] " + " currentStateType[" + currentStateType + "] to[" + transition.getTarget() + "] ");
				return null;
			}
			System.out.println( "StateMachine.fire: eventType[" + event.getType() + "] " + " currentStateType[" + currentStateType + "] to[" + transition.getTarget() + "] ");
			Collection<A> actions = transition.getActions();
			for( IAction<ST, E, C> action : actions ){
				action.execute( currentStateType, transition.getTarget(), event, context );
			}	
			this.lastStateEventType = this.currentStateType;
			this.currentStateType = transition.getTarget();
			
			notifyListener( this.lastStateEventType, this.currentStateType, event );
			
			return transition.getTarget();
		}
		System.out.println( "StateMachine.fire: no matched transition! eventType[" + event.getType() + "] " + " currentStateType[" + currentStateType + "] " );
		return null;
	}

	@Override
	public List<ITransition<ST, ET, A,G>> getTransitions() {
		// TODO Auto-generated method stub
		return transitions;
	}

	private ITransition<ST, ET, A,G> getTransition( ST currentStateType, ET eventType ){
		for( ITransition<ST, ET, A,G> transition : transitions ){
			if( transition.getSource() == currentStateType && transition.getEvent() == eventType ){
				return transition;
			}
		}
		return null;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(IStateChangeListener<ST, E> listener) {
		// TODO Auto-generated method stub
		if( !listeners.contains( listener ) ){
			listeners.add( listener );
		}
	}

	@Override
	public void removeListener(IStateChangeListener<ST, E> listener) {
		// TODO Auto-generated method stub
		if( listeners.contains( listener ) ){
			listeners.remove( listener );
		}
	}

	private void notifyListener( ST from, ST to, E event ){
		for( IStateChangeListener<ST, E> listener : listeners ){
			listener.stateChanged(from, to, event);
		}
	}
	
//	@Override
//	public void clear() {
//		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException();
//	}

//	@Override
//	public List<IStateChangeListener<ST, E>> getListeners() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void clearListener() {
		// TODO Auto-generated method stub
		
	}
	
}
