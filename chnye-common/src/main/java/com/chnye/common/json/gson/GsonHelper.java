package com.chnye.common.json.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializationContext;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;


public class GsonHelper {

	//单例
//	private static Gson gson;
//	
//	public static Gson getGson(){
//		if( gson == null ){
//			synchronized( GsonHelper.class ){
//				if( gson == null ){
//					GsonBuilder builder = new GsonBuilder();
//					builder.registerTypeAdapter( Request.class, new )
//				}
//			}//end sync
//		}
//	}
//	
//	public static JsonObject parseJsonString( String jsonString ){
//		return new JsonParser().parse( jsonString ).getAsJsonObject();
//	}
//	
//	public static JsonElement serialize( JsonSerializationContext context ){
//		JsonElement element = context.serialize(src)
//	    JsonArray array = context.serialize(src)
//	    JsonObject object = context.serialize(src)
//	}
	
	/** http://opensearch.krugle.org/document/view_filecontent/tadpolefordbtools5ca53680f4034ca09b2d7b822d03fd75/scmi_localhost_8767/master/com.hangum.tadpole.commons/src/com/hangum/tadpole/commons/util/JSONUtil.java
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static String getPretty( String jsonStr ){
		if( null == jsonStr ){
			return "";
		}
		try{
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse( jsonStr );
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String strGson = gson.toJson( element );
			if( null == strGson || "null".equals(strGson) ){
				strGson = "";
			}
			return strGson;		
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	public static GsonBuilder getGsonBuilder(){
		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		builder.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
		builder.setPrettyPrinting();
		builder.excludeFieldsWithoutExposeAnnotation();
		
		return builder;
	}
	
}
