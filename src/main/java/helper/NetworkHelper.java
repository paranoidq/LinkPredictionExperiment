package helper;

import beans.Feat;
import beans.Network;
import beans.Node;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import configs.PathConfigs;
import constants.Constants;
import org.apache.commons.lang3.StringUtils;
import utils.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by paranoidq on 16/4/26.
 */
public class NetworkHelper {

    private static String edgesPath = PathConfigs.EDGES_PATH;
    private static String featsPath = PathConfigs.FEATS_PATH;


    /**
     * Load whole network data
     * @return
     */
    public static Network loadNetwork() {
        Network network = Network.newNetwork(
                loadEdges(), loadFeats());
        return network;
    }



    private static Multimap<Node, Node> loadEdges() {
        Multimap<Node, Node> edges = HashMultimap.create();
        try(BufferedReader br = IOUtil.getReader(edgesPath)) {
            String line;
            while ( (line = br.readLine()) != null) {
                String[] sp = StringUtils.strip(line, Constants.NEWLINE).split(Constants.COMMA);
                Node node1 = Node.getNode(Integer.parseInt(sp[0]));
                Node node2 = Node.getNode(Integer.parseInt(sp[1]));
                edges.put(node1, node2);
                edges.put(node2, node1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edges;
    }

    private static Multimap<Node, Feat> loadFeats() {
//        Multimap<Node, Feat> feats = HashMultimap.create();
//        try(BufferedReader br = IOUtil.getReader(featsPath)) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] sp = StringUtils.strip(line, Constants.NEWLINE).split(Constants.COLON);
//                Node node = Node.getNode(Integer.parseInt(sp[0]));
//                String[] sp2 = sp[1].split(Constants.COMMA);
//                for (String id : sp2) {
//                    Feat feat = Feat.getFeat(Integer.parseInt(id));
//                    feats.put(node, feat);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return feats;
        return null;
    }

}
