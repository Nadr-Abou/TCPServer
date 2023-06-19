package org.example;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> utenti = new ArrayList<User>();

    public Users() {
        utenti.add(new User("nadr","password"));
        utenti.add(new User("mohamed","password"));
    }


    public List<User> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<User> utenti) {
        this.utenti = utenti;
    }

    public void addUser(User utente){
        utenti.add(utente);
    }

    public boolean verify(String nomeUtente, String password){
        for (User utente : utenti) {
            if(utente.getNomeUtente().equals(nomeUtente) && utente.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
