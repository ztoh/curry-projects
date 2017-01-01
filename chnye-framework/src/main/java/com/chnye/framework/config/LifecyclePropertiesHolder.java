package com.chnye.framework.config;

import com.chnye.common.lifecycle.IDestroyable;
import com.chnye.common.lifecycle.ISmartCycle;
import com.chnye.common.lifecycle.Initilize;
import com.chnye.common.lifecycle.LifecycleException;
import com.google.common.base.Throwables;

public class LifecyclePropertiesHolder implements IPropertiesHolder, Initilize, IDestroyable{

	private IPropertiesHolder holder;
	private boolean bInited = false;

	public static LifecyclePropertiesHolder createLifeCyclePropertiesHolder( IPropertiesHolder holder ){
		return new LifecyclePropertiesHolder( holder );
	}

	
	
	public LifecyclePropertiesHolder(IPropertiesHolder holder) {
		// TODO Auto-generated constructor stub
		this.holder = holder;
	}
	
	

	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		try {
			holder.loadProperties();
			setInited( true );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Throwables.propagate(e);
		}
		
	}

	@Override
	public void reInit() throws LifecycleException {
		// TODO Auto-generated method stub
		try {
			holder.loadProperties();
			setInited( true );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Throwables.propagate(e);
		}
		
	}

	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return this.bInited;
	}


	@Override
	public void lifecycleDestroy() throws LifecycleException {
		// TODO Auto-generated method stub
		lifecycleClear();
	}

	@Override
	public void lifecycleClear() throws LifecycleException {
		// TODO Auto-generated method stub
		holder.clear();
		setInited( false );
	}

	private void setInited( boolean bInited ){
		this.bInited = bInited;
	}


	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		holder.setName(name);
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return holder.getName();
	}


	@Override
	public void setLocations(String[] locations) {
		// TODO Auto-generated method stub
		holder.setLocations(locations);
	}


	@Override
	public String[] getLocations() {
		// TODO Auto-generated method stub
		return holder.getLocations();
	}


	@Override
	public void loadProperties() throws Exception{
		// TODO Auto-generated method stub
		holder.loadProperties();
	}


	@Override
	public Object getProperties() {
		// TODO Auto-generated method stub
		return holder.getProperties();
	}


	@Override
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return holder.getProperty(key);
	}


	@Override
	public String getProperty(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return holder.getProperty(key, defaultValue);
	}


	@Override
	public Boolean getBoolean(String key) {
		// TODO Auto-generated method stub
		return holder.getBoolean(key);
	}


	@Override
	public Boolean getBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return holder.getBoolean(key, defaultValue);
	}


	@Override
	public Integer getInteger(String key) {
		// TODO Auto-generated method stub
		return holder.getInteger(key);
	}


	@Override
	public Integer getInteger(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return holder.getInteger(key, defaultValue);
	}


	@Override
	public Long getLong(String key) {
		// TODO Auto-generated method stub
		return holder.getLong(key);
	}


	@Override
	public Long getLong(String key, long defaultValue) {
		// TODO Auto-generated method stub
		return holder.getLong(key, defaultValue);
	}


	@Override
	public Double getDouble(String key) {
		// TODO Auto-generated method stub
		return holder.getDouble(key);
	}


	@Override
	public Double getDouble(String key, double defaultValue) {
		// TODO Auto-generated method stub
		return holder.getDouble(key, defaultValue);
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		holder.clear();
	}

}
