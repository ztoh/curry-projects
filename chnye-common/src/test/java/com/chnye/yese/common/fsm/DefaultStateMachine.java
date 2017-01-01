package com.chnye.yese.common.fsm;

import java.util.List;

import com.chnye.common.fsm.IAction;
import com.chnye.common.fsm.IGuard;
import com.chnye.common.fsm.ITransition;
import com.chnye.common.fsm.StateMachine;

public class DefaultStateMachine extends StateMachine<CodeEvent, IAction<CodeStateType, CodeEvent, Context>, IGuard<Context>, Context, CodeStateType, CodeEventType> {

	public DefaultStateMachine(
			CodeStateType initialStateType,
			Context context,
			List<ITransition<CodeStateType, CodeEventType, IAction<CodeStateType, CodeEvent, Context>,IGuard<Context>>> transitions) {
		super(initialStateType, context, transitions);
		// TODO Auto-generated constructor stub
	}

}
