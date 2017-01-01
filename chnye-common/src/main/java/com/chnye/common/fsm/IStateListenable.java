package com.chnye.common.fsm;

import com.chnye.common.able.IListenable;

public interface IStateListenable<L extends IStateChangeListener<?,?>> extends IListenable<L>{

}
