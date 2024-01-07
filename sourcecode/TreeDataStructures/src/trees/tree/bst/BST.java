package trees.tree.bst;

import trees.node.BNode;
import trees.tree.bt.BT;

public class BST<E extends Comparable<E>> extends BT<E> {

//    public BNode<E> root;
//    private boolean search(BNode<E> root, E e){
//        if(root == null)
//            return false;
//        else if(e.compareTo(root.element) == 0)
//            return true;
//        else{
//            if(e.compareTo(root.element) > 0)
//                return search(root.right, e);
//            else
//                return search(root.left, e);
//        }
//    }



//    public boolean search(E e) {
//        return search(root, e);
//    }

    public BNode<E> insert(BNode<E> node, E e){
        if(node == null)
            node = createNewNode(e);
        else{
            if(e.compareTo(node.element) > 0)
                node.right = insert(node.right, e);
            else if(e.compareTo(node.element) < 0)
                node.left = insert(node.left, e);
            else
                return null;
        }
        return node;
    }

    public boolean insert(E e) {
        root = insert(root,e);
        if(root == null)
            return false;
        return true;
    }

//    public BNode<E> createNewNode(E e){
//        return new BNode<>(e);
//    }
//
//    public BNode<E> getRoot(){
//        return root;
//    }
//
//    public String traverseBFS() {
//        StringBuilder result = new StringBuilder();
//        if (root == null) {
//            return result.toString();
//        }
//
//        Queue<BNode<E>> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//            BNode<E> current = queue.poll();
//            result.append(current.element).append(" ");
//
//            if (current.left != null) {
//                queue.offer(current.left);
//            }
//
//            if (current.right != null) {
//                queue.offer(current.right);
//            }
//        }
//
//        return result.toString().trim();
//    }
//    public String traverseDFS() {
//        StringBuilder result = new StringBuilder();
//        if (root == null) {
//            return result.toString();
//        }
//        traverseDFS(root, result);
//
//        return result.toString().trim();
//    }
//
//    private void traverseDFS(BNode<E> node, StringBuilder result) {
//        if (node != null) {
//            result.append(node.element).append(" ");
//
//            traverseDFS(node.left, result);
//
//            traverseDFS(node.right, result);
//        }
//    }
    public BNode<E> delete(BNode<E> root, E e){
        if(root == null)
            return null;

        if(e.compareTo(root.element) > 0)
            root.right = delete(root.right, e);
        else if(e.compareTo(root.element) < 0)
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

    private E findMax(BNode<E> root){
        BNode<E> temp = root;
        while(temp.right != null)
            temp = temp.right;
        return temp.element;
    }

    public boolean delete(E e) {
        root = delete(root, e);
        if(root == null)
            return false;
        return true;
    }
    public boolean update(E oldValue, E newValue) {
        BNode<E> nodeToDelete = findNode(root, oldValue);

        if (nodeToDelete != null) {
            delete(oldValue);
            insert(newValue);
            return true;
        }

        return false;
    }

    private BNode<E> findNode(BNode<E> root, E e) {
        if (root == null)
            return null;
        else if (e.compareTo(root.element) == 0)
            return root;
        else if (e.compareTo(root.element) > 0)
            return findNode(root.right, e);
        else
            return findNode(root.left, e);
    }
}

