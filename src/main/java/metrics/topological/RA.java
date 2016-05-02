package metrics.topological;

import beans.Edge;
import beans.Network;
import beans.Node;
import metrics.Metrics;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * Created by paranoidq on 16/4/27.
 */
public class RA extends Metrics {

    public RA(Network groundNetwork) {
        super(groundNetwork);
    }

    @Override
    public double metrics(Edge e) {
        double score = 0;
        Collection<Node> commonNeighbors = CollectionUtils.intersection(groundNetwork.getNeighbors(e.getNode1()), groundNetwork.getNeighbors(e.getNode2()));
        for (Node n : commonNeighbors) {
            int degree = groundNetwork.degree(n);
            score += (double)1 / degree;
        }
        return score;
    }
}
