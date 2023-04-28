package web;
import java.util.*;
import java.io.File;  
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  
public class webCrawler {
	public static void main(String[] args) {
				String url = "https://takeuforward.org/data-structure/insert-node-at-beginning-of-linked-list/";
				crawl(1,url,new ArrayList<String>());
        }
	public static void crawl(int level, String url, ArrayList<String>visited){
		if(level<=5) {
			Document doc = request(url,visited);
			
			if(doc!=null) {
				for(Element link: doc.select("a[href]")) {
					System.out.println();
					String next_link = link.absUrl("href");
					
					if(visited.contains(next_link) == false) {
						crawl(level++,next_link, visited);
					}
				}
			}
		}
	}
	private static Document request(String url , ArrayList<String>list) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			 	if(con.response().statusCode() == 200) {
			 		System.out.println("Link:" + url);
			 		System.out.println(doc.title());
			 		list.add(url);
			 		
			 		return doc;
			 	}
			 	return null;
		}
		catch(IOException e) {
			return null;
		}
	}

}
