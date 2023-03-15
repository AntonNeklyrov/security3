package com.example.security3;

import java.util.ArrayList;
import java.util.List;

public class Cryptographer {

    private final int p = 3;
    private final int q = 7;
    private List<List<Integer>> encryptedMessage;

    public Cryptographer() {
    }

    public List<List<Integer>> getEncryptedMessage(String message,double e, double n) {
        char[] charsMessage = message.toCharArray();
        List<List<Integer>> encryptedMessageList = new ArrayList<>();
        int encryptedInt;

        for (int asciiMessage : charsMessage) {
            List<Integer> encryptedSubArray = new ArrayList<>();
            for (int m : getIntArray(asciiMessage)) {
                encryptedInt = (int) ((Math.pow(m, e)) % n);
                encryptedSubArray.add(encryptedInt);
            }

            encryptedMessageList.add(encryptedSubArray);
        }

        return encryptedMessageList;
    }

    public String getDecryptedMessage(List<List<Integer>> inputMessage) {

        int[] openedKeyArray = getOpenedKey();
        int n = openedKeyArray[1];
        int encryptedKey = openedKeyArray[0];
        int euler = Euler();
        int decryptedKey = getD(encryptedKey, euler);

        StringBuilder decryptedMessage = new StringBuilder();

        for (List<Integer> decrypted : inputMessage) {

            StringBuilder decryptedASCII = new StringBuilder();

            for (Integer c : decrypted) {
                int encrypted = (char) ((Math.pow(c, decryptedKey)) % n);
                decryptedASCII.append(encrypted);
            }

            int asci = Integer.parseInt(decryptedASCII.toString());
            String str = Character.toString((char) asci);
            decryptedMessage.append(str);
        }

        return decryptedMessage.toString();
    }

    private List<Integer> getIntArray(int inputInt) {
        List<Integer> integerList = new ArrayList<>();
        int a = inputInt;
        while (a > 0) {
            integerList.add(0, a % 10);
            a = a / 10;
        }

        return integerList;
    }

    public int[] getOpenedKey() {

        int n = p * q;
        int euler = Euler();
        int encryptedKey = getE(euler);

        return new int[]{encryptedKey, n};
    }

    private int getE(int euler) {
        int e;

        for (e = 2; e < euler; e++) {
            if (gcd(e, euler) == 1) {
                break;
            }
        }

        return e;
    }

    public int[] getClosedKey() {

        int n = p * q;
        int euler = Euler();
        int decryptedKey = getD(getE(euler),euler);

        return new int[]{decryptedKey, n};
    }

    private int Euler() {
        return (p - 1) * (q - 1);
    }


    private int gcd(int n1, int n2) {
        int gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    private int getD(int e, int euler) {
        int d;
        int a = Integer.MAX_VALUE;
        for(d = 2; d < a; d++) {
            if( ( (d * e) % euler) == 1 ) {
                break;
            }
        }

        return d;
    }


    public List<List<Integer>> getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(List<List<Integer>> encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

}
