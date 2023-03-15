package com.example.security3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.List;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) {

        Cryptographer cryptographer = new Cryptographer();

        VBox root = new VBox();
        root.setMaxHeight(700);
        root.setMaxWidth(800);
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);

        VBox encryptedBox = new VBox();
        VBox decryptedBox = new VBox();

        TextField e = new TextField();
        e.setPromptText("e");

        TextField n = new TextField();
        n.setPromptText("n");

        TextField d = new TextField();
        d.setPromptText("d");

        TextField nRight = new TextField();
        nRight.setPromptText("n");

        TextField inputField = new TextField();
        inputField.setPromptText("Начальное сообщение");

        TextField outputField = new TextField();
        outputField.setPromptText("Выходное соощение");

        TextField inputRightField = new TextField();
        inputRightField.setPromptText("Начальное сообщение");

        TextField outputRightField = new TextField();
        outputRightField.setPromptText("Выходное соощение");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_CENTER);
        HBox hboxRight = new HBox();
        hboxRight.setAlignment(Pos.TOP_CENTER);

        Button generateOpenedKey = new Button("Сгенерировать открытый ключ");
        generateOpenedKey.setOnAction(event -> {
            int[] openedKeyArray = cryptographer.getOpenedKey();
            n.setText(String.valueOf(openedKeyArray[1]));
            e.setText(String.valueOf(openedKeyArray[0]));
        });

        Button generateClosedKey = new Button("Сгенерировать закрытый ключ");
        generateClosedKey.setOnAction(event -> {
            int[] closedKey = cryptographer.getClosedKey();
            d.setText(String.valueOf(closedKey[0]));
            nRight.setText(String.valueOf(closedKey[1]));
        });

        Button encryptButton = new Button("Зашифровать");
        encryptButton.setOnAction(event -> {
            List<List<Integer>> encryptedMessage = cryptographer.getEncryptedMessage(
                    inputField.getText(),
                    Double.parseDouble(e.getText()),
                    Double.parseDouble(n.getText()));
            outputField.setText(encryptedMessage.toString());
            cryptographer.setEncryptedMessage(encryptedMessage);
        });

        Button decryptButton = new Button("Расшифровать");
        decryptButton.setOnAction(event -> {
            String decryptedMessage = cryptographer.getDecryptedMessage(cryptographer.getEncryptedMessage());
            outputRightField.setText(decryptedMessage);
        });

        hbox.getChildren().addAll(generateOpenedKey,encryptButton);
        hboxRight.getChildren().addAll(generateClosedKey,decryptButton);

        encryptedBox.getChildren().addAll(inputField,e,n,hbox,outputField);
        decryptedBox.getChildren().addAll(inputRightField,d,nRight,hboxRight,outputRightField);

        root.getChildren().addAll(encryptedBox,decryptedBox);




        Scene scene = new Scene(root,700, 800);
        stage.setTitle("RSA");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}