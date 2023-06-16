package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer
{
    static int portNumber = 1234;
    static ServerSocket serverSocket;

    static boolean startServer(){
        try{
            serverSocket = new ServerSocket(portNumber);
            return true;
        }catch (Exception e){
            return false;
        }

    }


    public static void main( String[] args )
    {

        Socket clientSocket;

        if(!startServer()){
            return;
        }

        try{
            clientSocket = serverSocket.accept();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        ClientHandler clientHandler = new ClientHandler(clientSocket);
        if(!clientHandler.manage()){
            System.out.println("Cannot run client");
        }
    }

}