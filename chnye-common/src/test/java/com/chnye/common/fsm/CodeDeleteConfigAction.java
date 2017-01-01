package com.chnye.common.fsm;

import com.chnye.common.fsm.IAction;

public class CodeDeleteConfigAction implements IAction<CodeStateType, CodeEvent, Context> {

	@Override
	public void execute(CodeStateType from, CodeStateType to, CodeEvent event,
			Context context) {
		// TODO Auto-generated method stub
		System.out.println( "CodeDeleteConfigAction.execute:" + " form[" + from + "] to[" + to + "] context[" + context + "]" );
	}

}
