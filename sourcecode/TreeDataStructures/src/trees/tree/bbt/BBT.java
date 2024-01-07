package trees.tree.bbt;

import trees.tree.avl.AVL;
import trees.node.BNode;

public class BBT extends AVL {
	@Override
    public BNode createNewNode(int e) {
        return new BNode(e);
    }
}
