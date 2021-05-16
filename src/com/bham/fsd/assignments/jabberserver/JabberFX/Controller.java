package com.bham.fsd.assignments.jabberserver.JabberFX;

import com.bham.fsd.assignments.jabberserver.JabberMessage;
import com.bham.fsd.assignments.jabberserver.clientside.Client;
import com.bham.fsd.assignments.jabberserver.clientside.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    Client c = new Client();
    ClientHandler clientHandler = new ClientHandler(c.getSocket());
    ObjectInputStream in;
    JabberMessage inJMsg = null;

    @FXML private Button login;
    @FXML private TextField username;
    @FXML private Button register;

    JabberMessage jmsg = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        new Thread(clientHandler).start();

    }

    public void loginAction(ActionEvent event) throws Exception
    {
        try {
            in = new ObjectInputStream(c.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        jmsg = new JabberMessage("signin " + username.getText());
        clientHandler.sendJabberMessageToServer(jmsg);


        try {
            inJMsg = (JabberMessage)in.readObject();
            System.out.println("[GUI]: " + inJMsg.getMessage());

            if (inJMsg.getMessage().equals("signedin"))
            {
                Stage stage = new Stage();
                ((Node) event.getSource()).getScene().getWindow().hide();
                Parent root  = FXMLLoader.load(getClass().getResource("User.fxml"));
                Scene  scene = new Scene(root, 700, 500);
                stage.setScene(scene);
                stage.show();
            }
            else{
                System.out.println("[CLIENT]: Incorrect Login. Try again.");
            }
        } catch (IOException e) {
            in.close();
            clientHandler.close();
        } catch (ClassNotFoundException e2) {
            in.close();
            clientHandler.close();
        }
    }

    public void registerAction(ActionEvent event) throws Exception
    {
        try {
            in = new ObjectInputStream(c.getSocket().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        jmsg = new JabberMessage("register " + username.getText());
        clientHandler.sendJabberMessageToServer(jmsg);


        try {
            inJMsg = (JabberMessage)in.readObject();
            System.out.println("[GUI]: " + inJMsg.getMessage());

            if (inJMsg.getMessage().equals("signedin"))
            {
                Stage stage = new Stage();
                ((Node)event.getSource()).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
                Scene scene = new Scene(root, 700,500);
                stage.setScene(scene);
                stage.show();
                System.out.println("[CLIENT]: User Registered.");
            }
            else{
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
}
