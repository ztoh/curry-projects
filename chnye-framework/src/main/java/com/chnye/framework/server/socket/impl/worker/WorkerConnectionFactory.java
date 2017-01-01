package com.chnye.framework.server.socket.impl.worker;

import java.io.IOException;
import java.net.Socket;

import com.chnye.framework.server.IConnection;
import com.chnye.framework.server.IConnectionCreationContext;
import com.chnye.framework.server.IConnectionFactory;
import com.chnye.framework.server.socket.impl.SocketContext;

public class WorkerConnectionFactory implements IConnectionFactory{

	private WorkerFactory workerFactory;
	
	private boolean keepAlive;
	
	public WorkerConnectionFactory( WorkerFactory workerFactory, boolean keepAlive ){
		this.workerFactory = workerFactory;
		this.keepAlive = keepAlive;
	}

	@Override
	public IConnection createConnection(IConnectionCreationContext context)
			throws IOException {
		// TODO Auto-generated method stub
		WorkerConnection connection = new WorkerConnection( 
					((SocketContext)context).getSocket(), 
					workerFactory.createWorker(), 
					keepAlive);
		return connection;
	}
}
