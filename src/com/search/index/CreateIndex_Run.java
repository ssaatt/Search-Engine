package com.search.index;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

import com.search.constant.Constant;
import com.search.file.File_Index;
import com.search.pageparser.Page;
import com.search.pageparser.Page_Preparation;

import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateIndex_Run {
	private IndexWriter indexWriter;
	
	private IndexWriter createIndexWriter(boolean create) throws IOException {
		if (indexWriter == null) {
			Directory dir = FSDirectory.open(new File(Constant.INDEX_FILE));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);
			
			if (create) {
				// Create a new index in the directory, removing any previously indexed documents:
				iwc.setOpenMode(OpenMode.CREATE);
				} else {
				// Add new documents to an existing index:
				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
				}
			
			indexWriter = new IndexWriter(dir, iwc);	
		}
		return indexWriter;
	}

	
	private void closeIndexWriter() throws IOException {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}


	public Document parsePage(Page page) {
		if (page.getLength() > 0) {
			Document doc = new Document();
			Field pageID = new TextField("id", "" + page.getPageId(),Field.Store.YES);
			String url = page.getURL();
			Field pageURL = new TextField("url", url, Field.Store.YES);
			Field contentField = new TextField("content", page.getContent(),Field.Store.YES);
			
            /*List<String> boosters = getBoosters();
			
			if(boosters.contains(url)){
			 pageID.setBoost(2F);
		     pageURL.setBoost(2F);
		      contentField.setBoost(2F);
			}
			*/
			
			
			doc.add(pageID);
			doc.add(pageURL);
			doc.add(contentField);
			return doc;
		}
		return null;
	}

	
	public void create() throws CorruptIndexException,LockObtainFailedException, IOException {
              createIndexWriter(true);
              Page_Preparation parser = new Page_Preparation();
              parser.prepareReadPage();
              while (parser.hasNextPage()) {
	          Page page = parser.getNextPage();     
	          if(page != null){	        	  
		      Document doc = parsePage(page);		       
		      if (doc != null) {
		     indexWriter.addDocument(doc);
		       }		   
		    System.out.println(page.getPageId());
	    }	
     }
       parser.finalizeReadPage();
       closeIndexWriter();
    }	
	
	private static List<String> constructQuery(){
		List<String> results = new ArrayList<String>();
		results.add("Bloomberg");
		results.add("Blackrock");
		results.add("finance");
		results.add("stock");
		results.add("equity");
		results.add("asset");
		results.add("risk management");
		results.add("business");
		results.add("currency");
		results.add("investment");
		results.add("banking");
		results.add("trading");
		results.add("ETFs");
		results.add("MBA");
		results.add("Mutual Funds");
		results.add("money");
		results.add("economics");
		results.add("Bonds");
		results.add("Real Estate");
		results.add("portfolio management");
		results.add("tax");
		
		
		return results;
	}
	
	private List<String> getBoosters(){
		List<String> queries = constructQuery();
		List<String> results = new ArrayList<String>();
		
		for(String s:queries){
			
			File_Index handler = new File_Index("google/"+s);
			results.addAll( handler.readLinesFromFile() );
		}
		return results;
	}
	
	
	
	public static void main(String[] args) {
		CreateIndex_Run test = new CreateIndex_Run();
		try {
			test.create();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
