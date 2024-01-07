package trees.tree.bst;

import trees.node.BNode;
import trees.tree.bt.BT;

public class BST extends BT {

    public BNode insert(BNode node, int e){

        if(node == null)
            node = createNewNode(e);
        else{
            if(e > node.element)
                node.right = insert(node.right, e);
            else if(e < node.element )
                node.left = insert(node.left, e);
            else
                return null;
        }
        return node;
    }
    @Override
    public BNode createNewNode(int e){
        return new BNode(e);
    }


    public boolean insert(int e) {
        root = insert(root,e);
        if(root == null)
            return false;
        return true;
    }

    public BNode delete(BNode root, int e){
        if(root == null)
            return null;

        if(e > root.element)
            root.right = delete(root.right, e);
        else if(e < root.element)
            root.left = delete(root.left, e);

        else{
            if(root.left == null && root.right == null)
                root = null;
            else if(root.left == null)
                root = root.right;
            else if(root.right == null)
                root = root.left;
            else {
                root.element = findMax(root.left);
                root.left = delete(root.left, root.element);
            }
        }
        return root;
    }

    private int findMax(BNode root){
        BNode temp = root;
        while(temp.right != null)
            temp = temp.right;
        return temp.element;
    }

    public boolean delete(int e) {
        root = delete(root, e);
        if(root == null)
            return false;
        return true;
    }
    public boolean update(int oldValue, int newValue) {
        BNode nodeToDelete = findNode(root, oldValue);

        if (nodeToDelete != null) {
            delete(oldValue);
            insert(newValue);
            return true;
        }

        return false;
    }

    private BNode findNode(BNode root, int e) {
        if (root == null)
            return null;
        else if (e == root.element)
            return root;
        else if (e > root.element)
            return findNode(root.right, e);
        else
            return findNode(root.left, e);
    }

}

