package com.chnye.restcli;

import java.util.List;
import java.util.Map;

import org.junit.Test;




public class JackSonTest {

	@Test
	public void testJackSon() throws Exception{
		String jsonString = "{\"loginname\":\"ha1西文\",\"passord\":\"hb2中文\"}";
		String jsonStringAry = "[{\"loginname\":\"ha1西文\",\"passord\":\"hb2中文\"}]";
		
//		ObjectMapper mapper = new ObjectMapper();
//		RequestParams reqObj = mapper.readValue( jsonString, RequestParams.class );
//		JacksonMapper myMapper = JacksonMapper.getInstance();
//		RequestParams reqObj = myMapper.json2obj( jsonString, RequestParams.class );
//		if( reqObj != null ){
//			System.out.println( "reqObj.getId()=" + reqObj.getId() );
//			System.out.println( "reqObj.getLoginname()=" + reqObj.getLoginname() );
//			System.out.println( "reqObj.getPassword()=" + reqObj.getPassword() );
//		}
		
//		String tmpjsonString = myMapper.readAsString( reqObj );
//		System.out.println( "tmpjsonString=" + tmpjsonString );
//		
//		Map<String, Object> map1 = myMapper.json2map(jsonString);
//		for( Map.Entry<String, Object> entry : map1.entrySet() ){
//			System.out.println( "key:" + entry.getKey() + " value:" + entry.getValue() );
//		}
//		
//		Map<String, Object> map2 = myMapper.json2objTypeRef(jsonString) ;
//		for( Map.Entry<String, Object> entry : map2.entrySet() ){
//			System.out.println( "2key:" + entry.getKey() + " value:" + entry.getValue() );
//		}
//		
//		List<Map<String, Object>> lists = myMapper.json2list( jsonStringAry );
//		for( Map<String, Object> map3 : lists ){
//			for( Map.Entry<String, Object> entry : map3.entrySet() ){
//				System.out.println( "3key:" + entry.getKey() + " value:" + entry.getValue() );
//			}
//		}
		
	}
	
}
