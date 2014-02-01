package com.search.crawler;

public class Crawler_Run {

	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		//crawler.StartCrawl("http://www.msn.com/");
		crawler.StartCrawl("http://www.yahoo.com/");
		System.out.println("Done!");
	}

}
