package com.chnye.framework.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


import com.chnye.framework.server.AbstractServer;
import com.chnye.framework.server.IConnection;
import com.chnye.framework.server.IConnectionFactory;
import com.chnye.framework.server.ServerException;
import com.chnye.framework.server.socket.ISocketServer;



public class NIOSocketServer extends AbstractServer implements ISocketServer, Runnable{

	private int port;

	protected ServerSocketChannel ssc;
	protected Selector selector;
	
	
	private ServerSocket  serverSocket;
	private Thread thread;

	private volatile boolean bRunning;
	private EServerStatus serverStatus;
	
	private EServerProtocol serverProtocol;
	
	private String name;
	
	public NIOSocketServer( int port, IConnectionFactory connectionFactory ){
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
				if( selector != null ){
					nioLoop();
				}
				interrupted = Thread.interrupted();
			} catch( IOException e ){
				e.printStackTrace();
				interrupted = true;
			}
		}//end while
		setRunning( false );
		thread = null;
	}

	private void nioLoop() throws IOException{
		setRunning( true );
		while( isRunning() ){
//			Socket s = serverSocket.accept();
//			IConnection newConnection = getConnectionFactory().createConnection( new SocketContext(s) );
//			super.notifyConnectionCreated( newConnection );
//			newConnection.open();
			
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iter = keys.iterator();
			while( iter.hasNext() ){
				SelectionKey key = iter.next();
				iter.remove();
				if( key.isAcceptable() ){
					SocketChannel client = ssc.accept();
					if( client == null ){
						continue;
					}
					client.configureBlocking( false );
					client.register( selector, SelectionKey.OP_READ );
//					handleAccept( client, key );
				}
				if( key.isReadable() ){
					SocketChannel client = (SocketChannel)key.channel();
					ByteBuffer oneByteBuffer = ByteBuffer.allocate(1);
					if( client.read(oneByteBuffer) < 0 ){
						continue;
					}
					oneByteBuffer.flip();
					
				}
			}//end while
		}//end while
		
		
	}

	@Override
	public void start() throws ServerException {
		// TODO Auto-generated method stub
		
		try{
			ssc = ServerSocketChannel.open();
			ssc.configureBlocking( false );
//			serverSocket = new ServerSocket( port );
			serverSocket = ssc.socket();
			serverSocket.bind( new InetSocketAddress(port) );
			selector = Selector.open();
			ssc.register( selector, SelectionKey.OP_ACCEPT );
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
