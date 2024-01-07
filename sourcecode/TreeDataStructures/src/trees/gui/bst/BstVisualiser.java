package trees.gui.bst;

import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import trees.tree.bst.BST;

public class BstVisualiser extends Application {
	//Stage primaryStage = new Stage();
    @Override
    public void start(Stage primaryStage){
    	//this.primaryStage=primaryStage;
        BST<Integer> tree = new BST<>();
        BorderPane pane = new BorderPane();
        BstPane view = new BstPane(tree);
        setPane(pane, view, tree,primaryStage);
        setStage(pane, primaryStage, "BST Visualisation");
    }

    public void setStage(BorderPane pane, Stage primaryStage, String title){
        Scene scene = new Scene(pane, 500,500);
        primaryStage.setTitle(title);;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPane(BorderPane pane, BstPane view, BST<Integer> tree, Stage primaryStage){
        pane.setCenter(view);
        TextField textField = new TextField();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        textField.setPrefColumnCount(3);
        textField1.setPrefColumnCount(3);
        textField2.setPrefColumnCount(3);
        textField.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button update = new Button("Update");
        Button search = new Button("Search");
        Button traverseBFS = new Button("Traverse BFS");
        Button traverseDFS = new Button("Traverse DFS");
        Button back = new Button("Back");
        Button undo = new Button("Updo");
        Button repo = new Button("Repo");
        addFunctionalities(textField,textField1,textField2, insert, delete,traverseBFS,traverseDFS,search,update,back,tree, view,primaryStage);

        HBox hBoxTop = new HBox(5);
        hBoxTop.getChildren().addAll(new Label("Enter a value"), textField,insert, delete, search);
        hBoxTop.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxMiddle = new HBox(5);
        hBoxMiddle.getChildren().addAll(traverseBFS,traverseDFS);
        hBoxMiddle.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxBottom = new HBox(5);
        hBoxBottom.getChildren().addAll(new Label("Old value"), textField1, new Label("New value"), textField2,update);
        hBoxBottom.setAlignment(Pos.BASELINE_CENTER);

        HBox hBoxActions = new HBox(5);
        hBoxActions.getChildren().addAll(undo, repo, back);
        hBoxActions.setAlignment(Pos.BASELINE_CENTER);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBoxTop, hBoxMiddle, hBoxBottom, hBoxActions);
        vBox.setAlignment(Pos.CENTER);

        pane.setBottom(vBox);
    }

    public void addFunctionalities(TextField textField,TextField textField1,TextField textField2, Button insert, Button delete,Button traverseBFS,Button traverseDFS,Button search,Button update,Button back,BST<Integer> tree, BstPane view,Stage primaryStage){

        insert.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                if (tree.search(key)) {
                    view.displayTree();
                    view.setStatus(key + " is already present!" );
                } else {
                    tree.insert(key);
                    view.displayTree();
                    view.setStatus(key + " is inserted!"+ tree.height(tree.getRoot()));
                }
                textField.clear();
            }
        });

        delete.setOnAction(e->{
        	if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
        	}
        	else {
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
        	}
        });
        search.setOnAction(e->{
            if(textField.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered anything!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key = Integer.parseInt(textField.getText());
                if (tree.search(key)) {
                	view.displayTree(key);
                    view.setStatus(key + " is found!");
                } else {
                	view.displayTree();
                    view.setStatus(key + " is not found!");
                }
                textField.clear();
            }
        });
        update.setOnAction(e->{
            if(textField1.getText().length() == 0||textField2.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "You haven't entered old value or new value!", ButtonType.OK);
                alert.getDialogPane().setMinHeight(80);
                alert.show();
            }
            else {
                int key1 = Integer.parseInt(textField1.getText());
                int key2 = Integer.parseInt(textField2.getText());
                if (tree.search(key1)&&!tree.search(key2)) {
                    tree.update(key1,key2);
                    view.displayTree();
                    view.setStatus(key1 + " is updated!");
                } else if (!tree.search(key1)){
                    view.displayTree();
                    view.setStatus(key1 + " is not found!");
                } else if (tree.search(key1)&&tree.search(key2)){
                	view.displayTree();
                    view.setStatus(key2 + " is already present!");
                }
                textField1.clear();
                textField2.clear();
            }
        });
        traverseBFS.setOnAction(e->{
        	view.displayTree();
            view.setStatus(tree.traverseBFS());
        });
        traverseDFS.setOnAction(e->{
        	view.displayTree();
            view.setStatus(tree.traverseDFS());
        });
        back.setOnAction(e->{
        	primaryStage.close();
        });
    }
}
	
