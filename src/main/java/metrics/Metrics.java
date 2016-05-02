package metrics;

import beans.Edge;
import beans.Network;

/**
 * Created by paranoidq on 16/4/26.
 */
public abstract class Metrics {

    protected Network groundNetwork;


    public Metrics(Network groundNetwork) {
        this.groundNetwork = groundNetwork;
    }

    /**
     * SubClass can override this method to implement different prediction algorithms.
     * @return
     */
    public abstract double metrics(Edge e);


    /**
     * Get children's classNames as the metrics name.
     * @return
     */
    public String name() {
        return this.getClass().getSimpleName();
    }
}
