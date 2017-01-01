package com.chnye.framework.config;

public abstract class AbstractPropertiesHolder implements IPropertiesHolder{

	protected String name;
	protected String[] locations;
	
	@Override
	public void setName( String name ){
		this.name = name;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public void setLocations( String[] locations ){
		this.locations = locations;
	}
	
	@Override
	public String[] getLocations(){
		return this.locations;
	}
	
	public abstract void loadProperties() throws Exception;
	
	public abstract Object getProperties();
	
	public abstract String getProperty(String key) ;

	@Override
	public String getProperty(String key, String defaultValue) {
		// TODO Auto-generated method stub
		String result = getProperty( key );
		if( null == result ){
			return defaultValue;
		}
		return result;
	}

	@Override
	public Boolean getBoolean(String key) {
		// TODO Auto-generated method stub
		String result = getProperty( key );
		if( null == result ){
			return null;
		}
		try{
			return Boolean.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}

	@Override
	public Boolean getBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		Boolean bResult = getBoolean( key );
		if( null == bResult ){
			return defaultValue;
		}
		return bResult;
	}

	@Override
	public Integer getInteger(String key) {
		// TODO Auto-generated method stub
		String result = getProperty( key );
		if( null == result ){
			return null;
		}
		try{
			return Integer.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}

	@Override
	public Integer getInteger(String key, int defaultValue) {
		// TODO Auto-generated method stub
		Integer iResult = getInteger( key );
		if( null == iResult ){
			return defaultValue;
		}
		return iResult;
	}

	@Override
	public Long getLong(String key) {
		// TODO Auto-generated method stub
		String result = getProperty( key );
		if( null == result ){
			return null;
		}
		try{
			return Long.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}

	@Override
	public Long getLong(String key, long defaultValue) {
		// TODO Auto-generated method stub
		Long lResult = getLong( key );
		if( null == lResult ){
			return defaultValue;
		}
		return lResult;
	}

	@Override
	public Double getDouble(String key) {
		// TODO Auto-generated method stub
		String result = getProperty( key );
		if( null == result ){
			return null;
		}
		try{
			return Double.valueOf( result );
		} catch( Exception e ){
			return null;
		}
	}

	@Override
	public Double getDouble(String key, double defaultValue) {
		// TODO Auto-generated method stub
		Double dResult = getDouble( key );
		if( null == dResult ){
			return defaultValue;
		}
		return dResult;
	}



}
