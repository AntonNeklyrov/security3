module main.java.com.example.security3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.security3 to javafx.fxml;
    exports main.java.com.example.security3;
}