package trees.implementation.genericT;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GenericTree<Integer> tree = new GenericTree<>();
        tree.insert(34,35);
        tree.insert(34,32);
        if(tree.search(32)) System.out.println("Đúng");
        tree.insert(35,34);
        tree.insert(32,23);
        tree.insert(12,15);
        tree.insert(12,17);
        tree.insert(14,13);
        tree.insert(13,21);
//        tree.delete(32);
//        tree.delete(17);
//        tree.update(35,37);
//        tree.delete(35);
        tree.insert(32,24);
        System.out.println(tree.traverseDFS());
        System.out.println(tree.traverseBFS());
    }
}
