import java.io.IOException;

public class KyleTest {

    public static void test() throws IOException {
        String[] test = {"known"};
        WikiCrawler wc = new WikiCrawler("/wiki/70th_Infantry_Division_(United_Kingdom)", 1, test, null);
        wc.crawl(true);
        PriorityQ pq = new PriorityQ();
    }
}
