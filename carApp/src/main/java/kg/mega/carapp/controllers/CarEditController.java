package kg.mega.carapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kg.mega.carapp.models.Car;
import kg.mega.carapp.services.CarService;

public class CarEditController {

    @FXML
    void initialize() {
        spnPrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000, 1000000));
        spnPrice.setEditable(true);

        spnMileage.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000, 1000000));
        spnMileage.setEditable(true);

        final ObservableList<String> carBodyType = FXCollections
                .observableArrayList("SEDAN","HATCHBACK","SUV","MINIVAN","CABRIOLET","COUPE","ROADSTER",
                        "SUPERCAR","PICKUP","VAN","LIMOUSINE","CAMPERVAN","TRUCK");

        final ObservableList<String> colors = FXCollections
                .observableArrayList("PEARL BLACK","ANGEL GREEN","FASHIONABLE ORANGE","NOBLE GREY",
                        "DREAM GREEN","SKY SILVER","ROSE RED","MILKY WHITE","CRYSTAL SILVER", "SKY BLUE");

        final ObservableList<String> engineType = FXCollections
                .observableArrayList("PETROL","DIESEL","PETROL/GAS","HYBRID","ELECTRIC","GAS");

        cbCarBodyStyle.setItems(carBodyType);
        cbColor.setItems(colors);
        cbEngine.setItems(engineType);
    }

    @FXML
    private CheckBox checkIsCleared;

    @FXML
    private Spinner<Double> spnMileage;

    @FXML
    private Spinner<Double> spnPrice;

    @FXML
    private TextField txtBrand;

    @FXML
    private ComboBox<String> cbCarBodyStyle;

    @FXML
    private Label lblSelectedBodyType;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtModelYear;

    @FXML
    private ComboBox<String> cbColor;

    @FXML
    private Label lblSelectedColor;

    @FXML
    private ComboBox<String> cbEngine;

    @FXML
    private Label lblSelectedEngine;

    @FXML
    void onSelectEngine(ActionEvent event) {
        String str = cbEngine.getSelectionModel().getSelectedItem();
        lblSelectedEngine.setText(str);
    }

    @FXML
    void onSelectColor(ActionEvent event) {
        String str = cbColor.getSelectionModel().getSelectedItem();
        lblSelectedColor.setText(str);
    }

    @FXML
    void onSelectBodyType(ActionEvent event) {
        String str = cbCarBodyStyle.getSelectionModel().getSelectedItem();
        lblSelectedBodyType.setText(str);
    }

    @FXML
    void onCancel(ActionEvent event) {
        checkIsCleared.getScene().getWindow().hide();
    }

    private Integer idSaver = 0;

    @FXML
    void onSave(ActionEvent event) {
        Car car = new Car();
        car.setId(idSaver);
        car.setBrand(txtBrand.getText());
        car.setModel(txtModel.getText());
        car.setModelYear(txtModelYear.getText());
        car.setCarBodyStyle(lblSelectedBodyType.getText());
        car.setPrice(spnPrice.getEditor().getText());
        car.setMileage(spnMileage.getEditor().getText());
        car.setIsCleared(checkIsCleared.isSelected());
        car.setColor(lblSelectedColor.getText());
        car.setEngine(lblSelectedEngine.getText());

        CarService carService = CarService.getInstance();

        boolean isHas = false;
        for (int i = 0; i < carService.getCars().size(); i++) {
            if (carService.getCars().get(i).getId() == idSaver){
                isHas = true;
            }
        }

        if (isHas)
            carService.update(car);
        else
            carService.add(car);

        onCancel(null);
    }

    public void initCar(Car car) {
        idSaver = car.getId();
        txtBrand.setText(car.getBrand());
        txtModel.setText(car.getModel());
        txtModelYear.setText(String.valueOf(car.getModelYear()));
        lblSelectedBodyType.setText(car.getCarBodyStyle());
        spnPrice.getEditor().setText(String.valueOf(car.getPrice()));
        spnMileage.getEditor().setText(String.valueOf(car.getMileage()));
        checkIsCleared.setSelected(car.getIsCleared());
        lblSelectedColor.setText(car.getColor());
        lblSelectedEngine.setText(car.getEngine());
    }
}
