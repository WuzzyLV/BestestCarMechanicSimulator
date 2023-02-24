package utils;

import objects.Car;

import java.util.ArrayList;

public class Testing {
    public static void printArr(ArrayList<Car> allCars){
        for(int i =0;i< allCars.size();i++) {
            System.out.println(allCars.get(i).brand + " " + allCars.get(i).model + " Quality " + allCars.get(i).quality +   " Original price: "+allCars.get(i).maxPrice+" Top speed: " + allCars.get(i).topSpeed);
        }
    }
    public static void printArray(ArrayList<Car> allCars){
        for(int i =0;i< allCars.size();i++) {
            System.out.println(allCars.get(i).brand + " " + allCars.get(i).model + " Quality: " + allCars.get(i).quality + " price: " + allCars.get(i).price + " Original price: "+allCars.get(i).maxPrice+" Top speed: " + allCars.get(i).topSpeed);
        }
    }
}
