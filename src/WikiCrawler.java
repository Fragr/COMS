import java.util.ArrayList;

public class WikiCrawler {

    /**
     *
     * @param seed related address of seed URL (within wiki domain)
     * @param max maximum number of pages to consider
     * @param topics arrays of strings representing keywords in a topic-list
     * @param output string representing the filename where the web graph over discovered pages are written
     */
    public WikiCrawler(String seed, int max, String[] topics, String output) {

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
