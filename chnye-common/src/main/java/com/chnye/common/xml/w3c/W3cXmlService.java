package com.chnye.common.xml.w3c;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import com.chnye.common.xml.IXmlService;

public class W3cXmlService implements IXmlService{

	@Override
	public <T> T getObject(InputStream is, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(File file, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(String location, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObjectFromXml(String xml, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(File file, Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(OutputStream os, Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String xml(Object obj, String encoding) {
		// TODO Auto-generated method stub
		return null;
	}

}
