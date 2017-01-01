package com.chnye.yese.common.fsm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.fsm.IAction;
import com.chnye.common.fsm.IGuard;
import com.chnye.common.fsm.IStateChangeListener;
import com.chnye.common.fsm.ITransition;
import com.chnye.common.fsm.Transition;
import com.chnye.common.utils.StopWatch;

public class TestFSM {
	CodeStateType initialStateType;
	DefaultStateMachine sm;
	Context context;
	List<ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context >,IGuard<Context>>> transitions;
	IGuard defaultGuard;
	
	@Before
	public void init(){
		StopWatch watch = new StopWatch();
		
		context = new Context();
		
		initialStateType = CodeStateType.FREE;
		
		IAction<CodeStateType, CodeEvent, Context> newConfigAction = new CodeNewConfigAction();
		IAction<CodeStateType, CodeEvent, Context> modifyConfigAction = new CodeModifyConfigAction();
		IAction<CodeStateType, CodeEvent, Context> deleteConfigAction = new CodeDeleteConfigAction();
		
//		Collection<IAction<CodeStateType, CodeEvent, Context>> collections = new ArrayList<IAction<CodeStateType, CodeEvent, Context>>();
//		collections.add( newConfigAction );
	
		defaultGuard = new DefaultGuard();
		
		ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>> transition1 = 
				new Transition<CodeStateType, CodeEventType, IAction<CodeStateType,CodeEvent,Context>, IGuard<Context>>(CodeStateType.FREE, CodeStateType.PREOCCUPY, CodeEventType.NEWCONFIG, newConfigAction, defaultGuard );
		ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>> transition2 = 
				new Transition<CodeStateType, CodeEventType, IAction<CodeStateType,CodeEvent,Context>, IGuard<Context>>(CodeStateType.PREOCCUPY, CodeStateType.OCCUPY, CodeEventType.NEWCONFIG_COMPLETE, newConfigAction, defaultGuard );
		ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>> transition3 = 
				new Transition<CodeStateType, CodeEventType, IAction<CodeStateType,CodeEvent,Context>, IGuard<Context>>(CodeStateType.PREOCCUPY, CodeStateType.FREE, CodeEventType.NEWCONFIG_ROLLBACK, newConfigAction, defaultGuard );
		
		ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>> transition11 = 
				new Transition<CodeStateType, CodeEventType, IAction<CodeStateType,CodeEvent,Context>, IGuard<Context>>(CodeStateType.OCCUPY, CodeStateType.PRERELEASE, CodeEventType.DELETECONFIG, deleteConfigAction, defaultGuard );
		ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>> transition12 = 
				new Transition<CodeStateType, CodeEventType, IAction<CodeStateType,CodeEvent,Context>, IGuard<Context>>(CodeStateType.PRERELEASE, CodeStateType.RELEASE, CodeEventType.DELETECONFIG_COMPLETE, deleteConfigAction, defaultGuard );
		ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>> transition13 = 
				new Transition<CodeStateType, CodeEventType, IAction<CodeStateType,CodeEvent,Context>, IGuard<Context>>(CodeStateType.PRERELEASE, CodeStateType.OCCUPY, CodeEventType.DELETECONFIG_ROLLBACK, deleteConfigAction, defaultGuard );
		
		transitions = 	new ArrayList<>();
		transitions.add( transition1 );
		transitions.add( transition2 );
		transitions.add( transition3 );
		transitions.add( transition11 );
		transitions.add( transition12 );
		transitions.add( transition13 );
		
		sm = new DefaultStateMachine(initialStateType, context, transitions);
		
		
		IStateChangeListener<CodeStateType, CodeEvent> listener = new CodeStateChangeListener();
		sm.addListener( listener );
		watch.stop();
		System.out.println( watch.elapsedTimeToMessage( " -----------1 " ) );
		System.out.println( watch.toString() );
	}
	
	
	@Test
	public void testFSM(){
		CodeEvent  codeEvent1 = new CodeEvent( CodeEventType.NEWCONFIG );
		CodeEvent  codeEvent2 = new CodeEvent( CodeEventType.NEWCONFIG_COMPLETE );
		CodeEvent  codeEvent3 = new CodeEvent( CodeEventType.NEWCONFIG_ROLLBACK );
		
		CodeEvent  codeEvent11 = new CodeEvent( CodeEventType.DELETECONFIG );
		CodeEvent  codeEvent12 = new CodeEvent( CodeEventType.DELETECONFIG_COMPLETE );
		CodeEvent  codeEvent13 = new CodeEvent( CodeEventType.DELETECONFIG_ROLLBACK );
		
		sm.fire( codeEvent1, context );
		sm.fire( codeEvent2, context );
		sm.fire( codeEvent3, context );
		
		sm.fire( codeEvent11, context );
		sm.fire( codeEvent12, context );
		sm.fire( codeEvent13, context );
		
		
		
	}
}
