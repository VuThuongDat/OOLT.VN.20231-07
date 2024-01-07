package trees.gui.avl;

import trees.gui.bst.BstPane;
import trees.tree.avl.AVL;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class AvlPane extends BstPane {
    private AVL tree;
    private double vGap = 50;
    
    protected AvlPane(){ };
    
    AvlPane(AVL tree){
        this.tree = tree;
        setStatus("Tree is empty");
        setBackground(new Background(new BackgroundFill(Color.web("#" + "9ACD32"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4, Color.SEAGREEN);
        }
    }
    @Override
    public void displayTree(int value){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4, Color.SEAGREEN,value);
        }
    }
}