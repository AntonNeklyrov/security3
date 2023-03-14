package main.java.com.example.security3;

import java.util.ArrayList;
import java.util.List;

public class Cryptographer {

    private int p;
    private int q;

    public Cryptographer(int p, int q) {
        this.p = p;
        this.q = q;
    }

    public List<List<Integer>> getEncryptedMessage(String message) {
        char[] charsMessage = message.toCharArray();
        int[] openedKeyArray = getOpenedKey();
        int n = openedKeyArray[0];
        int encryptedKey = openedKeyArray[1];
        List<List<Integer>> encryptedMessageList = new ArrayList<>();
        int encryptedInt;

        for (int asciiMessage : charsMessage) {
            List<Integer> encryptedSubArray = new ArrayList<>();
            for (int m : getIntArray(asciiMessage)) {
                encryptedInt = (int) ((Math.pow(m, encryptedKey)) % n);
                encryptedSubArray.add(encryptedInt);
            }

            encryptedMessageList.add(encryptedSubArray);
        }

        return encryptedMessageList;
    }

    public String getDecryptedMessage(List<List<Integer>> inputMessage) {

        int[] openedKeyArray = getOpenedKey();
        int n = openedKeyArray[0];
        int encryptedKey = openedKeyArray[1];
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


    private int[] getOpenedKey() {

        int n = p * q;
        int euler = Euler();
        int encryptedKey = getE(euler);

        return new int[]{n, encryptedKey};
    }

    private int Euler() {
        return (p - 1) * (q - 1);
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


    public int getP() {
        return this.p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return this.q;
    }

    public void setQ(int q) {
        this.q = q;
    }
}
