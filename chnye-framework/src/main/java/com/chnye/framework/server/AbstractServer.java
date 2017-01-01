package com.chnye.framework.server;

//http://www.grepcode.com/file/repo1.maven.org/maven2/net.anotheria/ano-net/2.1.0/net/anotheria/net/shared/server/AbstractServer.java?av=f


import com.chnye.common.collection.ListSupport;
import com.chnye.common.visitor.IVisitor;

public  abstract class AbstractServer implements IServer{

	private Iterable<IServerListener> listeners = ListSupport.newLinkedList();

	protected IConnectionFactory connectionFactory;
	
	public AbstractServer(){
	}
	
	public AbstractServer( IConnectionFactory connectionFactory ){
		this();
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public abstract void start() throws ServerException ;

	@Override
	public abstract void stop() throws ServerException ;

	@Override
	public abstract EServerStatus getServerStatus() ;

	@Override
	public abstract EServerProtocol getServerProtocol() ;

	@Override
	public void addServerListener(IServerListener listener) {
		// TODO Auto-generated method stub
		listeners = ListSupport.addListener(listener, listeners);
	}

	@Override
	public void removeServerListener(IServerListener listener) {
		// TODO Auto-generated method stub
		listeners = ListSupport.removeListener(listener, listeners);
	}

	protected void notifyConnectionCreated( final IConnection connection ){
		IVisitor<IServerListener> visitor = new IVisitor<IServerListener>(){
			@Override
			public boolean visit(IServerListener listener) {
				listener.connectionCreated( connection );
				return true;
			}
		};
		ListSupport.notifyListeners(listeners, visitor );
	}

	protected void notifyConnectionRemoved( final IConnection connection ){
		IVisitor<IServerListener> notification = new IVisitor<IServerListener>(){
			@Override
			public boolean visit(IServerListener listener) {
				listener.connectionRemoved( connection );
				return true;
			}
		};
		ListSupport.notifyListeners(listeners, notification);
	}

	
	public IConnectionFactory getConnectionFactory(){
		return this.connectionFactory;
	}
	
}
