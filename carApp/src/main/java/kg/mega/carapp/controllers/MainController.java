package kg.mega.carapp.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.mega.carapp.HelloApplication;
import kg.mega.carapp.models.Car;
import kg.mega.carapp.services.CarService;

import java.io.IOException;

public class MainController {

    private final CarService carService = CarService.getInstance();

    @FXML
    private TableColumn<?, ?> colmBrand;

    @FXML
    private TableColumn<?, ?> colmCarBodyStyle;

    @FXML
    private TableColumn<Car, Boolean> colmIsCleared;

    @FXML
    private TableColumn<?, ?> colmMileage;

    @FXML
    private TableColumn<?, ?> colmModel;

    @FXML
    private TableColumn<?, ?> colmModelYear;

    @FXML
    private TableColumn<?, ?> colmId;

    @FXML
    private TableColumn<?, ?> colmPrice;

    @FXML
    private TableColumn<?, ?> colmColor;

    @FXML
    private TableView<Car> tbCars;

    @FXML
    private TableColumn<?, ?> colmEngine;

    @FXML
    void initialize() {
        initColumns();
        refreshTable();
//        tbCars.setStyle("-fx-selection-bar: lightskyblue");
//        String style = "-fx-control-inner-background: linear-gradient(white, white);"
//                + "-fx-control-inner-background-alt: linear-gradient(lightgray, darkgray);";
//        tbCars.setStyle(style);
    }

    private void refreshTable() {
        tbCars.setItems(FXCollections.observableList(carService.getCars()));
    }

    private void initColumns() {
        colmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colmModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colmModelYear.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
        colmCarBodyStyle.setCellValueFactory(new PropertyValueFactory<>("carBodyStyle"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        colmColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colmEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));

        colmIsCleared.setCellFactory(column -> new CheckBoxTableCell<>());
        colmIsCleared.setCellValueFactory(cellData -> {
            Car cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(cellValue.getIsCleared());

            property.addListener((observable, oldValue, newValue) -> cellValue.setIsCleared(newValue));
            return property;
        });
    }

    @FXML
    void onCreate(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("car-edit-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @FXML
    void onUpdate(ActionEvent event) {
        Car selectedCar = tbCars.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("car-edit-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());

            CarEditController carEditController = loader.getController();
            carEditController.initCar(selectedCar);

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
            refreshTable();
        } catch (IOException exception){
            throw new RuntimeException();
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        Car selectedCar = tbCars.getSelectionModel().getSelectedItem();
        if (selectedCar != null){
            carService.delete(selectedCar);
            refreshTable();
        }
    }
}
