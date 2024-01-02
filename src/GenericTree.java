import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
class GenericTree {
    private Node root;

    public GenericTree() {
        this.root = null;
    }

    public Node findNode(int value) {
        return findNodeRec(root, value);
    }

    private Node findNodeRec(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (node.value == value) {
            return node;
        }

        for (Node child : node.children) {
            Node foundNode = findNodeRec(child, value);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    public void insert(int parentValue, int value) {
        if(root == null){
            root = new Node(parentValue);
            root.children.add(new Node(value));
        }else {
            Node parentNode = findNode(parentValue);
            if (parentNode != null) { // nếu cha tồn tại
                if (findNode(value) == null) // gtri con ko tồn tại thì thêm
                    parentNode.children.add(new Node(value)); // ktra gia da ton tai chua
                else System.out.println("Node with value " + value + " already exist");
            } else { // nếu giá trị cha chưa tồn tại tạo thêm nhánh vào root
                root.children.add(new Node(parentValue));
                Node parentNode1 = findNode(parentValue);
                if (parentNode1 != null) { // nếu cha tồn tại
                    if (findNode(value) == null) // gtri con ko tồn tại thì thêm
                        parentNode1.children.add(new Node(value)); // ktra gia da ton tai chua
                    else System.out.println("Node with value " + value + " already exist");
                }
                //System.out.println("Parent node with value " + parentValue + " not found");
            }
        }
    }

    public void update(int oldValue, int newValue) {
        Node nodeToUpdate = findNode(oldValue);
        if (nodeToUpdate != null) {
            if(findNode(newValue) == null) nodeToUpdate.value = newValue;
            else System.out.println("Node with value " + newValue + " already exist");
        } else {
            System.out.println("Node with value " + oldValue + " not found");
        }
    }

    public void delete(int value) {
        if (root == null) {
            System.out.println("The tree is empty. Cannot delete.");
            return;
        }

        if (root.value == value) {
            if (root.children.isEmpty()) {
                root = null;
            } else {
                Node newRoot = root.children.get(0);
                root.children.remove(0);
                while (!root.children.isEmpty()) {
                    newRoot.children.add(root.children.remove(0));
                }
                root = newRoot;
            }
        } else {
            //root = deleteRec(root, value);
            Node node = findNode(value);
            Node parentNode = findParentNode(root, value);
            if (parentNode != null) {
                parentNode.children.remove(node);
                parentNode.children.addAll(node.children);
            }
        }
    }

//    private Node deleteRec(Node node, int value) {
//        if (node == null) {
//            return null;
//        }
//
//        if (node.value == value) {
//            Node parentNode = findParentNode(root, value);
//            if (parentNode != null) {
//                parentNode.children.remove(node);
//                parentNode.children.addAll(node.children);
//            }
//        } else {
////            for (Node child : node.children) {
////                Node newChild = deleteRec(child, value);
////                if (newChild != null) {
////                    node.children.remove(newChild);
////                    node.children.addAll(newChild.children);
////                    break;
////                }
////            }
//            Node childNode = findNode(value);
//
//        }
//
//        return node;
//    }

    private Node findParentNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        for (Node child : node.children) {
            if (child.value == value) {
                return node;
            }
        }

        for (Node child : node.children) {
            Node parent = findParentNode(child, value);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

    public void search(int value) {
        //return findNodeRec(root, value) != null;
        Node node = findNodeRec(root, value);
        if(node != null) System.out.println("Node with value " + value +" found");
        else  System.out.println("Node with value " + value +" NOT found");
    }

    public void traverseBFS() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.value + " ");

            for (Node child : current.children) {
                queue.add(child);
            }
        }
        System.out.println();
    }
}