package com.search.pageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.search.constant.Constant;
import com.search.file.File_WriteRead;

public class Page implements Page_Abstract{
	private String content;
	private Page_Directory directory;

	
	public Page(Page_Directory dir, String content){
		this.directory = dir;
		this.content = content;
	}

	public int getPageId() {
		return this.directory.getDocId();
	}

	
	public String getURL() {
		return this.directory.getURL();
	}
	
	public int getLength() {
		return this.directory.getContentLentgh();
	}
	
	public List<String> getWords(){
		return filterStopWords( tokenizeString(content) );
	}
	
	public String getContent() {
		return this.content;
	}
	
	private List<String> tokenizeString(String str){
		List<String> tokens = 
				Arrays.asList( str.split(Constant.WORD_FILTER.pattern()) );
		return tokens;
	}
	
	public List<String> filterStopWords(List<String> words){
		List<String> stopWords = new ArrayList<String>();	
		List<String> filterWords = new ArrayList<String>();
		File_WriteRead f_WR = new File_WriteRead("stopwords");
		stopWords = f_WR.readLinesFromFile();
		int size = words.size();
		for(int i = 0; i < size; i++){
			String word = words.get(i).toLowerCase().trim();
			if(!stopWords.contains(word) && !word.equals(""))
				filterWords.add(word);
		}
		return filterWords;
	}
}
