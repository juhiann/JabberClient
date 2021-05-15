package com.bham.fsd.assignments.jabberserver.clientside;

import com.bham.fsd.assignments.jabberserver.JabberMessage;

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

    /**
     *  Constructor
     */
    public Client()
    {
        try {
            socket = new Socket(HOST, PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

//    /**
//     *
//     */
//    public void listenForInputFromServer()
//    {
//        Scanner console = new Scanner(System.in);
//
//        while(true)
//        {
//            while(!console.hasNextLine())
//            {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            String input = console.nextLine();
//
//            if (input.equalsIgnoreCase("quit")) {
//                break;
//            }
//        }
//        System.out.println("Client Closed");
//        clientHandler.close();
//    }
}
