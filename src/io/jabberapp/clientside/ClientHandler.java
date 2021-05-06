package io.jabberapp.clientside;

import io.jabberapp.model.Message;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    boolean shouldRun = true;
    private Message msg;

    public ClientHandler(Socket socket, Client client)
    {
        this.socket = socket;
    }

    public void sendStringToServer(String input)
    {
        try {
//            out.writeUTF(input);
            out.writeObject(input);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    @Override
    public void run()
    {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            while (shouldRun)
            {
                try {
                    while (in.available() == 0)
                    {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String reply = in.readUTF();
                    System.out.println(reply);

                } catch (IOException e) {
                    e.printStackTrace();
                    close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

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
