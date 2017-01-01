package com.chnye.framework.server;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.ijsberg/iglu-common/0.9.3/org/ijsberg/iglu/server/connection/ConnectionFactory.java?av=f

import java.io.IOException;
import java.net.Socket;

public interface IConnectionFactory {
	
	IConnection createConnection( IConnectionCreationContext context ) throws IOException;
}
