import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import metrics.Metrics;
import metrics.topological.CN;

/**
 * Created by paranoidq on 16/4/26.
 */
public class Test {


    public static void main(String[] args) {
        Multimap<Integer, Integer> a = HashMultimap.create();
        a.put(1, 2);
        a.put(1, 3);
        System.out.println(a.entries().size());
    }
}
