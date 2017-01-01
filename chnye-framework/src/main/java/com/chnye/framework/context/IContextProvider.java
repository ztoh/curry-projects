package com.chnye.framework.context;


public interface IContextProvider {
	IContext createContext();
	IContext createContext( String contextName );
}
