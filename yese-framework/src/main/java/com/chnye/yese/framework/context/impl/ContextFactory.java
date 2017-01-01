package com.chnye.yese.framework.context.impl;

import com.chnye.yese.framework.context.IContext;
import com.chnye.yese.framework.context.IContextProvider;

public class ContextFactory {

	private static IContextProvider contextProviderInstance = new ContextProvider();
	
	public static IContextProvider getContextProvider(){
		return contextProviderInstance;
	}
	public static void setContextProvider( IContextProvider contextProvider ){
		contextProviderInstance = contextProvider;
	}
	
	public static IContext create( String name ){
		return contextProviderInstance.createContext( name );
	}
	
}
