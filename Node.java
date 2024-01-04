//Node class to create nodes of the Binary Tree
public class Node {

    Node leftChild;
    Node rightChild;
    int data;
    int h;

    //Default constructor for the Node class
    Node() {
        leftChild = null;
        rightChild = null;
        data = 0;
        h = 0;
    }

    //Custom constructor for the Node class
    Node(int a) {
        leftChild = null;
        rightChild = null;
        data = a;
        h = 0;
    }
    
    //Method to get the height of a node 
    public int getHeight() {
        return h;
    }
    
    //Method to get the maximum height from left and right node  
    public int getMaxHeight() {
        return (leftChild == null ? -1 : leftChild.h) > (rightChild == null ? -1 : rightChild.h) ? (leftChild == null ? -1 : leftChild.h) : (rightChild == null ? -1 : rightChild.h);
    }
    
    //Method to get the maximum height from left and a given integer
    public int getMaxHeightLeft(int rightNodeHeight) {
        return (leftChild == null ? -1 : leftChild.h) > rightNodeHeight ? (leftChild == null ? -1 : leftChild.h) : rightNodeHeight;
    }
    
    //Method to get the maximum height from right and a given integer
    public int getMaxHeightRight(int leftNodeHeight) {
        return leftNodeHeight > (rightChild == null ? -1 : rightChild.h) ? leftNodeHeight : (rightChild == null ? -1 : rightChild.h);
    }
}