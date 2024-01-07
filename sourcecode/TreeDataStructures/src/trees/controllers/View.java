package trees.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Button quit;
    @FXML
    void quitAction(ActionEvent event) {
        // Hiển thị hộp thoại xác nhận
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Quit");
        alert.setContentText("Are you sure you want to quit?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                // Thực hiện hành động thoát ứng dụng ở đây
                System.exit(0);
            }
        });
    }
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
