package com.chnye.framework.config.xml.impl;


import java.util.HashMap;
import java.util.Map;

import com.chnye.common.propertycontainer.impl.PropertyContainer;
import com.chnye.framework.config.xml.IXmlDefinition;


public class DefaultXmlDefinition extends PropertyContainer implements IXmlDefinition{


	
	private Map<String, IXmlDefinition> subDefinitions =  new HashMap<String, IXmlDefinition>();
	
	public DefaultXmlDefinition(){
		super();
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		setProperty( KEY_NAME, name );
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return getString( KEY_NAME );
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		setProperty( KEY_ID, id );
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getString( KEY_ID );
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return getString( KEY_PATH );
	}

	@Override
	public String getSimplePath() {
		// TODO Auto-generated method stub
		return getString( KEY_SIMPLEPATH );
	}

	@Override
	public String getQName() {
		// TODO Auto-generated method stub
		return getString( KEY_QNAME );
	}

	@Override
	public IXmlDefinition getSubDefinition(String key) {
		// TODO Auto-generated method stub
		return subDefinitions.get(key);
	}

	@Override
	public Map<String, IXmlDefinition> getSubDefinitions() {
		// TODO Auto-generated method stub
		return subDefinitions;
	}

	@Override
	public void addSubDefinition(String key, IXmlDefinition def) {
		// TODO Auto-generated method stub
		subDefinitions.put(key, def);
	}


}
