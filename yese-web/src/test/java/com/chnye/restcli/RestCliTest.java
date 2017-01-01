package com.chnye.restcli;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RestCliTest {

	@Test
	public void testRestCli(){
		try {
			   URL url = new URL(
			     "http://localhost:8080/WebContent/user/qry");
			   HttpURLConnection connection = (HttpURLConnection) url
			     .openConnection();
			   connection.setDoOutput(true);
			   connection.setDoInput(true);
			   connection.setRequestMethod("POST");
			   connection.setUseCaches(false);
			   connection.setInstanceFollowRedirects(true);
			   connection.setRequestProperty("Content-Type", "application/json");
			   connection.connect();

			   DataOutputStream out = new DataOutputStream(
			     connection.getOutputStream());
//			   MisCartInfo mci = new MisCartInfo();
//			   mci.setCardnumber("001000000000005");
//			   List<MisAddPackage> mas = new ArrayList();
//			   MisAddPackage ma = new MisAddPackage();
//			   ma.setAddpackname("fuck");
//			   List<AddCheckitem> acs = new ArrayList();
//			   AddCheckitem ac = new AddCheckitem();
//			   ac.setName("shit");
//			   acs.add(ac);
//			   ma.setSddCheckitems(acs);
//			   mas.add(ma);
//			   mci.setMisAddPackages(mas);
			   RequestParams reqJson = new RequestParams();
			   reqJson.setLoginname("h");
			   reqJson.setPassword("h");
			   
			   
//			   JsonGenerator jg = null;
//			   ObjectMapper objectMapper = new ObjectMapper();
//			   jg = objectMapper.getJsonFactory().createJsonGenerator(System.out,
//			     JsonEncoding.UTF8);
//			   objectMapper.writeValue(System.out, reqJson);
//			   objectMapper.writeValue(out, reqJson );
//			   out.flush();
//			   out.close();
			   
			   BufferedReader reader = new BufferedReader(new InputStreamReader(
			     connection.getInputStream()));
			   String lines;
			   StringBuffer sb = new StringBuffer("");
			   while ((lines = reader.readLine()) != null) {
			    lines = new String(lines.getBytes(), "utf-8");
			    sb.append(lines);
			   }
			   System.out.println(sb.toString());
			   reader.close();
			   //
			   connection.disconnect();
			  } catch (MalformedURLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
	}
	
}
