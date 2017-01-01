package com.chnye.common.xml.jaxb.definitions;

/*
 * 
<entities key="db">
	<!-- 其实期望的格式是<database key="db01" /> -->
	<entity key="db01" type="database">
    	<map>
    		<!-- 其实期望的格式是<key>value</key> 即<dbName>zhangsan</dbName>  -->
        	<item key="dbName" >zhangsan</item>
    	</map>
	</entity>
	<entity key="field01" type="field"></entity>
</entities>

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.chnye.common.xml.jaxb.adapter.MapPropertiesAdapter;

@XmlRootElement(name = "entities")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListMapDefinitions{
	
	@XmlAttribute
	private String key;

	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String desc;
	
	
	@XmlElement(name="entity")
	private List<MapDefinition> list = new ArrayList<MapDefinition>();
	
	private Map<String, MapDefinition> mapDefs;
	
	
	
	
	
	@XmlRootElement(name = "entity")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class MapDefinition {
		
		@XmlAttribute
		private String key;
		
		@XmlAttribute
		private String type;
		
		@XmlAttribute
		private String desc;
		
		
		@XmlJavaTypeAdapter(MapPropertiesAdapter.class)
		private Map<String,String> map;
		
		
		
		public String getKey(){
			return key;
		}
		public String getType(){
			return type;
		}
		public String getDesc(){
			return desc;
		}
		
		
		public String getString( String key ){
			return map.get(key);
		}
		
		public String getString( String key, String defaultValue ){
			String reStr = getString(key);
			if( null == reStr ){
				return defaultValue;
			}
			return reStr;
		}
		
		public Boolean getBoolean( String key ){
			String reStr = getString( key );
			if( null == reStr ){
				return null;
			}
			try{
				return Boolean.valueOf( reStr );
			} catch ( Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		public Boolean getBoolean( String key, Boolean defaultValue ){
			Boolean reBoolean = getBoolean( key );
			if( null == reBoolean ){
				return defaultValue;
			}
			return reBoolean;
		}
		
		public Integer getInteger( String key ){
			String reStr = getString( key );
			if( null == reStr ){
				return null;
			}
			try{
				return Integer.valueOf( reStr );
			} catch( NumberFormatException e ){
				e.printStackTrace();
			}
			return null;
		}
		
		public Integer getInteger( String key, Integer defaultValue ){
			Integer reInteger = getInteger( key );
			if( null == reInteger ){
				return defaultValue;
			}
			return reInteger;
		}
		
		public Long getLong( String key ){
			String reStr = getString( key );
			if( null == reStr ){
				return null;
			}
			try{
				return Long.valueOf( reStr );
			} catch( NumberFormatException e ){
				e.printStackTrace();
			}
			return null;
		}
		
		public Long getInteger( String key, Long defaultValue ){
			Long reLong = getLong( key );
			if( null == reLong ){
				return defaultValue;
			}
			return reLong;
		}
		
		@Override
		public String toString(){
			StringBuffer sb = new StringBuffer();
			sb.append( "----->" );
			sb.append( this.getClass().getName() );
			sb.append( " key[" );
			sb.append( key );
			sb.append( "] type[" );
			sb.append( type );
			sb.append( "] desc[" );
			sb.append( desc );
			sb.append( "] map.size[" );
			sb.append( map != null ? map.size() : 0 );
			sb.append( "]\r\n" );
			if( map != null ){
				for( Map.Entry<String, String> entry : map.entrySet() ){
					sb.append("\t").append( entry.getKey() ).append("=").append( entry.getValue() ).append("\r\n");
				}
			}
			return sb.toString();
		}
		
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append( this.getClass().getName() );
		sb.append( " key[" );
		sb.append( key );
		sb.append( "] type[" );
		sb.append( type );
		sb.append( "] desc[" );
		sb.append( desc );
		sb.append( "] list.size[");
		sb.append( list != null ? list.size() : 0 );
		sb.append( "]\r\n" );
		if( list != null ){
			for( MapDefinition entity : list ){
				sb.append( "\t" );
				sb.append( entity.toString() );
			}
		}
		sb.append( "----------map----------\r\n" );
		if( mapDefs != null ){
			for( Map.Entry<String, MapDefinition> entry : mapDefs.entrySet() ){
				sb.append( "\t" );
				sb.append( "key[" );
				sb.append( entry.getKey() );
				sb.append( "] " );
				sb.append( entry.getValue() );
				sb.append( "");
			}
		}
		return sb.toString();
	}

	public String getKey(){
		return key;
	}
	public String getType(){
		return type;
	}
	public String getDesc(){
		return desc;
	}
	
	public void setMapDefinitions( List<MapDefinition> list ){
		this.list = list;
	}
	public List<MapDefinition> getMapDefinitions(){
		return list;
	}

	public List<MapDefinition> getMapDefinitionsByType( String type ){
		if( null == key ){
			return Collections.emptyList();
		}
		List<MapDefinition> reDefs = new ArrayList<MapDefinition>();
		for( MapDefinition def : list ){
			if( type.equals( def.getType() ) ){
				reDefs.add( def );
			}
		}
		return reDefs;
	}
	
	public MapDefinition getMapDefinitionByKey( String key ){
		if( null == key || null == mapDefs  ){
			return null;
		}
		return mapDefs.get(key);
	}
	
	void afterUnmarshal(Unmarshaller u, Object p){
//		System.out.println( "XXXXXXXXXXXXXXXXXXXXXXXXXXX" );
		mapDefs = new HashMap<String, MapDefinition>();
		for( MapDefinition mapDef : list ){
			mapDefs.put( mapDef.getKey(), mapDef );
		}
	}
}


