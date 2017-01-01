package com.chnye.framework.xmlparser.dom;

import java.io.IOException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


public class TestDom {

	private DOMParser parser = null;
	private String fileName = "bin\\com\\chnye\\framework\\sax\\example01.xml";
	private Document doc;
	
	@Before
	public void init() throws SAXException, IOException{
		parser = new DOMParser();
		parser.parse( fileName );
		doc = parser.getDocument();
	}
	
	private void assertDom() {
	
		//根节点
		NodeList root = doc.getChildNodes();
		System.out.println( "root:" + root );
		System.out.println( "root:" + root.getLength() );

//		//取任意某个节点
		Node node = DOMUtil.getNode( "startNode", root );
		System.out.println( "node:" + node );
		//取节点的属性 
		String strAttr = DOMUtil.getNodeAttr( "target", node );
		System.out.println( "attr:" + strAttr );
		//取节点的值
		String strValue = DOMUtil.getNodeValue( node );
		System.out.println( "value:" + strValue );
		
	}
	
	private void assertXPath() throws XPathExpressionException{
		//读取节点的属性
//		String expression = "/operation/@type";
//		String expression = "/operation/info/@author";
//		String expression = "/operation/processDefine/startNode/@displayName";
		String expression = "/operation/processDefine/opstep[@id='procedureAccessOpStep0']/@displayName";

		XPath xPath = XPathFactory.newInstance().newXPath();
		String strAttr = xPath.compile(expression).evaluate( doc );
		System.out.println( "strAttr:" + strAttr );
		
		//读取节点
		Node node = (Node)xPath.compile(expression).evaluate(doc, XPathConstants.NODE );
		System.out.println( "node:" + node );
		
		//读取节点集合
		expression = "/operation/processDefine/opstep";
		NodeList nodes = (NodeList)xPath.compile(expression).evaluate(doc, XPathConstants.NODESET );
		if( nodes != null ){
			System.out.println( "nodes:" + nodes );
			for( int i = 0; i < nodes.getLength(); i++ ){
				Node childnode = nodes.item(i);
				//Node.ELEMENT_NODE / Node.ATTRIBUTE_NODE / ...
				System.out.println( " node type:" + childnode.getNodeType() );
				NamedNodeMap attrs = childnode.getAttributes();
				for( int j = 0; j < attrs.getLength(); j++ ){
					Node attr = attrs.item( j );
					System.out.println( "\t" + attr.getNodeName() + "=" + attr.getNodeValue() );
				}
			}
		}
		
		
	}
	
	
	@Test
	public void testDom(){
		assertDom();
	}
	
	@Test
	public void testXPath(){
		try {
			assertXPath();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
