import java.util.ArrayList;
import java.util.List;

public class Node {
        int value;
        List<Node> children;

        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
}
