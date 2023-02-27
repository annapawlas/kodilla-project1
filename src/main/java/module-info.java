module com.kodilla.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kodilla.project to javafx.fxml;
    exports com.kodilla.project;
    exports com.kodilla.project.controllers;
    opens com.kodilla.project.controllers to javafx.fxml;
}