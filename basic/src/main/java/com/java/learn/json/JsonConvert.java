package com.java.learn.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonConvert {

	private static Book book = new Book("1", "susan");
	private static String jsonStr = "{\"id\":\"2\",\"name\":\"Json技术\"}";
	private static String jsonArrayStr = "[{\"id\":\"2\",\"name\":\"Json技术\"},{\"id\":\"3\",\"name\":\"java技术\"}]";

	private static void gsonConvert() {
		// JavaBean -> jsonStr
		Gson gson = new Gson();
		String jsonStr1 = gson.toJson(book);
		System.out.println("JavaBean to jsonStr:" + jsonStr1);

		// jsonStr -> JavaBean
		Book book2 = gson.fromJson(jsonStr, Book.class);
		System.out.println("jsonStr to bean:" + book2);

		// jsonStr -> List<T>
		List<Book> bookList = gson.fromJson(jsonArrayStr, new TypeToken<List<Book>>() {}.getType());
		System.out.println("jsonStr to list:" + bookList);

		// jsonStr -> Set<T>
		Set<Book> bookSet = gson.fromJson(jsonArrayStr, new TypeToken<Set<Book>>() {}.getType());
		System.out.println("jsonStr to set:" + bookSet);

		// 通过json对象直接操作json以及一些json的工具
		// a)格式化Json
		Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonArrayStr);
		String json = gson.toJson(je);

		// b)判断字符串是否是json,通过捕捉的异常来判断是否是json
		boolean jsonFlag;
		try {
			new JsonParser().parse(jsonArrayStr).getAsJsonObject();
			jsonFlag = true;
		} catch (Exception e) {
			jsonFlag = false;
		}
		// c)从json串中获取属性
		try {
			String propertyName = "id";
			String propertyValue = "";
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(jsonStr);
			JsonObject jsonObj = element.getAsJsonObject();
			propertyValue = jsonObj.get(propertyName).toString();
		} catch (Exception e) {
		}
		// d)除去json中的某个属性
		try {
			String propertyName = "id";
			String propertyValue = "";
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(jsonStr);
			JsonObject jsonObj = element.getAsJsonObject();
			jsonObj.remove(propertyName);
			json = jsonObj.toString();
		} catch (Exception e) {
		}

		// e)向json中添加属性
		try {
			String propertyName = "desc";
			Object propertyValue = "json各种技术的调研";
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(jsonStr);
			JsonObject jsonObj = element.getAsJsonObject();
			jsonObj.addProperty(propertyName, new Gson().toJson(propertyValue));
			json = jsonObj.toString();
		} catch (Exception e) {
		}

		// f)修改json中的属性 先删除后增加
		try {
			String propertyName = "name";
			Object propertyValue = "C技术";
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(jsonStr);
			JsonObject jsonObj = element.getAsJsonObject();
			jsonObj.remove(propertyName);
			jsonObj.addProperty(propertyName, new Gson().toJson(propertyValue));
			json = jsonObj.toString();
		} catch (Exception e) {
		}

		// g)判断json中是否有属性
		try {
			String propertyName = "name";
			boolean isContains = false;
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(jsonStr);
			JsonObject jsonObj = element.getAsJsonObject();
			isContains = jsonObj.has(propertyName);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// h)json中日期格式的处理 然后使用gson对象进行json的处理，如果出现日期Date类的对象，就会按照设置的格式进行处理
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Gson gson3 = builder.create();

		// i)json中对于Html的转义 这种对象默认对Html进行转义，如果不想转义使用下面的方法
		GsonBuilder builder2 = new GsonBuilder();
		builder2.disableHtmlEscaping();
		Gson gson4 = builder.create();

	}

	private static void fastJsonConvert() {
		/*SerializeWriter：相当于StringBuffer
		JSONArray：相当于List<Object>
		JSONObject：相当于Map<String, Object>
		JSON反序列化没有真正数组，本质类型都是List<Object>*/
		
		
		// JavaBean -> jsonStr
		String jsonStr1 =  JSON.toJSONString(book, true);
		System.out.println("JavaBean to jsonStr:" + jsonStr1);
		
		// JavaBean -> jsonStr 将对象转换成非格式化的json
		String jsonStr2 =  JSON.toJSONString(book, false);
		System.out.println("JavaBean to jsonStr:" + jsonStr2);

		// 对于复杂类型的转换,对于重复的引用在转成json串后在json串中出现引用的字符,比如 $ref":"$[0].books[1]。
		Student stu = new Student();
		Set<Book> tmpSet= new HashSet<Book>();
		tmpSet.add(book);
		stu.setBooks(tmpSet);
		
		List<Student> stuList = new ArrayList<Student>();
		for(int i=0;i<5;i++) {
			stuList.add(stu);
		}
		
		String json = JSON.toJSONString(stuList, false);
		System.out.println("JavaBean to jsonStr:" + json);

		json = JSON.toJSONString(stuList, SerializerFeature.DisableCircularReferenceDetect);
		// 或者全局设置 JSON.DEFAULT_GENERATE_FEATURE = SerializerFeature.DisableCircularReferenceDetect.getMask();
		System.out.println("JavaBean to jsonStr(close refer):" + json);
		
		// jsonStr -> bean
		Book book2 = JSON.parseObject(jsonStr, Book.class);
		System.out.println("jsonStr to bean:" + book2);
		Book book3 = JSON.parseObject(jsonStr, new TypeReference<Book>(){});
		System.out.println("jsonStr to bean:" + book3);
		
		// jsonStr -> map
		Map<String, String> bookMap = (Map<String, String>)JSON.parse(jsonStr);  
		System.out.println("jsonStr to map:" + bookMap);
		
		// map -> jsonStr
		String jsonStr3 = JSON.toJSONString(bookMap);
		System.out.println("map to jsonStr:" + jsonStr3);
		
		// jsonStr -> JSONObject
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		System.out.println("jsonStr to JSONObject:" + jsonObject);

		// JSONObject -> jsonStr
		String jsonStr4 = jsonObject.toJSONString();
		System.out.println("jsonObject to jsonStr:" + jsonStr4);
				
		// jsonArrayStr -> Array
        Book[] books = JSON.parseObject(jsonArrayStr,new TypeReference<Book[]>(){});
        System.out.println("jsonArrayStr to array:" + StringUtils.join(books, ", "));
        
        // Array -> jsonArrayStr
        String jsonArrayStr3 = JSON.toJSONString(books, true); 
        System.out.println("array to jsonArrayStr:" + jsonArrayStr3);

		// jsonArrayStr -> List<T>
		List<Book> bookList = JSON.parseObject(jsonArrayStr, new TypeReference<ArrayList<Book>>(){});
		System.out.println("jsonArrayStr to list:" + bookList);
		
		List<Book> bookList2 = JSON.parseArray(jsonArrayStr, Book.class);
		System.out.println("jsonArrayStr to list:" + bookList2);

		//List -> jsonArrayStr
        String jsonArrayStr2 = JSON.toJSONString(bookList, true); 
        System.out.println("list to jsonArrayStr:" + jsonArrayStr2);

		// jsonArrayStr -> Set<T>
		Set<Book> bookSet = JSON.parseObject(jsonArrayStr, new TypeReference<HashSet<Book>>(){});
		System.out.println("jsonArrayStr to set:" + bookSet);
		
		// jsonArrayStr -> JSONArray
		JSONArray jsonArray = JSONArray.parseArray(jsonArrayStr);
		System.out.println("jsonStr to JSONArray:" + jsonArray);

		
		//将JavaBean转换为JSONObject或者JSONArray
	/*	JSONObject jsonObject2 = JSONObject.toJSON(book);
		System.out.println("JavaBean to JSONObject:" + jsonArray);
		*/
	    
		// JSONObject -> javaBean
        Book book4 = (Book)JSONObject.toJavaObject(jsonObject, Book.class);  
        System.out.println("JSONObject to javaBean:" + book4);
        
        // javaBean -> JSONObject
        JSONObject jsonObject2 = (JSONObject)JSONObject.toJSON(book4);
        System.out.println("javaBean to JSONObject:" + jsonObject2);
        
        // JSONArray -> list<T>  
        List<Book> booksList2 = JSONArray.parseArray(jsonArrayStr, Book.class);  
        System.out.println("JSONArray to list:" + booksList2);
        
        // list<T> -> JSONArray
        JSONArray jsonArray2 = (JSONArray) JSONArray.toJSON(booksList2);  
        System.out.println("list to JSONArray:" + jsonArray2);

		// 通过json对象直接操作json以及一些json的工具
		// a)从json串中获取属性
		try {
			String propertyName = "id";
			Object propertyValue = "";
			JSONObject obj = JSON.parseObject(jsonStr);
			propertyValue = obj.get(propertyName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// b)除去json中的某个属性
		try {
			String propertyName = "id";
			boolean success;
			JSONObject obj = JSON.parseObject(jsonStr);
			Set set = obj.keySet();
			success = set.remove(propertyName);
			json = obj.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// c)向json中添加属性
		try {
		String propertyName = "desc";
		Object propertyValue = "json的玩意儿";
		JSONObject obj = JSON.parseObject(jsonStr);
		obj.put(propertyName, JSON.toJSONString(propertyValue));
		json = obj.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//d)修改json中的属性
		try {
			String propertyName = "name";
			Object propertyValue = "json的玩意儿";
			JSONObject obj = JSON.parseObject(jsonStr);
			Set set = obj.keySet();
			if(set.contains(propertyName)) {
				obj.put(propertyName, JSON.toJSONString(propertyValue));
			}
			json = obj.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// e)判断json中是否有属性
		try {
			String propertyName = "name";
			boolean isContain = false;
			JSONObject obj = JSON.parseObject(jsonStr);
			Set set = obj.keySet();
			isContain = set.contains(propertyName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// f)json中日期格式的处理 使用JSON.toJSONStringWithDateFormat,该方法可以使用设置的日期格式对日期进行转换
		try {
			Object obj = new Date();
			JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss.SSS");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		System.out.println("-----------------start fastjson-----------");
		fastJsonConvert();
		System.out.println("-----------------start gson-----------");
		gsonConvert();
	}

}
