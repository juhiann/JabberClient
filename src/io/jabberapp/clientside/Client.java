package io.jabberapp.client.clientside;

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

    public Client()
    {
        try {
            socket = new Socket(HOST, PORT);
            clientHandler = new ClientHandler(socket, this);
            new Thread(clientHandler).start();
            listenForInput();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForInput()
    {
        Scanner console = new Scanner(System.in);
        while (true)
        {
            while(!console.hasNextLine())
            {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String input = console.nextLine();

            if (input.equalsIgnoreCase("quit"))
            {
                break;
            }
            clientHandler.sendStringToServer(input);
        }
        clientHandler.close();
    }
}
