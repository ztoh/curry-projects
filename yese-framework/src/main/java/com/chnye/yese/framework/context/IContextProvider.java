package com.chnye.yese.framework.context;


public interface IContextProvider {
	IContext createContext();
	IContext createContext( String contextName );
}