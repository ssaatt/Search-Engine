package com.search.pageparser;

public class PageURL extends Page_Basic implements Page_Abstract{

	private String url;
	private String content;
	
	public PageURL(int id, String url, String content){
		super(id);
		this.url = url;
		this.content = content;
	}
	
	@Override
	public String getURL(){
		return this.url;
	}	

	@Override
	public String getContent() {
		return this.content;
	}
}
