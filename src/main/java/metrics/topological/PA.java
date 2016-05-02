package metrics.topological;

import beans.Edge;
import beans.Network;
import metrics.Metrics;

/**
 * Created by paranoidq on 16/4/27.
 */
public class PA extends Metrics{

    public PA(Network groundNetwork) {
        super(groundNetwork);
    }

    @Override
    public double metrics(Edge e) {
        return groundNetwork.degree(e.getNode1())
                * groundNetwork.degree(e.getNode2());
    }
}
