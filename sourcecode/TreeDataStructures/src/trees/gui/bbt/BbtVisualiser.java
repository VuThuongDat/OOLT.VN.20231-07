package trees.gui.bbt;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import trees.gui.avl.AvlVisualiser;
import trees.tree.bbt.BBT;

public class BbtVisualiser extends AvlVisualiser {
	private static BBT<Integer> tree;
    private static BbtPane view;

    @Override
    public void start(Stage primaryStage){
        tree = new BBT<>();
        BorderPane pane = new BorderPane();
        view = new BbtPane(tree);
        setPane(pane, view, tree,primaryStage);
        setStage(pane, primaryStage, "BBT Visualisation");
    }
}
