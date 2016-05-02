package helper;

import beans.Edge;
import beans.EvalData;
import beans.Network;
import beans.Node;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import configs.RunConfig;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * Created by paranoidq on 16/4/26.
 */
public class EvaluationHelper {

    private static double PARTITION = RunConfig.TRAIN_PARTITION;
    private static double NEG_TRAIN_RATIO = RunConfig.NEG_TRAIN_RATIO;
    private static int SAMPLE_NODES = RunConfig.SAMPLE_NODES;


    public static EvalData config(Network network) {
        EvalData data = new EvalData();

        int posTrainSize = (int)(PARTITION * network.totalEdges());
        int negTrainSize = (int)(NEG_TRAIN_RATIO * posTrainSize);

        // config edges
        configPositiveEdges(posTrainSize, network, data);
        configNegativeEdges(negTrainSize, network, data);

        // config feats
        data.configFeats(network.getFeats());

        return data;
    }

    private static void configPositiveEdges(int posTrainSize, Network network, EvalData config) {
        List<Edge> edgeList = network.getEdgesAsList();
        Collections.shuffle(edgeList);

        Multimap<Node, Node> trainPosEdges = asMap(edgeList.subList(0, posTrainSize));
        Multimap<Node, Node> testPosEdges = asMap(edgeList.subList(posTrainSize + 1, edgeList.size()));

        config.configTrainPos(trainPosEdges).configTestPos(testPosEdges);
    }

    private static void configNegativeEdges(int negTrainSize, Network network, EvalData config) {
        Multimap<Node, Node> testPosEdges = config.getTestPosEdges();
        Object[] nodes =  testPosEdges.keys().toArray();
        Set<Node> selectedNodes = Sets.newHashSet();
        while (selectedNodes.size() < SAMPLE_NODES) {
            selectedNodes.add((Node)nodes[RandomUtils.nextInt(0, nodes.length)]);
        }

        // iterate negative test edges wrt. selected nodes
        Multimap<Node, Node> testNegEdges = HashMultimap.create();
        for (Node node : selectedNodes) {
            for (Node node2 : testPosEdges.keys()) {
                if (node != node2 && network.isNegativeEdge(node,node2)) {
                    testNegEdges.put(node, node2);
                    testNegEdges.put(node2, node);
                }
            }
        }
        config.configTestNeg(testNegEdges);

        // random generate negative train edges
        Set<Edge> trainNegEdges = Sets.newHashSet();
        while (trainNegEdges.size() < negTrainSize) {
            Node node = (Node)nodes[RandomUtils.nextInt(0, nodes.length)];
            Node node2 = (Node)nodes[RandomUtils.nextInt(0, nodes.length)];
            /*  trainNegEdges选边条件:
                1. not self-circled
                2. negative
                3. 没有被选入testNegEdges
            */
            if (node != node2
                    && network.isNegativeEdge(node, node2)
                    && (!testNegEdges.containsKey(node) || !testNegEdges.get(node).contains(node2))) {
                trainNegEdges.add(Edge.newEdge(node, node2));
            }
        }
        config.configTrainNeg(asMap(trainNegEdges));
    }


    private static Multimap<Node, Node> asMap(Collection<Edge> edges) {
        Multimap<Node, Node> map = HashMultimap.create();
        for (Edge edge : edges) {
            map.put(edge.getNode1(), edge.getNode2());
            map.put(edge.getNode2(), edge.getNode1());
        }
        return map;
    }

}
