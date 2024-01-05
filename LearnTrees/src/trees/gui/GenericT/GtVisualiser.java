package trees.gui.GenericT;

import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import trees.tree.genericT.GenericTree;

public class GtVisualiser extends Application {
    @Override
    public void start(Stage primaryStage){
        GenericTree<Integer> tree = new GenericTree<>();
        BorderPane pane = new BorderPane();
        GtPane view = new GtPane(tree);
        setPane(pane, view, tree);
        setStage(pane, primaryStage, "GT Visualisation");
    }

    public void setStage(BorderPane pane, Stage primaryStage, String title){
        Scene scene = new Scene(pane, 800,500);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPane(BorderPane pane, GtPane view, GenericTree<Integer> tree){
        pane.setCenter(view);
        TextField textParent = new TextField();
        TextField textChild = new TextField();
        TextField textDel = new TextField();
        TextField textOldValue = new TextField();
        TextField textNewValue = new TextField();

        textParent.setPrefColumnCount(3);
        textChild.setPrefColumnCount(3);
        textDel.setPrefColumnCount(3);
        textOldValue.setPrefColumnCount(3);
        textNewValue.setPrefColumnCount(3);

        //textField.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button update = new Button("Update");
        Button search = new Button("Search");
        Button traverseBFS = new Button("Traverse BFS");
        Button back = new Button("Back");
        Button undo = new Button("Updo");
        Button repo = new Button("Repo");

        addFunctionInsert(textParent,textChild,insert,tree,view);
        addFunctionDelSearch(textDel,delete, search,tree, view);
        addFunctionUpdate(textOldValue,textNewValue,update,traverseBFS,tree,view);


        HBox hBoxTop = new HBox(5);
        hBoxTop.getChildren().addAll(new Label("Parent value"), textParent, new Label("Child value"), textChild,insert);
        hBoxTop.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxMiddle = new HBox(5);
        hBoxMiddle.getChildren().addAll(new Label("Enter a value"), textDel,update, delete, search);
        hBoxMiddle.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxMiddle1 = new HBox(5);
        hBoxMiddle1.getChildren().addAll(new Label("Old value"), textOldValue,new Label("New value"), textNewValue ,update);
        hBoxMiddle1.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxBottom = new HBox(5);
        hBoxBottom.getChildren().addAll(traverseBFS);
        hBoxBottom.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxActions = new HBox(5);
        hBoxActions.getChildren().addAll(undo, repo, back);
        hBoxActions.setAlignment(Pos.BASELINE_CENTER);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBoxTop, hBoxMiddle, hBoxMiddle1,hBoxBottom, hBoxActions);
        vBox.setAlignment(Pos.CENTER);

        pane.setBottom(vBox);
    }

    public void addFunctionDelSearch(TextField textField, Button delete, Button search,GenericTree<Integer> tree, GtPane view){
        search.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                if (tree.search(key)) {
                    view.displayTree();
                    view.setStatus(key + " is found");
                } else {
                    view.displayTree();
                    view.setStatus(key + " is not found!");
                }
                textField.clear();
            }
        });

        delete.setOnAction(e->{
            int key = Integer.parseInt(textField.getText());
            if(!tree.search(key)){
                view.displayTree();
                view.setStatus(key +" is not present!");
            }
            else{
                tree.delete(key);
                view.displayTree();
                view.setStatus(key+" is replaced by its predecessor and is deleted!");
            }
            textField.clear();
        });
    }

    public void addFunctionInsert(TextField textParent,TextField textChild, Button insert, GenericTree<Integer> tree, GtPane view) {
        insert.setOnAction(e -> {
            if (textParent.getText().length() == 0 || textChild.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered enought!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            } else {
                int keyP = Integer.parseInt(textParent.getText());
                int keyC = Integer.parseInt(textChild.getText());
                if(tree.search(keyP)){
                    if (tree.search(keyC)) {
                        view.displayTree();
                        view.setStatus(keyC + " is already present!");
                    } else {
                        tree.insert(keyP, keyC);
                        view.displayTree();
                        view.setStatus(keyC + " is inserted!");
                    }
                }else{
                    if (tree.search(keyC)) {
                        tree.insert(keyP, keyC);
                        view.displayTree();
                        view.setStatus(keyC + " is already present!\n" + keyP + " is inserted");
                    } else {
                        tree.insert(keyP, keyC);
                        view.displayTree();
                        view.setStatus(keyC + " and " + keyP + " is inserted!");
                    }
                }

                textParent.clear();
                textChild.clear();
            }
        });
    }

    public void addFunctionUpdate(TextField textOld,TextField textNew, Button update,Button traverseBFS, GenericTree<Integer> tree, GtPane view) {
        update.setOnAction(e -> {
            if (textOld.getText().length() == 0 || textNew.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered enought!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            } else {
                int keyOld = Integer.parseInt(textOld.getText());
                int keyNew = Integer.parseInt(textNew.getText());
                if (tree.search(keyOld)) {
                    if(!tree.search(keyNew)){
                        tree.update(keyOld,keyNew);
                        view.displayTree();
                        view.setStatus(keyOld + " have changed to " + keyNew );
                    }else{
                        view.displayTree();
                        view.setStatus(keyNew + " is already present!");
                    }

                } else {
                    view.displayTree();
                    view.setStatus(keyOld + "not found!");
                }
                textOld.clear();
                textNew.clear();
            }
        });

        traverseBFS.setOnAction(e->{
            view.displayTree();
            view.setStatus(tree.traverseBFS());
        });


    }

}
