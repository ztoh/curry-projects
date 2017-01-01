package com.chnye.yese.framework.context.impl;

import com.chnye.yese.framework.context.IContext;
import com.chnye.yese.framework.context.IContextProvider;

public class ContextProvider implements IContextProvider{

	@Override
	public IContext createContext() {
		// TODO Auto-generated method stub
		return new Context();
	}

	@Override
	public IContext createContext(String contextName) {
		// TODO Auto-generated method stub
		return new Context( contextName );
	}

	
}
