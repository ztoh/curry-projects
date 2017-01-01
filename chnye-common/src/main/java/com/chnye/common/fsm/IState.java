package com.chnye.common.fsm;

import com.chnye.common.enums.IType;

public interface IState<T extends Enum<?>> extends IType<T>{
	void enterAction();
	void exitAction();
}
