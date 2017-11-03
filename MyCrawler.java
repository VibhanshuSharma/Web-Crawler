


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.slf4j.LoggerFactory;




import com.csvreader.CsvWriter;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {
  
	/* private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|"
			 + "|mp3|mp3|zip|gz))$");*/
	 private static final Pattern FILTERS = Pattern.compile(
		        ".*(\\.(css|js|bmp|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v" +
		        "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	 //private static String[] crawlDomain;
	 //private static File crawlStorageFolder;
	 
	

	 /*public MyCrawler() {
			// TODO Auto-generated constructor stub
	    	myCrawlStat = new CrawlStat();
	}*/
	        
	/* public static void configure(String[] domain,
				String crawlStorageFolder) {
			// TODO Auto-generated method stub
			crawlDomain = domain;
	}*/
	 
			 @Override
	  public boolean shouldVisit(Page referringPage, WebURL url) {
			
			String href = url.getURL().toLowerCase();
			
			//String stat = String.valueOf(statusCode).toString();
			String URL = url.getURL();
			String stat = "";
			if(href.startsWith("https://www.usatoday.com/")){stat = "OK";}
			else{stat="N_OK";}
			
			if(href.startsWith("https://www.usatoday.com/") && !FILTERS.matcher(href).matches()){
				/*try {	
					CSVWriter writer = null;	
					String CSV = "C:\\Users\\Vibhanshu Sharma\\Downloads\\572\\third_file.csv";
					List<String[]> data = new ArrayList<String[]>();
					
					writer = new CSVWriter(new FileWriter(CSV,true));
			        data.add(new String[] {URL,stat});
					writer.writeAll(data);
					
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				return true;
			}
			return false;
		}
			 
			 /**
			  * This function is called when a page is fetched and ready
			  * to be processed by your program.
			  */
			  @Override
	public void visit(Page page) {
			  
			  String csv = "C:\\Users\\Vibhanshu Sharma\\Downloads\\572\\second_file_count.csv";
			  List<String[]> data = new ArrayList<String[]>();
			  int count=0,count1=0,count2=0,count3=0,count4=0;
			  
			  String url = page.getWebURL().getURL();
			 
			  int statusCode = page.getStatusCode();
			  String contentType = page.getContentType();
			  String stat = String.valueOf(statusCode).toString();
			  
			  try {
				  //FileWriter pw = new FileWriter("C:\\Users\\Vibhanshu Sharma\\Downloads\\572\\second_file.csv");
				 //pw.write(url);
				  CsvWriter writer = new CsvWriter(new FileWriter(csv,true),',');
	            //CSVWriter writer = new CSVWriter(new FileWriter(csv,true),',');
				//data.add(new String[] {url});
		
				writer.write(url);
			  if (page.getParseData() instanceof HtmlParseData) {
				  HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
				  String outgoingCount = htmlParseData.getHtml();
				  int fileSize = outgoingCount.length();
				  String file_size = String.valueOf(fileSize).toString();
				  
				  Set<WebURL> links = htmlParseData.getOutgoingUrls();
				  int outGoingSize = links.size();
				  String outCOunt = String.valueOf(outGoingSize).toString();
				  String text = htmlParseData.getText();
				  //myCrawlStat.incTotalLinks(links.size());	
				  
				  String outgoing = String.valueOf(outgoingCount).toString();
				  System.out.println("URL: " + url);
				  System.out.println("Text length: " + text.length());
				  System.out.println("# of outgoing links: " + outgoingCount);
				  System.out.println("content Type: " + contentType);
				  System.out.println("Status Code: " + statusCode);
				  System.out.println("=========================================\n");
				  
				  if(fileSize<1024){count++;}
					 else if(1024<fileSize && fileSize<10240){
						 count1++;
						 }
					 else if(10240<fileSize &&  fileSize<102400){count2++;}
					 else if(102400<fileSize && fileSize<104875){count3++;}
					 else if(fileSize>104875){count4++;}
				  writer.write(file_size);
				  writer.write(outCOunt);
				 // data.add(new String[] {file_size,outCOunt});
				 
				  
			 }else if(page.getParseData() instanceof BinaryParseData){
				 
				 BinaryParseData binaryParseData = (BinaryParseData) page.getParseData();
				 int outGoingSize = binaryParseData.getOutgoingUrls().size();
				 String outCOunt = String.valueOf(outGoingSize).toString();
				 int fSize= page.getContentData().length;
				 String file_size = String.valueOf(fSize).toString();
				 
				
				 
				 writer.write(file_size);
				  writer.write(outCOunt);
				//data.add(new String[] {file_size,outCOunt});
				
			 }
			  writer.write(contentType);
			  writer.endRecord();
			  writer.close();
			 // data.add(new String[] {contentType});
			 // writer.writeAll(data);
			  
			  //writer.close();
	      } catch (IOException e) {
	    	  // TODO Auto-generated catch block	
	    	  e.printStackTrace();
			}
			  System.out.println(count);
			  System.out.println(count1);
			  System.out.println(count2);
			  System.out.println(count3);
			  System.out.println(count4);
		}

			  @Override
	 protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {

				  String stat = String.valueOf(statusCode).toString();
				  String URL = webUrl.getURL();
			/*	  try {	
						CSVWriter writer = null;	
						String CSV = "C:\\Users\\Vibhanshu Sharma\\Downloads\\572\\first_file.csv";
						List<String[]> data = new ArrayList<String[]>();
						
						writer = new CSVWriter(new FileWriter(CSV,true));
				        data.add(new String[] {URL,stat});
						writer.writeAll(data);
						
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					*/}
			  //}
}			  

