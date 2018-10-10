import java.util.Comparator;

public class PriorityComparator<T extends Node> implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        T n1 = (T) o1;
        T n2 = (T) o2;
        if(n2.getPriority() < n1.getPriority()) {
            return -1;
        }
        else if(n2.getPriority() > n1.getPriority()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
