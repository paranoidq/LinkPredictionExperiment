package configs;

import java.io.File;

/**
 * Created by paranoidq on 16/4/26.
 */
public interface PathConfigs {

    String SEP = File.separator;

    String _BASE = "/Users/paranoidq/exp/polblogs";

    String EDGES_PATH = _BASE + SEP + "edges";
    String FEATS_PATH = _BASE + SEP + "feats";

}
