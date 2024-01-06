package trees.node;

import java.util.ArrayList;
import java.util.List;

public class Node <E extends Comparable<E>> {
    public E  element;
    public List<Node<E>> children = new ArrayList<>();
    public Node(E e) {
        this.element = e;
    }
}