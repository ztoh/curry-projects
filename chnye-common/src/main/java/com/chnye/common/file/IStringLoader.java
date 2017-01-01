package com.chnye.common.file;

import java.io.IOException;

public interface IStringLoader {
	String load( String location, String encoding ) throws IOException;
}
