module com.example.projectf {
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.graphics;


    opens com.example.projectf to javafx.fxml;
    exports com.example.projectf;
}