package com.chnye.common.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FileLineIterable implements Iterable<String>{

	private BufferedReader reader;
	
	public FileLineIterable( String filePath ) throws FileNotFoundException{
		reader = new BufferedReader( new FileReader( filePath ) );
	}
	
	public void close(){
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return new BigFileIterator();
	}
	
	
	private class BigFileIterator implements Iterator<String>{

		private String currentLine;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			try {
				currentLine = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				currentLine = null;
			}
			return currentLine != null;
		}

		@Override
		public String next() {
			// TODO Auto-generated method stub
			return currentLine;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}//end class 

}
