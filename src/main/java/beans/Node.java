package beans;

import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by paranoidq on 16/4/26.
 */
public class Node implements Comparable {

    public static Map<Integer, Node> nodes = Maps.newHashMap();

    private Integer id;


    private Node(int id) {
        this.id = id;
    }

    public static Node getNode(int id) {
        if (nodes.containsKey(id)) {
            return nodes.get(id);
        } else {
            Node newNode = new Node(id);
            nodes.put(id, newNode);
            return newNode;
        }
    }


    public int getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Node)) {
            return false;
        }
        Node n = (Node) obj;
        return n.id.equals(this.id);
    }


    public int compareTo(Object o) {
        Node n = (Node)o;
        return id.compareTo(n.id);
    }
}
