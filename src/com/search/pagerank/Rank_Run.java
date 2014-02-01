package com.search.pagerank;

import java.util.ArrayList;
import java.util.List;

import com.search.file.File_Index;
import com.search.pagerank.GoogleResults.Result;



public class Rank_Run {

	private static List<String> constructQuery(){
		List<String> results = new ArrayList<String>();
		//results.add("Bloomberg");
		//results.add("Blackrock");
		//results.add("finance");
		//results.add("stock");
		//results.add("equity");
		//results.add("asset");
		//results.add("risk management");
		//results.add("business");
		//results.add("currency");
		//results.add("investment");
		//results.add("banking");
		//results.add("trading");
		results.add("ETFs");
		results.add("MBA");
		results.add("Mutual Funds");
		results.add("money");
		results.add("economics");
		results.add("Bonds");
		results.add("Real Estate");
		results.add("portfolio management");
		results.add("tax");
		
		
		return results;
	}
	
	public static void main(String[] args)  {
		List<String> queries = constructQuery();
		for(String s:queries){
			StringBuffer buffer = new StringBuffer();
			buffer.append(s);
			Google search = new Google(buffer.toString());
			File_Index handler = new File_Index("google/"+s);
			List<Result> results = search.getReults();
			List<String> content = new ArrayList<String>();
			for(Result r:results) content.add(r.toString());
			handler.writeLinesToFile(content);
		}
		System.out.println("Done!");
	}
	
}
