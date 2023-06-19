package org.example;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static GameModel INSTANCE;
    List<ClientHandler> ClientList = new ArrayList<>();


    private GameModel() {
    }

    public static GameModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameModel();
        }

        return INSTANCE;
    }

    void addClient(ClientHandler client) {
        this.ClientList.add(client);
    }

}
