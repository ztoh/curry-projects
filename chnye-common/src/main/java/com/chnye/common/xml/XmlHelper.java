package com.chnye.common.xml;

public class XmlHelper {

	public static String entag( String tag ){
		return "<" +  tag + ">";
	}
	
	public static String detag( String tag ){
		return "</" + tag + ">";
	}
	
	public static String escape( String text ){
		if( text == null ){
			return null;
		}
		StringBuffer result = new StringBuffer( text.length() );
		char[] chars = text.toCharArray();
		for( int i = 0; i < chars.length; i++ ){
			switch( chars[i] ){
			case '&':
				result.append("&amp;");
				break;
			case '<':
				result.append("&lt;");
				break;
			case '\'':
				result.append("&apos;");
				break;
			case '\"':
				result.append("&quot;");
				break;
			default:
				result.append( chars[i] );
			}//end switch
		}//end for
		return result.toString();
	}
}
