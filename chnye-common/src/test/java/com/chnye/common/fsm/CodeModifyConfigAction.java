package com.chnye.common.fsm;

import com.chnye.common.fsm.IAction;

public class CodeModifyConfigAction implements IAction<CodeStateType, CodeEvent, Context> {

	@Override
	public void execute(CodeStateType from, CodeStateType to, CodeEvent event,
			Context context) {
		// TODO Auto-generated method stub
		System.out.println( "CodeModifyConfigAction.execute:" + " form[" + from + "] to[" + to + "] context[" + context + "]" );
	}

}
