package kg.mega.carapp.services;

import kg.mega.carapp.dao.DbHelper;
import kg.mega.carapp.models.Car;

import java.util.List;

public class CarService {

    private final static CarService INSTANCE = new CarService();

    private CarService() {
    }

    public static CarService getInstance(){
        return INSTANCE;
    }

    public void add(Car car){
        DbHelper.getINSTANCE().insertCar(car);
        //cars.add(car);
    }

    public List<Car> getCars() {
        return DbHelper.getINSTANCE().selectCars();
    }

    public void update(Car car){
        DbHelper.getINSTANCE().update(car);
    }

    public void delete(Car car){
        DbHelper.getINSTANCE().delete(car.getId());
    }
}
