package com.chnye.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Before;
import org.junit.Test;


import com.chnye.common.eventbus.IEventListener;
import com.chnye.common.eventbus.MapSetsEventManager;

public class TestReadBigFile {

	public static final String BIG_FILE_PATH = ".\\bin\\com\\chnye\\common\\file\\test01.txt";
	
//	private  MapSetsEventManager eventBus = null;

	private ExecutorService executor = null;
	
	private AtomicInteger  iCount = new AtomicInteger(0);
	
	@Before
	public void init(){
		
		executor = Executors.newFixedThreadPool(5);
		
		MapSetsEventManager.getInstance().setExecutor(executor);
		
		IEventListener<LineInfo> listenerLine = new IEventListener<LineInfo>(){
			@Override
			public void onEvent(LineInfo event) {
				// TODO Auto-generated method stub
				try{
					Thread.sleep(6);
				}catch( Exception e){
				}
				System.out.println( Thread.currentThread() + " event: " + event.getLineNumber() + " " + event.getLine() );
				iCount.incrementAndGet();
			}
		};
		
		MapSetsEventManager.getInstance().addListener(LineInfo.class, listenerLine );
		
	}
	
	
	private void assertReadBigFile() throws FileNotFoundException{
		System.out.println( "----------assertReadBigFile----------" );
		FileLineIterable iterable = new FileLineIterable( BIG_FILE_PATH );
		Iterator<String> iter = iterable.iterator();
		int i = 1;
		while( iter.hasNext() ){
			String line = iter.next();
			System.out.println( i + " " + line );
			i++;
		}//end while 
	}
	
	/**
	 * 使用Scanner读取
	 * @throws IOException 
	 */
	private void assertReadBigFile2() throws IOException{
		System.out.println( "----------assertReadBigFile2----------" );
		FileInputStream fis = null;
		Scanner sc = null;
		try{
		  fis = new FileInputStream( BIG_FILE_PATH );
		  sc = new Scanner( fis, "UTF-8" );
		  int i = 0;
		  while( sc.hasNextLine() ){
		    String line = sc.nextLine();
		    System.out.println( i + " " + line );
		    i++;
		  }//end while
		  if( sc.ioException() != null ){
		    throw sc.ioException();
		  }
		} finally{
		  if( fis != null ){
		    try{
		      fis.close();
		    }catch(IOException e){}
		  }
		  if( sc != null ){
		    sc.close();
		  }

		}
	}
	
	/**
	 * 使用 apache commons IO流的API读取
	 * @throws IOException 
	 */
	private void assertReadBigFile3() throws IOException{
		System.out.println( "----------assertReadBigFile3----------" );
		LineIterator iter = FileUtils.lineIterator( new File(BIG_FILE_PATH), "UTF-8" );
		int i = 0;
		try{
		  while( iter.hasNext() ){
		    String line = iter.nextLine();
//		    System.out.println( i + " " + line );
		    LineInfo lineInfo = LineInfo.create(line, BIG_FILE_PATH, String.valueOf(i), "rule01" );
		    MapSetsEventManager.getInstance().notifyAsyncListeners(LineInfo.class, lineInfo );
		    i++;
		  }//end while
		} finally {
		  LineIterator.closeQuietly( iter );
		}
		
	}
	
	@Test
	public void testReadBigFile(){
		try {
			
//			assertReadBigFile();
			
//			assertReadBigFile2();
			
			assertReadBigFile3();
			
			try{
				Thread.sleep(3200000);
			}catch(Exception e){
			}
			System.out.println("总共接收到的消息数: " + iCount );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static class LineInfo{
		private String line;
		
		private String filePath;
		
		private String lineNumber;
		
		private String ruleInfo;
		
		public static LineInfo create( String line, String filePath, String lineNumber, String ruleInfo ){
			return new LineInfo(line, filePath, lineNumber, ruleInfo );
		}
		
		public LineInfo( String line ){
			this(line, null, null, null);
		}
		
		
		public LineInfo( String line, String filePath, String lineNumber, String ruleInfo ){
			this.line = line;
			this.filePath = filePath;
			this.lineNumber = lineNumber;
			this.ruleInfo = ruleInfo;
		}
		
		
		public String getLine() {
			return line;
		}
		public void setLine(String line) {
			this.line = line;
		}


		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}


		public String getLineNumber() {
			return lineNumber;
		}
		public void setLineNumber(String lineNumber) {
			this.lineNumber = lineNumber;
		}


		public String getRuleInfo() {
			return ruleInfo;
		}
		public void setRuleInfo(String ruleInfo) {
			this.ruleInfo = ruleInfo;
		}
		
	}//end class
	
	
}
