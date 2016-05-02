package evaluator;

import beans.Edge;
import beans.EvalData;
import beans.Node;
import com.google.common.collect.Multimap;
import configs.RunConfig;
import constants.Constants;
import metrics.Metrics;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * Created by paranoidq on 16/4/26.
 */
public class AucEvaluator {

    private static final int AUC_SAMPLE_TIMES = RunConfig.AUC.AUC_SAMPLE_TIMES;

    private Metrics metrics;
    private EvalData data;


    public AucEvaluator(Metrics metrics, EvalData data) {
        this.metrics = metrics;
        this.data = data;
    }


    public void evaluate() {
        List<Edge> testPosEdges = data.getTestPosEdgesAsList();
        List<Edge> testNegEdges = data.getTestNegEdgesAsList();
        // 记录运行时间
        double time = System.nanoTime();
        double score = 0;
        for (int i = 0; i < AUC_SAMPLE_TIMES; i++) {
            Edge positive = testPosEdges.get(RandomUtils.nextInt(0, testPosEdges.size()));
            Edge negative = testNegEdges.get(RandomUtils.nextInt(0, testNegEdges.size()));

            double positiveScore = metrics.metrics(positive);
            double negativeScore = metrics.metrics(negative);
            if (positiveScore > negativeScore) {
                score += 1;
            } else if (positiveScore == negativeScore) {
                score += .5;
            }
        }
        System.out.println(metrics.name() + Constants.COLON + score / AUC_SAMPLE_TIMES);
        System.out.println((System.nanoTime() - time) / AUC_SAMPLE_TIMES * Math.pow(10, 6));
    }
}
