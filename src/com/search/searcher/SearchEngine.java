package com.search.searcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.search.constant.Constant;
import com.search.pageparser.PageURL;
import com.search.pageparser.Page_Abstract;

import java.io.File;
import java.net.URL;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchEngine{
	
	 private IndexSearcher searcher;
     private QueryParser parser;
     private AmazonS3           s3;
     
     
     public SearchEngine(){
        try {
        //	AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();	 
        //	s3     = new AmazonS3Client(credentialsProvider); 
        // 	GetObjectRequest getObjectRequest = new GetObjectRequest("js4153","InvertedIndexes");
		//S3Object object=s3.getObject(getObjectRequest);	
		//InputStream objectData=object.getObjectContent();	
      
   IndexReader reader = DirectoryReader.open(FSDirectory.open((new File(".dropbox-dist/InvertedIndexe"))));
        //	IndexReader reader = DirectoryReader.open(FSDirectory.open((new File("D:\\InvertedIndexes"))));
		     searcher = new IndexSearcher(reader);
         } catch (CorruptIndexException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }

         Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
         parser = new QueryParser(Version.LUCENE_46, "content", analyzer);
 }
     
     
     private TopDocs performSearch(String queryString) throws IOException, ParseException{
         Query query = parser.parse(queryString);
         TopDocs hits = searcher.search(query,100);
      return hits;
      } 
     
     public String getTitle(String url) {
         if(url == null) return null;
         try {
                 return TitleExtractor.getPageTitle(url);
         } catch (IOException e) {
                 e.printStackTrace();
         }
         return null;
     }
	
	 public List<Page_Abstract> search(String query){
         if (query == null)
                 return null;
         List<Page_Abstract> docs = new ArrayList<Page_Abstract>();
         
         try {
                 TopDocs results = performSearch(query.toLowerCase());
                 System.out.println("Finish");
                 for (int i = 0; i < results.totalHits; i++) {
                        
                         try{
                        	 Document doc = searcher.doc(results.scoreDocs[i].doc);
                             String url = doc.get("url");
                                 int id = Integer.parseInt(doc.get("id"));
                                 String content = doc.get("content");
                                 PageURL page = new PageURL(id, url, content);
                                 docs.add(page);
                         }catch(Exception e){
                                 e.printStackTrace();
                         }
                 }
         } catch (IOException e) {
                 e.printStackTrace();
         } catch (ParseException e) {
                 e.printStackTrace();
         }
         return docs;
 } 
}
