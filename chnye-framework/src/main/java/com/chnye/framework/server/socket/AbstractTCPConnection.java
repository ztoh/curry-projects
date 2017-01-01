package com.chnye.framework.server.socket;

//http://www.grepcode.com/file/repo1.maven.org/maven2/net.anotheria/ano-net/2.1.0/net/anotheria/net/tcp/server/AbstractTCPConnection.java?av=f

import java.io.IOException;
import java.net.Socket;

import com.chnye.framework.server.AbstractConnection;

public abstract class AbstractTCPConnection extends AbstractConnection implements Runnable{

	protected Socket socket;
	
	protected Thread thread;
	
	public AbstractTCPConnection( Socket socket ){
		this.socket = socket;
	}
	
	@Override
	public abstract void run() ;

	@Override
	public void open() {
		super.open();
		thread = new Thread( this );
		thread.start();
	}

	@Override
	public void close() {
		try{
			socket.close();
		} catch( IOException e ){
			e.printStackTrace();
		}
		super.close();
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
