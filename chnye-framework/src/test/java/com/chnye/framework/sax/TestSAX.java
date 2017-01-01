package com.chnye.framework.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.chnye.framework.config.xml.IXmlDefinition;
import com.chnye.framework.config.xml.impl.DefaultXmlDefinition;

public class TestSAX {

	private SAXParser parser;
	
	
	@Before
	public void init() throws ParserConfigurationException, SAXException{
		parser = SAXParserFactory.newInstance().newSAXParser();
	}

	private void assertSAX() throws IOException, SAXException{
		System.out.println( "----------SAX----------111");
		String fileName = "bin\\com\\chnye\\framework\\sax\\example01.xml";
		File file = new File( fileName );
		MyHandler myHandler = new MyHandler();
		parser.parse( file, myHandler );
		System.out.println( "----------SAX----------222");
			Map<String, IXmlDefinition> allDefinitions = myHandler.getAllDefinitions();
			System.out.println( "all size:" + allDefinitions.size() );
			for( Map.Entry<String, IXmlDefinition> entry : allDefinitions.entrySet() ){
				System.out.println( entry.getKey() );
				IXmlDefinition def = entry.getValue();
				if( def != null ){
					Map<String, Object> props = def.getProperties();
					for( Map.Entry<String, Object> prop : props.entrySet() ){
						System.out.println( "\t key[" + prop.getKey() + "] value[" + prop.getValue() + "]");
					}
				}
			}
		
	}
	
	
	
	@Test
	public void testSAX(){
		try {
			assertSAX();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class MyHandler extends DefaultHandler{
		
		  private String currentTag = null;       //正在解析的元素的名字
		  private String currentValue = null;     //正在解析的元素的值


		  private StackContext stackContext = new StackContext();
		  
		  private DefaultXmlDefinition rootDefinition;
		  private DefaultXmlDefinition nodeDefinition;
		  
		  private Map<String, IXmlDefinition> allDefinitions = new HashMap<String,IXmlDefinition>();
		  
		  //开始解析文档
		  public void startDocument(){
			  System.out.println("-----startDocument-----");
		  }

		  //结束解析文档
		  public void endDocument(){
			  System.out.println("-----endDocument-----");
		  }

		  //开始解析节点
		  public void startElement( String uri, String localName, String qName, Attributes attributes ){

			  stackContext.getPath().add( qName );
//			  System.out.println( "path:" + stackContext.getPath().toString() );
			  nodeDefinition = new DefaultXmlDefinition();
//			  System.out.println( "current:" + nodeDefinition );
			  if( rootDefinition == null ){
				  rootDefinition = nodeDefinition;
			  }
			  System.out.println( "xxxxxxxxxxxxxxx:" + nodeDefinition.getProperties() );
			  stackContext.getStack().push( nodeDefinition );
			  nodeDefinition.setProperty( IXmlDefinition.KEY_QNAME, qName );
			  nodeDefinition.setProperty( IXmlDefinition.KEY_PATH, stackContext.getPath().toString() );
			
		    //判断正在解析的元素是否有属性值
		    if( attributes != null ){
		      for( int i = 0; i < attributes.getLength(); i++ ){
		    	  nodeDefinition.setProperty( attributes.getQName(i), attributes.getValue(i) );   
		      }
		    }
		    currentTag = qName;
		  }

		  //保存节点内容
		  public void character( char[] ch, int start, int length ){
		    if( currentTag != null ){
		      currentValue = new String( ch, start, length );
		      if( currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n") ){
		    	  nodeDefinition.setProperty( IXmlDefinition.KEY_NODE_VALUE, currentValue );  
		      }
		      currentTag = null;  
		      currentValue = null;
		    }
		  }

		  //结束解析节点
		  public void endElement( String uri, String localName, String qName ){
			  System.out.println( "xxx:" + stackContext.getPath().toString() );
			  allDefinitions.put(stackContext.getPath().toString(), nodeDefinition );
		    	stackContext.getStack().pop();
		    	if( !stackContext.getStack().isEmpty() ){
		    		IXmlDefinition parentDefinition = (IXmlDefinition)stackContext.getStack().peek();
		    		if( parentDefinition != null ){
		    			parentDefinition.addSubDefinition( stackContext.getPath().getLeafName(), nodeDefinition );
		    		}
		    	}
		    	stackContext.getPath().remove();
		    	nodeDefinition = null;

		  }
		
		  		  
		  IXmlDefinition getRootDefinition(){
			  return rootDefinition;
		  }
		  
		  Map<String,IXmlDefinition> getAllDefinitions(){
			  return allDefinitions;
		  }
		  
	}
	
	
}
