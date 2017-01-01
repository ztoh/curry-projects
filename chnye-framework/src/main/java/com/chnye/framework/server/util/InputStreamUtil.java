package com.chnye.framework.server.util;

import java.io.InputStream;

public interface InputStreamUtil {

	//读取指定长度的字节流
	byte[] read( InputStream is, int length );
	
}
