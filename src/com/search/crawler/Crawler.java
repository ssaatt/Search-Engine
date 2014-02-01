package com.search.crawler;

import com.search.constant.*;

import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.*;

public class Crawler {

	public Crawler() {

	}

	private static void setCrawlConfiguration(CrawlConfig config) {
		config.setCrawlStorageFolder(Constant.CRAWL_STORAGE_FOLDER);
		config.setPolitenessDelay(Constant.POLITENESS_DELAY_TIME);
		config.setMaxPagesToFetch(Constant.NUMBER_OF_PAGES_LIMIT);
		config.setMaxDepthOfCrawling(Constant.MAX_DEPTH_OF_CRAWLING);
		config.setMaxDownloadSize(Constant.MAX_DOWNLOAD_PAGE);
		config.setMaxOutgoingLinksToFollow(Constant.MAX_OUT_LINK);
		config.setConnectionTimeout(Constant.CONNECTION_TIME_OUT);
		config.setResumableCrawling(Constant.IS_RESUME);
		config.setUserAgentString(Constant.AGENT);
	}
	
	public void StartCrawl(String seedURL) {

		CrawlConfig config = new CrawlConfig();
		setCrawlConfiguration(config);	 
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);

		try {
			CrawlController controller = new CrawlController(config,
					pageFetcher, robotstxtServer);

			controller.addSeed(seedURL);
			controller.start(CrawlerFilter.class,
					Constant.NUMBER_OF_CRAWLERS);

			controller.shutdown();

			while (!controller.isFinished()) {
				controller.waitUntilFinish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
