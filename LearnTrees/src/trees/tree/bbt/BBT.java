package trees.tree.bbt;

import trees.tree.avl.AVL;
import trees.node.BNode;

public class BBT<E extends Comparable<E>> extends AVL<E> {
	@Override
    public BNode<E> createNewNode(E e) {
        return new BNode<>(e);
    }
}
