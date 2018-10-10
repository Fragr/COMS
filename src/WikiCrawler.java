import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiCrawler {
//    static final String BASE_URL = "https://en.wikipedia.org";
    static final String BASE_URL = "http://web.cs.iastate.edu/~pavan";
    String seed;
    // Count is the current count of considered pages.
    int count = 0;
    int max;
    String[]topics;
    String output;

    /**
     *
     * @param seed related address of seed URL (within wiki domain)
     * @param max maximum number of pages to consider
     * @param topics arrays of strings representing keywords in a topic-list
     * @param output string representing the filename where the web graph over discovered pages are written
     */
    public WikiCrawler(String seed, int max, String[] topics, String output) throws IOException {
        this.seed = seed;
        this.max = max;
        this.topics = topics;
        this.output = output;

        //TODO Implement Crawler
//        System.out.println(extractLinks("/wiki/A.html")); //For testing specific pages
    }


    /**
     * Takes as input a document representing an entire HTML document.
     * Returns a list of strings consisting of links from the document.
     * @param document
     * @return finishedLinks
     */
    private ArrayList<String> extractLinks(String document) throws IOException {
        // Increment Count by one for max pages
        count++;

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
     public int crawl(boolean focused) throws IOException {
        //Starts the WikiCrawler from seed until max value
        ArrayList<String> seedLinks = extractLinks(seed);
        int limit;
        if (seedLinks.size() < max) limit = seedLinks.size();
        else limit = max;

        if(focused) {
//            relevance(seedLinks.get(i));
        }else{

            ArrayList<String> queue = new ArrayList<String>();
            ArrayList<String> discovered = new ArrayList<String>();
            queue.add(seed);
            discovered.add(seed);

            System.out.println(seed);

            while( !queue.isEmpty() && discovered.size() < max ) {
                ArrayList<String> newLinks = extractLinks(queue.get(queue.size()-1));
                printList(newLinks);

                for(String s : newLinks) {
                    if( !discovered.contains(s) ){
                        queue.add(s);
                        discovered.add(s);
                    }
                }
            }

            printList(queue);
            printList(discovered);

//            for(int i=0;i < limit; i++) {
//                System.out.println(seedLinks.get(i));
//                ArrayList<String> newlinks = extractLinks(seedLinks.get(i));
//                for(int j = 0; j < newlinks.size(); j++){
//                    System.out.println("\t - " + newlinks.get(j));
//                }
//            }
        }
        // TODO Crawl will call extract links from the initial seed extraction.
        // TODO focused explores in BFS when false
        // TODO focused, when true depends on relevance
        // TODO sleep 3sec after every 20 page crawls //Thread.sleep
        return 0;
    }


    public void relevance(String link) throws IOException {
        URL url = new URL(BASE_URL+link);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        int relevance = 0;


        while((line = br.readLine()) != null){
            Scanner sc = new Scanner(line);
            while(sc.hasNext()){
                String current = sc.next();
                for(int i =0; i<topics.length; i++){
                    if(current.equals(topics[i])){
                        relevance++;
                    }
                }

            }

        }
        System.out.println(relevance);
        br.close();
    }

    private void printList(ArrayList<String> list) {
         for( String s : list ){
             System.out.print(s + " ");
         }
         System.out.println();
    }
}



