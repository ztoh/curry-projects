package com.chnye.framework.server.socket.impl.worker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IWorker {
	
	void read( InputStream is ) throws IOException;
	
	void write( OutputStream os ) throws IOException;
	
}
