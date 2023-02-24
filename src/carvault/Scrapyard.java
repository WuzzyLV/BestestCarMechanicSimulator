package carvault;

import objects.Car;

import java.util.ArrayList;
import java.util.Random;


public class Scrapyard {
	Random rand = new Random();
	ArrayList<Car> scrapyT1 = new ArrayList<>();
	ArrayList<Car> scrapyT2 = new ArrayList<>();
	ArrayList<Car> scrapyT3 = new ArrayList<>();
	ArrayList<Car> scrapyT4 = new ArrayList<>();
	ArrayList<Car> scrapyT5 = new ArrayList<>();

	 public void initCars(ArrayList<Car> allCars){
		 for (Car allCar : allCars) {
			 int currentQuality = rand.nextInt(7) + 1;
			 Car car = new Car(allCar.brand, allCar.model, currentQuality, allCar.maxPrice, allCar.maxTopSpeed, allCar.tier);
			 car.calculatePrice();

			 switch (car.tier) {
				 case 1 -> scrapyT1.add(car);
				 case 2 -> scrapyT2.add(car);
				 case 3 -> scrapyT3.add(car);
				 case 4 -> scrapyT4.add(car);
				 case 5 -> scrapyT5.add(car);
			 }
		 }

	 }
	 public ArrayList<Car> getCars(int tier){
		 switch (tier) {
			 case 1 -> {
				 return scrapyT1;
			 }
			 case 2 -> {
				 return scrapyT2;
			 }
			 case 3 -> {
				 return scrapyT3;
			 }
			 case 4 -> {
				 return scrapyT4;
			 }
			 case 5 -> {
				 return scrapyT5;
			 }
		 }
		 return null;
	 }

	  public void removeCar(Car car, int tier){
		  switch (tier) {
			  case 1:
				  scrapyT1.remove(car);
			  case 2:
				  scrapyT2.remove(car);
			  case 3:
				  scrapyT3.remove(car);
			  case 4:
				  scrapyT4.remove(car);
			  case 5:
				  scrapyT5.remove(car);
		  }
	  }
}
