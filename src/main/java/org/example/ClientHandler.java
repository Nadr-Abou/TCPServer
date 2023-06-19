package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Locale;

import com.google.gson.Gson;


public class ClientHandler extends Thread{

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        InetAddress inetAddress = this.clientSocket.getInetAddress();
        System.out.println("Connected from: " + inetAddress);
    }

    boolean manage(){

        BufferedReader in;

        try {
            in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            return false;
        }


        PrintWriter out = null; // allocate to write answer to client.


        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Ciao client");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        String s = "";
        Gson g = new Gson();

        while(true){
            try {
                if ((s = in.readLine()) == null) {
                    break;
                }
            }catch (IOException e){
                return false;
            }
            System.out.println(s);
            out.println(s.toUpperCase());
        }
        return true;
    }

    @Override
    public void run() {
        manage();

        GameModel.getInstance().removeClient(this);
    }
}
