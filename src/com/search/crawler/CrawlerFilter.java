package com.search.crawler;

import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.*;

import com.search.constant.*;
import com.search.file.File_Directory;
import com.search.file.File_Page;
import com.search.pageparser.Page_Directory;

public class CrawlerFilter extends WebCrawler {

	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL();
		if (href == null) {
			return false;
		} else {
			href = href.toLowerCase();
		}
		return !href.contains("?")
				&& !href.matches(".*(/[^/]+)\\1\\1/")
				&& !Constant.FILTERS.matcher(href).matches();
	}

	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			
			String pageContent = htmlParseData.getText();
			
			int pageContentLength = pageContent.length();
			Page_Directory line = new Page_Directory(docid, pageContentLength, url);
			File_Directory dirFile = new File_Directory(Constant.DIRECTORY_FILE);
			File_Page pageFile = new File_Page(Constant.PAGE_FILE + docid);
			dirFile.writeDirectory(line);
			pageFile.writePage(pageContent);
		}
		System.out.println(docid);
	}

}
