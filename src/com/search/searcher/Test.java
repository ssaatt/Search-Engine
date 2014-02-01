package com.search.searcher;


import java.io.IOException;
import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;

import com.search.pageparser.Page_Abstract;

public class Test {

	public static void main(String[] args) throws IOException, ParseException {
		
		SearchEngine search = new SearchEngine();
		List<Page_Abstract> results = search.search("bloomberg");
		for(int i=0; i<results.size();i++){
		System.out.println(results.get(i).getPageId());
		System.out.println(results.get(i).getURL());
		//System.out.println(results.get(0).getContent());
		System.out.println(search.getTitle(results.get(i).getURL()));
		System.out.println();
		}
	}

}
