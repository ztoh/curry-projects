package com.chnye.common.xml.dom4j;

/**
 * http://www.grepcode.com/file/repository.pentaho.org/artifactory/pentaho/pentaho/pentaho-platform-core/5.1-SNAPSHOT/org/pentaho/platform/util/xml/dom4j/XmlDom4JHelper.java?av=f
 * http://www.grepcode.com/file/repo1.maven.org/maven2/org.test4j/test4j.spec/2.0.7/org/test4j/spec/util/XmlHelper.java#XmlHelper
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class XmlDom4JHelper {

	public static Document getDocumentFromClasspath( String filePath, String encoding ){
		try{
			InputStream is = XmlDom4JHelper.class.getClassLoader().getResourceAsStream( filePath );
			return getDocumentFromStream(is, encoding);
		}catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Document getDocumentFromString( final String str ){
		try{
//			return DocumentHelper.parseText( str );
			
			SAXReader reader = new SAXReader();
			return reader.read( new StringReader( str) );
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
//	public static Document getDocumentFromString( final String str, final EntityResolver resolver ){
//		return getDocumentFromStream( new ByteArrayInputStream( str.getBytes()), resolver ); 
//	}
	
	public static Document getDocumentFromFile( final File file ){
		SAXReader reader = new SAXReader();
		try{
			Document doc = reader.read( file );
			return doc;
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
//	public static Document getDocumentFromFile( final File file, final EntityResolver resolver  ){
//		SAXReader reader = new SAXReader();
//		try{
//			if( resolver != null ){
//				reader.setEntityResolver( resolver );
//			}
//			return reader.read( file );
//		} catch ( Exception e ){
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public static Document getDocumentFromStream( final InputStream is, String encoding ){
		
		try{
			SAXReader reader = new SAXReader();
			if( !StringUtils.isEmpty( encoding ) ){
				reader.setEncoding( encoding );
			}
			return reader.read( is );
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
//	public static Document getDocumentFromStream( final InputStream is, final EntityResolver resolver ){
//		SAXReader reader = new SAXReader();
//		try{
//			if( resolver != null ){
//				reader.setEntityResolver( resolver );
//			}
//			return reader.read( is );
//		} catch ( Exception e ){
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public static org.dom4j.Document convertToDom4JDocument( final org.w3c.dom.Document doc ){
		try{
			DOMSource source = new DOMSource( doc );
			StreamResult result = new StreamResult( new StringWriter() );
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.transform( source, result );
			String strXML = result.getWriter().toString();
			return DocumentHelper.parseText( strXML );
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getNodeText( final String xpath, final Node rootNode, final String defaultValue ){
		if( rootNode == null ){
			return defaultValue;
		}
		
		Node node = rootNode.selectSingleNode( xpath );
		if( node == null ){
			return defaultValue;
		}
		return node.getText();
	}
	
	public static long getNodeText( final String xpath, final Node rootNode, long defaultValue ){
		String reValue = getNodeText( xpath, rootNode, Long.toString(defaultValue) );
		try{
			return Long.parseLong( reValue );
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return defaultValue;
	}
	
	public static final String documentToString( final Document doc ){
		try{
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			XMLWriter writer = new XMLWriter( bos );
//			writer.write( doc );
//			return bos.toString();
			
			Writer writer = new StringWriter();
			doc.write(writer);
			return writer.toString();
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
	private static String textFormat = "<root>%s</root>";
	public static List<Node> parseNodeFromText( String xml ){
		Document doc = getDocumentFromString( String.format( textFormat, xml) );
		List<Node> nodes = new ArrayList<Node>();
		Iterator iter = doc.getRootElement().nodeIterator();
		while( iter.hasNext() ){
			Node node = (Node)iter.next();
//			if( node instanceof Element ){
//				String name = ((Element)node).attributeValue(qName);
//			}
			nodes.add( node );
		}//end while
		return nodes;
	}
	
	
	public static EntityResolver IgnoreEntityResolver = new EntityResolver(){

		@Override
		public InputSource resolveEntity(String publicId, String systemId)
				throws SAXException, IOException {
			// TODO Auto-generated method stub
			return new InputSource( new ByteArrayInputStream("<?xml version=1.0' encoding='UTF-8'?>".getBytes()) );
		}
		
	};
	
	
	private static String fromat( String xmlStr, OutputFormat formater ){
		SAXReader reader = new SAXReader();
		StringReader strReader = new StringReader( xmlStr );
		Document doc;
		XMLWriter writer = null;
		StringWriter strWriter = new StringWriter();
		try{
			doc = reader.read( strReader );
			writer = new XMLWriter( strWriter, formater );
			writer.write( doc );
			return strWriter.toString();
		} catch ( DocumentException e ){
			e.printStackTrace();
		} catch ( IOException e ){
			e.printStackTrace();
		} finally {
			if( writer != null ){
				try{
					writer.close();
				} catch( IOException e ){}
			}
		}
		return xmlStr;
	}
	
	
	
	
	
}
