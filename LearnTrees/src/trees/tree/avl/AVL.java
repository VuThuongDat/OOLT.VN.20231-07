package trees.tree.avl;

import trees.node.BNode;
import trees.tree.bst.BST;

import java.util.ArrayList;

public class AVL<E extends Comparable<E>> extends BST<E> {
    public AVL(){}

    @Override
    public BNode<E> createNewNode(E e) {
        return new BNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if(!successful)
            return false;
        else
            balancePath(e);
        return true;
    }

    private void updateHeight(BNode<E> node){
        if(node.left == null && node.right == null)
            node.height = 0;
        else if(node.left == null)
            node.height = 1 + node.right.height;
        else if(node.right == null)
            node.height = 1 + node.left.height;
        else
            node.height = 1 + Math.max(node.left.height, node.right.height);
    }
    public ArrayList<BNode<E>> path(E e){
        ArrayList<BNode<E>> list = new ArrayList<>();
        BNode<E> current = root;
        while(current != null){
            list.add(current);
            if(e.compareTo(current.element) < 0)
                current = current.left;
            else if(e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }
        return list;
    }

    private void balancePath(E e){
        ArrayList<BNode<E>> path = path(e);
        for(int i=path.size()-1; i>=0; i--){
            BNode<E> A = path.get(i);
            updateHeight(A);
            BNode<E> parentOfA = A == root? null:path.get(i-1);

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

    private int balanceFactor(BNode<E> node){
        if(node.right == null)
            return -node.height;
        else if(node.left == null)
            return node.height;
        else
            return node.right.height - node.left.height;
    }

    private void balanceLL(BNode<E> A, BNode<E> parentOfA){
        BNode<E> B = A.left;
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

    private void balanceRR(BNode<E> A, BNode<E> parentOfA){
        BNode<E> B = A.right;
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

    private void balanceLR(BNode<E> A, BNode<E> parentOfA){
        BNode<E> B = A.left;
        BNode<E> C = B.right;
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

    private void balanceRL(BNode<E> A, BNode<E> parentOfA){
        BNode<E> B = A.right;
        BNode<E> C = B.left;
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
    public boolean delete(E element){
        if(root == null)
            return false;

        BNode<E> parent = null;
        BNode<E> current = root;
        while(current != null){
            if(element.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }
            else if(element.compareTo(current.element) > 0){
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
                if(element.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
                balancePath(parent.element);
            }
        }
        else{
            BNode<E> parentOfRightMost = current;
            BNode<E> rightMost = current.left;

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
