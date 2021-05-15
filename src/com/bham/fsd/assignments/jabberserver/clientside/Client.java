package com.bham.fsd.assignments.jabberserver.clientside;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private static final String HOST = "localhost";
    private static final int PORT = 44444;
    private ClientHandler clientHandler;

    /**
     *  Constructor
     */
    public Client()
    {
        try {
            socket = new Socket(HOST, PORT);
            clientHandler = new ClientHandler(socket, this);
            new Thread(clientHandler).start();
            listenForInputFromServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void listenForInputFromServer()
    {
        Scanner console = new Scanner(System.in);
        while (true)
        {
            while(!console.hasNextLine())
            {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String input = console.nextLine();

            if (input.equalsIgnoreCase("quit"))
            {
                break;
            }
            clientHandler.sendJabberMessageToServer(input);
        }
        System.out.println("Client Closed");
        clientHandler.close();
    }
}
