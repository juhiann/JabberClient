package com.bham.fsd.assignments.jabberserver.clientside;

import com.bham.fsd.assignments.jabberserver.JabberMessage;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    JabberMessage jbmsg = new JabberMessage("signin edballs");
    int messageiterator = 1;
    /**
     * Constructor
     * @param socket
     * @param client
     */
    public ClientHandler(Socket socket, Client client)
    {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is responsible for sending the
     * JabberMessage object to the server.
     *
     */
    public void sendJabberMessageToServer()
    {
        try {
            out.flush();
            out.writeObject(jbmsg);
            out.flush();
            if (jbmsg.getMessage().contains("signout"))
            {
                close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }

        messageiterator++;

        if (messageiterator == 2)
            jbmsg = new JabberMessage("signin Non-Existing-User"); // unknown-user
        if (messageiterator == 3)
            jbmsg = new JabberMessage("register edballs"); //already exists
        if (messageiterator == 4)
            jbmsg = new JabberMessage("register DummyUser1"); //signin
        if (messageiterator == 5)
            jbmsg = new JabberMessage("invalidMessage blabla"); //invalid message
        if (messageiterator == 6)
            jbmsg = new JabberMessage("signout"); //no response
    }

    @Override
    public void run()
    {
        boolean keepLooping = true;

        while(keepLooping) {
            try {
                JabberMessage jm = (JabberMessage) in.readObject();
                System.out.println("[CLIENT]: Message: " + jm.getMessage());
            } catch (IOException e) {
                break;
            } catch (ClassNotFoundException e2) {
                break;
            }
        }
        close();
    }

    /**
     * Closes all the connections to the Server
     */
    public void close()
    {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
