package metrics.topological;

import beans.Edge;
import beans.Network;
import metrics.Metrics;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;

/**
 * Created by paranoidq on 16/4/27.
 */
public class Jaccard extends Metrics {

    public Jaccard(Network groundNetwork) {
        super(groundNetwork);
    }

    @Override
    public double metrics(Edge e) {
        return (double) CollectionUtils.intersection(groundNetwork.getNeighbors(e.getNode1()), groundNetwork.getNeighbors(e.getNode2())).size()
                / CollectionUtils.union(groundNetwork.getNeighbors(e.getNode1()), groundNetwork.getNeighbors(e.getNode2())).size();
    }
}
