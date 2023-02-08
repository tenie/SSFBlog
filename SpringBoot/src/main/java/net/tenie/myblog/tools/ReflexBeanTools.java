package net.tenie.myblog.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class ReflexBeanTools  {
	
	public static  HashMap<String, Object>  beanToMap(Class zlass, Object val)throws InstantiationException, IllegalAccessException  {
		return byField(zlass, val);
	}
	// 通过字段来获取
	public  static HashMap<String, Object> byField(Class zlass, Object val) throws InstantiationException, IllegalAccessException {
		HashMap<String, Object> rs = new HashMap<String, Object>();
		Field[] field =  zlass.getDeclaredFields();
		 
		for(int i = 0 ; i < field.length; i ++) {
			String name = field[i].getName();
			field[i].setAccessible(true);//设置data属性为可访问的
			Object v = field[i].get(val);
			rs.put(name, v); 
			
		}
		return rs;
	}
	
	 // 根据类名和类的字段生成bean对象
	  public static Object  buildObject(String classname, Map<String, Object> fieldname) throws  Exception {
		  Class<?> classobj = Class.forName(classname);
		  Object obj = classobj.newInstance(); 
		  Method[] methods = classobj.getMethods(); 
        for(int i = 0; i < methods.length; i++){  
	           Method method = methods[i];   
	           String methodName = method.getName();
	           if(methodName.startsWith("set")){ 
	        	  String key = methodName.substring(3, methodName.length()-1);
	        	  Object val = fieldname.get(key);
	        	  if(val !=null )
	        	     method.invoke(obj, val);
	           }  
	       } 
        return obj;
	  }
	
 
 
}
