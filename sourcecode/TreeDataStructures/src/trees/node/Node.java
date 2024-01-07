package trees.node;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int element;
    public List<Node> children = new ArrayList<>();
    public Node(int e) {
        super();
        this.element = e;
    }
}
