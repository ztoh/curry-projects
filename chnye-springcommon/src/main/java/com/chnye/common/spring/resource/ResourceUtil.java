package com.chnye.common.spring.resource;

/**
 * import org.springframework.core.io.ClassPathResource;
 * import org.springframework.core.io.Resource;
 */

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

public class ResourceUtil {

	
	/**  http://blog.csdn.net/heartdiamond/article/details/5985260
	 * 
	 * @param classpath   "com/chnye/framework/context/xml/examples01.xml"
	 * @param encoding     "UTF-8"
	 * @return
	 * @throws IOException 
	 */
	public static String readStringByClasspathResource( String fileClassPath, String encoding ) throws IOException{
		Resource res = new ClassPathResource( fileClassPath );
		EncodedResource enRes = new EncodedResource( res, encoding );
		return FileCopyUtils.copyToString( enRes.getReader() );
	}
	
	
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/org.kuali.rice/rice-core-api/2.5.3/org/kuali/rice/core/api/util/RiceUtilities.java?av=f
	
	//绝对路径
	private static class AbsoluteFileSystemResourceLoader extends FileSystemResourceLoader{
		@Override
		protected Resource getResourceByPath( String path ){
			return new FileSystemResource( path );
		}
	}
	
	private static ClassLoader getDefaultClassLoader(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if( null == classLoader ){
			classLoader = ResourceUtil.class.getClassLoader();
		}
		return classLoader;
	}
	
	private static Resource getResource( String resourceLocation ){
		AbsoluteFileSystemResourceLoader resourceLoader = new AbsoluteFileSystemResourceLoader();
		resourceLoader.setClassLoader( getDefaultClassLoader() );
		return resourceLoader.getResource( resourceLocation );
	}
	
	
	public static InputStream getResourceAsStream( String resourceLocation ) throws IOException{
		Resource resource = getResource(resourceLocation);
		if( resource.exists() ){
			return resource.getInputStream();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
