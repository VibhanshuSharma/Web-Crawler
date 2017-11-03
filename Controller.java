
import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class Controller {
	
	/*private static final org.slf4j.Logger logger =
	        LoggerFactory.getLogger(Controller.class);
*/
		       
	public static void main(String[] args) throws Exception {
		
		String crawlStorageFolder = "data/crawl";
		 int numberOfCrawlers = 10;
		 CrawlConfig config = new CrawlConfig();
		 config.setCrawlStorageFolder(crawlStorageFolder);
		
		 config.setMaxDepthOfCrawling(16);
		 config.setMaxPagesToFetch(20000);
	  
		 /*
		 * Instantiate the controller for this crawl.
		 */
		// String[] crawlDoamin = {"http://www.nbcnews.com/"};
			
		 config.setIncludeBinaryContentInCrawling(true);
		 PageFetcher pageFetcher = new PageFetcher(config);
		 RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		 RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		 CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		// config.isResumableCrawling();
		 /*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		 
		 /*for(String domain: crawlDoamin){
		 controller.addSeed(domain);
		 }*/
		
		 controller.addSeed("https://www.usatoday.com/");
		 
		 /*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * * will reach the line after this only when crawling is finished.
		 */
		
		//MyCrawler.configure(crawlDoamin,crawlStorageFolder);
		controller.start(MyCrawler.class, numberOfCrawlers);
		
		List<Object> crawlersLocalData = controller.getCrawlersLocalData();
		/*long totalLinks = 0;
        long totalTextSize = 0;
        int totalProcessedPages = 0;
        for (Object localData : crawlersLocalData) {
            CrawlStat stat = (CrawlStat) localData;
            totalLinks += stat.getTotalLinks();
            totalTextSize += stat.getTotalTextSize();
            totalProcessedPages += stat.getTotalProcessedPages();
        }
        
        System.out.println("Aggregated Statistics:");
        System.out.println("\tProcessed Pages: {}"+ totalProcessedPages);
        System.out.println("\tTotal Links found: {}"+ totalLinks);
        System.out.println("\tTotal Text Size: {}"+ totalTextSize);
    */
	
 }
}
