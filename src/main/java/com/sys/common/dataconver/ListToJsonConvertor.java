package com.sys.common.dataconver;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ListToJsonConvertor {

		public static JSONArray convertorList(List list, String[] condition){
			JsonConfig config = new JsonConfig();  
			config.setExcludes(condition);  
	        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
	        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);  
	        config.registerDefaultValueProcessor(String.class,new DefaultDefaultValueProcessor());
	       // JSONArray jsonArray = new JSONArray();  
	        JSONArray jsonArray = JSONArray.fromObject(list,config); 
	        System.out.println("ListToJsonConvertor--json--test---"+jsonArray.toString());	
	        return jsonArray;
		}
		
		public static JSONObject convertorObject(Object ob, String[] condition){
			JsonConfig config = new JsonConfig();  
			config.setExcludes(condition);  
	        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
	        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);

	        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
	        config.registerDefaultValueProcessor(String.class,new DefaultDefaultValueProcessor());
	        JSONObject jo = new JSONObject();
	        jo = JSONObject.fromObject(ob,config);
	        System.out.println("ListToJsonConvertor--json--test---"+jo.toString());	
	        return jo;
		}
		
		public static JSONArray convertorString(String  json, String[] condition){
			JsonConfig config = new JsonConfig();  
			config.setExcludes(condition);  
	        JsonDateValueProcessor jsonValueProcessor = new JsonDateValueProcessor();  
	        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);  
	        JSONArray jsonArray = new JSONArray();  
	        jsonArray = jsonArray.fromObject(json,config); 
	        System.out.println("ListToJsonConvertor--json--test---"+jsonArray.toString());	
	        return jsonArray;
		}
}
