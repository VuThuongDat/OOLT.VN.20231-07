package trees.implementation.genericT;

public interface GTree<E extends Comparable<E>> {
    public boolean insert(E p,E c);
    public boolean delete(E e);
    public boolean update(E o, E n);
    public boolean search(E e);
    public String traverseBFS();
    public String traverseDFS();
}
