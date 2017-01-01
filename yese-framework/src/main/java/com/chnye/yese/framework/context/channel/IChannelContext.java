package com.chnye.yese.framework.context.channel;

import com.chnye.yese.framework.context.IContext;

public interface IChannelContext extends IContext{
	IContext getSessionContext();
	void setSessionContext( IContext sessionContext );
	
	IContext getFlowContext();
	void setFlowContext( IContext flowContext );
}
