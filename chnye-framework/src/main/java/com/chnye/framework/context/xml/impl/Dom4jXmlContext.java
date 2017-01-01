package com.chnye.framework.context.xml.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.chnye.framework.context.xml.AbstractXmlContext;

public class Dom4jXmlContext extends AbstractXmlContext {

	private Element root;
	
	
	public Dom4jXmlContext(){
		this.name = "Dom4jXmlContext_" + System.currentTimeMillis();
		
		Document doc = DocumentHelper.createDocument();
		doc.setRootElement( DocumentHelper.createElement( this.name ) );
		this.root = doc.getRootElement();
				
	}

	public Dom4jXmlContext( String xml ){
		this.name = "Dom4jXmlContext_" + System.currentTimeMillis();
		
		try {
			Document doc = DocumentHelper.parseText( xml );
			this.root = doc.getRootElement();
			this.encoding = doc.getXMLEncoding();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	@Override
//	public void setRootName(String rootName) {
//		// TODO Auto-generated method stub
//		this.root.setName( rootName );
//	}
//
//
//	@Override
//	public String getRootName() {
//		// TODO Auto-generated method stub
//		return this.root.getName();
//	}


//	@Override
//	public void setNodeName(String xpath, String nodeName) {
//		// TODO Auto-generated method stub
//		XPath xpathExp = (XPath)getXPathExp( xpath );
//		List list =  xpathExp.selectNodes( this.root );
//		if( list != null && list.size() > 0 ){
//			for( int i = 0; i < list.size(); i++ ){
//				if( list.get(i) instanceof Element ){
//					Element element = (Element)list.get(i);
//					element.setName( nodeName );
//				} else if( list.get(i) instanceof Node ){
//					Node node = (Node)list.get(i);
//					node.setName( nodeName );
//				}
//			}
//		}
//	}


//	@Override
//	public String getNodeName(String xpath) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object toXPathExp(String xpath) {
//		// TODO Auto-generated method stub
//		XPath xpathExp = DocumentHelper.createXPath( xpath );
//		return xpathExp;
//	}

//	@Override
//	public String getXml(String encoding, boolean bFormat) throws UnsupportedEncodingException {
//		// TODO Auto-generated method stub
//		return getXml( this.root.getDocument(), encoding, bFormat );
//	}


	public static String getXml( Document doc, String encoding, boolean bFormat ) throws UnsupportedEncodingException{
		"".getBytes( encoding );
		
		OutputFormat fmt = OutputFormat.createPrettyPrint();
		fmt.setEncoding( encoding );
		fmt.setExpandEmptyElements( true );
		if( bFormat ){
			fmt.setLineSeparator( "\r\n" );
		}
		StringWriter sw = new StringWriter();
		XMLWriter xwriter = new XMLWriter( sw, fmt );
		xwriter.setEscapeText( false );   //是否对属性和元素值进行转移 
		
		try {
			xwriter.write( doc );
			xwriter.flush();
			xwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}

//	@Override
//	public void setXml(String xpath, String xml) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setXml(String xml) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
}
