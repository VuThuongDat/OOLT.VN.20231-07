package trees.implementation.genericT;

import java.util.*;
public class GenericTree <E extends Comparable<E>> implements GTree<E> {
    private Node<E> root;

    public GenericTree() {
        this.root = null;
    }
    public Node<E> getRoot(){
        return root;
    }
    public Node<E> findNode(E e) {
        return findNodeRec(root, e);
    }

    private Node<E> findNodeRec(Node<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.element) == 0) {
            return node;
        }

        for (Node<E> child : node.children) {
            Node<E> foundNode = findNodeRec(child, e);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }



    public boolean insert(E parentValue, E e) {
        if(root == null){
            root = new Node<E>(parentValue);
            root.children.add(new Node<E>(e));
            return true;
        }else {
            Node<E> parentNode = findNode(parentValue);
            if (parentNode != null) { // nếu cha tồn tại
                if (findNode(e) == null) {// gtri con ko tồn tại thì thêm
                    parentNode.children.add(new Node<>(e)); // ktra gia da ton tai chua
                    return true;
                }
                else return false;//System.out.println("Node with value " + e + " already exist");
            } else{
                // nếu giá trị cha chưa tồn tại tạo thêm nhánh vào root
                Node<E> parentNode1 = new Node<E>(parentValue);
                root.children.add(parentNode1);
                if (findNode(e) == null) {// gtri con ko tồn tại thì thêm
                    parentNode1.children.add(new Node<E>(e)); // ktra gia da ton tai chua
                    return true;
                }
                else return false;
                //System.out.println("Parent node with value " + parentValue + " not found");
            }
        }
        //return root;
    }
    //    @Override
//     public boolean insert(E p,E c) {
//         root = insert(p,c);
//         if(root == null)
//             return false;
//         return true;
//     }
    public boolean update(E oldE, E newE) {
        Node<E> nodeToUpdate = findNode(oldE);
        if (nodeToUpdate != null) {
            if(findNode(newE) == null) nodeToUpdate.element = newE;
            else return false;
            //System.out.println("Node with value " + newE + " already exist");
        } else {
            return false;
            //System.out.println("Node with value " + oldE + " not found");
        }
        return false;
    }

    @Override
    public boolean search(E e) {
        return findNode(e) != null;
    }



    public boolean delete(E e) {
        if (root == null) {
            //System.out.println("The tree is empty. Cannot delete.");
            return false;
        }

        if (e.compareTo(root.element) == 0) {
            if (root.children.isEmpty()) {
                root = null;
                return false;
            } else {
                Node<E> newRoot = root.children.get(0); // đặt root mới là con đầu tiên
                root.children.remove(0); // xóa đi con đầu tiên của root cũ
                while (!root.children.isEmpty()) {
                    newRoot.children.add(root.children.remove(0)); // thêm các con vào root mới
                }
                root = newRoot;
                return true;
            }
        } else {
            //root = deleteRec(root, value);
            Node<E> node = findNode(e);
            Node<E> parentNode = findParentNode(root, e);
            if (parentNode != null) {
                parentNode.children.remove(node);
                parentNode.children.addAll(node.children);
            }
        }
        return true;
    }

    private Node<E> findParentNode(Node<E> node, E value) {
        if (node == null) {
            return null;
        }

        for (Node<E> child : node.children) {
            if (child.element == value) {
                return node;
            }
        }

        for (Node<E> child : node.children) {
            Node<E> parent = findParentNode(child, value);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

//    public boolean delete(E e){
//        root = delete(root,e);
//        if(root == null) return false;
//        return true;
//    }
//    public void search(Node<E> root, E value) {
//        //return findNodeRec(root, value) != null;
//        Node<E> node = findNodeRec(root, value);
//        if(node != null) System.out.println("Node with value " + value +" found");
//        else  System.out.println("Node with value " + value +" NOT found");
//    }


    public String traverseBFS() {
        StringBuilder result = new StringBuilder();

        if (root == null)
            return result.toString();

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                Node<E> node = queue.poll();
                result.append(node.element).append(" ");

                for (Node<E> child : node.children) {
                    queue.add(child);
                }
                count--;
            }

            //result.append("\n"); // Xuống dòng sau khi in xong 1 cấp độ
        }

        return result.toString().trim(); // Loại bỏ khoảng trắng cuối cùng
    }

    @Override
    public String traverseDFS() {
        if (root == null) {
            return ""; // Trả về chuỗi rỗng nếu nút gốc là null
        }

        StringBuilder result = new StringBuilder();
        traverseDFS(root, result); // Gọi đệ quy DFS với nút gốc và StringBuilder

        return result.toString();
    }

    private void traverseDFS(Node<E> node, StringBuilder result) {
        if (node == null) {
            return;
        }

        result.append(node.element).append(" ");

        for (Node<E> child : node.children) {
            traverseDFS(child, result);
        }
    }


    public Iterator<E> iterator() {
        return null;
    }
}