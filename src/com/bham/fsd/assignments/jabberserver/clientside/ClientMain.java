package com.bham.fsd.assignments.jabberserver.clientside;

//import com.bham.fsd.assignments.jabberserver.clientside.Client;

import com.bham.fsd.assignments.jabberserver.JabberMessage;

public class ClientMain
{
    public static void main(String[] args)
    {
        //sample message
        JabberMessage message = new JabberMessage("signin edballs");

        // hopefully all the client handler methods should be avaiable from main
        // the Client class is not longer initialising the "ClientHandler" thread
        ClientHandler clientHandler;
        Client c = new Client();
        clientHandler = new ClientHandler(c.getSocket(), c);
        new Thread(clientHandler).start(); // This starts the "run" method in ClientHandler

        clientHandler.sendJabberMessageToServer(message); //try pass the msg object into this from your GUI??
    }
}
