package carvault;

import objects.Car;

import java.util.ArrayList;
import java.util.Random;

public class Racing {

	ArrayList<Car> carsTier1 = new ArrayList<Car>();
	ArrayList<Car> carsTier2 = new ArrayList<Car>();
	ArrayList<Car> carsTier3 = new ArrayList<Car>();
	ArrayList<Car> carsTier4 = new ArrayList<Car>();
	ArrayList<Car> carsTier5 = new ArrayList<Car>();

	Random rand = new Random();
	public void initCars(ArrayList<Car> allCars){
		 //Make a copy of all cars
		 for(int i =0;i<allCars.size();i++){
			 //Make a copy of current car
			 int currentQuality=rand.nextInt(10)+3;
			 Car car = new Car(allCars.get(i).brand, allCars.get(i).model, currentQuality, allCars.get(i).maxPrice, allCars.get(i).maxTopSpeed, allCars.get(i).tier);

			 switch (car.tier){
				 case 1:
					 carsTier1.add(car);
					 break;
				 case 2:
					 carsTier2.add(car);
					 break;
				 case 3:
					 carsTier3.add(car);
					 break;
				 case 4:
					 carsTier4.add(car);
					 break;
				 case 5:
					 carsTier5.add(car);
					 break;
			 }
		 }
  	}
	 public Car getRandomCar(int tier){

		 switch (tier){
			 case 1:
				 //pick random car from tier 1
				 int pick=rand.nextInt(carsTier1.size());
				 Car opponent = carsTier1.get(pick);
				 return opponent;
			 case 2:
				 //pick random car from tier 2
				 pick=rand.nextInt(carsTier2.size());
				 opponent = carsTier2.get(pick);
				 return opponent;
			 case 3:
				 //pick random car from tier 3
				 pick=rand.nextInt(carsTier3.size());
				 opponent = carsTier3.get(pick);
				 return opponent;
			 case 4:
				 //pick random car from tier 4
				 pick=rand.nextInt(carsTier4.size());
				 opponent = carsTier4.get(pick);
				 return opponent;
			 case 5:
				 //pick random car from tier 5
				 pick=rand.nextInt(carsTier5.size());
				 opponent = carsTier5.get(pick);
				 return opponent;
		 }
		 return null;
	 }

	public int getCarCount(){
		return carsTier1.size()+carsTier2.size()+carsTier3.size()+carsTier4.size()+carsTier5.size();
	}
}
