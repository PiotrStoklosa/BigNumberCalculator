module Calculator {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    exports main;
    opens controllers to javafx.fxml;
}