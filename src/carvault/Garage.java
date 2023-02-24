package carvault;

import objects.Car;

import java.util.ArrayList;


public class Garage {
    ArrayList<Car> myCars = new ArrayList<>();

    // Getters
    public ArrayList<Car> getMyCars() {
        return myCars;
    }

    //Add car to garage
    public void addCar(Car car){
        myCars.add(car);
    }
    //Remove car from garage
    public void removeCar(Car car){
        myCars.remove(car);
    }

}

