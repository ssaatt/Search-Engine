package com.search.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.IOUtils;

public class File_Page extends File_WriteRead{

	Reader input;
	
	public File_Page(String pageFileName) {
		super(pageFileName);
	}

	public void initReader(){
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writePage(String content) {
		writeStringToFile(content);
	}
	
	public String readPage(int length){
		char[] buffer = new char[length];
		try {
			IOUtils.readFully(input, buffer, 0, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new StringBuffer().append(buffer).toString();
	}
	
	public void close(){
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
