package com.bham.fsd.assignments.jabberserver.JabberFX;

import com.bham.fsd.assignments.jabberserver.controller.IncomeMessageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

  @FXML
  private Button login;
  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private Button register;

  public void loginAction(ActionEvent event) throws Exception{

    String text = username.getText();
    IncomeMessageController user = new IncomeMessageController(text);
    if(user.) {

      Stage stage = new Stage();
      ((Node) event.getSource()).getScene().getWindow().hide();
      Parent root  = FXMLLoader.load(getClass().getResource("User.fxml"));
      Scene  scene = new Scene(root, 700, 500);
      stage.setScene(scene);
      stage.show();
    }


  }
  public void registerAction(ActionEvent event) throws Exception{
    Stage stage = new Stage();
    ((Node)event.getSource()).getScene().getWindow().hide();
    Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
    Scene scene = new Scene(root, 700,500);
    stage.setScene(scene);
    stage.show();

  }
}
