package com.thread.comparetoandlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

public class Demo1 {
	
	private static RestTemplate restTemplate;
	
	 public static void main(String[] args) {
		 Detest tDt = new Detest();
		 tDt.setCode("434");
		 tDt.setName("ccc");
		 
		 List<Deo> det = new ArrayList<Deo>();
		 Deo dote = new Deo();
		 dote.setPrinAmt("56");
		 dote.setTerm("1");
		 dote.setTotalAmt("9999");
		 det.add(dote);
		 
		 Map<String, Object> map = new HashMap<>(2);
		 map.put("forceUpdate", "true");
		 map.put("customerName", "是否");

		 tDt.setExtParam(JSON.toJSONString(map));
		 tDt.setDetos(det);
		 
		 System.out.println(JSON.toJSONString(tDt));
		 String data =JSON.toJSONString(tDt);
		 
		 HttpHeaders headers = new HttpHeaders();
		 MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		 headers.setContentType(type);
		 headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		 HttpEntity<String> formEntity = new HttpEntity<>(data, headers);
		 String s = restTemplate.postForObject("http://ant.laiyongche.com/ant/billSync", formEntity, String.class);
		 
		 System.out.println(formEntity);
	}
}
