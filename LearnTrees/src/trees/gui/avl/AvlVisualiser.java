package trees.gui.avl;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import trees.gui.bst.BstVisualiser;
import trees.tree.avl.AVL;


public class AvlVisualiser extends BstVisualiser {
    private static AVL<Integer> tree;
    private static AvlPane view;

    @Override
    public void start(Stage primaryStage){
        tree = new AVL<>();
        BorderPane pane = new BorderPane();
        view = new AvlPane(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "AVL Visualisation");
    }
}
