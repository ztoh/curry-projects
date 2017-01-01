package com.chnye.common.utils;

import java.util.regex.Pattern;

public class PatternUtil {

	public static Pattern strPatternToPattern( final String pattern ){
		final String pat = pattern.replace(".", "\\.")
				.replace("(", "\\(")
				.replace(")", "\\)")
				.replace("[", "\\[")
				.replace("]", "\\]")
				.replace("?", "\\?")
				.replace("$", "\\$")
				.replace("+", "\\+")
				.replace("*", "(?:.*?)" );
		return Pattern.compile( '^' + pat + '$');
	}
}
