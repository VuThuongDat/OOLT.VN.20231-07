package trees.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import trees.gui.GenericT.GtVisualiser;
import trees.gui.avl.AvlVisualiser;
import trees.gui.bst.BstVisualiser;
import trees.gui.bbt.BbtVisualiser;
public class View {
    @FXML
    private Button avl;
    @FXML
    private Button bst;
    @FXML
    private Button gt;
    @FXML
    private Button bbt;
    @FXML
    
    private void gtAction() { gt.setOnAction(e->setStage1(new GtVisualiser()));}

    @FXML
    private void bstAction(){
        bst.setOnAction(e-> setStage(new BstVisualiser()));
    }
    @FXML
    private void avlAction(){
        avl.setOnAction(e-> setStage(new AvlVisualiser()));
    }
    @FXML
    private void bbtAction(){
        bbt.setOnAction(e-> setStage(new BbtVisualiser()));
    }
    private void setStage(BstVisualiser menu){
        Stage menuStage = new Stage();
        menu.start(menuStage);
        menuStage.show();
    }
//    @Override
    private void setStage1(GtVisualiser menu){
        Stage menuStage = new Stage();
        menu.start(menuStage);
        menuStage.show();
    }
}
