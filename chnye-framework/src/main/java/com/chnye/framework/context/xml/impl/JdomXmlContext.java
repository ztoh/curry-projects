package com.chnye.framework.context.xml.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.chnye.framework.context.xml.AbstractXmlContext;

public class JdomXmlContext extends AbstractXmlContext {

	private Element root;
	
	public JdomXmlContext(){
		this.name = "JdomXmlContext_" + System.currentTimeMillis();
		
		Document doc = new Document().setRootElement( new Element( this.name ) );
		this.root = doc.getRootElement();
		
	}

	public JdomXmlContext( String xml ){
		this.name = "JdomXmlContext_" + System.currentTimeMillis();
		
		SAXBuilder builder = new SAXBuilder( false );
		try {
			Document doc = builder.build( new StringReader(xml) );
			this.root = doc.getRootElement();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
//	@Override
//	public String getRootName() {
//		// TODO Auto-generated method stub
//		return this.root.getName();
//	}
//
//	@Override
//	public void setNodeName(String xpath, String nodeName) {
//		// TODO Auto-generated method stub
//		XPath xpathExp = (XPath)getXPathExp( xpath );
//		List list = null;
//		try {
//			list = xpathExp.selectNodes( this.root );
//		} catch (JDOMException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if( list != null && list.size() > 0 ){
//			for( int i = 0; i < list.size(); i++ ){
//				if( list.get(i) instanceof Element ){
//					Element element = (Element)list.get(i);
//					element.setName( nodeName );
//				} else if( list.get(i) instanceof Attribute ){
//					Attribute attribute = (Attribute)list.get(i);
//					attribute.setName( nodeName );
//				}
//			}
//		}
//	}
//
//	@Override
//	public String getNodeName(String xpath) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object toXPathExp(String xpath) {
//		// TODO Auto-generated method stub
//		try {
//			XPath xpathExp = XPath.newInstance( xpath );
//			return xpath;
//		} catch (JDOMException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//	@Override
//	public String getXml(String encoding, boolean bFormat) throws UnsupportedEncodingException{
//		// TODO Auto-generated method stub
//		return getXml( this.root.getDocument(), encoding, bFormat );
//	}

	public static String getXml( Document doc, String encoding, boolean bFormat ) throws UnsupportedEncodingException{
		"".getBytes( encoding );
		
		Format fmt = Format.getPrettyFormat();
		fmt.setEncoding(encoding);
		fmt.setExpandEmptyElements( true );
		if( bFormat ){
			fmt.setLineSeparator( "\r\n" );
		}
		XMLOutputter xmlOut = new XMLOutputter( fmt );
		return xmlOut.outputString( doc );
	}
//
//	@Override
//	public void setXml(String xpath, String xml) {
//		// TODO Auto-generated method stub
//		xpath = xpath.trim();
//		if( xpath.equals("/") ){
//			setXml( xml );
//		}
//	}
//
//	@Override
//	public void setXml(String xml) {
//		// TODO Auto-generated method stub
//		
//	}

	
	
}
