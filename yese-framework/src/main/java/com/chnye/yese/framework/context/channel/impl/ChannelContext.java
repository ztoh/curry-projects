package com.chnye.yese.framework.context.channel.impl;

import com.chnye.yese.framework.context.IContext;
import com.chnye.yese.framework.context.channel.ChannelConstants;
import com.chnye.yese.framework.context.channel.IChannelContext;
import com.chnye.yese.framework.context.impl.Context;
import com.chnye.yese.framework.context.impl.ContextFactory;

public class ChannelContext extends Context implements IChannelContext{

//	private static final String SERVER_CONTEXT_NAME = "serverContext";
	private static  IContext serverContext = ContextFactory.create( ChannelConstants.SERVER_CONTEXT_NAME );
	 
	public static IContext getServerContext(){
		return serverContext;
	}
	
	public ChannelContext(){
		getDatas().put( ChannelConstants.SERVER_CONTEXT_NAME, serverContext );
	}
	
	@Override
	public IContext getSessionContext() {
		// TODO Auto-generated method stub
		return (IContext)getDatas().get( ChannelConstants.SESSION_CONTEXT_NAME );
	}

	@Override
	public void setSessionContext(IContext sessionContext) {
		// TODO Auto-generated method stub
		getDatas().put( ChannelConstants.SESSION_CONTEXT_NAME, sessionContext );
	}

	@Override
	public IContext getFlowContext() {
		// TODO Auto-generated method stub
		return (IContext)getDatas().get( ChannelConstants.FLOW_CONTEXT_NAME );
	}

	@Override
	public void setFlowContext(IContext flowContext) {
		// TODO Auto-generated method stub
		getDatas().put( ChannelConstants.FLOW_CONTEXT_NAME, flowContext );
	}

	public void reset(){
		serverContext = ContextFactory.create( ChannelConstants.SERVER_CONTEXT_NAME );
	}

}
