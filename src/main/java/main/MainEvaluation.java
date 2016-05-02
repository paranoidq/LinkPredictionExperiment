package main;

import beans.EvalData;
import beans.Network;
import com.google.common.collect.Lists;
import evaluator.Evaluation;
import helper.EvaluationHelper;
import helper.NetworkHelper;
import metrics.Metrics;
import metrics.topological.*;

import java.util.List;

/**
 * Created by paranoidq on 16/4/26.
 */
public class MainEvaluation {


    public static void main(String[] args) {
        Network network = NetworkHelper.loadNetwork();

        EvalData evalData = EvaluationHelper.config(network);
        Network groundNetwork = evalData.getGroundNetwork();

        List<Metrics> metrics = Lists.newLinkedList();


        metrics.add(new CN(groundNetwork));
//        metrics.add(new Jaccard(groundNetwork));
//        metrics.add(new AA(groundNetwork));
//        metrics.add(new RA(groundNetwork));
//        metrics.add(new PA(groundNetwork));
//        metrics.add(new LP(groundNetwork));
//        metrics.add(new LRW(groundNetwork));


        for (Metrics m : metrics) {
            Evaluation evaluation = new Evaluation(m, evalData);
            evaluation.evaluate();
        }

    }

}
