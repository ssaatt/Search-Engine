package com.search.pageparser;

import java.util.List;

import com.search.constant.Constant;
import com.search.file.*;

public class Page_Preparation {
	private List<String> dirs;
	private File_Directory dirFile;
	
	private int flag;
	
	public Page_Preparation(){
		dirFile = new File_Directory(Constant.DIRECTORY_FILE);
		
	}
	
	public boolean hasNextPage(){
		return flag < dirs.size();
	}
	
	public void prepareReadPage(){
		dirs = dirFile.readAllDirectory();
		flag = 0;
	}

	public void finalizeReadPage(){
		dirs.clear();
	}
	public Page getNextPage(){
		Page page = null;

		Page_Directory directory = new Page_Directory(dirs.get(flag));
		directory.setInfo();
		int id = directory.getDocId();
		int length = directory.getContentLentgh();
		if(length > 0){
			String pageFileName = Constant.PAGE_FILE + id;
			File_Page pageFile = new File_Page(pageFileName);
			pageFile.initReader();
			String content = pageFile.readPage(length);
			page = new Page(directory, content);
			pageFile.close();
		}
		flag++;
		return page;
	}
}
