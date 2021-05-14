package io.jabberapp.clientside;

import com.bham.fsd.assignments.jabberserver.JabberMessage;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    JabberMessage jbmsg = new JabberMessage("signin edballs");

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
     * @param input
     */
    public void sendJabberMessageToServer(String input)
    {
        try {
            out.flush();
            out.writeObject(jbmsg);
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
        jbmsg = new JabberMessage("invalidMessageTest edballs");
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
