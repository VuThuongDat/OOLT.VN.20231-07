public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GenericTree tree = new GenericTree();
        tree.insert(34,35);
        tree.insert(34,32);
        //tree.insert(35,34);
        tree.insert(32,23);
        tree.insert(12,15);
        tree.delete(32);
        tree.update(35,37);
       // tree.insert(32,24);
        tree.search(32);
        tree.traverseBFS();

    }
}