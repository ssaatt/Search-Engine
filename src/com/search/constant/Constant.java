package com.search.constant;


import java.util.regex.Pattern;

public class Constant {
	// set the filter of crawler target
	public final static Pattern FILTERS = 
			Pattern.compile(".*(\\.(data|test|java|c|cpp|cc|z|rpm|uai|css|pfm|pptx|ppt|doc|docx|pdf|js|bmp|gif|jpe?g" 
                    + "|png|tiff?|mid|mp2|mp3|mp4"
                    + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
                    + "|rm|smil|wmv|swf|wma|zip|rar|tbz2|gz|icsgz))$");
	
	//word filter for tokenizer
    public static final Pattern WORD_FILTER = Pattern.compile("\\W+");
	
	// Max number of outgoing links which are processed from a page                  
	public final static int MAX_OUT_LINK = 100; 
	public final static int MAX_DOWNLOAD_PAGE = 100000;
	
	// numberOfCrawlers shows the number of concurrent threads that should be initiated for crawling.
	public final static int NUMBER_OF_CRAWLERS = 16;
		
	//depth of crawling, which is 0 for seed page and -1 for infinity
	public final static int MAX_DEPTH_OF_CRAWLING = 100;
		
	//politenessDelay is the delay of crawler's frequency
	public final static int POLITENESS_DELAY_TIME = 300;
		
	// numberOfPagesLimit shows number of pages can be crawled
	public final static int NUMBER_OF_PAGES_LIMIT = -1;
		
	// thread sleep time
	public final static int THREAD_SLEEP_TIME = 10 * 1000;
		
	// set the connection time out time 
	public final static int CONNECTION_TIME_OUT = 30*1000;		
		
	//number of inverted index files
	public static final char INDEX_FILE_SIZE = 1;
		
	// whether resume crawl is allowed
	public final static boolean IS_RESUME = false;
		
	// set crawler results Storage Folder
	public final static String CRAWL_STORAGE_FOLDER = "data/crawl/root";
	
	//set the name of crawler seen by web server
	public final static String AGENT= "Columbia crawler";
	
	//set the name of file which stores all pages
	public final static String PAGE_FILE = "CrawledPages/Page";

	public static final String DIRECTORY_FILE = "directory_CrawledPages";
	
	public static final String INDEX_FILE = "InvertedIndexes";

}
