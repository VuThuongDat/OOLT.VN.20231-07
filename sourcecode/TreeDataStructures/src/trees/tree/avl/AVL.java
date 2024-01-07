package trees.tree.avl;

import trees.node.BNode;
import trees.tree.bst.BST;
import java.util.ArrayList;

public class AVL extends BST {

    @Override
    public BNode createNewNode(int e) {
        return new BNode(e);
    }

    @Override
    public boolean insert(int e) {
        boolean successful = super.insert(e);
        if(!successful)
            return false;
        else
            balancePath(e);
        return true;
    }

    private void updateHeight(BNode node){
        if(node.left == null && node.right == null)
            node.height = 0;
        else if(node.left == null)
            node.height = 1 + node.right.height;
        else if(node.right == null)
            node.height = 1 + node.left.height;
        else
            node.height = 1 + Math.max(node.left.height, node.right.height);
    }
    public ArrayList<BNode> path(int e){
        ArrayList<BNode> list = new ArrayList<>();
        BNode current = root;
        while(current != null){
            list.add(current);
            if(e < root.element)
                current = current.left;
            else if(e > root.element )
                current = current.right;
            else
                break;
        }
        return list;
    }

    private void balancePath(int e){
        ArrayList<BNode> path = path(e);
        for(int i=path.size()-1; i>=0; i--){
            BNode A = path.get(i);
            updateHeight(A);
            BNode parentOfA = A == root? null:path.get(i-1);

            switch (balanceFactor(A)){
                case -2:
                    if(balanceFactor(A.left) <= 0)
                        balanceLL(A, parentOfA);
                    else
                        balanceLR(A, parentOfA);
                    break;
                case 2:
                    if(balanceFactor(A.right) >= 0)
                        balanceRR(A, parentOfA);
                    else
                        balanceRL(A, parentOfA);
                    break;
            }
        }
    }

    private int balanceFactor(BNode node){
        if(node.right == null)
            return -node.height;
        else if(node.left == null)
            return node.height;
        else
            return node.right.height - node.left.height;
    }

    private void balanceLL(BNode A, BNode parentOfA){
        BNode B = A.left;
        if(A == root)
            root = B;
        else{
            if(parentOfA.left == A)
                parentOfA.left = B;
            else
                parentOfA.right = B;
        }
        A.left = B.right;
        B.right = A;
        updateHeight(A);
        updateHeight(B);
    }

    private void balanceRR(BNode A, BNode parentOfA){
        BNode B = A.right;
        if(A == root)
            root = B;
        else{
            if(parentOfA.left == A)
                parentOfA.left = B;
            else
                parentOfA.right = B;
        }
        A.right = B.left;
        B.left = A;
        updateHeight(A);
        updateHeight(B);
    }

    private void balanceLR(BNode A, BNode parentOfA){
        BNode B = A.left;
        BNode C = B.right;
        if(A == root)
            root = C;
        else{
            if(parentOfA.left == A)
                parentOfA.left = C;
            else
                parentOfA.right = C;
        }
        B.right = C.left;
        A.left = C.right;
        C.left = B;
        C.right = A;
        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    private void balanceRL(BNode A, BNode parentOfA){
        BNode B = A.right;
        BNode C = B.left;
        if(A == root)
            root = C;
        else{
            if(parentOfA.left == A)
                parentOfA.left = C;
            else
                parentOfA.right = C;
        }
        B.left = C.right;
        A.right = C.left;
        C.left = A;
        C.right = B;
        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    @Override
    public boolean delete(int element){
        if(root == null)
            return false;

        BNode parent = null;
        BNode current = root;
        while(current != null){
            if(element < current.element){
                parent = current;
                current = current.left;
            }
            else if(element > current.element){
                parent = current;
                current = current.right;
            }
            else break;
        }
        if(current == null)
            return false;
        if(current.left == null){
            if(parent == null)
                root = current.right;
            else{
                if(element < parent.element)
                    parent.left = current.right;
                else
                    parent.right = current.right;
                balancePath(parent.element);
            }
        }
        else{
            BNode parentOfRightMost = current;
            BNode rightMost = current.left;

            while(rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if(parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                parentOfRightMost.left = rightMost.left;
            balancePath(parentOfRightMost.element);
        }
        return true;
    }
}
