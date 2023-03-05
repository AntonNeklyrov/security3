package com.example.security3;

import java.util.Random;

public class Cryptographer {


    private String getOpenedKey(int p, int q) {

        int n = p * q;
        int eulerVal = Euler(p,q);

        return null;
    }

    private int Euler(int p, int q) {
        return (p - 1) * (q - 1);
    }

    private int getE(int p, int q, int n, int eulerVal) {
        Random random = new Random();
        int key;
        while(true) {
             key = random.nextInt(1, eulerVal);


        }

    }

}
