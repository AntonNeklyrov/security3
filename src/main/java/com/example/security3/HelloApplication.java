package main.java.com.example.security3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Cryptographer cryptographer = new Cryptographer(3,8);
        List<List<Integer>> encryptedMessage = cryptographer.getEncryptedMessage("an");
        System.out.println(encryptedMessage.toString());
        System.out.println(cryptographer.getDecryptedMessage(encryptedMessage));
    }


    public static void main(String[] args) {
        launch();
    }
}