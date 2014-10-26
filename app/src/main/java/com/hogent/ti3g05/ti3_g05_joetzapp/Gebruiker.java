package com.hogent.ti3g05.ti3_g05_joetzapp;

/**
 * Created by Gebruiker on 26/10/2014.
 */
public class Gebruiker {
    public long userId;
    public String gebruikersnaam;
    public String wachtwoord;

    public Gebruiker(long userId, String gebruikersnaam, String wachtwoord){
        this.userId=userId;
        this.gebruikersnaam=gebruikersnaam;
        this.wachtwoord=wachtwoord;
    }
}
