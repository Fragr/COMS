import java.io.IOException;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class KyleTest {

    public static void test() throws IOException {
        String[] test = {"known"};
        WikiCrawler wc = new WikiCrawler("/wiki/A.html", 6, test, null);
        wc.crawl(false);
//        PriorityQ pq = new PriorityQ();
    }
}
