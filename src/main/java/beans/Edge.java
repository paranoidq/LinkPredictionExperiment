package beans;


/**
 * Created by paranoidq on 16/4/26.
 */
public class Edge {

    private Node node1;
    private Node node2;

    private double score;


    public Node getNode1() {
        return this.node1;
    }

    public Node getNode2() {
        return this.node2;
    }

    private Edge(Node node1, Node node2) {
        if (node1.compareTo(node2) == 0) {
            throw new RuntimeException("Edge cannot be self circled.");
        }
        if (node1.compareTo(node2) > 0) {
            this.node1 = node2;
            this.node2 = node1;
        } else {
            this.node1 = node1;
            this.node2 = node2;
        }
    }

    public static Edge newEdge(Node node1, Node node2) {
        return new Edge(node1, node2);
    }


    @Override
    public int hashCode() {
        return (node1.hashCode()*17 + node2.hashCode())*23 + 37;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Edge)) {
            return false;
        }
        Edge e = (Edge) obj;
        /*
        node is singleton, so use == instead of equals() is OK here.
         */
        return this.node1 == e.getNode1() && this.node2 == e.getNode2();
    }


    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
