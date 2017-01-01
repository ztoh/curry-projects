package com.chnye.framework.config;

import java.util.List;
import java.util.Map;

import com.chnye.common.collection.MapSupport;
import com.chnye.common.function.Functions;
import com.chnye.common.function.Function;
import com.chnye.common.lifecycle.ISmartCycle;
import com.chnye.common.lifecycle.LifecycleException;
import com.chnye.common.lifecycle.LifecycleHelper;




public class PropertiesManager implements ISmartCycle{
	
	
	private static PropertiesManager instance;
	
	public static PropertiesManager getInstance(){
		if( instance == null ){
			synchronized( PropertiesManager.class ){
				if( instance == null ){
					instance = new PropertiesManager();
				}
			}//end sync
		}
		return instance;
	}
	
//	public ConcurrentHashMap<String, LifeCyclePropertiesHolder> propertiesMaps = new ConcurrentHashMap<String, LifeCyclePropertiesHolder>(0);	
	public Map<String, IPropertiesHolder>  propertiesMaps  = MapSupport.newConCurrentHashMap();
	
	public void addProperties( String key, IPropertiesHolder props ){
		if( props != null ){
			propertiesMaps.put( key, props );
		} else {
			propertiesMaps.remove( key );
		}
	}
	
	public IPropertiesHolder getProperties( String key ){
		return propertiesMaps.get(key);
	}
	
	public IPropertiesHolder removeProperties( String key ){
		IPropertiesHolder props = propertiesMaps.get(key);
		propertiesMaps.remove( key );
		return props;
	}

	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
//		for( Map.Entry<String, IPropertiesHolder>  entry :  propertiesMaps.entrySet() ){
//			IPropertiesHolder holder = entry.getValue();
//			LifecycleHelper.init( holder );
//		}
		
		//写法二:
		Function<Map<String, IPropertiesHolder>, List<IPropertiesHolder>> func = Functions.mapValuesList();
		LifecycleHelper.init( func.apply( propertiesMaps ) );
		
	}

	@Override
	public void reInit() throws LifecycleException {
		// TODO Auto-generated method stub
//		for( Map.Entry<String, IPropertiesHolder>  entry :  propertiesMaps.entrySet() ){
//			IPropertiesHolder holder = entry.getValue();
//			LifecycleHelper.reInit( holder );
//		}
		
		//写法二:
		Function<Map<String, IPropertiesHolder>, List<IPropertiesHolder>> func = Functions.mapValuesList();
		LifecycleHelper.reInit( func.apply( propertiesMaps ) );
	}

	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lifecycleDestroy() throws LifecycleException {
		// TODO Auto-generated method stub
//		lifecycleClear();
		propertiesMaps = null;
	}

	@Override
	public void lifecycleClear() throws LifecycleException {
		// TODO Auto-generated method stub
//		for( Map.Entry<String, IPropertiesHolder>  entry :  propertiesMaps.entrySet() ){
//			IPropertiesHolder holder = entry.getValue();
//			LifecycleHelper.lifecycleClear( holder );
//		}
		
		//写法二:
		Function<Map<String, IPropertiesHolder>, List<IPropertiesHolder>> func = Functions.mapValuesList();
		LifecycleHelper.lifecycleClear( func.apply( propertiesMaps ) );
				
		propertiesMaps.clear();
	}
	
	
	
	
	
	
}
