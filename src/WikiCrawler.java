import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiCrawler {
    static final String BASE_URL = "https://en.wikipedia.org";
    /**
     *
     * @param seed related address of seed URL (within wiki domain)
     * @param max maximum number of pages to consider
     * @param topics arrays of strings representing keywords in a topic-list
     * @param output string representing the filename where the web graph over discovered pages are written
     */
    public WikiCrawler(String seed, int max, String[] topics, String output) throws IOException {
        //Starts the WikiCrawler from seed until max value
        ArrayList<String> seedLinks = extractLinks(seed);
        for(int i=0;i < max; i++) {
            extractLinks(seedLinks.get(i));
        }
        //TODO Implement Crawler
//        System.out.println(extractLinks("/wiki/Physics")); //For testing specific pages
    }

    /**
     * Takes as input a document representing an entire HTML document.
     * Returns a list of strings consisting of links from the document.
     * @param document
     * @return finishedLinks
     */
    private ArrayList<String> extractLinks(String document) throws IOException {
        ArrayList<String> finishedLinks = new ArrayList<>();
        URL url = new URL(BASE_URL+document);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;

        Pattern urlPattern = Pattern.compile(
                "href=\"([^\"]*)\"",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        while((line = br.readLine()) != null){
            Matcher matcher = urlPattern.matcher(line);
            while(matcher.find()){
                String wikiLink;
                // Removes links we dont care about
                if(matcher.group(0).contains("%")
                        || matcher.group(0).contains("php?")
                        || matcher.group(0).contains("#")
                        || matcher.group(0).contains(":")
                        || matcher.group(0).contains("wikipedia.org")
                        || matcher.group(0).contains("//")
                        || matcher.group(0).contains("/static/")
                        || matcher.group(0).contains("/w/"));
                else {
                    wikiLink = matcher.group(0).replace("href=\"", "").replace("\"", ""); //Removes href="XXXX" where xxxx is the wiki link
                    finishedLinks.add(wikiLink);
                }
            }
        }
        br.close();
        return finishedLinks;
    }

    /**
     * Crawls/explores the web pages starting from the seed URL. Crawl the first max
     * number of pages (including the seed page), that contains every keywords in\
     * the Topics list (if Topics list is empty then this condition is vacuously considered true),
     * and are explored starting from the seed.
     * @param focused
     * @return
     */
    public int crawl(boolean focused) {
        // TODO Crawl will call extract links from the initial seed extraction.
        // TODO focused explores in BFS when false
        // TODO focused, when true depends on relevance
        // TODO sleep 3sec after every 20 page crawls //Thread.sleep
        return 0;
    }
}
