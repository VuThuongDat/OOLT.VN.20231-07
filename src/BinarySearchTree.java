import java.util.ArrayList;
import java.util.List;
class BinarySearchTree extends GenericTree {

    // Constructor
    public BinarySearchTree() {
        super();
    }

    // Overriding insert method to maintain BST property
    @Override
    public void insert(int parentValue, int value) {
        // Ensure that the BinarySearchTree is constructed appropriately
        if (!(this instanceof BinarySearchTree)) {
            System.out.println("Error: Cannot use BinarySearchTree methods on a non-BinarySearchTree object.");
            return;
        }

        // Use the GenericTree insert method to add the node
        super.insert(parentValue, value);
    }

    // Helper method to convert a tree to a Binary Search Tree
    private Node convertToBST(Node node) {
        if (node == null) {
            return null;
        }

        List<Node> sortedNodes = inOrderTraversal(node);
        return buildBSTFromSortedNodes(sortedNodes, 0, sortedNodes.size() - 1);
    }

    // Helper method to perform in-order traversal and return a sorted list of nodes
    private List<Node> inOrderTraversal(Node node) {
        List<Node> result = new ArrayList<>();
        inOrderTraversalHelper(node, result);
        return result;
    }

    // Helper method for in-order traversal
    private void inOrderTraversalHelper(Node node, List<Node> result) {
        if (node != null) {
            inOrderTraversalHelper(node.children.get(0), result);
            result.add(node);
            inOrderTraversalHelper(node.children.get(1), result);
        }
    }

    // Helper method to build a Binary Search Tree from a sorted list of nodes
    private Node buildBSTFromSortedNodes(List<Node> nodes, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node midNode = nodes.get(mid);

        midNode.children.set(0, buildBSTFromSortedNodes(nodes, start, mid - 1));
        midNode.children.set(1, buildBSTFromSortedNodes(nodes, mid + 1, end));

        return midNode;
    }
}
