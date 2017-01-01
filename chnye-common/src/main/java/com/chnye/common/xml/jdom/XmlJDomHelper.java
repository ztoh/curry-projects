package com.chnye.common.xml.jdom;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
 
public class XmlJDomHelper {

	public static org.jdom.Document convertToJDomDocument( org.w3c.dom.Document doc ){
		return new DOMBuilder().build( doc );
	}
	

	
}
