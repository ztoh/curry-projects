package com.chnye.framework.config.xml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chnye.common.xml.jaxb.JaxbXmlService;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions.MapDefinition;
import com.chnye.framework.config.AbstractPropertiesHolder;

public class JaxbPropertiesHolder extends AbstractPropertiesHolder{

	private ListMapDefinitions data;
	
	private static Pattern ARRAY_PATTERN = Pattern.compile("([\\w/]+)\\[([\\w/]+)\\]");
	
	public static JaxbPropertiesHolder createPropertiesHolder( String name ){
		return new JaxbPropertiesHolder( name );
	}
	public static JaxbPropertiesHolder createPropertiesHolder( String name, String...locations ){
		return new JaxbPropertiesHolder( name, locations );
	}
	
	public JaxbPropertiesHolder( String name ){
		this( name, null );
	}
	
	public JaxbPropertiesHolder(String name, String[] locations ){
		super.setName(name);
		super.setLocations(locations);
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadProperties() throws Exception {
		// TODO Auto-generated method stub
		this.data = JaxbXmlService.getInstance().getObject( locations[0], ListMapDefinitions.class );
	}

	@Override
	public Object getProperties() {
		// TODO Auto-generated method stub
		return data;
	}

	/**
	 * key:  支持数组形式    field01[name]
	 */
	@Override
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		Matcher matcher = ARRAY_PATTERN.matcher(key);
		if( matcher.find() ){
			MapDefinition  mapDef = data.getMapDefinitionByKey( matcher.group(1) );
			if( mapDef != null ){
				return mapDef.getString( matcher.group(2) );
			}
		}
		return data.getMapDefinitionByKey(key).toString();
	}

	public String getKey(){
		return this.data.getKey();
	}
	public String getType(){
		return this.data.getType();
	}
	public String getDesc(){
		return this.data.getDesc();
	}
	
}
