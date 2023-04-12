package kg.mega.carapp.dao;

import kg.mega.carapp.models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    private static DbHelper INSTANCE;

    private DbHelper() {
    }

    public static synchronized DbHelper getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new DbHelper();
        return INSTANCE;
    }

    private Connection connect(){
        Connection connection;
        String url = "jdbc:sqlite:C:/Users/Aktan/forDb/carDb.db";
        try {
            connection = DriverManager.getConnection(url);
            //System.out.println("CONNECTION SUCCESSFULLY CONNECTED!");
        } catch (SQLException e){
            //System.out.println("ERROR!");
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void insertCar(Car car){
        String query = "INSERT INTO cars(brand, model, model_year, car_body_style, price, is_cleared, mileage, color, engine) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(query)){

            setMaterial(car, ps);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> selectCars(){
        List<Car> carList = new ArrayList<>();

        String query = "SELECT id, brand, model, model_year, car_body_style, price, is_cleared, mileage, color, engine FROM cars";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setModelYear(resultSet.getInt("model_year"));
                car.setCarBodyStyle(resultSet.getString("car_body_style"));
                car.setPrice(resultSet.getDouble("price"));
                car.setIsCleared(resultSet.getBoolean("is_cleared"));
                car.setMileage(resultSet.getDouble("mileage"));
                car.setColor(resultSet.getString("color"));
                car.setEngine(resultSet.getString("engine"));

                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public void update(Car car){
        String query = "UPDATE cars SET brand=?, model=?, model_year=?, car_body_style=?, price=?, is_cleared=?, mileage=?, color=?, engine=? WHERE id=?";

        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(query)){

            setMaterial(car, ps);
            ps.setInt(10, car.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String query = "DELETE FROM cars WHERE id = ?";
        try(Connection connection = connect();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void setMaterial(Car car, PreparedStatement ps) throws SQLException {
        ps.setString(1, car.getBrand());
        ps.setString(2, car.getModel());
        ps.setInt(3, car.getModelYear());
        ps.setString(4, car.getCarBodyStyle());
        ps.setDouble(5, car.getPrice());
        ps.setBoolean(6, car.getIsCleared());
        ps.setDouble(7, car.getMileage());
        ps.setString(8, car.getColor());
        ps.setString(9, car.getEngine());
    }
}
