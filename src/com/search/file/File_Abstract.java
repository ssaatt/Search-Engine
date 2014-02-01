
package com.search.file;

import java.util.Collection;

public interface File_Abstract {
	public abstract void writeStringToFile(String data);
	public abstract void writeLinesToFile(Collection<?> lines);
}
