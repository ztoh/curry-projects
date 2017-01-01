package com.chnye.common.xml.xpath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.chnye.common.xml.w3c.W3CXmlHelper;
import com.chnye.common.xml.xpath.XPathHelper;

public class TestXPath {

	private Document document;
	
	
	@Before
	public void init() throws FileNotFoundException, IOException{
		String fileName = ".\\bin\\com\\chnye\\common\\utils\\xml\\xpath\\example01.xml";
//		File file = new File( fileName, "GBK" );
		String xmlContent = IOUtils.toString( new FileInputStream( fileName ) );
		System.out.println( xmlContent );
		document = W3CXmlHelper.from( xmlContent );
		System.out.println( "document:\r\n" + W3CXmlHelper.toXmlString( document ) );
	}
	
//	private void assertXPath() throws TransformerException{
//		System.out.println( "---------assertXPath---------" );
//		String xpathExpr1 = "/root/head/TransCode";
//		String value = XPathHelper.getXPathSingleTextContent( document, xpathExpr1 );
//		System.out.println( "value:" + value );
//		
//		List<Node> nodes = W3CXmlHelper.getChildrensByName( document.getDocumentElement(), "head" );
//		if( nodes != null && nodes.size() > 0 ){
//			Node ele = nodes.get(0);
//			System.out.println( "head子节点: " + W3CXmlHelper.toXmlString( ele ) );
//			
//			/**
//			 * 注意此时的相对XPath，不是head/PUBLIC... 也不是/head/PUBLIC...
//			 */
//			String xpathExpr2 = "PUBLIC/INFO/BUSICOMMENTS";
//			String value2 = XPathHelper.getXPathSingleTextContent( ele, xpathExpr2 );
//			System.out.println( "value2:" + value2 );
//		}//end while
//	}
	
	@Test
	public void testXPath(){
//		try {
//			assertXPath();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
