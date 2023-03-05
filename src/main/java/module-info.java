module com.example.security3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.security3 to javafx.fxml;
    exports com.example.security3;
}