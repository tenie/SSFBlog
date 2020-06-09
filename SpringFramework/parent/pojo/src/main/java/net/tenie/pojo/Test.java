package net.tenie.pojo;

import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

public class Test {
	public static void main(String[] args) {
		new DB("university").open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/web", "dev1", "dev1");
		 
		LazyList<Model> l = new BlogTag().findAll();
		System.out.println("十多岁 "+l.size());
        
        new DB("university").close();
	}
}
