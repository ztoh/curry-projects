package com.chnye.framework.xmlparser.dom;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMUtil {
	
	public static Node getNode( String tagName, NodeList nodes ){
		for( int i = 0; i < nodes.getLength(); i++ ){
			Node node = nodes.item( i );
			if( node.getNodeName().equalsIgnoreCase( tagName ) ){
				return node;
			} else {
				NodeList childNodes = node.getChildNodes();
				Node childNode = getNode(tagName, childNodes);
				if( childNode != null ){
					return childNode;
				}
			}
		}
		return null;
	}
	
	public static String getNodeValue( Node node ){
		NodeList childNodes = node.getChildNodes();
		for( int i = 0; i < childNodes.getLength(); i++ ){
			Node childNode = childNodes.item( i );
			if( childNode.getNodeType() == Node.TEXT_NODE ){
				return childNode.getNodeValue();
			}
		}
		return "";
	}
	
	public static String getNodeValue( String tagName, NodeList nodes ){
		Node node = getNode( tagName, nodes );
		if( node != null ){
			return getNodeValue( node );
		}
		return "";
	}
	
	public static String getNodeAttr( String attrName, Node node ){
		NamedNodeMap attrs = node.getAttributes();
		for( int i = 0; i < attrs.getLength(); i++ ){
			Node attr = attrs.item( i );
			if( attr.getNodeName().equals( attrName) ){
				return attr.getNodeValue();
			}
		}
		return "";
	}
	
	public static String getNodeAttr( String tagName, String attrName, NodeList nodes ){
		Node node = getNode( tagName, nodes );
		if( node != null ){
			return getNodeAttr( attrName, node );
		}
		return "";
	}
	
}
