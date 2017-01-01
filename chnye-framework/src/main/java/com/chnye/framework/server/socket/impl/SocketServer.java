package com.chnye.framework.server.socket.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import com.chnye.framework.server.AbstractServer;
import com.chnye.framework.server.IConnection;
import com.chnye.framework.server.IConnectionFactory;
import com.chnye.framework.server.ServerException;
import com.chnye.framework.server.socket.ISocketServer;

public class SocketServer extends AbstractServer implements ISocketServer, Runnable{

	private int port;
	
	private ServerSocket  serverSocket;
	
	private Thread thread;

	private volatile boolean bRunning;
	private EServerStatus serverStatus;
	
	private EServerProtocol serverProtocol;
	
	private String name;
	
	public SocketServer( int port, IConnectionFactory connectionFactory ){
		super( connectionFactory );
		this.port = port;
		this.serverProtocol = EServerProtocol.SOCKET;
		this.name = "SocketServer-" + System.currentTimeMillis();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean interrupted = false;
		while( !interrupted ){
			try{
				loop();
				interrupted = Thread.interrupted();
			} catch( IOException e ){
				e.printStackTrace();
				interrupted = true;
			}
		}//end while
		setRunning( false );
		thread = null;
	}

	private void loop() throws IOException{
		setRunning( true );
		while( isRunning() ){
			Socket s = serverSocket.accept();
			IConnection newConnection = getConnectionFactory().createConnection( new SocketContext(s) );
			super.notifyConnectionCreated( newConnection );
			newConnection.open();
		}//end while
	}

	@Override
	public void start() throws ServerException {
		// TODO Auto-generated method stub
		try{
			serverSocket = new ServerSocket( port );
		} catch ( IOException e ){
			throw new ServerException("Couldn't bind port:" + port + " : " + e.getMessage() );
		}
		thread = new Thread( this );
		thread.setDaemon( true );
		thread.start();
	}


	@Override
	public void stop() throws ServerException {
		// TODO Auto-generated method stub
		setRunning( false );
		thread.interrupt();
	}

	public void setRunning( boolean bRunning ){
		this.bRunning = bRunning;
		if( bRunning ){
			serverStatus = EServerStatus.START;
		} else {
			serverStatus = EServerStatus.STOP;
		}
	}
	public boolean isRunning(){
		return this.bRunning;
	}


	@Override
	public EServerStatus getServerStatus() {
		// TODO Auto-generated method stub
		return serverStatus;
	}


	@Override
	public EServerProtocol getServerProtocol() {
		// TODO Auto-generated method stub
		return serverProtocol;
	}


	@Override
	public void restart() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return port ;
	}


	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return name;
	}


	@Override
	public void setRequestEncoding(String encoding) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setResponseEncoding(String encoding) {
		// TODO Auto-generated method stub
		
	}
	
}
