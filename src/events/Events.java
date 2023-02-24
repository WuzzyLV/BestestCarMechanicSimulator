package events;

import carvault.Garage;
import objects.Car;
import objects.Player;

public class Events {

    public static boolean boughtCar(Car car, Player player, Garage garage){

        if(player.money>=car.price){
            player.takeMoney(car.price);
            garage.addCar(car);
            player.addExperience(30);
            return true;
        }else{
            return false;
        }
    }
    public static boolean repairCar(Car car, Player player){
        if (car.quality==10){
            return false;
        }
        double price = car.repairPrice();
        if(player.money>=price){
            player.takeMoney(price);
            car.improveCar(true);
            player.addExperience(20*player.playerLevel);
            return true;
        }
        return false;
    }
    public static boolean upgradeCar(Car car, Player player) {
        if (car.quality >= 10 && car.quality < car.maxQuality && player.canAfford(car.upgradeCost())) {
            System.out.println("arrived here");
            car.improveCar(false);
            player.takeMoney(car.upgradeCost());
            player.addExperience(20*player.playerLevel);
            return true;
        }
        return false;
    }
    public static boolean evolveCar(Car car, Player player) {
        if (car.canEvolve && player.canAfford(car.evolveCost())) {
            car.evolveCar();
            player.takeMoney(car.evolveCost());
            player.addExperience(100*player.playerLevel);
            return true;
        }
        return false;
    }

    public static boolean sellCar(Car car, Player player, Garage garage){
        garage.removeCar(car);
        player.money+=car.price;
        player.addExperience(30*player.playerLevel);
        return true;
    }
    
    public static int race(Car car, boolean hasWon, Player player){
        //If has won then add money and return 1
        if(hasWon){
            player.wins++;
            player.money+=7500*car.tier;
            player.moneyWon+=7500*car.tier;
            car.tierWins++;
            player.addExperience(50*player.playerLevel);
            return 1;
        }else{
        System.out.println("You lost registered");
        player.money-=2500*car.tier;
        player.loses++;
        car.tierLoses++;
        player.addExperience(20*player.playerLevel);
        return 0;
        }
    }

}

