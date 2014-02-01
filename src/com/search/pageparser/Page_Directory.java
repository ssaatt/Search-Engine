package com.search.pageparser;

public class Page_Directory {
	private int docID;
	private int contentLength;
	private String url;

	private String line;
	private int index;
	private int index2;
	
	
	public Page_Directory(String line){
		this.line = line;
	}

	public Page_Directory(int docId, int contentLength, String url){
		this.docID = docId;
		this.contentLength = contentLength;
		this.url = handleURL(url);
	}

	public String getURL(){
		return this.url;
	}
	public int getDocId(){
		return this.docID;
	}
	public int getContentLentgh(){
		return this.contentLength;
	}
	
	private String handleURL(String url) {
		url = url.replaceAll(" ", "");
		url = url.replaceAll("\r", "");
		url = url.replaceAll("\n", "");
		url = url.replaceAll("\\r\\n", "");
		return url;
	}
	
	
	public void setInfo(){
		String[] info = line.trim().split(" ");
		docID = Integer.parseInt(info[0]);
		contentLength = Integer.parseInt(info[1]);
		url = info[2];
	}

	public boolean validateLine(){
		index = line.indexOf(' ');
		if(index == -1)
			return false;
		index2 = line.indexOf(' ', index+1);
		if(index2 == -1)
			return false;
		if(index > index2)
			return false;
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.docID + " " + this.contentLength + " " + this.url;
	}
}
