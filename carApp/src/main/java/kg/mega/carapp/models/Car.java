package kg.mega.carapp.models;

public class Car {
    private int id;
    private String brand;
    private String model;
    private Integer modelYear;
    private String carBodyStyle;
    private Double price;
    private Boolean isCleared;
    private Double mileage;
    private String color;
    private String engine;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        if (engine.isBlank())
            throw new RuntimeException("Engine should not be empty!");
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color.isBlank())
            throw new RuntimeException("Color should not be empty!");
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand.isBlank())
            throw new RuntimeException("Brand should not be empty!");
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.isBlank())
            throw new RuntimeException("Model should not be empty!");
        this.model = model;
    }

    public String getCarBodyStyle() {
        return carBodyStyle;
    }

    public void setCarBodyStyle(String carBodyStyle) {
        this.carBodyStyle = carBodyStyle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        double carPrice;
        try {
            carPrice = Double.parseDouble(price);
        } catch (Exception e){
            throw new RuntimeException("Incorrect number!");
        }
        setPrice(carPrice);
    }

    public void setPrice(Double price){
        if (price < 0)
            throw new RuntimeException("Price cannot be negative!");
        this.price = price;
    }

    public Boolean getIsCleared() {
        return isCleared;
    }

    public void setIsCleared(Boolean aNew) {
        isCleared = aNew;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        double carMileage;
        try {
            carMileage = Double.parseDouble(mileage);
        } catch (Exception e){
            throw new RuntimeException("Incorrect number!");
        }
        setMileage(carMileage);
    }

    public void setMileage(Double mileage){
        if (mileage < 0)
            throw new RuntimeException("Mileage cannot be negative!");
        this.mileage = mileage;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        int carModelYear;
        try {
            carModelYear = Integer.parseInt(modelYear);
        } catch (Exception e){
            throw new RuntimeException("Incorrect number!");
        }
        setModelYear(carModelYear);
    }

    public void setModelYear(Integer modelYear){
        if(modelYear < 1950 || modelYear > 2023){
            throw new RuntimeException("Incorrect number!");
        }
        this.modelYear = modelYear;
    }
}
