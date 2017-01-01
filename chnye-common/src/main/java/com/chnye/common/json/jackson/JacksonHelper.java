package com.chnye.common.json.jackson;

/*
 * http://grepcode.com/file/repo1.maven.org/maven2/org.usergrid/usergrid-core/0.0.27.1/org/usergrid/utils/JsonUtils.java?av=f
http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.fge/jackson-coreutils/1.8/com/github/fge/jackson/JacksonUtils.java#JacksonUtils

 */

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;



import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


import com.fasterxml.jackson.core.JsonFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.collect.Maps;



public class JacksonHelper {
	private static final JsonNodeFactory FACTORY = JsonNodeFactory.instance;
	private static final ObjectReader READER;
	private static final ObjectWriter WRITER;
	
	static{
		final ObjectMapper mapper = newMapper();
		READER = mapper.reader();
		WRITER = mapper.writer();
	}
	
	private static ObjectMapper newMapper(){
		return new ObjectMapper().setNodeFactory(FACTORY)
				.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
				.enable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN)
				.enable(SerializationFeature.INDENT_OUTPUT);
				
	}
	
	public static ObjectReader getReader(){
		return READER;
	}
	public static ObjectWriter getWriter(){
		return WRITER;
	}
	public static JsonNodeFactory getNodeFactory(){
		return FACTORY;
	}
	
	public static Map<String,JsonNode> asMap( final JsonNode node ){
		if( !node.isObject() ){
			return Collections.emptyMap();
		}
		final Iterator<Map.Entry<String,JsonNode>> iter = node.fields();
		final Map<String,JsonNode> reMap = Maps.newHashMap();
		Map.Entry<String, JsonNode> entry ;
		while( iter.hasNext() ){
			entry = iter.next();
			reMap.put( entry.getKey(), entry.getValue() );
		}
		return reMap;
	}
	
	public static String nodeToString( final JsonNode node ){
		final StringWriter writer = new StringWriter();
		try{
			WRITER.writeValue( writer, node );
			writer.flush();
		} catch ( JsonGenerationException e ){
			
		} catch ( JsonMappingException e ){
			
		} catch ( IOException e ){
			
		}
		return writer.toString();
	}
	
//	public static String mapToJsonString( Object obj ){
//		try{
//			return mapper.writeValueAsString( obj );
//		} catch ( JsonGenerationException e ){
//			
//		} catch ( JsonMappingException e ){
//			
//		} catch ( IOException e ){
//			
//		}
//		return "{}";
//	}
//	
//	public static String mapToFormattedJsonString( Object obj ){
//		try{
//			return indentMapper.writeValueAsString( obj );
//		} catch ( JsonGenerationException e ){
//			
//		} catch ( JsonMappingException e ){
//			
//		} catch ( IOException e ){
//			
//		}
//		return "{}";
//	}
//	
//	public static Object parse( String json ){
//		try{
//			return mapper.readValue( json, Object.class );
//		} catch ( JsonParseException e ){
//			
//		} catch ( JsonMappingException e ){
//			
//		} catch ( IOException e ){
//			
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	 * @param obj
//	 * @return
//	 */
//	public static JsonNode toJsonNode( Object obj ){
//		if( obj == null ){
//			return null;
//		}
//		JsonNode node = mapper.convertValue( obj, JsonNode.class );
//		return node;
//	}
//	
//	public static Map<String,Object> toJsonMap( Object obj ){
//		if( obj == null ){
//			return null;
//		}
//		Map<String,Object> map = mapper.convertValue( obj, Map.class );
//		return map;
//	}
//	
//	public static Object loadFromResourceFile( String fileName ){
//		Object json = null;
//		try{
//			URL url = JacksonHelper.class.getResource( fileName );
//			json = mapper.readValue( url, Object.class );
//		} catch ( Exception e ){
//			
//		}
//		return json;
//	}
//	
//	public static Object loadFromFilesystem( String fileName ){
//		Object json = null;
//		try{
//			File file = new File( fileName );
//			json = mapper.readValue( file, Object.class );
//		} catch ( Exception e ){
//			
//		}
//		return json;
//	}
	
}
