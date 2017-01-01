package com.chnye.common.xml.w3c;

/**
 * http://www.grepcode.com/file/repository.pentaho.org/artifactory/pentaho/pentaho/pentaho-platform-core/5.1-SNAPSHOT/org/pentaho/platform/util/xml/w3c/XmlW3CHelper.java?av=f
 */

/**
 * Node 与 Element 的关系和区别
 *    Node是Element的父类
 *    Element多了接口方法
 *       getAttribute(String) / setAttribute(String,String) / removeAttribute(String)
 *       getTagName()
 *       能获取属性对应的对象Attr
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import com.google.common.base.Throwables;

/*
 * 警告: XPathAPI是内部专用 API, 可能会在未来发行版中删除 
 */
//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;






public class W3CXmlHelper {

	/*--------------------------构造Document----------------------*/
	
	public static final Document from(){
		return from( null );
	}
	
	/**
	 * 将字符串形式的xml内容，转换为Document对象
	 * @param xmlStr
	 * @return
	 */
	public static final Document from( final String xmlStr ){
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
//		if( xmlStr == null ){
//			throw new IllegalArgumentException("str can not be null");
//		}
		try{
			factory = DocumentBuilderFactory.newInstance();
			
			factory.setValidating( false );
			factory.setIgnoringElementContentWhitespace( false );
			
			builder = factory.newDocumentBuilder();
			if( xmlStr != null ){
				doc = builder.parse( new InputSource( new StringReader(xmlStr) ) );
			} else {
				doc = builder.newDocument();
			}
			return doc;
		} catch ( Exception e ){
			e.printStackTrace();
		} finally{
			try{
				builder.setErrorHandler(null);
				builder.setEntityResolver(null);
			}catch( Throwable e){}
		}
		return null;
	}
	
	
	public static Element fromRoot( String rootName ){
		Document document = from();
		Element root = document.createElement( rootName );
		document.appendChild( root );
		return root;
	}
	
	/**
	 * 将节点输出为字符串
	 * @param node
	 * @return
	 */
	public static final String toXmlString( final Node node ){
		StringBuffer sb = null;
		StringWriter sw = new StringWriter();
		Transformer transformer = null;
		try{
//			transformer = createTransformer( null );
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			DOMSource docSrc = new DOMSource( node);
			transformer.transform( docSrc, new StreamResult( sw ) );
			sb = sw.getBuffer();
			return sb.toString();
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}

	
	/*
	 * 警告: XPathAPI是内部专用 API, 可能会在未来发行版中删除 
	 */
	
	/**
	 *  将节点输出为格式化后的字符串
	 * @param node
	 * @return
	 */
//	public static final String toFormatString( final Node node ) {
//		Writer out = null;
//		try {
//			Document doc = node.getNodeType() == Node.DOCUMENT_NODE ? (Document)node : node.getOwnerDocument() ;
//			OutputFormat format = new OutputFormat( doc );
//			format.setIndenting(true);//设置是否缩进，默认为true  
//			format.setIndent(4);//设置缩进字符数  
//			format.setPreserveSpace(false);//设置是否保持原来的格式,默认为 false  
//			format.setLineWidth(500);//设置行宽度  
//        
//			out = new StringWriter();
//			XMLSerializer serializer = new XMLSerializer(out, format);
//			serializer.serialize( node );
//			return out.toString();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Throwables.propagate(e);
//		} finally {
//			if( out != null ){
//				try{
//					out.close();
//				} catch ( Exception e ){}
//			}
//		}
//        return null;
//	}
	
	
	
//	private static final Transformer createTransformer( String encoding ){
//		Transformer transformer = null;
//		try{
//			TransformerFactory factory = TransformerFactory.newInstance();
//			transformer = factory.newTransformer();
//			
//			if( StringUtils.isNotBlank(encoding) ){
//				transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
//			}
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
//			
//		} catch (TransformerConfigurationException e) {
//			throw Throwables.propagate(e);
//		} catch (TransformerFactoryConfigurationError e) {
//			throw Throwables.propagate(e);
//		}
//		return transformer;
//	}
	
	
	

	
	
	/*--------------------------访问Document----------------------*/
	
	
	/*----------访问子节点----------*/
	
	/**
	 * 取节点的某个子节点
	 * @param element
	 * @param tagName
	 * @return
	 */
	
	public static ArrayList<Node> getChildrensByName( Node node, String tagName ){
		if( node == null ){
			return null;
		}
		NodeList children = node.getChildNodes();
		ArrayList<Node> goodChildren = new ArrayList<>();
		for( int i = 0; i < children.getLength(); i++ ){
			Node currChild = children.item( i );
			if( currChild.getNodeType() == Node.ELEMENT_NODE && ((Element)currChild).getTagName().equals( tagName) ){
				goodChildren.add( (Element)currChild );
			}
		}
		return goodChildren;
	}
	
	public static Element getFirstChildByName( Node node, String tagName ){
		NodeList children = node.getChildNodes();
		for( int i = 0; i < children.getLength(); i++ ){
			Node child = children.item(i);
			if( child != null && child.getNodeType() == Node.ELEMENT_NODE 
				&& child.getLocalName() != null && child.getLocalName().equals( tagName) ){
				return (Element)child;
			}
		}
		return null;
	}
	
	
	
	/**
	 * 返回匹配成功的节点
	 * @param xpath    xpath路径
	 */
	public static Node getChildByName( Node node, String xpath, boolean bAllMatcher ){
		return getChildByName( node, split2List(xpath), bAllMatcher );
	}
	

	/**
	 *  返回匹配成功的节点
	 * @param node
	 * @param names   xml的路径数组
	 * @param bAllMatcher  是否严格匹配, 否,返回最靠近叶子节点的匹配节点
	 * @return
	 */
	public static Node getChildByName( Node node, List<String> names, boolean bAllMatcher ){
		if( node == null ){
			return null;	
		}
		Node child = null;
		NodeList children = node.getChildNodes();
		String name = null;
		boolean isAllMatchered = false;
		
		outer: for( int i = 0; i < names.size(); i++ ){
			name = names.get(i);
			if( i == 0 && name.equals( node.getNodeName()) ){
				continue outer;
			}
			for( int loop = 0, length =  children.getLength(); loop < length; loop++  ){
				Node childNode = children.item(loop);
				if( !( node instanceof Element ) ){
					continue;
				}
				child = childNode;
				System.out.println( name +  " nodeTYpe:" + childNode.getNodeType() + " " + ((Element)childNode).getTagName() );
				if( childNode.getNodeType() == Node.ELEMENT_NODE && name.equals( ((Element)childNode).getTagName() ) ){
					children = childNode.getChildNodes();
					if( i == names.size() - 1 ){
						isAllMatchered = true;
					}
					continue outer;
				}
			}//end for
		}//end for
		if( bAllMatcher == true && isAllMatchered == false ){
			return null;
		} else {
			return child;
		}
	}
	
	/** http://www.grepcode.com/file/repo1.maven.org/maven2/org.apache.felix/maven-bundle-plugin/2.5.4/org/apache/felix/obrplugin/XmlHelper.java#XmlHelper
	 * 取某节点的值
	 * @param node
	 * @return
	 */
	public static String getTextContent( Node node ){
//		String textContent = null;
//		NodeList nodes = node.getChildNodes();
//		for( int i = 0; i < nodes.getLength(); i++ ){
//			Node tmpNode = nodes.item( i );
//			if( node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.CDATA_SECTION_NODE ){
//				textContent = node.getNodeValue();
//			}
//		}
//		return textContent;
//		
		switch( node.getNodeType() ){
		case Node.ELEMENT_NODE:
		case Node.ATTRIBUTE_NODE:
		case Node.ENTITY_NODE:
		case Node.ENTITY_REFERENCE_NODE:
		case Node.DOCUMENT_FRAGMENT_NODE:
			return mergeTextContent( node.getChildNodes() );
		case Node.TEXT_NODE:
		case Node.CDATA_SECTION_NODE:
		case Node.COMMENT_NODE:
		case Node.PROCESSING_INSTRUCTION_NODE:
			return node.getNodeValue();
		case Node.DOCUMENT_NODE:
		case Node.DOCUMENT_TYPE_NODE:
		case Node.NOTATION_NODE:
		default:
				return null;
		}
	}
	
	private static String mergeTextContent( NodeList nodes ){
		StringBuffer sb = new StringBuffer();
		for( int i = 0; i < nodes.getLength(); i++ ){
			Node node = nodes.item(i);
			final String text;
			switch( node.getNodeType() ){
			case Node.COMMENT_NODE:
			case Node.PROCESSING_INSTRUCTION_NODE:
				text = null;
				break;
			default:
				text = getTextContent( node );
				break;
			}
			if( text != null ){
				sb.append( text );
			}
		}
		return sb.toString();
	}
	
	/** http://www.grepcode.com/file/repo1.maven.org/maven2/net.sf.jfcunit/jfcunit/2.08/junit/extensions/xml/XMLUtil.java#XMLUtil
	 * 
	 * @param node
	 * @return
	 */
	public static String getPath( Node node ){
		StringBuffer sb = new StringBuffer();
		
		if( node.getParentNode() != null && node.getParentNode().getNodeType() == Node.ELEMENT_NODE ){
			sb.append(  getPath( node.getParentNode() ) );
			sb.append( "." );
		}
		sb.append(  node.getNodeName() );
		return sb.toString();

		
	}
	
	/*--------------------------修改XML树----------------------*/
	
	public static void setNode( Node root, String xpath, Object value ){
		setNode(root, split2List( xpath ), value );
	}
	
	private static void setNode(Node root, final List<String> paths, Object value ){
		
		Node curNode = root;
		Node tmpNode = null;
		ArrayList<Node> childNodes = null;
		String path = null;
		String strValue = null;
		
		outer: for( int i = 0; i < paths.size(); i++ ){
			path = paths.get(i);
			if( i == 0 && path.equals( root.getNodeName() ) ){
				continue;
			}
			if( i == paths.size() - 1 ){
				strValue = value.toString();
			}
			childNodes = getChildrensByName( curNode, getPathKey(path) );
			if( childNodes != null && childNodes.size() > 0 ){
				if( isPathList(path) ){
					int curSize = childNodes.size(); 
					if( curSize >= getIndex(path) ){	//xpath中数组下标是从1开始的
						curNode = childNodes.get( getIndex(path)-1 );
					} else {
						//创建节点
						for( int l = curSize+1; l <= getIndex(path); l++ ){
							tmpNode = addNode( curNode, getPathKey(path), strValue );
						}
						curNode = tmpNode;
					}
				} else {
					curNode = childNodes.get( 0 );
				}
				continue outer;
			} else {
				
				//开始创建节点
				if( isPathList(path) ){
					for( int j = 1; j <= getIndex(path); j++ ){
						tmpNode = addNode( curNode, getPathKey(path), strValue );
					}
					curNode = tmpNode;
				} else {
					curNode = addNode( curNode, path, strValue );
				}
				continue outer;
			}
		}
	}
	
	private static List<String> split2List( String path ){
		return Arrays.asList( StringUtils.split( path, '/') );
	}
	
	private static String getPathKey( String path ){
		if( isPathList( path ) ){
			return path.substring( 0, path.indexOf("[") );
		}
		return path;
	}
	/**
	 * 是否为数组型路径
	 */
	private static boolean isPathList( String path ){
		return path.contains("[") && path.contains("]" );
	}
	
	private static int getIndex( String each ){
//		String index = each.replace("[", "").replace("]", "");
		String index = each.substring( each.indexOf("[") + 1, each.indexOf("]") );
		if( !StringUtils.isNumeric( index ) ){
			throw new IllegalArgumentException(String.format("索引必须为数字. %s", each ) );
		}
		return Integer.valueOf( index );
	}
	
	
	
	/**
	 * 移除某个节点下所有的子节点
	 * @param node
	 */
	public static void removeDirectChildren( Node node ){
		while( node.hasChildNodes() ){
			node.removeChild( node.getFirstChild() );
		}
		
//		NodeList childNodes = node.getChildNodes();
//		while( childNodes.getLength() > 0 ){
//			node.removeChild( childNodes.item(0) );
//		}
	}
	
	/**
	 * 设置某个节点的Text值
	 * @param element
	 * @param textContent
	 */
	public static void appendTextContent( Element element, String textContent ){
		if( textContent == null ){
			return;
		}
		
		removeDirectChildren( element );
		
		Document doc = element.getOwnerDocument();
		Text textNode = doc.createTextNode( textContent );
		element.appendChild( textNode );
	}
	
	public static Element stringToElement( String tagName, String value ){
		Document doc = from();
		Element element = doc.createElement(tagName);
		Text textNode = doc.createTextNode( value );
		element.appendChild( textNode );
		return element;
	}
	
	
//	public static Node addNode( final Document doc, final Node parent, final String tagName, final String value ){
//		Element element = doc.createElement(tagName);
//		if( value != null ){
//			Text textNode = doc.createTextNode( value );
//			element.appendChild( textNode );
//		}
//		if( parent != null ){
//			parent.appendChild( element );
//		} else {
//			doc.appendChild( element );
//		}
//		return element;
//	}
	
	public static Node addNode( final Node parent, final String tagName, final String value ){
		Document doc = null;
		if( parent.getNodeType() == Node.DOCUMENT_NODE ){
			doc = (Document)parent;
		} else {
			doc = parent.getOwnerDocument();
		}
		if( doc != null ){
			Element element = doc.createElement(tagName);
			if( value != null ){
				Text textNode = doc.createTextNode( value );
				element.appendChild( textNode );
			}
			parent.appendChild( element );
			return element;
		}
		return null;
	}
	
	/** http://www.grepcode.com/file/repo1.maven.org/maven2/org.objectweb.petals/petals-jbi-descriptor/1.2.0/org/objectweb/petals/jbi/descriptor/util/XMLUtil.java#XMLUtil
	 * 
	 * @param parent
	 * @param tagName
	 * @param value
	 * @param attrValue
	 * @return
	 */
	public static Node addNode( final Node parent, final String tagName, final String value, final String... attrValue ){
		Node node = addNode( parent, tagName, value );
		if( node != null && attrValue.length % 2 == 0 ){
			
			Document doc = null;
			if( parent.getNodeType() == Node.DOCUMENT_NODE ){
				doc = (Document)parent;
			} else {
				doc = parent.getOwnerDocument();
			}
			
			for( int i = 0; i < attrValue.length; i = i + 2 ){
				node.getAttributes().setNamedItem( createAttribute(doc, attrValue[i], attrValue[i+1] ) );
			}
		}
		return node;
	}
	
	public static void addXml( final Node parent, String xmlStr ){
		Document otherDoc = from( xmlStr );
		Node importedNode = parent.getOwnerDocument().importNode( otherDoc.getDocumentElement(), true );
		parent.appendChild( importedNode );
	}
	
	/**  http://www.grepcode.com/file/repo1.maven.org/maven2/org.apache.empire-db/empire-db/2.4.3/org/apache/empire/xml/XMLUtil.java#XMLUtil
	 * 
	 * @param node
	 * @param newName
	 * @return
	 */
	public static boolean setNodeName( Node node, String newName ){
		if( node == null ){
			return false;
		}
		Document document = node.getOwnerDocument();
		Element newElement = document.createElement( newName );
		//拷贝属性
		NamedNodeMap attrs = node.getAttributes();
		for( int i = 0; i < attrs.getLength(); i++ ){
			Attr newAttr = (Attr)document.importNode( attrs.item(i), true );
			newElement.getAttributes().setNamedItem( newAttr );
		}
		//拷贝子节点
		for( Node tmpNode = node.getFirstChild(); tmpNode != null; tmpNode = tmpNode.getNextSibling() ){
			newElement.appendChild( tmpNode.cloneNode(true) );
		}
		Node parent = node.getParentNode();
		parent.replaceChild( newElement, node );
		return true;
	}
	
	/*--------------------------编码解码----------------------*/
	
	public static String encode( String xmlStr ){
		return StringEscapeUtils.escapeXml10( xmlStr );
	}
	
	public static String decode( String xmlStr ){
		return StringEscapeUtils.unescapeXml( xmlStr );
	}
	
	/*--------------------------namespace命名空间----------------------*/
	public static String removeXmlns( String s ){
		if( s == null ){
			return null;
		}
		s = s.replaceAll("(?s)xmlns=['\"].*?['\"]", "");
		s = s.replaceAll("(?s)\\w*:schemaLocation=['\"].*?['\"]", "");
		return s;
	}
	
	
	/*--------------------------属性----------------------*/
	
	/**
	 * 取得节点上所有的属性
	 * @param element
	 * @return
	 */
	public static Map<String, String> getAttributes( Element element ){
		Map<String, String> result = Collections.emptyMap(); 
		if( !element.hasAttributes() ){
			return result;
		}
		Node node;
		
		NamedNodeMap attrs = element.getAttributes();
		for( int i = 0; i < attrs.getLength(); i++ ){
			result.put( attrs.item(i).getNodeName(), attrs.item(i).getNodeValue() );
		}
		return result;
	}
	
	/**
	 * 在节点上设置多个属性
	 * @param element
	 * @param attributes
	 */
	public static void setAttributes( Element element, Map<String, String> attributes ){
		if( attributes == null ){
			return;
		}
		for( Map.Entry<String, String> entry : attributes.entrySet() ){
			String value = entry.getValue();
			if( value == null ){
				continue;
			}
			element.setAttribute( entry.getKey(), value );
		}
	}
	
	/**
	 * 在节点上设置单个属性 
	 * @param element
	 * @param name
	 * @param value
	 */
	public static void addAttribute( Element element, String name, String value ){
		if( name == null || value == null ){
			return ;
		}
		element.setAttribute(name, value);
	}
	
	public static Node createAttribute( Document doc, String name, String value ){
		Attr attr = null;
		if( doc != null ){
			attr = doc.createAttribute( name );
			attr.setValue(value);
		}
		return attr;
	}
	/**
	 * 取节点的ID属性
	 * @param element
	 * @return
	 */
//	public static Attr getIdAttribute( Element element ){
//		if( !element.hasAttributes() ){
//			return null;
//		}
//		NamedNodeMap attributes = element.getAttributes();
//		Attr attribute;
//		for( int i = 0; i < attributes.getLength(); i++ ){
//			attribute = (Attr)attributes.item( i );
//			if( attribute.isId() ){
//				return attribute;
//			}
//		}
//		return null;
//	}
	
	/**
	 * 取节点上某个属性的值
	 * @param element
	 * @param attrName
	 * @return
	 */
	public static String getStringAttribute( Element element, String attrName ){
		if( !element.hasAttributes() ){
			return null;
		}
		NamedNodeMap attributes = element.getAttributes();
		Attr attribute;
		for( int i = 0; i < attributes.getLength(); i++ ){
			attribute = (Attr)attributes.item( i );
			if( attribute.getName().equals( attrName ) ){
				return attribute.getValue();
			}
		}
		return null;
	}
	
//	public static Boolean getAttributeValueAsBoolean( Attr attribute ){
//		if( attribute == null ){
//			return null;
//		}
//		String value = attribute.getValue();
//		if( value.equals("0") || value.equals("false") ){
//			return Boolean.FALSE;
//		} else if( value.equals("1") || value.equals("true") ){
//			return Boolean.TRUE;
//		} else {
//			return null;
//		}
//	}
	
	public static Boolean getBooleanAttribute( Element element, String attrName ){
		String result = getStringAttribute( element, attrName );
		if( null == result ){
			return null;
		}
		try{
			return Boolean.valueOf( result );
		} catch( NumberFormatException e ){
			return null;
		}
	}
	
	public static Boolean getBooleanAttribute( Element element, String attrName, boolean defaultValue ){
		Boolean reBool = getBooleanAttribute( element, attrName );
		if( null == reBool ){
			return defaultValue;
		}
		return reBool;
	}
	
	public static Integer getIntAttribute( Element element, String attrName ){
		String result = getStringAttribute( element, attrName );
		if( null == result ){
			return null;
		}
		try{
			return Integer.valueOf( result );
		} catch( NumberFormatException e ){
			return null;
		}
	}
	
	public static Integer getIntAttribute( Element element, String attrName, int defaultValue ){
		Integer reInt = getIntAttribute( element, attrName );
		if( null == reInt ){
			return defaultValue;
		}
		return reInt;
	}
	
	public static Long getLongAttribute( Element element, String attrName ){
		String result = getStringAttribute( element, attrName );
		if( null == result ){
			return null;
		}
		try{
			return Long.valueOf( result );
		} catch( NumberFormatException e ){
			return null;
		}
	}
	
	public static Long getLongAttribute( Element element, String attrName, long defaultValue ){
		Long reLong = getLongAttribute( element, attrName );
		if( null == reLong ){
			return defaultValue;
		}
		return reLong;
	}
	
	
	
	
}
