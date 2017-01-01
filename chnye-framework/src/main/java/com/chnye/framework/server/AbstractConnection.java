package com.chnye.framework.server;

//http://www.grepcode.com/file/repo1.maven.org/maven2/net.anotheria/ano-net/2.1.0/net/anotheria/net/shared/server/AbstractConnection.java?av=f

import com.chnye.common.collection.ListSupport;
import com.chnye.common.visitor.IVisitor;

public abstract class AbstractConnection implements IConnection{

	private Iterable<IConnectionListener> listeners = ListSupport.newLinkedList(); 
	
	@Override
	public void open() {
		// TODO Auto-generated method stub
		final IConnection connection = this;
		IVisitor<IConnectionListener> visitor = new IVisitor<IConnectionListener>(){
			@Override
			public boolean visit(IConnectionListener listener) {
				listener.connectionOpened( connection );
				return true;
			}
		};
		ListSupport.notifyListeners(listeners, visitor );
	}

	@Override
	public void close() {
		final IConnection connection = this;
		// TODO Auto-generated method stub
		IVisitor<IConnectionListener> visitor = new IVisitor<IConnectionListener>(){
			@Override
			public boolean visit(IConnectionListener listener) {
				listener.connectionClosed( connection );
				return true;
			}
		};
		ListSupport.notifyListeners(listeners, visitor );
	}

	@Override
	public void addConnectionListener(IConnectionListener listener) {
		// TODO Auto-generated method stub
		listeners = ListSupport.addListener(listener, listeners);
	}

	@Override
	public void removeConnectionListener(IConnectionListener listener) {
		// TODO Auto-generated method stub
		listeners = ListSupport.removeListener(listener, listeners);
	}

}
