package trees.tree.genericT;

import java.util.*;

import trees.node.Node;
public class GenericTree <E extends Comparable<E>> {
    private Node root;
	public GenericTree() {
        this.root = null;
    }
    public Node getRoot(){
        return root;
    }
    public Node findNode(int e) {
        return findNodeRec(root, e);
    }

    private Node findNodeRec(Node node, int e) {
        if (node == null) {
            return null;
        }

        if (e == node.element) {
            return node;
        }

        for (Node child : node.children) {
            Node foundNode = findNodeRec(child, e);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }



    public boolean insert(int parentValue, int e) {
        if(root == null){
            root = new Node(parentValue);
            root.children.add(new Node(e));
            return true;
        }else {
            Node parentNode = findNode(parentValue);
            if (parentNode != null) {
                if (findNode(e) == null) {
                    parentNode.children.add(new Node(e));
                    return true;
                }
                else return false;
            } else{
                Node parentNode1 = new Node(parentValue);
                root.children.add(parentNode1);
                if (findNode(e) == null) {
                    parentNode1.children.add(new Node(e));
                    return true;
                }
                else return false;
            }
        }
    }
    public boolean update(int oldE, int newE) {
        Node nodeToUpdate = findNode(oldE);
        if (nodeToUpdate != null) {
            if(findNode(newE) == null) nodeToUpdate.element = newE;
            else return false;
        } else {
            return false;
        }
        return false;
    }

    public boolean search(int e) {
        return findNode(e) != null;
    }

    public boolean delete(int e) {
        if (root == null) {
            return false;
        }

        if (e == root.element) {
            if (root.children.isEmpty()) {
                root = null;
                return false;
            } else {
                Node newRoot = root.children.get(0);
                root.children.remove(0);
                while (!root.children.isEmpty()) {
                    newRoot.children.add(root.children.remove(0));
                }
                root = newRoot;
                return true;
            }
        } else {

            Node node = findNode(e);
            Node parentNode = findParentNode(root, e);
            if (parentNode != null) {
                parentNode.children.remove(node);
                parentNode.children.addAll(node.children);
            }
        }
        return true;
    }

    private Node findParentNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        for (Node child : node.children) {
            if (child.element == value) {
                return node;
            }
        }

        for (Node child : node.children) {
            Node parent = findParentNode(child, value);
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

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                Node node = queue.poll();
                result.append(node.element).append(" ");

                for (Node child : node.children) {
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

    private void traverseDFS(Node node, StringBuilder result) {
        if (node == null) {
            return;
        }

        result.append(node.element).append(" ");

        for (Node child : node.children) {
            traverseDFS(child, result);
        }
    }
}