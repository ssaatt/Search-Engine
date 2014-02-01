package com.search.file;

import java.io.IOException;


public class File_Index extends File_WriteRead{

	public File_Index(String name) {
		super(name);
	}

	public boolean clearFile(){
		if(!file.canExecute())
			file.setExecutable(true);
		if(file.exists()){
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}else
			return false;
		return true;
	}

}
