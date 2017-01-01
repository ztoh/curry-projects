package com.chnye.common.xml.xpath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * 警告: XPathAPI是内部专用 API, 可能会在未来发行版中删除 
 */
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//import com.sun.org.apache.xpath.internal.XPathAPI;




public class XPathHelper {

	private static final Map<String, XPathExpression> XPATH_EXPRESSION_CACHE = new HashMap<>();
	private static final XPath XPATH = XPathFactory.newInstance().newXPath();
	
	
	/**
	 * 将一个字符串形式的Xpath编译成XPathExpression对象
	 * @param xpathStr
	 * @return
	 * @throws XPathExpressionException
	 */
	public static XPathExpression getXPathByCache( String xpathStr ) throws XPathExpressionException{
		XPathExpression expr =  XPATH_EXPRESSION_CACHE.get( xpathStr );
		if( expr == null ){
			expr = XPATH.compile( xpathStr );
			XPATH_EXPRESSION_CACHE.put( xpathStr, expr );
		}
		return expr;
	}
	
	/**
	 * 使用XPathExpression查询并返回XML元素的数据,返回多个
	 */
	public static List<Element> getXPathElements( Node node, XPathExpression expr ) throws XPathExpressionException{
		List<Element> eles = new ArrayList<>();
		NodeList nodes = (NodeList) expr.evaluate( node, XPathConstants.NODESET );
		if( nodes == null || nodes.getLength() == 0 ){
			return Collections.emptyList();
		}
		for( int i = 0; i < nodes.getLength(); i++ ){
			Element ele = (Element)nodes.item(i);
			eles.add( ele );
		}
		return eles;
	}
	
	/**
	 * 使用XPathExpression查询并返回XML元素的数据,返回单个
	 */
	public static Node findNode( Node node, XPathExpression expr ) throws XPathExpressionException{
		Node reNode = null;
		reNode = (Node)expr.evaluate( reNode, XPathConstants.NODE );
		return reNode;
	}
	
	
	/** http://www.grepcode.com/file/repo1.maven.org/maven2/org.kuali.rice/rice-core-api/2.5.3/org/kuali/rice/core/api/util/xml/XmlHelper.java#XmlHelper
	 * 
	 */
	public boolean pathExists( Node node, String xpathExpression ) throws XPathExpressionException{
		return ((Boolean)XPATH.evaluate(xpathExpression, node, XPathConstants.BOOLEAN)).booleanValue();
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * 警告: XPathAPI是内部专用 API, 可能会在未来发行版中删除 
	 */

/*	
	@Deprecated
	public static NodeList getNodeList( Node node, String xpathExpression ) throws SAXException, IOException, TransformerException{
		NodeList nodes = null;
		if( node.getNodeType() == Node.DOCUMENT_NODE ){
			nodes = XPathAPI.selectNodeList( node, xpathExpression );
		} else {
			DOMParser parser = new DOMParser();
			parser.parse( new InputSource( new StringReader( W3CXmlHelper.toXmlString(node) ) ) );
			nodes = XPathAPI.selectNodeList( parser.getDocument(), xpathExpression );
		}
		return nodes;
	}
	
	@Deprecated
	public static List<Element> getXPathElements( Node node, String xpathExpression ) throws SAXException, IOException, TransformerException{
		List<Element> eles = new ArrayList<>();
		NodeList nodes = getNodeList( node, xpathExpression );
		if( nodes == null || nodes.getLength() == 0 ){
			return Collections.emptyList();
		}
		for( int i = 0; i < nodes.getLength(); i++ ){
			Element ele = (Element)nodes.item(i);
			eles.add( ele );
		}
		return eles;
	}
	
	@Deprecated
	public static List<String> getXPathTextContents( Node node, String xpathExpression ) throws SAXException, IOException, TransformerException{
		List<String> list = new ArrayList<>();
		NodeList nodes = getNodeList(node, xpathExpression);
		if( nodes == null || nodes.getLength() == 0 ){
			return Collections.emptyList();
		}
		for( int i = 0; i < nodes.getLength(); i++ ){
			Element ele = (Element)nodes.item(i);
			list.add( ele.getTextContent() );
		}
		return list;
	}
	
	@Deprecated
	public static String getXPathSingleTextContent( Node node, String xpathExpression ) throws TransformerException{
		Element ele = (Element)XPathAPI.selectSingleNode( node, xpathExpression );
		if( ele != null ){
			return ele.getTextContent();
		}
		return null;
	}
*/	
	
}
