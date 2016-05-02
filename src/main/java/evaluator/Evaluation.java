package evaluator;

import beans.Edge;
import beans.EvalData;
import metrics.Metrics;

import java.util.List;

/**
 * Created by paranoidq on 16/4/26.
 */
public class Evaluation {

    // metrics for evaluator
    private Metrics metrics;

    // AUC Evaluator
    private AucEvaluator aEvaluator;

    // Precision Evaluator
    private PrcEvaluator pEvaluator;


    public Evaluation(Metrics metrics, EvalData data) {
        this.aEvaluator = new AucEvaluator(metrics, data);
        this.pEvaluator = new PrcEvaluator(metrics, data);
        this.metrics = metrics;
    }

    public void evaluate() {
        aEvaluator.evaluate();
        pEvaluator.evaluate();
    }
}
