package beans;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by paranoidq on 16/4/26.
 */
public class EvalData {


    private Multimap<Node, Node> trainPosEdges;
    private Multimap<Node, Node> trainNegEdges;

    private Multimap<Node, Node> testPosEdges;
    private Multimap<Node, Node> testNegEdges;

    private Multimap<Node, Feat> feats;

    private Network groundNetwork;

    private List<Edge> testPosEdgesAsList;
    private List<Edge> testNegEdgesAsList;



    public EvalData configTrainPos(Multimap<Node, Node> edges) {
        this.trainPosEdges = edges;
        return this;
    }

    public EvalData configTestPos(Multimap<Node, Node> edges) {
        this.testPosEdges = edges;
        return this;
    }


    public EvalData configTrainNeg(Multimap<Node, Node> edges) {
        this.trainNegEdges = edges;
        return this;
    }

    public EvalData configTestNeg(Multimap<Node, Node> edges) {
        this.testNegEdges = edges;
        return this;
    }

    public EvalData configFeats(Multimap<Node, Feat> feats) {
        this.feats = feats;
        return this;
    }


    public Multimap<Node, Node> getTrainPosEdges() {
        return this.trainPosEdges;
    }

    public Multimap<Node, Node> getTestPosEdges() {
        return this.testPosEdges;
    }

    public Multimap<Node, Node> getTrainNegEdges() {
        return this.trainNegEdges;
    }

    public Multimap<Node, Node> getTestNegEdges() {
        return this.testNegEdges;
    }


    public List<Edge> getTestPosEdgesAsList() {
        if (this.testPosEdgesAsList == null) {
            this.testPosEdgesAsList = asList(this.testPosEdges);
        }
        return testPosEdgesAsList;
    }

    public List<Edge> getTestNegEdgesAsList() {
        if (this.testNegEdgesAsList == null) {
            this.testNegEdgesAsList = asList(this.testNegEdges);
        }
        return testNegEdgesAsList;
    }

    private List<Edge> asList(Multimap<Node, Node> edges) {
        Set<Edge> edgesSet = Sets.newHashSet(); // for remove duplicate edges
        for (Map.Entry<Node, Node> entry : edges.entries()) {
            Edge edge = Edge.newEdge(entry.getKey(), entry.getValue());
            edgesSet.add(edge);
        }
        return Lists.newArrayList(edgesSet);
    }


    public Network getGroundNetwork() {
        if (this.groundNetwork == null) {
            this.groundNetwork = Network.newNetwork(trainPosEdges, feats);
        }
        return this.groundNetwork;
    }


}
