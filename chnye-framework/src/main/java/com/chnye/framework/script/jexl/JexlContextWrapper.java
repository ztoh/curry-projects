package com.chnye.framework.script.jexl;

import org.apache.commons.jexl2.JexlContext;

import com.chnye.common.script.jexl.JexlUtil;
import com.chnye.framework.context.IContext;

public class JexlContextWrapper implements JexlContext{

	IContext context;
	
	JexlContextWrapper( IContext context ){
		this.context = context;
	}
	
	@Override
	public Object get(String name) {
		// TODO Auto-generated method stub
		name = name.replaceAll( JexlUtil.INTERNAL_SLASH, "/");
		
		Object result =  context.getValueAt( name );
		System.out.println( "name: " + name + "result: " + result );
		return result;
	}

	@Override
	public void set(String name, Object value) {
		// TODO Auto-generated method stub
		context.setValueAt( name, value );
	}

	@Override
	public boolean has(String name) {
		// TODO Auto-generated method stub
		return context.hasProperty( name );
	}

}
