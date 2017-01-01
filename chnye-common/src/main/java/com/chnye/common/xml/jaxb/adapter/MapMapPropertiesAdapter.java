package com.chnye.common.xml.jaxb.adapter;

/**
 * 将如下结构映射为Map<String,Map<String,String>>结构形式
<maps>
    <map key="db01" type="database">
    		<!-- 其实期望的格式是<key>value</key> 即<dbName>zhangsan</dbName>  -->
        	<item key="dbName" >zhangsan</item>
    </map>
   	<map key="field01" type="field">
    </map>
</maps>    
   	
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.chnye.common.xml.jaxb.definitions.MapMapBean;
import com.chnye.common.xml.jaxb.definitions.MapMapBean.MapBean;

public class MapMapPropertiesAdapter extends XmlAdapter<MapMapPropertiesAdapter.Entities, Map<String, MapMapBean.MapBean>>{

	@XmlRootElement(name = "maps")
	public static class Entities{
		
		@XmlElement(name="map")
		public List<Entity> entitys = new ArrayList<Entity>();

	}

	@XmlRootElement(name = "map")
	public static class Entity{
		@XmlAttribute
		public String key;
		
		@XmlAttribute
		public String type;
		
		@XmlElement(name="item")
		public List<Item> items = new ArrayList<Item>();
		
	}

	@XmlRootElement(name = "item")
	public static class Item{
		@XmlAttribute
		public String key;
		
//		@XmlAttribute
		@XmlValue
		public String value;
	}
	

	@Override
	public Map<String, MapMapBean.MapBean> unmarshal(Entities entities ) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println( "props:" + entities + " " + entities.entitys.size() );
		Map<String, MapMapBean.MapBean> map = new HashMap<String, MapMapBean.MapBean>();
		for( Entity entity : entities.entitys ){
//			System.out.println( "entity.key:" + entity.key );
//			System.out.println( "entity.items:" + entity.items.size() );
			
			MapMapBean.MapBean bean = new MapMapBean.MapBean();
			bean.setKey( entity.key );
			bean.setType( entity.type );
			
			Map<String, String> subMap = new HashMap<String,String>();
			for( Item item : entity.items ){
				subMap.put( item.key, item.value );
				bean.setMap( subMap );
			}
			if( bean != null ){
				map.put( entity.key, bean );
			}
		}
		return map;
	}
	
	/**
	 * 将Bean转换为XML
	 *  Bean对象中有个Map<String,String> map 
	 */
	@Override
	public Entities marshal(Map<String, MapMapBean.MapBean> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
