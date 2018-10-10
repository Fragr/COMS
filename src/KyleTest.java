import java.io.IOException;

public class KyleTest {

    public static void test() throws IOException {
        String[] test = {"known"};
        WikiCrawler wc = new WikiCrawler("/wiki/A.html", 6, test, null);
        wc.crawl(false);
//        PriorityQ pq = new PriorityQ();
    }
}
