package com.chnye.framework.server;

public interface IServerCreator<T extends IServer> {
	public T create( String serviceName, int port );
}
