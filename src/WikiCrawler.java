import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class WikiCrawler {
    static final String BASE_URL = "https://en.wikipedia.org";
    //static final String BASE_URL = "http://web.cs.iastate.edu/~pavan";
    String seed;
    // Count is the current count of considered pages.
    int count = 0;
    int max;
    String[]topics;
    String output;

    ArrayList<Point> graph;
    PriorityQ priorityQueue;

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

        graph = new ArrayList<Point>();
        priorityQueue = new PriorityQ();
    }


    /**
     * Takes as input a document representing an entire HTML document.
     * Returns a list of strings consisting of links from the document.
     * @param document
     * @return finishedLinks
     */
    private ArrayList<String> extractLinks(String document) throws IOException, InterruptedException {
        // Increment Count by one for max pages
        count++;

        ArrayList<String> finishedLinks = new ArrayList<>();
        URL url = new URL(BASE_URL+document);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        boolean flag = false;

        Pattern urlPattern = Pattern.compile(
                "href=\"([^\"]*)\"",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        while((line = br.readLine()) != null){
            Matcher matcher = urlPattern.matcher(line);

            if( line.contains("<p>") ) flag = true;

            while(matcher.find() && flag){
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
                else{
                    wikiLink = matcher.group(0).replace("href=\"", "").replace("\"", ""); //Removes href="XXXX" where xxxx is the wiki link
                    finishedLinks.add(wikiLink);
                }
            }
        }
        br.close();
        //Thread.sleep((3 * 1000)/20);
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
     public int crawl(boolean focused) throws IOException, InterruptedException {
        //Starts the WikiCrawler from seed until max value
        ArrayList<String> seedLinks = extractLinks(seed);
        int limit;
        if (seedLinks.size() < max) limit = seedLinks.size();
        else limit = max;

        if(focused) {
            ArrayList<String> discovered = new ArrayList<String>();

            if( topics.length != 0 ){
                //Check the relevance of the seed first
                if( checkRelevance(seed) > 0 ){
                    priorityQueue.add(seed, checkRelevance(seed));
                    discovered.add(seed);

                    while( !priorityQueue.isEmpty() ) {
                        String source = priorityQueue.getValue(0);
                        ArrayList<String> newLinks = extractLinks(source);
                        priorityQueue.extractMax();


                        for( int i = 0; i < newLinks.size(); i++ ){
                            //Check the relevance of each new link first
                            if( checkRelevance(newLinks.get(i)) > 0 ){ // Means the page contains at least 1 mention of the topics included
                                if( discovered.size() < max )
                                    graph.add(new Point(source, newLinks.get(i)));
                                else if( discovered.size() >= max && discovered.contains(newLinks.get(i))){
                                    graph.add(new Point(source, newLinks.get(i)));
                                }
                            }
                        }

                        for(String s : newLinks) {
                            //Check the relevance of each new link first
                            if( checkRelevance(s) > 0 ){ // Means the page contains at least 1 mention of the topics included
                                if( !discovered.contains(s) && discovered.size() < max ) {
                                    priorityQueue.add(s, checkRelevance(s));
                                    discovered.add(s);
                                }
                            }
                        }
                    }

                    for( Point p : graph ){
                        output += p.toString() + "\n";
                    }

                    outputToFile(discovered.size());
                }else{
                    //TODO What to do if the seed has no relevance?
                }
            }else{
                //TODO Topics list is empty, but focused is still true. All priority = 0?
                System.out.println("TEST");
                priorityQueue.add(seed, 0);
                discovered.add(seed);

                while( !priorityQueue.isEmpty() ) {
                    String source = priorityQueue.getValue(0);
                    ArrayList<String> newLinks = extractLinks(source);
                    priorityQueue.extractMax();


                    for( int i = 0; i < newLinks.size(); i++ ){
                            if( discovered.size() < max )
                                graph.add(new Point(source, newLinks.get(i)));
                            else if( discovered.size() >= max && discovered.contains(newLinks.get(i))){
                                graph.add(new Point(source, newLinks.get(i)));
                            }
                    }

                    for(String s : newLinks) {
                        if( !discovered.contains(s) && discovered.size() < max ) {
                            priorityQueue.add(s, 0);
                            discovered.add(s);
                        }
                    }
                }

                for( Point p : graph ){
                    output += p.toString() + "\n";
                }

                outputToFile(discovered.size());
            }

        }else{
            ArrayList<String> queue = new ArrayList<String>();
            ArrayList<String> discovered = new ArrayList<String>();
            queue.add(seed);
            discovered.add(seed);

            if( topics.length != 0 ){
                while( !queue.isEmpty() ) {
                    String source = queue.get(0);
                    ArrayList<String> newLinks = extractLinks(source);
                    queue.remove(0);

                    for( int i = 0; i < newLinks.size(); i++ ){
                        //Check the relevance of each new link first
                        if( checkRelevance(newLinks.get(i)) > 0 ){ // Means the page contains at least 1 mention of the topics included
                            if( discovered.size() < max )
                                graph.add(new Point(source, newLinks.get(i)));
                            else if( discovered.size() >= max && discovered.contains(newLinks.get(i))){
                                graph.add(new Point(source, newLinks.get(i)));
                            }
                        }
                    }

                    for(String s : newLinks) {
                        //Check the relevance of each new link first
                        if( checkRelevance(s) > 0 ){ // Means the page contains at least 1 mention of the topics included
                            if( !discovered.contains(s) && discovered.size() < max ) {
                                queue.add(s);
                                discovered.add(s);
                            }
                        }
                    }
                }

                for( Point p : graph ){
                    output += p.toString() + "\n";
                }

                outputToFile(discovered.size());

            }else{
                while( !queue.isEmpty() ) {
                    String source = queue.get(0);
                    ArrayList<String> newLinks = extractLinks(source);
                    queue.remove(0);

                    for( int i = 0; i < newLinks.size(); i++ ){
                        if( discovered.size() < max
                                && !source.equals(newLinks.get(i))
                                && !graph.contains( new Point(source, newLinks.get(i))) )
                            graph.add(new Point(source, newLinks.get(i) ));
                        else if( discovered.size() >= max
                                && discovered.contains(newLinks.get(i))
                                && !source.equals(newLinks.get(i))
                                && !graph.contains( new Point(source, newLinks.get(i)) ) ){
                            graph.add(new Point(source, newLinks.get(i) ));
                        }
                    }

                    for(String s : newLinks) {
                        if( !discovered.contains(s) && discovered.size() < max ){
                            queue.add(s);
                            discovered.add(s);
                        }
                    }
                }

                //removeDuplicates();

                for( Point p : graph ){
                    output += p.toString() + "\n";
                }

                outputToFile(discovered.size());
            }
        }
        // TODO Crawl will call extract links from the initial seed extraction.
        // TODO focused explores in BFS when false
        // TODO focused, when true depends on relevance
        // TODO sleep 3sec after every 20 page crawls //Thread.sleep
        return 0;
    }

    /**
     * @param link String of the HTML page you are checking
     * @return relevance which counts the total # of times that a string
     *      in the topics list is on the page
     * @throws IOException
     */
    private int checkRelevance(String link) throws IOException {
        URL url = new URL(BASE_URL+link);
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        int relevance = 0;


        while((line = br.readLine()) != null){
            Scanner sc = new Scanner(line);
            while(sc.hasNext()){
                String current = sc.next();
                for(int i = 0; i < topics.length; i++){
                    if(current.equals(topics[i])){
                        relevance++;
                    }
                }
            }
        }
        System.out.println(relevance);
        br.close();
        return relevance;
    }

    private void removeDuplicates() {
        for( int i = 0; i < graph.size()-1; i++){
            Point tempPoint = graph.get(i);
            for(int j = 1; j < graph.size()-1; j++){
                if( tempPoint.equals(graph.get(j)) ){
                    graph.remove(j);
                    j--;
                }
            }
//             if(tempPoint.equals(graph.get(i+1))){
//                 tempPoint = graph.get(i);
//                 graph.remove(i+1);
//             } else {
//                 tempPoint = graph.get(i+1);
//             }
         }
    }

    /**
     * Writes the output global variable to a file
     * @param length of discover arraylist
     */
    private void outputToFile(int length) throws IOException {
        FileWriter writer = new FileWriter("WikiCC.txt");
        writer.write(length + "\r\n");
        writer.write(output);
        writer.close();
    }

    private void printList(ArrayList<String> list) {
         for( String s : list ){
             System.out.print(s + " ");
         }
         System.out.println();
    }
}



