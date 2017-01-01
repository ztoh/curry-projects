package com.chnye.framework.server.socket.impl;

import java.net.Socket;

import com.chnye.framework.server.IConnectionCreationContext;

public class SocketContext implements IConnectionCreationContext{

	private Socket socket;
	
	public SocketContext(){}
	
	public SocketContext( Socket socket ){
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
