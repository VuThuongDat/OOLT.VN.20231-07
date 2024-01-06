package trees.tree.genericT;

import java.util.*;

import trees.node.Node;
public class GenericTree <E extends Comparable<E>> {
    private Node<E> root;
	public GenericTree() {
        this.root = null;
    }
    public Node<E> getRoot(){
        return root;
    }
    public Node<E> findNode(E e) {
        return findNodeRec(root, e);
    }

    private Node<E> findNodeRec(Node<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.element) == 0) {
            return node;
        }

        for (Node<E> child : node.children) {
            Node<E> foundNode = findNodeRec(child, e);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }



    public boolean insert(E parentValue, E e) {
        if(root == null){
            root = new Node<E>(parentValue);
            root.children.add(new Node<E>(e));
            return true;
        }else {
            Node<E> parentNode = findNode(parentValue);
            if (parentNode != null) {
                if (findNode(e) == null) {
                    parentNode.children.add(new Node<>(e));
                    return true;
                }
                else return false;
            } else{
                Node<E> parentNode1 = new Node<E>(parentValue);
                root.children.add(parentNode1);
                if (findNode(e) == null) {
                    parentNode1.children.add(new Node<E>(e));
                    return true;
                }
                else return false;
            }
        }
    }
    public boolean update(E oldE, E newE) {
        Node<E> nodeToUpdate = findNode(oldE);
        if (nodeToUpdate != null) {
            if(findNode(newE) == null) nodeToUpdate.element = newE;
            else return false;
        } else {
            return false;
        }
        return false;
    }

    public boolean search(E e) {
        return findNode(e) != null;
    }

    public boolean delete(E e) {
        if (root == null) {
            return false;
        }

        if (e.compareTo(root.element) == 0) {
            if (root.children.isEmpty()) {
                root = null;
                return false;
            } else {
                Node<E> newRoot = root.children.get(0);
                root.children.remove(0);
                while (!root.children.isEmpty()) {
                    newRoot.children.add(root.children.remove(0));
                }
                root = newRoot;
                return true;
            }
        } else {

            Node<E> node = findNode(e);
            Node<E> parentNode = findParentNode(root, e);
            if (parentNode != null) {
                parentNode.children.remove(node);
                parentNode.children.addAll(node.children);
            }
        }
        return true;
    }

    private Node<E> findParentNode(Node<E> node, E value) {
        if (node == null) {
            return null;
        }

        for (Node<E> child : node.children) {
            if (child.element == value) {
                return node;
            }
        }

        for (Node<E> child : node.children) {
            Node<E> parent = findParentNode(child, value);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

    public String traverseBFS() {
        StringBuilder result = new StringBuilder();

        if (root == null)
            return result.toString();

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                Node<E> node = queue.poll();
                result.append(node.element).append(" ");

                for (Node<E> child : node.children) {
                    queue.add(child);
                }
                count--;
            }
        }
        return result.toString().trim();
    }
    public String traverseDFS() {
        if (root == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        traverseDFS(root, result);

        return result.toString();
    }

    private void traverseDFS(Node<E> node, StringBuilder result) {
        if (node == null) {
            return;
        }

        result.append(node.element).append(" ");

        for (Node<E> child : node.children) {
            traverseDFS(child, result);
        }
    }
}