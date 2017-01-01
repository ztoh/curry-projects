package com.chnye.common.xml.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class JaxbUtil {

	public static String convertToXml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false );
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    } 

	public static String convertToXmlNoHeader(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true );
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    } 
	
	
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean( String xml, Class<T> cls ){
		T result = null;
		try{
			JAXBContext context = JAXBContext.newInstance( cls );
			Unmarshaller unmarshaller = context.createUnmarshaller();
			result = (T)unmarshaller.unmarshal( new StringReader( xml ) );
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return result;
	}
	
}
