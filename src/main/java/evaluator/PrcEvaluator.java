package evaluator;

import beans.Edge;
import beans.EvalData;
import configs.RunConfig;
import constants.Constants;
import metrics.Metrics;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by paranoidq on 16/4/26.
 */
public class PrcEvaluator {

    private static int L = RunConfig.Precision.L;

    private Metrics metrics;
    private EvalData data;

    public PrcEvaluator(Metrics metrics, EvalData data) {
        this.metrics = metrics;
        this.data = data;
    }


    public void evaluate() {
        List<Edge> testPosEdges = data.getTestPosEdgesAsList();
        List<Edge> testNegEdges = data.getTestNegEdgesAsList();

        List<Edge> sorted = new ArrayList<>(testPosEdges.size() + testNegEdges.size());
        for (Edge e : testPosEdges) {
            e.setScore(metrics.metrics(e));
            sorted.add(e);
        }
        for (Edge e : testNegEdges) {
            e.setScore(metrics.metrics(e));
            sorted.add(e);
        }
        Collections.sort(sorted, (o1, o2) -> Double.compare(o2.getScore(), o1.getScore()));

//        L = testPosEdges.size();
        Collection<Edge> topLPositive = CollectionUtils.intersection(sorted.subList(0, L), testPosEdges);
        double precision = 0;
        if (!CollectionUtils.isEmpty(topLPositive)) {
            precision = (double)topLPositive.size() / L;
        }
        System.out.println(metrics.name() + Constants.COLON + precision);

    }
}
