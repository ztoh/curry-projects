package com.chnye.common.xml.jaxb.definitions;


import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.chnye.common.xml.jaxb.adapter.MapMapPropertiesAdapter;


@XmlRootElement(name = "entities")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapMapBean {

	@XmlAttribute
	private String key;
	
	@XmlJavaTypeAdapter(MapMapPropertiesAdapter.class)
	private Map<String, MapBean> maps;
	
	
	public static class MapBean {
		
		private String key;
		
		private String type;
		
		private Map<String,String> map;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Map<String, String> getMap() {
			return map;
		}

		public void setMap(Map<String, String> map) {
			this.map = map;
		}
		
		@Override
		public String toString(){
			StringBuffer sb = new StringBuffer();
			sb.append( "----->" );
			sb.append( this.getClass().getName() );
			sb.append( " key[" );
			sb.append( "] type[" );
			sb.append( type );
			sb.append( "\r\n" );
			for( Map.Entry<String, String> entry : map.entrySet() ){
				sb.append( "\t\t" ).append( entry.getKey() ).append("=").append( entry.getValue() ).append( "\r\n" );
			}
			return sb.toString();
		}
		
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append( "----->" );
		sb.append( this.getClass().getName() );
		sb.append( " key[" );
		sb.append( key );
		sb.append( "]\r\n" );
		if( maps != null ){
			for( Map.Entry<String, MapBean> entry : maps.entrySet() ){
				MapBean mapDef = entry.getValue();
				sb.append( "\t").append( "key[").append( mapDef.key ).append( "] type[").append( mapDef.type).append( "]\r\n" );
				sb.append("\t").append( entry.getKey() ).append( "=" ).append( entry.getValue() ).append( "\r\n" );
				
			}
		}
		return sb.toString();
	}
	
}
