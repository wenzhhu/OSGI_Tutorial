package org.osgi.book.reader.api;

import java.io.InputStream; 

public interface Message {
	long getId();
	
	String getSummary();
	
	String getMIMEType();
	
	InputStream getContent() throws MessageReaderException;
}
