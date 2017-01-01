package com.chnye.common.xml.w3c;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;


import com.chnye.common.xml.w3c.W3CXmlHelper;



public class TestXmlW3C {

	private Document document;
	
	
	@Before
	public void init() throws FileNotFoundException, IOException{
		String fileName = ".\\bin\\com\\chnye\\common\\utils\\xml\\w3c\\example01.xml";
//		File file = new File( fileName, "GBK" );
		String xmlContent = IOUtils.toString( new FileInputStream( fileName ) );
		System.out.println( xmlContent );
		document = W3CXmlHelper.from( xmlContent );
		System.out.println( "document:\r\n" + W3CXmlHelper.toXmlString( document ) );
	}
	
	private void assertXmlW3C(){
		System.out.println( "---------assertXmlW3C---------");
		//遍历Document
		//1.得到根节点
		Element root = document.getDocumentElement();
//		//2.遍历根节点的属性
//		NamedNodeMap attrs = root.getAttributes();
//		System.out.println( "attrs: " + attrs.getLength() );
//		for( int i = 0; i < attrs.getLength(); i++ ){
//			Node node = attrs.item(i);
//			System.out.println( "attr: name=" + node.getNodeName() + " value=" + node.getNodeValue() + " type=" + node.getNodeType() );
//		}
		
		String attrValue = W3CXmlHelper.getStringAttribute( root, "name" );
		System.out.println( "attr value:" + attrValue );
		
		String nodeValue = W3CXmlHelper.getTextContent(root );
		System.out.println( "根节点 value:" + nodeValue );
		
//		//3.取节点下所有子节点
//		NodeList nodes = root.getChildNodes();
//		for( int j = 0; j < nodes.getLength(); j++ ){
//			Node node = nodes.item(j);
//			System.out.println( "child nodes: name=" + node.getNodeName() + " value=" + node.getNodeValue() + " type=" + node.getNodeType() );
//		}
		
		ArrayList<Node> nodes = W3CXmlHelper.getChildrensByName( root, "head" );
		for( Node node : nodes ){
			System.out.println( "head子节点: " + W3CXmlHelper.toXmlString( node ) );
		}//end while
		
	}
	
	private void assertUpdateXml(){
		System.out.println( "---------assertUpdateXml---------");
		
		Element root = document.getDocumentElement();
		
		W3CXmlHelper.setNode( root, "PUBLIC/TRXCODE", "3222" );
		W3CXmlHelper.setNode( root, "PRIVATE/BANKS/BANK/NAME", "ICBC" );
		W3CXmlHelper.setNode( root, "PRIVATE/BANKS/BANK/AMOUNT", "23000" );
		System.out.println( "root子节点: " + W3CXmlHelper.toXmlString( root ) );
		
	}
	
	private void assertNewDocument(){
		System.out.println( "---------assertNewDocument---------");
		Document doc = W3CXmlHelper.from();
		System.out.println( "doc: " + W3CXmlHelper.toXmlString( doc ) );
		W3CXmlHelper.addNode(doc, null, "PUBLIC" );
		Element root = doc.getDocumentElement();
		System.out.println( "root:" + root );
		System.out.println( "root:" + root.getOwnerDocument() );
	}
	
	@Test
	public  void testXmlW3C(){
//		assertXmlW3C();
		
//		assertUpdateXml();
		
		assertNewDocument();
	}
	
}
