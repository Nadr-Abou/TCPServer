package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler {

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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        String s = "";


        try {
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                out.println(s.toUpperCase());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(clientSocket.getInetAddress());
        return true;
    }

}
