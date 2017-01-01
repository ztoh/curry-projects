package com.chnye.framework.context.xml.impl;


import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.chnye.common.xml.w3c.W3CXmlHelper;
import com.chnye.framework.context.xml.AbstractXmlContext;
import com.chnye.framework.context.xml.IXmlContext;

public class W3cXmlContext extends AbstractXmlContext implements IXmlContext{

	private static final String DEFAULT_ROOT_NAME = "root";
	
	private Element root;
	
	public W3cXmlContext(){
		this(null, null, DEFAULT_ROOT_NAME );
	}
	
	public W3cXmlContext( String rootName ){
		this( null, null, rootName );		
	}

	public W3cXmlContext( String name, String xml, String rootName ){
		if( name == null ){
			this.name = "W3cXmlContext_" + System.currentTimeMillis();
		} else {
			this.name = name;
		}
		
		Document doc = W3CXmlHelper.from( xml );
		this.root = doc.getDocumentElement();	
		if( this.root == null && StringUtils.isNoneBlank(rootName) ){
			W3CXmlHelper.addNode( doc, rootName, null );
			this.root = doc.getDocumentElement();	
		}
	}

	@Override
	public void setValueAt(String xpath, Object value) {
		// TODO Auto-generated method stub
		W3CXmlHelper.setNode(this.root, xpath, value );
	}

	@Override
	public Object getValueAt(String xpath ) {
		// TODO Auto-generated method stub
		Object result = null;
		Node node = W3CXmlHelper.getChildByName( this.root, xpath, true );
		if( node != null ){
			result = W3CXmlHelper.getTextContent(node);
		} 
		return result;
	}
	
	@Override
	public String toString(){
//		return W3CXmlHelper.toFormatString( this.root );
		return W3CXmlHelper.toXmlString( this.root );
	}
	
}
