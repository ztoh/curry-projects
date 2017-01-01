package com.chnye.common.fsm;

import com.chnye.common.enums.AbstractType;
import com.chnye.common.fsm.IEvent;

public class CodeEvent extends AbstractType<CodeEventType> implements IEvent<CodeEventType>{

	public CodeEvent(CodeEventType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
