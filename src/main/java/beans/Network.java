package beans;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Network model
 *
 * For some methods, e.g. topological methods, just use network edges, without explicit training phrases.
 * For other methods, e.g. classification based, will have training phrase.
 *
 * Created by paranoidq on 16/4/26.
 */
public class Network {


    private Multimap<Node, Node> edges;
    private Multimap<Node, Feat> feats;

    private int edgesCount = -1;


    private Network(Multimap<Node, Node> edges, Multimap<Node, Feat> feats) {
        this.edges = edges;
        this.feats = feats;
    }

    public static Network newNetwork(Multimap<Node, Node> edges, Multimap<Node, Feat> feats) {
        return new Network(edges, feats);
    }


    /**
     * Get total Edges of network
     * @return
     */
    public int totalEdges() {
        assert edges.entries().size()%2 == 0;
        return edges.entries().size() / 2;
    }

    /**
     *
     * @return
     */
    public List<Edge> getEdgesAsList() {
        Set<Edge> edgesSet = Sets.newHashSet(); // for remove duplicate edges
        for (Map.Entry<Node, Node> entry : edges.entries()) {
            Edge edge = Edge.newEdge(entry.getKey(), entry.getValue());
            edgesSet.add(edge);
        }
        return Lists.newArrayList(edgesSet);
    }

    /**
     * Decide an edge contained in network?
     * @param node1
     * @param node2
     * @return
     */
    public boolean isNegativeEdge(Node node1, Node node2) {
        return !(edges.get(node1).contains(node2) || edges.get(node2).contains(node1));
    }


    /**
     * Get Neighbors
     * @param n
     * @return
     */
    public Set<Node> getNeighbors(Node n) {
        return edges.containsKey(n) ? (Set<Node>)edges.get(n) : Collections.emptySet();
    }


    /**
     * Get degree of node
     * @param n
     * @return
     */
    public int degree(Node n) {
        return edges.get(n).size();
    }

    /**
     * Return feats of network
     * @return
     */
    public Multimap<Node, Feat> getFeats() {
        return this.feats;
    }


    /**
     * If contains node in network
     * @param node
     * @return
     */
    public boolean containsNode(Node node) {
        return edges.containsKey(node);
    }

}
