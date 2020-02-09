package net.tenie.web.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;

public class TestJson {
	 private static JsonGenerator jsonGenerator = null;
	 private static ObjectMapper objectMapper = null;
	 private static User user = null;

	 public static void writeEntity2Json() throws IOException {
	//  System.out.println("使用JsonGenerator转化实体为json串-------------");
	  // writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
	 // jsonGenerator.writeObject(user);
	  System.out.println();
	  System.out.println("使用ObjectMapper-----------");
	  // writeValue具有和writeObject相同的功能
	  objectMapper.writeValue(System.out, user);
	 }

	 public static void writeList2Json() throws IOException {
	  List<User> userList = new ArrayList<User>();
	  userList.add(user);
	  User u = new User();
	  u.setUid(10);
	  u.setUname("archie");
	  u.setUpwd("123");
	  userList.add(u);
	  objectMapper.writeValue(System.out, userList);
	 }

	 public static void writeMap2Json() {
	  try {
	   Map<String, Object> map = new HashMap<String, Object>();
	   User u = new User();
	   u.setUid(10);
	   u.setUname("archie");
	   u.setUpwd("123");
	   map.put("uid", u.getUid());
	   map.put("uname", u.getUname());
	   map.put("upwd", u.getUpwd());
	   System.out.println("jsonGenerator");
	   jsonGenerator.writeObject(map);
	   objectMapper.writeValue(System.out, map);
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }

	 public static void writeOthersJSON() {
	  try {
	   String[] arr = { "a", "b", "c" };
	   System.out.println("jsonGenerator");
	   String str = "hello world jackson!";
	   // byte
	   jsonGenerator.writeBinary(str.getBytes());
	   // boolean
	   jsonGenerator.writeBoolean(true);
	   // null
	   jsonGenerator.writeNull();
	   // float
	   jsonGenerator.writeNumber(2.2f);
	   // char
	   jsonGenerator.writeRaw("c");
	   // String
	   jsonGenerator.writeRaw(str, 5, 10);
	   // String
	   jsonGenerator.writeRawValue(str, 5, 5);
	   // String
	   jsonGenerator.writeString(str);
	   jsonGenerator.writeTree(JsonNodeFactory.instance.POJONode(str));
	   System.out.println();
	   // Object
	   jsonGenerator.writeStartObject();// {
	   jsonGenerator.writeObjectFieldStart("user");// user:
	   jsonGenerator.writeStringField("name", "jackson");// name:jackson
	   jsonGenerator.writeBooleanField("sex", true);// sex:true
	   jsonGenerator.writeNumberField("age", 22);// age:22
	   jsonGenerator.writeEndObject();
	   jsonGenerator.writeArrayFieldStart("infos");// infos:[
	   jsonGenerator.writeNumber(22);// 22
	   jsonGenerator.writeString("this is array");// this is array
	   jsonGenerator.writeEndArray();// ]
	   jsonGenerator.writeEndObject();// }
	   User u = new User();
	   user.setUid(5);
	   user.setUname("tom");
	   user.setUpwd("123");
	   user.setNumber(3.44);
	   // complex Object
	   jsonGenerator.writeStartObject();// {
	   jsonGenerator.writeObjectField("uid", u);// user:{bean}
	   jsonGenerator.writeObjectField("infos", arr);// infos:[array]
	   jsonGenerator.writeEndObject();// }
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }

	 /**
	  * JSON字符串转换为对象
	  */
	 public static void readJson2Entity() {
	  String json = "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}";
	  try {
	   User acc = objectMapper.readValue(json, User.class);
	   System.out.println(acc.getUid());
	   System.out.println(acc);
	  } catch (JsonParseException e) {
	   e.printStackTrace();
	  } catch (JsonMappingException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }

	 /**
	  * JSON转换为List对象
	  */
	 public static void readJson2List() {
	  String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
	    + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]";
	  try {
	   List<LinkedHashMap<String, Object>> list = objectMapper.readValue(
	     json, List.class);
	   System.out.println(list.size());
	   for (int i = 0; i < list.size(); i++) {
	    Map<String, Object> map = list.get(i);
	    Set<String> set = map.keySet();
	    for (Iterator<String> it = set.iterator(); it.hasNext();) {
	     String key = it.next();
	     System.out.println(key + ":" + map.get(key));
	    }
	   }
	  } catch (JsonParseException e) {
	   e.printStackTrace();
	  } catch (JsonMappingException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }

	 /**
	  * JSON转换为数组对象
	  */
	 public static void readJson2Array() {
	  String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
	    + "{\"uid\":2,\"uname\":\"sdfsdf\",\"number\":4745,\"upwd\":\"23456\"}]";
	  try {
	   User[] arr = objectMapper.readValue(json, User[].class);
	   System.out.println(arr.length);
	   for (int i = 0; i < arr.length; i++) {
	    System.out.println(arr[i]);
	   }
	  } catch (JsonParseException e) {
	   e.printStackTrace();
	  } catch (JsonMappingException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }

	 /**
	  * JSON转换为Map对象
	  */
	 public static void readJson2Map() {
	  String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+  
	  "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
	  try {
	   Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
	   System.out.println(maps.size());
	   Set<String> key = maps.keySet();
	   Iterator<String> iter = key.iterator();
	   while (iter.hasNext()) {
	    String field = iter.next();
	    System.out.println(field + ":" + maps.get(field));
	   }
	  } catch (JsonParseException e) {
	   e.printStackTrace();
	  } catch (JsonMappingException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }

	 public static void main(String[] args) {
	  user = new User();
	  user.setUid(5);
	  user.setUname("tom");
	  user.setUpwd("123");
	  user.setNumber(3.44);
	  objectMapper = new ObjectMapper();
	  try {
	  // jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
	    writeEntity2Json();
	   // writeMap2Json();
	   // writeList2Json();
	   // writeOthersJSON();
	   // readJson2Entity();
	   // readJson2List();
	//   readJson2Array();
	 //  readJson2Map();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
	}
