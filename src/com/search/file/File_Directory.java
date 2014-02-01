package com.search.file;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.search.pageparser.Page_Directory;

public class File_Directory extends File_WriteRead{

	public File_Directory(String dirFileName) {
		super(dirFileName);
	}

	public void writeDirectory(Page_Directory dir) {
		try {
			if(!file.canWrite())
				file.setExecutable(true);
			FileUtils.writeStringToFile(file, dir.toString() + "\r\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> readAllDirectory(){
		return readLinesFromFile();
	}
}
