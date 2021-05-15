package com.bham.fsd.assignments.jabberserver.JabberFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserController {

  @FXML
  private TableView<JabberUser> Timeline;
  @FXML private TableColumn<JabberUser, String> username;
  @FXML private TableColumn<JabberUser, String> jab;
  @FXML private TableColumn<JabberUser, String> like;
  @FXML private TableColumn<JabberUser, String> likeCount;
  @FXML
  private Button signOut;



  public ObservableList<JabberUser> list = FXCollections.observableArrayList();{
    new JabberUser("trump", "maga", "si", "5");
  }


  public void initialize(URL location, ResourceBundle resources){

    username.setCellValueFactory(new PropertyValueFactory<JabberUser, String>("username"));
    jab.setCellValueFactory(new PropertyValueFactory<JabberUser, String>(
        "jab"));
    like.setCellValueFactory(new PropertyValueFactory<JabberUser, String>(
        "like"));
    likeCount.setCellValueFactory(new PropertyValueFactory<JabberUser, String>(
        "likeCount"));

    Timeline.setItems(list);
  }

  public void getUser(String user) {

    signOut.setText(user);
  }

  public void signOut(ActionEvent event) {
      ((Node)event.getSource()).getScene().getWindow().hide();
      Stage    stage  = new Stage();
      FXMLLoader loader = new FXMLLoader();
      Pane       root;
      try {
        root = loader.load(getClass().getResource("Login.fxml").openStream());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }

  }



}
