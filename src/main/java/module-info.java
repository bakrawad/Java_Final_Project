module com.example.ph23 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ph23 to javafx.fxml;
    exports com.example.ph23;
}