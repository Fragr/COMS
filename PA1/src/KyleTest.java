import java.io.IOException;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class KyleTest {

    public static void test() throws IOException, InterruptedException {
        String[] test = {};
        WikiCrawler wc = new WikiCrawler("/wiki/Complexity", 100, test, new String());
        wc.crawl(false);
//        PriorityQ pq = new PriorityQ();
    }
}
