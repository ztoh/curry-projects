package com.chnye.framework.server.socket;

import com.chnye.framework.server.IServer;

public interface ISocketServer extends IServer{
	
	void restart() throws Exception;
	
	int getPort();
	
	String getServerName();
	
	
	void setRequestEncoding( String encoding );
	
	void setResponseEncoding( String encoding );
	
	
}
