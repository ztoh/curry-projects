package com.chnye.framework.config.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chnye.common.able.INamable;
import com.chnye.common.able.Identifiable;
import com.chnye.common.propertycontainer.IPropertyContainer;


public interface IXmlDefinition extends IPropertyContainer, INamable, Identifiable{

	//节点在XML树中的全路径
	String getPath();
	
	//节点在XML树中的简单名称
	String getSimplePath();

	//节点名称
	String getQName();
	
	void addSubDefinition( String key, IXmlDefinition def );
	IXmlDefinition getSubDefinition( String key );	
	Map<String, IXmlDefinition> getSubDefinitions();
	
	public static final String KEY_ID = "INNER_KEY_ID";
	public static final String KEY_NAME = "INNER_KEY_NAME";
	public static final String KEY_PATH = "INNER_KEY_PATH";
	public static final String KEY_SIMPLEPATH = "INNER_KEY_SIMPLEPATH";
	public static final String KEY_QNAME = "INNER_KEY_QNAME";
	public static final String KEY_NODE_VALUE = "INNER_KEY_NODEVALUE";

}
