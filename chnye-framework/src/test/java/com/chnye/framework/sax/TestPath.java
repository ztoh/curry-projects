package com.chnye.framework.sax;

import org.junit.Test;

public class TestPath {

	
	
	private void assertPath(){
		Path path = Path.parse( "com//chnye//framework");
		path.add( "/context//wo//xx/rr// ");
		path.add( "xml", 3);
		path.add( " //impl/ ");
		System.out.println( path );
		path.remove();
		path.remove();
		path.remove();
		System.out.println( path );
		System.out.println( path.getLeafName() );
	}
	
	
	
	@Test
	public void testPath(){
		assertPath();
	}
	
}
