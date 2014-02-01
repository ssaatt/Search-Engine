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



3)  PageRank

4)  Searcher

5)  Web Server. 

