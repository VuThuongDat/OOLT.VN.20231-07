package trees.gui.GenericT;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import trees.implementation.genericT.GenericTree;
import trees.implementation.genericT.Node;


public class GtPane extends Pane {
    private GenericTree<Integer> tree;
    private double radius = 15;
    private double vGap = 50;

    protected GtPane(){ }

    GtPane(GenericTree<Integer> tree){
        this.tree = tree;
        setStatus("Tree is empty");
        setBackground(new Background(new BackgroundFill(Color.web("#" + "FFC0CB"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setStatus(String msg){
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4, Color.CYAN);
        }
    }

    protected void displayTree(Node<Integer> root, double x, double y, double hGap, Color color){
        if(!root.children.isEmpty()){
            double startX = x - hGap * (root.children.size() - 1) / 2;
            // Lặp qua từng con và kết nối chúng với cha (root)
            for (Node<Integer> child : root.children) {
                getChildren().add(new Line(x, y, startX, y + vGap));
                // Gọi đệ quy để hiển thị cây con
                displayTree(child, startX, y + vGap, hGap / 2, color);
                // Tăng giá trị startX để đảm bảo vị trí đúng cho con tiếp theo
                startX += hGap;
            }
        }
        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }

}