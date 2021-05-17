package com.bham.fsd.assignments.jabberserver.clientside;

import com.bham.fsd.assignments.jabberserver.JabberMessage;
import com.bham.fsd.assignments.jabberserver.clientside.Client;
import com.bham.fsd.assignments.jabberserver.clientside.ClientHandler;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    Client client;
    ClientHandler clientHandler;
    ObjectInputStream in;
    JabberMessage incomingJabberMessage = null;
    JabberMessage outgoingJabberMessage = null;

    @FXML private Button login;
    @FXML private TextField username;
    @FXML private TextField jabtext;
    @FXML private Button register;
    @FXML private Button logout;
    @FXML private Button postJab;
    @FXML private ListView<Timeline> timelineList;
    @FXML private ListView<Users> followList;
    List<Timeline> Alist = new ArrayList<>();


    public Controller()
    {
        client = new Client();
        clientHandler = new ClientHandler(client.getSocket());
        new Thread(clientHandler).start();
        try {
            in = new ObjectInputStream(client.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Timeline> AObserlist = FXCollections.observableList(Alist);
    }

    public static class Timeline extends HBox
    {
        private Label unameAndJab = new Label();
        private Label jabMessage = new Label();
        private ImageView imview = new ImageView();
        private Button button = new Button();
        private Image img = new Image("heart.png", 15, 15, false, false);
        Timeline(String uName, String jText, String likes)
        {
            super();
            unameAndJab.setText(uName);
            unameAndJab.setMaxWidth(110);
            jabMessage.setText(jText);
            jabMessage.setWrapText(true);
            jabMessage.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(jabMessage, Priority.ALWAYS);
            HBox.setHgrow(unameAndJab, Priority.ALWAYS);
            imview.setImage(img);
            button = new Button(likes , imview);
            button.setPrefSize(50, 30);

            this.getChildren().addAll(unameAndJab, jabMessage, button); //, likecounter);
        }
    }

    public static class Users extends HBox
    {
        private Label whotofollow = new Label();
        private ImageView imview = new ImageView();
        private Button button = new Button();
        private Image img = new Image("plus.png", 15, 15, false, false);

        Users(String uName)
        {
            super();
            whotofollow.setText(uName);
            whotofollow.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(whotofollow, Priority.ALWAYS);
            imview.setImage(img);
            button.setGraphic(imview);
            button.setPrefSize(50, 30);

            this.getChildren().addAll(whotofollow, button); //, likecounter);
        }
    }

    public void loginAction(ActionEvent event) throws Exception
    {
        outgoingJabberMessage = new JabberMessage("signin " + username.getText());
        clientHandler.sendJabberMessageToServer(outgoingJabberMessage);

        try {
            incomingJabberMessage = (JabberMessage)in.readObject();
            System.out.println("[CLIENT]: " + incomingJabberMessage.getMessage());

            if (incomingJabberMessage.getMessage().equals("signedin"))
            {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setContentText("Sign in Successful");
                a.show();
                {
                    outgoingJabberMessage = new JabberMessage("timeline");
                    clientHandler.sendJabberMessageToServer(outgoingJabberMessage);
                    postLoginEnablement();
                    incomingJabberMessage = (JabberMessage) in.readObject();
                    System.out.println("[CLIENT]: " + incomingJabberMessage.getMessage());

                    List<Timeline> list = new ArrayList<>();
                    for (ArrayList<String> s : incomingJabberMessage.getData()) {
                        list.add(new Timeline(s.get(0) + ": ", s.get(1), s.get(3)));
                    }
                    ObservableList<Timeline> myObservableList = FXCollections.observableList(list);
                    timelineList.setItems(myObservableList);
                }

                {
                    outgoingJabberMessage = new JabberMessage("users");
                    clientHandler.sendJabberMessageToServer(outgoingJabberMessage);
                    incomingJabberMessage = (JabberMessage)in.readObject();
                    System.out.println("[CLIENT]: " + incomingJabberMessage.getMessage());

                    List<Users> list = new ArrayList<>();
                    for(ArrayList<String> s : incomingJabberMessage.getData())
                    {
                        list.add(new Users(s.get(0)));
                    }

                    ObservableList<Users> userlist = FXCollections.observableList(list);
                    followList.setItems(userlist);
                }

            }
            else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Sign in Failed");
                a.show();
                System.out.println("[CLIENT]: Incorrect Login. Try again.");
            }
        } catch (IOException e) {
            System.out.println("Here 1");
            in.close();
            clientHandler.close();
        } catch (ClassNotFoundException e2) {
            System.out.println("Here 2");
            in.close();
            clientHandler.close();
        }
    }

    public void logoutAction(ActionEvent event) throws Exception
    {
        outgoingJabberMessage = new JabberMessage("signout");
        clientHandler.sendJabberMessageToServer(outgoingJabberMessage);


        System.out.println("[CLIENT]: Logging out");

        Platform.exit();
        System.exit(0);
    }

    public void postjabAction(ActionEvent event) throws Exception
    {
        if(jabtext.getText().trim()=="")
        {
            //Do nothing
        }
        else
        {

        }
    }


    public void registerAction(ActionEvent event) throws Exception
    {
        outgoingJabberMessage = new JabberMessage("register " + username.getText());
        clientHandler.sendJabberMessageToServer(outgoingJabberMessage);

        try {
            incomingJabberMessage = (JabberMessage)in.readObject();

            if (incomingJabberMessage.getMessage().equals("signedin"))
            {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setContentText("Registration Successful");
                a.show();
                System.out.println("[CLIENT]: Registration Success.");

                {
                    postLoginEnablement();
                    outgoingJabberMessage = new JabberMessage("users");
                    clientHandler.sendJabberMessageToServer(outgoingJabberMessage);
                    incomingJabberMessage = (JabberMessage)in.readObject();
                    System.out.println("[CLIENT]: " + incomingJabberMessage.getMessage());

                    List<Users> list = new ArrayList<>();
                    for(ArrayList<String> s : incomingJabberMessage.getData())
                    {
                        list.add(new Users(s.get(0)));
                    }

                    ObservableList<Users> userlist = FXCollections.observableList(list);
                    followList.setItems(userlist);
                }
            }
            else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Registration Failed");
                a.show();
                System.out.println("[CLIENT]: Registration failed.");
            }
        } catch (IOException e) {
            in.close();
            clientHandler.close();
        } catch (ClassNotFoundException e2) {
            in.close();
            clientHandler.close();
        }
    }

    public void postLoginEnablement()
    {
        login.setDisable(true);
        username.setDisable(true);
        register.setDisable(true);
        jabtext.setDisable(false);
        logout.setDisable(false);
        postJab.setDisable(false);
        timelineList.setDisable(false);
        followList.setDisable(false);
    }


}
