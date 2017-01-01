package com.chnye.test.guava;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.google.common.base.Throwables;

public class TestThrowable {

	private void assertThrowables(){
		
		List<Throwable> throwables = null;
		String expString = null;
		Throwable  rootThrowable = null;
		
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<FileInputStream> fileCallable = new Callable<FileInputStream>(){
			@Override
			public FileInputStream call() throws Exception {
				// TODO Auto-generated method stub
				return new FileInputStream("xxxxxxxxxxxxx");
			}
		};
		Future<FileInputStream> fileFuture = executor.submit( fileCallable );
		try{
			fileFuture.get();
		} catch ( Exception e ){
			e.printStackTrace();
			//获取异常链
			throwables = Throwables.getCausalChain( e );
			//得到异常的字符串形式
			expString = Throwables.getStackTraceAsString( e );
			//获取根异常
			rootThrowable = Throwables.getRootCause( e );
		}
		executor.shutdown();
		
		if( throwables != null ){
			System.out.println( "size:" + throwables.size() );
			for( Throwable throwable : throwables ){
				System.out.println( "EX: " + throwable );
			}
		}
		System.out.println( "expString:" + expString );
		
		System.out.println( "rootThrowable:" + rootThrowable );
	}
	
	
	@Test
	public void testThrowables(){
		assertThrowables();
	}
}
