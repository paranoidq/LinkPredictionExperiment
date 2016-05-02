package metrics.topological;

import beans.Edge;
import beans.Network;
import metrics.Metrics;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by paranoidq on 16/4/26.
 */
public class CN extends Metrics {

    public CN(Network groundNetwork) {
        super(groundNetwork);
    }

    @Override
    public double metrics(Edge e) {
        return CollectionUtils.intersection(
                groundNetwork.getNeighbors(e.getNode1()),
                groundNetwork.getNeighbors(e.getNode2())).size();
    }
}
