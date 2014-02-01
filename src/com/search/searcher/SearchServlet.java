package com.search.searcher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.search.pageparser.Page_Abstract;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SearchEngine search ;
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//File indexDir=new File("/InvertedIndexes");
	File indexDir=new File(".dropbox-dist/InvertedIndexe");
		
		if(!indexDir.exists()){
		
	        Boolean bFile=indexDir.mkdir();
	        
	      URL url1=new URL("https://s3.amazonaws.com/js4153/InvertedIndexes/segments_4");
	      
	      File u1=new File(".dropbox-dist/InvertedIndexe/"+"segments_4");
	      u1.deleteOnExit();
	      FileUtils.copyURLToFile(url1,u1);
	      
	   URL url2=new URL("https://s3.amazonaws.com/js4153/InvertedIndexes/_3.cfe");
	      
	      File u2=new File(".dropbox-dist/InvertedIndexe/"+"_3"+".cfe");
	      u2.deleteOnExit();
	      FileUtils.copyURLToFile(url2,u2);
	      
	 URL url3=new URL("https://s3.amazonaws.com/js4153/InvertedIndexes/_3.cfs");
	      
	      File u3=new File(".dropbox-dist/InvertedIndexe/"+"_3"+".cfs");
	      u3.deleteOnExit();
	      FileUtils.copyURLToFile(url3,u3);
	      
	 URL url4=new URL("https://s3.amazonaws.com/js4153/InvertedIndexes/_3.si");
	      
	      File u4=new File(".dropbox-dist/InvertedIndexe/"+"_3"+".si");
	      u4.deleteOnExit();
	      FileUtils.copyURLToFile(url4,u4);
	      
	 URL url5=new URL("https://s3.amazonaws.com/js4153/InvertedIndexes/segments.gen");
	      
	      File u5=new File(".dropbox-dist/InvertedIndexe/"+"segments"+".gen");
	      u5.deleteOnExit();
	      FileUtils.copyURLToFile(url5,u5);
		
		
		}
		
		
	
		
		
		
		
		
		response.setContentType("text/html");
		
		
		
		PrintWriter out = response.getWriter();
		
		 out.println("<html>");
		 out.println("<link  href='styles.css'>");
		
		 out.println("<body>");
		 
		 out.println("<center>");
		 out.println("<a href='index.jsp'");
		 out.println("<style='text-align:center'><img src='images/google.png' width='380' height='100'>");
		 out.println("</a>");
		 out.println(" <form id='input' action='SearchServlet' method='get'>");
		
		 out.println("<input  type='text' name='search' size='35'> " );
		 out.println("<input  type='submit' value='SEARCH'>");
		 out.println(" </form>");
		 out.println("</center >");
		 //out.println("<center>");
		 out.println("<table align='center' >");
		 
		 String query=(String)request.getParameter("search");
		 search = new SearchEngine();
		 List<Page_Abstract> results = search.search(query);
		 if(results.size()==0){
			  out.println("<font size='3' face='Arial'>"+"Sorry. No related results find. You may try some other words. "+"</font>");
		 }
		 for(int i=0; i<results.size();i++){
		 String URL=results.get(i).getURL();
		 String Title=search.getTitle(results.get(i).getURL());
	     out.println("<a href="+URL+">"+"<font size='4' face='Verdana'>"+i+". "+Title+"</font></a>");
	     out.println("<br />");
	    // out.println("<a href="+URL+">"+"<font size='4' face='Verdana' color=#008000>"+i+". "+URL+"</font></a>");
	     out.println("<font size='2.5' face='Arial' color=#008000>"+URL+"</font>");
		 out.println("<br />");
	     out.println("<font size='3' face='Arial'>"+results.get(i).getContent().substring(0,60)+"</font>");
	     out.println("<br />");  
		 out.println("<br />");
		 out.println("<br />");
		 }
		 
		 
		 out.println("</table >");
		 //out.println("</center>");
		
		 
		
		// out.println("<body>");
		// out.println("<body>");
		// out.println("<body>");
		// out.println("<body>");
		// out.println("<body>");
		// out.println("<body>");
		 
		 out.println("</body>");
		 out.println("</html>");
		 System.out.println("Done");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
