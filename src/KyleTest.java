import java.io.IOException;

/**
 * @author Peter DeBisschop (pjd), Kyle Zelnio (kjzelnio)
 */

public class KyleTest {

    public static void test() throws IOException, InterruptedException {
        String[] test = {};
        WikiCrawler wc = new WikiCrawler("/wiki/Complexity_theory", 100, test, new String());
        wc.crawl(true);
//        PriorityQ pq = new PriorityQ();
    }
}
