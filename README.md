Search-Engine
=============

The project consists of the following components:

1)  Web Crawler

A crawler is a program that visits web sites and reads their pages and other information in order to create 
entries for a search engine index. It crawls through a site a page at a time, following the links to other pages 
on the site until all pages have been read. 

I use the Crwaler4j, an open source multi-thread web crawler , to serve as crawler. It could crawl back Textual Content from the web. 

It works by first setting the configurations for the crawler, for example, the number of crawlers, the crawl storage folder, the max depth of crawling, the max out link for one page and the connection time out. 

Then start with a seed URL, say http://www.msn.com/ or http://www.yahoo.com. As the crawler visits the URL, it identifies the hyperlinks in the page and adds them to the list of URLs to visit, called the crawl frontier. URLs from the frontier are recursively visited according to a set of polices. After Crawling, the pages retrieved from web will be stored in a folder called “CrawledPages” . Also, a directory for the Crawled Pages will be created, including the URL, the docID and the contentLength for each page.

<br>
2)  Indexer

An indexer parses the page retrieved by the web crawler , and analyzed the various elements, such as titles, page content etc, to create an index. An index is a special database that contains a complied version of the Web site content. 

I use the Apache Lucene, an open source information retrieval library, to serve as  indexer. Lucene is an inverted full-text index. It takes all the documents, splits them into words, and then builds an index for each word. Since the index is an exact string-match, unordered, it can be very fast.

The two key pieces for Lucene to create an index are Documents and Fields. A Document is a Java class that 
represents a searchable item in Lucene. It is the thing that you find when you search.  A Document is made 
of some Fields, each Field has a name and a value.

I will process page by page by referencing the previously created directory for Crawled Pages. For each page, first make the Document object, and then add three Fields, the pageID, the pageURL and the content to it. Once created the Document with those Fields, add it to the Lucene index writer and ask it to create our index. The created index directory is in the “InvertedIndexes” folder. From this point on we can search according to the indexed Fields for any of our Documents. <br>
<br>
3)  PageRank

Page Rank is an algorithm used by a search engine to rank websites in the search results. It is a way of measuring the importance of website pages. Google’ s PageRank works by counting the number and quality of links to a page to determine a rough estimate of how important the website is. The underlying assumption is that more import websites are likely to receive more links from other websites.
<br>
<br>
4)  Searcher

IndexReader class in Lucene is used to read the directory created in the indexer part. The name for the directory is “InvertedIndexes” in the code. 

The user first set a query, the query will be processed by the QueryParser in Lucene. Then the IndexSearcher class in Lucene is called to search the index. Finally, print out the returned results.

In order to get the Title for each Page, wrote a class called TitleExtractor.java. This class  could get the Page Title for a given URL. Since this class has to connect to the net for each search result. However, the time for searching will be longer. 

<br>
5)  Web Server. 

Create a bucket in AWS S3. 

Set CloudFront for this bucket. 

Designing a User Interface for the Search Engine.

COnfigure the Linux server. 

Deploy the project on AWS Elastic Beanstalk
<br>
<img src="https://s3.amazonaws.com/js4153/20.png">

<br>
<br>
<img src="https://s3.amazonaws.com/js4153/21.png">
