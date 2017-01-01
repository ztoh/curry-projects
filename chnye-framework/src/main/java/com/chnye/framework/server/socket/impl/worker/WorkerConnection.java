package com.chnye.framework.server.socket.impl.worker;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.chnye.framework.server.socket.AbstractTCPConnection;

public class WorkerConnection extends AbstractTCPConnection{

	private boolean keepAlive;
	
	private IWorker worker;
	
	
	public WorkerConnection(Socket socket) {
		super(socket);
		// TODO Auto-generated constructor stub
	}

	public WorkerConnection(Socket socket, IWorker worker, boolean keepAlive ) {
		super(socket);
		// TODO Auto-generated constructor stub
		this.worker = worker;
		this.keepAlive = keepAlive;
	}
	
	@Override
	public void run() {
//		InputStream is = null;
//		OutputStream os = null;
		// TODO Auto-generated method stub
		while( keepAlive && worker != null ){
//			worker.read( socket.getInputStream() );
//			worker.write( socket.getOutputStream() );
		}//end while 
		close();
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	
	private byte[] getMessage( InputStream is ){
		int iReceived = 0;
		try{
			//读长度
			byte[] headerBytes = new byte[8];
			int iTmpLen = is.read( headerBytes );
//			Integer reqLen = Integer.parseInt( headerBytes );
			
		}catch ( Exception e ){
			
		}
		return null;
	}
	
}
