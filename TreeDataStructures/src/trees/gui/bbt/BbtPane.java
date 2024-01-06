package trees.gui.bbt;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import trees.gui.avl.AvlPane;
import trees.tree.bbt.BBT;

public class BbtPane extends AvlPane {
	Stage primaryStage = new Stage();
	private BBT<Integer> tree;
    private double vGap = 50;
    
    BbtPane(BBT<Integer> tree){
        this.tree = tree;
        setStatus("Tree is empty");
        setBackground(new Background(new BackgroundFill(Color.web("#" + "FFA500"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4, Color.GRAY);
        }
    }
}
