package com.chnye.common.script.jexl;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.revolsys.open/com.revolsys.open.core/2014.09.10-MTEC1/com/revolsys/util/JexlUtil.java#JexlUtil


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.JexlException;



public class JexlUtil {

	public static final String DEFAULT_EXPRESSION_PATTERN = "\\$\\{([^\\}]+)\\}";
	public static final Pattern attrburitesPattern = Pattern.compile( DEFAULT_EXPRESSION_PATTERN );
	
	public static  JexlEngine jexlEngine = null;
	
	private static JexlEngine getEngine(){
		synchronized ( JexlUtil.class ) {
			if( jexlEngine == null ){
				jexlEngine = new JexlEngine();
//				jexlEngine.setClassLoader(loader);
				jexlEngine.setCache( 512 );
				jexlEngine.setLenient( true );
				jexlEngine.setSilent( false );
			}
		}
		return jexlEngine;
	}
	
	
	public static final String INTERNAL_SLASH = "@\\$@"; 
	
	public static Expression createExpression( final String expression ) throws Exception{
		return createExpression( expression, DEFAULT_EXPRESSION_PATTERN );
	}
	
	public static Expression createExpression( final String expression, final String expressionPattern ) throws Exception{
		final String newExpression = expression.replaceAll("\n", "");
		final Pattern compiledPattern = Pattern.compile( expressionPattern );
		final Matcher matcher = compiledPattern.matcher( newExpression );
		int lastEnd = 0;
		if( matcher.find() ){
			final StringBuffer jexlExpression = new StringBuffer();
			do{
				final int startIndex = matcher.start();
				if( startIndex != lastEnd ){
					final String text = newExpression.substring(lastEnd, startIndex);
					addText( jexlExpression, text );
					jexlExpression.append(" + ");
				}
				String matcherExpression = matcher.group(1);
				System.out.println( "matcherExpression:" + matcherExpression );
				matcherExpression = matcherExpression.replaceAll("/", INTERNAL_SLASH );
				jexlExpression.append( matcherExpression ).append(" + ");
				lastEnd = matcher.end();
			} while ( matcher.find() );
			addText( jexlExpression, newExpression.substring( lastEnd ) );
			
			String expr = jexlExpression.toString();
			expr = expr.replaceAll(" \\+ '' \\+ ", " + ");
			expr = expr.replaceAll("^'' \\+ ", "");
			expr = expr.replaceAll("\\+ ''$", "");
			System.out.println( expr );
			return getEngine().createExpression( expr );
		} else {  
			return null;
		}
	}
	
	public static Object evaluateExpression( final JexlContext context, final Expression expression ){
		try{
			return expression.evaluate(context);
		} catch( final Exception e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	private static void addText(final StringBuffer jexlExpression, final String text) {
	     jexlExpression.append("'")
	       .append(text.replaceAll("'", "' + \"'\" + '"))
	       .append("'");
	}
	
	public static void initJexlContext( final String expression, JexlContext jexlContext, VarCallback varCallback ){
		final String newExpression = expression.replaceAll("\n", "");
		final Matcher matcher = attrburitesPattern.matcher( newExpression );
		while( matcher.find() ){
			String matcherExpression = matcher.group(1);
			System.out.println( "match: " + matcherExpression );
			if( varCallback != null ){
				Object value = varCallback.getValue( matcherExpression );
				if( value != null ){
					System.out.println( matcherExpression + " vs " + value );
					jexlContext.set(matcherExpression, value);
				}
			}
		}
	}
	
	
	public interface VarCallback{
		Object getValue( String var );
	}
	
	
	public static boolean isExpressionValid( final String expression ){
		boolean result;
		try{
			getEngine().createExpression(expression);
			result = true;
		} catch ( JexlException e ){
			result = false;
		}
		return result;
	}
	
	
}
