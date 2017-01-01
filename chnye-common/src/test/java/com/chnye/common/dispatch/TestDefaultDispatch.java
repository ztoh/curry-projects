package com.chnye.common.dispatch;


import org.junit.Before;
import org.junit.Test;

import com.chnye.common.dispatch.DefaultDispatch;
import com.chnye.common.tuple.Tuple3;
import com.chnye.common.tuple.TupleUtil;



public class TestDefaultDispatch {

	private DefaultDispatch<Tuple3, Tuple3>  dispatcher = null;
	
	private FttbAcceptHandler  fttbHandler = null;
	private FtthAcceptHandler  ftthHandler = null;
	
	@Before
	public void init(){
		
		dispatcher = new DefaultDispatch<Tuple3, Tuple3>();
		
		fttbHandler = new FttbAcceptHandler();
		ftthHandler = new FtthAcceptHandler();
		
		dispatcher.addAcceptHandler( fttbHandler );
		dispatcher.addAcceptHandler( ftthHandler );
		
	}
	
	@Test
	public void testFunc() throws Throwable{
		
		for( int i = 0; i < 10; i++ ){
			
			Tuple3 input = TupleUtil.create( "FTTB", "parama" + i , "paramb" + i );
			
			Tuple3 result = dispatcher.dispatch( input );
			System.out.println( "R: " + result );
		}
		
	}
}
