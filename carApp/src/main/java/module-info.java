module kg.mega.carapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kg.mega.carapp to javafx.fxml;
    exports kg.mega.carapp;
    exports kg.mega.carapp.controllers;
    opens kg.mega.carapp.controllers to javafx.fxml;
    exports kg.mega.carapp.models;
    opens kg.mega.carapp.models to javafx.fxml;
}