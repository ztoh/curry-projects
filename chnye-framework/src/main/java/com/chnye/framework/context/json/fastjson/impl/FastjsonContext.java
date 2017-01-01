package com.chnye.framework.context.json.fastjson.impl;

import com.chnye.common.json.fastjson.FastjsonHelper;
import com.chnye.framework.context.impl.Context;

public class FastjsonContext extends Context{

	private Object root;
	
	public static FastjsonContext create( String name ){
		return new FastjsonContext( name, "{}" );
	}
	public static FastjsonContext create( String name, String json ){
		return new FastjsonContext( name, json );
	}
	
	public FastjsonContext( String name, String json ){
		super.setName(name);
		this.root = FastjsonHelper.from( json );
	}
	
	
	@Override
	public void setValueAt(String key, Object value) {
		// TODO Auto-generated method stub
		FastjsonHelper.set( this.root, key, value );
	}

	@Override
	public Object getValueAt(String key) {
		// TODO Auto-generated method stub
		return FastjsonHelper.findObject( this.root, key );
	}

	@Override
	public Object getDatas() {
		// TODO Auto-generated method stub
		return this.root;
	}
	
	@Override
	public String toString(){
		return FastjsonHelper.toJSONString( this.root );
	}
	 
}
