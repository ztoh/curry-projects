package com.chnye.common.dispatch;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.dispatch.DefaultAsyncDispatch;
import com.chnye.common.dispatch.DefaultDispatch;
import com.chnye.common.tuple.Tuple3;
import com.chnye.common.tuple.TupleUtil;


public class TestDefaultAsyncDispatch {


	private DefaultDispatch<Tuple3, Tuple3>  dispatcher = null;
	private DefaultAsyncDispatch<Tuple3, Tuple3> asyncDispatcher = null;
	
	private FttbAcceptHandler  fttbHandler = null;
	private FtthAcceptHandler  ftthHandler = null;
	
	@Before
	public void init(){
		
		dispatcher = new DefaultDispatch<Tuple3, Tuple3>();
		
		asyncDispatcher = new DefaultAsyncDispatch( dispatcher );
		
		asyncDispatcher.init();
		
		fttbHandler = new FttbAcceptHandler();
		ftthHandler = new FtthAcceptHandler();
		
		dispatcher.addAcceptHandler( fttbHandler );
		dispatcher.addAcceptHandler( ftthHandler );
		
	}
	
	@Test
	public void testFunc() throws Throwable{
		
		for( int i = 0; i < 10; i++ ){
			
			Tuple3 input = TupleUtil.create( "FTTB", "parama" + i , "paramb" + i );
			
			asyncDispatcher.dispatch( input );
//			System.out.println( "R: " + result );
		}
		
	}
	
}
