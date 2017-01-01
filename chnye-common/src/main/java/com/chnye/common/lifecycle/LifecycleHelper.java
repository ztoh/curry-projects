package com.chnye.common.lifecycle;

import java.util.Collection;


public class LifecycleHelper {
	
	/*------------------------init/reInit--------------------------*/
	
	public static void init( Object obj ) throws LifecycleException{
		if( obj instanceof Initilize ){
			init( (Initilize)obj );
		}
	}
	
	public static void init( Initilize obj ) throws LifecycleException{
		obj.init();
	}
	
	public static void init( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			init( obj );
		}
	}
	
	public static void reInit( Object obj ) throws LifecycleException{
		if( obj instanceof Initilize ){
			reInit( (Initilize)obj );
		}
	}
	
	public static void reInit( Initilize obj ) throws LifecycleException{
		obj.reInit();
	}
	
	public static void reInit( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			reInit( obj );
		}
	}
	
	/*------------------------start/reStart--------------------------*/
	
	public static void start( Object obj ) throws LifecycleException{
		if( obj instanceof IStartable ){
			start( (IStartable)obj );
		}
	}
	
	public static void start( IStartable obj ) throws LifecycleException{
		obj.start();
	}
	
	public static void start( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			start( obj );
		}
	}
	
	public static void reStart( Object obj ) throws LifecycleException{
		if( obj instanceof Initilize ){
			reInit( (Initilize)obj );
		}
	}
	
	public static void reStart( IStartable obj ) throws LifecycleException{
		obj.reStart();
	}
	
	public static void reStart( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			reStart( obj );
		}
	}
	
	/*------------------------stop--------------------------*/
	
	public static void stop( Object obj ) throws LifecycleException{
		if( obj instanceof IStopable ){
			stop( (IStopable)obj );
		}
	}
	
	public static void stop( IStopable obj ) throws LifecycleException{
		obj.stop();
	}
	
	public static void stop( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			stop( obj );
		}
	}
	
	
	/*------------------------lifecycleDestroy/lifecycleClear--------------------------*/
	
	public static void lifecycleDestroy( Object obj ) throws LifecycleException{
		if( obj instanceof IDestroyable ){
			stop( (IDestroyable)obj );
		}
	}
	
	public static void lifecycleDestroy( IDestroyable obj ) throws LifecycleException{
		obj.lifecycleDestroy();
	}
	
	public static void lifecycleDestroy( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			lifecycleDestroy( obj );
		}
	}
	
	
	public static void lifecycleClear( Object obj ) throws LifecycleException{
		if( obj instanceof IDestroyable ){
			lifecycleClear( (IDestroyable)obj );
		}
	}
	
	public static void lifecycleClear( IDestroyable obj ) throws LifecycleException{
		obj.lifecycleClear();
	}
	
	public static void lifecycleClear( Collection coll ) throws LifecycleException{
		if( coll == null || coll.isEmpty() ){
			return;
		}
		for( Object obj : coll ){
			lifecycleClear( obj );
		}
	}
	
	
}
