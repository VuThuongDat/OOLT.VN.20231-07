package trees.tree.bt;

import trees.node.BNode;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BT {
    public BNode root;

    private int maxHeight;

    public int getMaxHeight(){
        return this.maxHeight;
    }

    public int height(BNode node) {
        if (node == null) {
            return -1;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            // Trả về chiều cao của cây con cao nhất cộng thêm 1 (nút hiện tại)
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    private boolean search(BNode root, int e){
        if(root == null)
            return false;
        else if(e == root.element)
            return true;
        else{
            if(e > root.element)
                return search(root.right, e);
            else
                return search(root.left, e);
        }
    }
    public boolean search(int e) {
        return search(root, e);
    }
    public BNode createNewNode(int e){
        return new BNode(e);
    }

    public BNode getRoot(){
        return root;
    }

    public String traverseBFS() {
        StringBuilder result = new StringBuilder();
        if (root == null) {
            return result.toString();
        }

        Queue<BNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BNode current = queue.poll();
            result.append(current.element).append(" ");

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result.toString().trim();
    }
    public String traverseDFS() {
        StringBuilder result = new StringBuilder();
        if (root == null) {
            return result.toString();
        }
        traverseDFS(root, result);

        return result.toString().trim();
    }

    private void traverseDFS(BNode node, StringBuilder result) {
        if (node != null) {
            result.append(node.element).append(" ");

            traverseDFS(node.left, result);

            traverseDFS(node.right, result);
        }
    }
}
