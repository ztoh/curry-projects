package com.chnye.restcli;

import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;



public class RestCliTest2 {

	 public static String urlGet = "http://localhost:8080/users/{id}";
	    public static String urlPost = "http://localhost:8080/users/add";
	    public static RestTemplate rt = new RestTemplate();

	    // 从Rest接口获取数据
	    public static void getUser(long userId) {
	        HttpHeaders headers = new HttpHeaders();
	        // 设置Accept表示浏览器支持的MIME类型,此处意思是要返回的类型
	        headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_XML_VALUE));
	        HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

	        System.out.println(
	                rt.exchange(
	                        urlGet,
	                        HttpMethod.GET,
	                        requestEntity,
	                        String.class,
	                        userId
	                )
	        );
	        System.out.println(
	                rt.getForObject(urlGet, String.class, userId)
	        );
	    }

	    // 传JSON数据到REST接口,自动将JSON数据转化成类
	    public static void addUserByJson(String userJson) {
	        HttpHeaders headers = new HttpHeaders();
	        // 设置ContentType标明数据是JSON数据,否则报415(Unsupported Media Type)
	        // 此处必须和REST接口的RequestMapping的ContentType对应
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Object> requestEntity = new HttpEntity<Object>(userJson, headers);
	        System.out.println(requestEntity.getBody().toString());
	        rt.exchange("http://localhost:8080/WebContent/user/qry", HttpMethod.POST, requestEntity, RequestParams.class);
//	        rt.exchange
//	        RequestParams  reqJson = new RequestParams();
//	        reqJson.setLoginname("h");
//	        reqJson.setPassword("h");
//	        rt.postForLocation("http://localhost:8080/WebContent/user/qry", reqJson);
	    }

	    public static void main(String[] args) {
//	        getUser(1);
	        addUserByJson("{\"loginname\":\"h\",\"passord\":\"h\"}");
	    }
	    
}
