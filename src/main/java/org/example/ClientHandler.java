package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import com.google.gson.Gson;

import javax.naming.AuthenticationNotSupportedException;

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
        Command cmd = null;
        /*while(true){
            try {
                if ((s = in.readLine()) == null) {
                    break;
                }
            }catch (IOException e){
                return false;
            }
            try{
                System.out.println(s);
                cmd = g.fromJson(s, Command.class);
            }
            catch (Exception e){}
            String result;
            if(cmd!=null){
                result = executeCmd(cmd);
                out.println(result);
            }
            else{out.println(new Answer(false, "command not reconized").asJSON());}
        }*/
        return true;
    }
    String executeCmd(Command cmd){
        if (cmd==null){
            return new Answer(false, "command not reconized").asJSON();
        }
        if(cmd.cmd.equals("login")){
            if(new Users().verify(cmd.param1, cmd.param2)){
                return new Answer(true, "Login effettuato").asJSON();
            }
            else{
                return new Answer(false, "Utente non trovato").asJSON();
            }
        }
        else{
            return new ListAnswer().listPrint(new Store().getProdotti());
        }
    }
    @Override
    public void run() {
        manage();
    }
}
