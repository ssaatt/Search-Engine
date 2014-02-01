package com.search.file;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public abstract class File_Basic implements File_Abstract{

	public abstract void writeStringToFile(String data);
	public abstract void writeLinesToFile(Collection<?> lines);
	
	protected File file;
	public File_Basic(String name){
		file = new File(name);
		createFile();
	}
	
	private void createFile(){
		if( !file.exists() )
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
}
