package com.chnye.common.thread.runnable;


public final class SafeRunner{

	public static void run( ISafeRunnable  runnable ){
		try{
			runnable.run();
		} catch( Exception e ){
			runnable.handleException( e );
		}
	}
	
	
	public static interface ISafeRunnable{
		void run() throws Exception;
		void handleException( Throwable t );
	}
}