//Class to manage all the logical 
public class AVLTree {

    //Global root node for the tree
    Node root = null;
    //Global root node for the mirror tree
    Node mirrorRoot = null;

    //String to contain inorder traversal
    String iOrder;
    //String to contain postorder traversal
    String pOrder;
    //String to contain preorder traversal
    String prOrder;
    //String to contain levelorder traversal
    String lvlOrder;

    //Method to insert elements in the tree
    public Node insert(Node node, int data) {
        //Check whether the node is null or not  
        if (node == null) {
            node = new Node(data);
        } //Insert a node in case when the given element is lesser than the element of the root node  
        else if (data < node.data) {
            node.leftChild = insert(node.leftChild, data);
            if ((node.leftChild == null ? -1 : node.leftChild.getHeight()) - (node.rightChild == null ? -1 : node.rightChild.getHeight()) == 2) {
                if (data < node.leftChild.data) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            }
        } else if (data > node.data) {
            node.rightChild = insert(node.rightChild, data);
            if ((node.rightChild == null ? -1 : node.rightChild.getHeight()) - (node.leftChild == null ? -1 : node.leftChild.getHeight()) == 2) {
                if (data > node.rightChild.data) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        } else  
            ;  //If the element is already present in the tree, we will do nothing   
        node.h = node.getMaxHeight() + 1;

        return node;
    }

    //Method to perform rotation of binary tree node with left child        
    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        //Actualize the heights of the nodes
        node2.h = node2.getMaxHeight() + 1;
        node1.h = node1.getMaxHeightLeft(node2.h) + 1;
        return node1;
    }

    //Method to perform rotation of binary tree node with right child        
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        //Actualize the heights of the nodes
        node1.h = node1.getMaxHeight() + 1;
        node2.h = node2.getMaxHeightRight(node1.h) + 1;
        return node2;
    }

    //Method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node with the new left child  
    private Node doubleWithLeftChild(Node node) {
        node.leftChild = rotateWithRightChild(node.leftChild);
        return rotateWithLeftChild(node);
    }

    //Method to perform double rotation of binary tree node. This method first rotate the right child with its left child, and after that, node with the new right child  
    private Node doubleWithRightChild(Node node) {
        node.rightChild = rotateWithLeftChild(node.rightChild);
        return rotateWithRightChild(node);
    }

    //Method to perform the inorder traversal of a binary tree
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.leftChild);
            iOrder += root.data + " ";
            inOrder(root.rightChild);
        }
    }

    //Method to perform the preorder traversal of a binary tree
    public void preOrder(Node root) {
        if (root != null) {
            prOrder += root.data + " ";
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    //Method to perform the postoder traversal of a binary tree
    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            pOrder += root.data + " ";
        }
    }

    //Method to perform the level order traversal of a binary tree
    public void levelOrder(Node node) {
        int h = node.h + 1;
        int i;
        for (i = 1; i <= h; i++) {
            getCurrentLevel(node, i);
        }
    }

    //Method to get all nodes of a given level
    private void getCurrentLevel(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            lvlOrder += node.data + " ";
        } else if (level > 1) {
            getCurrentLevel(node.leftChild, level - 1);
            getCurrentLevel(node.rightChild, level - 1);
        }
    }

    //Method to create a mirror tree of passed tree (pass the root)
    public void mirrorTree(Node node) {
        if (node != null) {
            mirrorTree(node.leftChild);
            mirrorTree(node.rightChild);
            Node temp = node.leftChild;
            node.leftChild = node.rightChild;
            node.rightChild = temp;
        }
    }
}