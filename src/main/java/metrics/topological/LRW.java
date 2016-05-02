package metrics.topological;

import beans.Edge;
import beans.Network;
import beans.Node;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix1D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.google.common.collect.Maps;
import configs.RunConfig;
import metrics.Metrics;

import java.util.Map;
import java.util.Set;

/**
 * Created by paranoidq on 16/4/27.
 */
public class LRW extends Metrics {

    private static final int MAX_HOPS = RunConfig.LRW.MAX_HOPS;
    private static final int MATRIX_LEN = Node.nodes.size();

    private final Map<Node, DoubleMatrix1D> caches = Maps.newHashMap();

    // 概率转移矩阵的转置形式
    private DoubleMatrix2D transitionMatrixTransposed;


    public LRW(Network groundNetwork) {
        super(groundNetwork);
        buildMatrix();
    }

    @Override
    public double metrics(Edge e) {
        return lrw4Node(e.getNode1(), e.getNode2()) + lrw4Node(e.getNode2(), e.getNode1());
    }


    private void buildMatrix() {
        DoubleMatrix2D p = new SparseDoubleMatrix2D(MATRIX_LEN, MATRIX_LEN);
        for (int key = 0; key < MATRIX_LEN; key++) {
            Node n = Node.getNode(key);
            if (groundNetwork.containsNode(n)) {
                Set<Node> cn = groundNetwork.getNeighbors(n);
                int degree = groundNetwork.degree(n);
                for (Node neighbor : cn) {
                    p.set(key, neighbor.getId(), (double)1/degree);
                }
            } else { // 度为0的点特殊处理
                for (int neighborId = 0; neighborId < MATRIX_LEN; neighborId++) {
                    p.set(key, neighborId, (double)1/(MATRIX_LEN-1));
                }
            }
        }
        transitionMatrixTransposed = Algebra.DEFAULT.transpose(p);
    }

    private double lrw4Node(Node src, Node dest) {
        DoubleMatrix1D probabilityVector;
        if (caches.containsKey(src)) {
            probabilityVector = caches.get(src);
        } else {
            probabilityVector = new SparseDoubleMatrix1D(MATRIX_LEN);
            probabilityVector.set(src.getId(), 1);
            int hops = 0;
            while (hops < MAX_HOPS) {
                probabilityVector = Algebra.DEFAULT.mult(transitionMatrixTransposed, probabilityVector);
                ++hops;
            }
            caches.put(src, probabilityVector);
        }
        return (double) 1000 * groundNetwork.degree(src) / 2 / groundNetwork.totalEdges()
                * probabilityVector.get(dest.getId());
    }





}
