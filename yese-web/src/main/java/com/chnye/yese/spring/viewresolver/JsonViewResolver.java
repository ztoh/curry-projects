package com.chnye.yese.spring.viewresolver;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	@Autowired
	MappingJackson2JsonView view;
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// TODO Auto-generated method stub
//		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint( true );
		return view;
	}

}
