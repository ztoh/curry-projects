package com.chnye.framework.server.socket.impl.worker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import com.chnye.framework.server.bussiness.ITransMsg;

public class DefaultWorker implements IWorker{

	private int headerLen = 8;
	private byte[] headerBytes = new byte[ headerLen ];
	private int bodyerLen = 0;
	private byte[] bodyerBytes;
	
	private ITransMsg transMsg;
	
	
	
	@Override
	public void read(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		//读消息头
		int iReceivedLen = is.read( headerBytes );
		System.out.println( "header: received len:" + iReceivedLen );
		bodyerLen = Integer.parseInt( new String(headerBytes).trim() );
		iReceivedLen = 0;
		
		//读消息体
		bodyerBytes = new byte[ bodyerLen ];
		int bytesRecvd = 0;
		int n = 0;
		while( n < bodyerLen ){
			if( (bytesRecvd = is.read( bodyerBytes, n, bodyerLen-n) ) == -1 ){
				throw new SocketException("期待size[" + bodyerLen + "],实际收到size[" + iReceivedLen + "]!");
			}
			n += bytesRecvd;
			iReceivedLen = n;
		}//end while
	}

	@Override
	public void write(OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		byte[] returnBytes = transMsg.trans( bodyerBytes );
		os.write( returnBytes );
	}

	
}
