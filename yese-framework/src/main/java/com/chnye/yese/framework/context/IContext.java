package com.chnye.yese.framework.context;


import java.util.Map;

import com.chnye.yese.common.able.INamed;
import com.chnye.yese.common.able.Identifiable;
import com.chnye.yese.framework.propertycontainer.IPropertyContainer;

public interface IContext extends IPropertyContainer, INamed, Identifiable {

	Map<String,Object> getDatas();
	
	/*
	 * 一套扩展接口，
	 *   用于其他特定Context的读取和写入操作，因为key可以是一系列表达式,如XML的xpath, JSON的路径
	 *   最终存储时还是在setValueAt中调用setProperty来存储
	 */
	void setValueAt( String key, Object value );
	Object getValueAt( String key );
	/*
	 * 返回扩展的所有数据
	 */
//	Map<String,Object> getData();
	
	
}
