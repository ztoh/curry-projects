package com.chnye.framework.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceEditor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;




public class ResourceUtil {

	public static ResourceLoader resLoader = new DefaultResourceLoader();
	
	public static Properties loadProperties( String[] locations ){
		Properties props = new Properties();
		
		if( locations == null || locations.length == 0 ){
			return props;
		}
		
		for( String location : locations ){
			InputStream is = null;
			try{
				Resource resource = resLoader.getResource(location);
				is = resource.getInputStream();
				props.load( is );
			} catch ( IOException ex ){
				ex.printStackTrace();
			} finally {
				if( is != null ){
					try{
						is.close();
					} catch( IOException e ){
					}
				}
			}
		}//end for 
		return props;
	}
	
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
	
	public static String getResourceContent( String filePath ) throws IOException{
		String xmlStr = null;
		if( StringUtils.startsWith(filePath, "file:") || StringUtils.startsWith(filePath, "classpath:") ){
			ResourceEditor editor = new ResourceEditor();
			editor.setAsText( filePath );
			Resource res = (Resource)editor.getValue();
			xmlStr = new String( IOUtils.toCharArray( res.getInputStream() ) );
		}
		return xmlStr;
	}
	
	public static Resource getResource( String location ){
		if( isExistingFile(location) ){
			return new FileSystemResource( location );
		} else {
			return resLoader.getResource( location );
		}
	}
	
	private static boolean isExistingFile( String location ){
		return new File(location).exists();
	}
	
	
}
