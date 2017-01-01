package com.chnye.common.xml.jaxb.adapter;

//http://stackoverflow.com/questions/17024050/jaxb-xmladapter-map-list-adapter-marshall-only
//http://stackoverflow.com/questions/11329388/jaxb-mapping-for-a-map
//http://stackoverflow.com/questions/33190939/how-to-marshall-mapstring-listobjects-using-jaxb

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapPropertiesAdapter extends XmlAdapter<MapPropertiesAdapter.Entity, Map<String,String>>{


	@XmlRootElement(name = "map")
	public static class Entity{
		@XmlElement(name="item")
		public List<Item> items = new ArrayList<Item>();
		
//		public void addItem( String key, String value ){
//			items.add( new Item(key, value ) );
//		}
	}

	@XmlRootElement(name = "item")
	public static class Item{
		@XmlAttribute
		public String key;
		
//		@XmlAttribute
		@XmlValue
		public String value;
		
//		public Item(){}
//		public Item( String key, String value ){
//			this.key = key;
//			this.value = value;
//		}
	}
	
	/**
	 xml转bean整个逻辑
	    1.将Definitions.class注册到JAXBContext, 注册的注解的值有entities/entity
	    2.当解析config_rule.xml文件时, 碰到<entities><entity>节点时自动映射到definitions类上
	    3.当解析到xml中map节点时，缺省对应的是Definition的map属性。发现是自定义解析器，于是就调用MapPropertiesAdapter。
	           发现适配器让它自动映射到Entity对象， 然后再调用unmarshal()方法，将自动映射的Entity对象转换为希望的真实的Map<String,String>对象
	           
	 */
	@Override
	public Map<String, String> unmarshal(Entity entity ) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println( "props:" + entity + " " + entity.items.size() );
		Map<String,String> map = new HashMap<String,String>( entity.items.size() );
		for( Item property : entity.items ){
//			System.out.println( "map: " + property.key + " = " + property.value );
			map.put( property.key, property.value );
		}
		return map;
	}
	
	/**
	 * 将Bean转换为XML
	 *  Bean对象中有个Map<String,String> map 
	 */
	@Override
	public Entity marshal(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		if( null == map ){
			return null;
		}
		Entity entity = new Entity();
		for( Map.Entry<String, String> entry : map.entrySet() ){
			Item item = new Item();
			item.key = entry.getKey();
			item.value = entry.getValue();
			entity.items.add( item );
		}
		return entity;
	}
	
	
}
