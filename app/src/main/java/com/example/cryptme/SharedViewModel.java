package com.example.cryptme;

import androidx.lifecycle.ViewModel;

import javax.crypto.SecretKey;

public class SharedViewModel extends ViewModel {
    private SecretKey secretKey;

    public SecretKey getSecretKey(){

        return this.secretKey;
    }
    public void setSecretKey(SecretKey secretKey)
    {
        this.secretKey = secretKey;
    }
}
