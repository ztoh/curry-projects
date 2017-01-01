package com.chnye.yese.common.fsm;

import com.chnye.common.fsm.IStateChangeListener;

public class CodeStateChangeListener implements IStateChangeListener<CodeStateType, CodeEvent> {

	@Override
	public void stateChanged(CodeStateType from, CodeStateType to, CodeEvent event) {
		// TODO Auto-generated method stub
		System.out.println( "CodeStateChangeListener.stateChanged from[" + from + "] to[" + to + "] e[" + event +"] ");
	}

}
