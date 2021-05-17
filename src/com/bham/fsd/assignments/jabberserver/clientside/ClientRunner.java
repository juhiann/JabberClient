package com.bham.fsd.assignments.jabberserver.clientside;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientRunner extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Jabber");
        primaryStage.setScene(new Scene(root, 900, 642));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
