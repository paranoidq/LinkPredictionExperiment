package beans;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by paranoidq on 16/4/26.
 */
public class Feat {

    public static Map<Integer, Feat> feats = Maps.newHashMap();

    private int id;
    private String feat;


    /**
     * 暂时不考虑feat的文本,而直接以id代替之
     * @param id
     * @param feat
     */
    @Deprecated
    private Feat(int id, String feat) {
        this.id = id;
        this.feat = feat;
    }
    private Feat(int id) {
        this.id = id;
    }

    public static Feat getFeat(int id) {
        if (feats.containsKey(id)) {
            return feats.get(id);
        } else {
            Feat newFeat = new Feat(id);
            feats.put(id, newFeat);
            return newFeat;
        }
    }


    @Override
    public int hashCode() {
        return id * 17 + 39;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Feat)) {
            return false;
        }
        Feat f = (Feat) obj;
        return f.id == this.id;
    }
}
