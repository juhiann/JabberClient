package com.bham.fsd.assignments.jabberserver.clientside;

import com.bham.fsd.assignments.jabberserver.JabberMessage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable
{
    Socket socket;
    ObjectOutputStream out;

    /**
     * Constructor
     * @param socket
     */
    public ClientHandler(Socket socket)
    {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is responsible for sending the
     * JabberMessage object to the server.
     *
     */
    public void sendJabberMessageToServer(JabberMessage jmessage)
    {
        try {
            Scanner sc = new Scanner(System.in);

            out.flush();
            out.writeObject(jmessage);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    @Override
    public void run(){}

    /**
     * Closes all the connections to the Server
     */
    public void close()
    {
        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
