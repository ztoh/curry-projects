package com.chnye.framework.context.xml;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.chnye.common.utils.CharsetUtil;
import com.chnye.framework.context.impl.Context;

public abstract class AbstractXmlContext extends Context {

	protected String encoding;
	
//	protected Map<String, Object> xpathCache = new HashMap<>();
	
	public AbstractXmlContext(){
		this.name = "AbstractXmlContext_" + System.currentTimeMillis();
		this.encoding = CharsetUtil.getDefaultEncoding();
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
//	public abstract void setRootName( String rootName );
//	public abstract String getRootName();
//	
//	public abstract void setNodeName( String xpath, String nodeName );
//	public abstract String getNodeName( String xpath );
	
	
//	public abstract Object toXPathExp( String xpath );
//	
//	public Object getXPathExp( String xpath ){
//		Object xpathExp = xpathCache.get( xpath );
//		if( xpathExp == null ){
//			xpathExp = toXPathExp( xpath );
//			xpathCache.put( xpath, xpathExp );
//		}
//		return xpathExp;
//	}
	
	
//	public String getXml() throws UnsupportedEncodingException{
//		return getXml( this.encoding, false );
//	}
//	
//	public String getXml( String encoding ) throws UnsupportedEncodingException{
//		return getXml( encoding, false );
//	}
//	public abstract String getXml( String encoding, boolean bFormat ) throws UnsupportedEncodingException;
//	
//	
//	public abstract void setXml( String xpath, String xml );
//	
//	public abstract void setXml( String xml );
//	
	
}
