package com.chnye.yese.framework.context.xml.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.chnye.yese.common.utils.CharsetUtil;
import com.chnye.yese.framework.context.impl.Context;
import com.chnye.yese.framework.context.xml.IXmlContext;

public abstract class  AbstractXmlContext extends Context implements IXmlContext{

	public Map<String,Object> xpathCache = new HashMap();
	protected String encoding;
	
	public AbstractXmlContext(){
		super( AbstractXmlContext.class.getSimpleName() + System.currentTimeMillis() );
		this.encoding = CharsetUtil.getDefaultEncoding();
	}
	
	public String getEncoding(){
		return this.encoding;
	}
	
	public void setEncoding( String encoding ) throws UnsupportedEncodingException{
		"".getBytes( encoding );
		this.encoding = encoding;
	}

	public abstract String getRootName();
	public abstract void setRootName( String rootName );
	
	public abstract void setNodeName( String param1, String param2 );
	
	public Object getValueAt( String expression ){
		expression = expression.trim();
		
	}
	
	public void setValueAt( String expression, Object value ){
		if( value == null ){
			value = "";
		}
		
		expression = expression.trim();
		
		
	}
	
	
	
	
	
}
