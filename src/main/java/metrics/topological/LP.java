package metrics.topological;

import beans.Edge;
import beans.Network;
import beans.Node;
import com.google.common.collect.Sets;
import configs.RunConfig;
import metrics.Metrics;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Set;

/**
 * Created by paranoidq on 16/4/27.
 */
public class LP extends Metrics{

    private static final double alpha = RunConfig.LP.alpha;

    public LP(Network groundNetwork) {
        super(groundNetwork);
    }

    @Override
    public double metrics(Edge e) {
        double hop2CNs = CollectionUtils.intersection(groundNetwork.getNeighbors(e.getNode1()),
                groundNetwork.getNeighbors(e.getNode2())).size();
        double hop3CNs;
        Set<Node> cn = groundNetwork.getNeighbors(e.getNode1());
        Set<Node> cnCn = Sets.newHashSet();
        for (Node n : cn) {
            Set<Node> nodes = groundNetwork.getNeighbors(n);
            for (Node hop2Cn : nodes) {
                if (!cn.contains(hop2Cn)) { // 不能回到1跳邻居的节点中形成内部的环
                    cnCn.add(hop2Cn);
                }
            }
        }
        hop3CNs = CollectionUtils.intersection(cnCn, groundNetwork.getNeighbors(e.getNode2())).size();
        return hop2CNs + alpha * hop3CNs;
    }
}
