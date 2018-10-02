import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
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
    public WikiCrawler(String seed, int max, String[] topics, String output) throws FileNotFoundException {
        File testDoc = new File(".\\wiki_Iowa_State_University.html");
        Scanner sc = new Scanner(testDoc).useDelimiter("<p>");
        // OG ReGex - "<\\s*a[^>]*>(.*?)<\\s*/\\s*a>"
        Pattern p = Pattern.compile("<a[^>]+href=[\\\"']?([\\\"'>]+)[\\\"']?[^>]*>(.+?)<\\/a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Matcher m = p.matcher(line);
            while(m.find()){
                System.out.println(m.group(0));
            }
        }
    }

    /**
     * Takes as input a document representing an entire HTML document.
     * Returns a list of strings consisting of links from the document.
     * @param document
     * @return
     */
    private ArrayList<String> extractLinks(String document) {
        return null;
    }

    /**
     * Crawls/explores the web pages starting from the seed URL. Crawl the first max
     * number of pages (including the seed page), that contains every keywords in\
     * the Topics list (if Topics list is empty then this condition is vacuously considered true),
     * and are explored starting from the seed.
     * @param focused
     * @return
     */
    private int crawl(boolean focused) {
        return 0;
    }
}
