package trees.node;

public class BNode<E extends Comparable<E>> extends Node<E> {
    public BNode<E> left;
    public BNode<E> right;
    public int height = 0;
    public BNode(E e){
        super(e);
    }
}