package configs;

/**
 * Created by paranoidq on 16/4/26.
 */
public interface RunConfig {

    double TRAIN_PARTITION = 0.8;

    // neg train VS. pos train ratio
    double NEG_TRAIN_RATIO = 1;

    int SAMPLE_NODES = 100;

    /**
     *  Auc Evaluation
     */
    interface AUC {
        int AUC_SAMPLE_TIMES = 10000;
    }

    /**
     * Precision Evaluation
     */
    interface Precision {
        int L = 200;
    }


    /**
     * Pattern configs
     */
    interface Pattern {
        int PATTERN_MIN_LEN = 2;
        int MIN_SUPP = 20;
        int PER_INS_COVER_TIMES = 5;
        double COVER_DELTA = 0.9;
    }


    /**
     * LRW configs
     */
    interface LRW {
        int MAX_HOPS = 5;
    }

    /**
     * LP alpha
     */
    interface LP {
        double alpha = 0.01;
    }
}
