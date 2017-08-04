package net.tenie.test;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupLearn {
	public static void main(String[] args) throws Exception {
	//	Document doc = Jsoup.connect("http://t66y.com/htm_mob/8/1705/2420439.html").get();
		Document doc =Jsoup.parse(new File("/Users/tenie/Desktop/1024.html"), "utf-8");
		//String title = doc.title();
		//System.out.println( doc);
		Elements e =doc.select("input[type='image']");
	 
		System.out.println(e.size());
		for(Element i :e){
			
			System.out.println(i.attr("src"));
		}
		
		//
	}
}
