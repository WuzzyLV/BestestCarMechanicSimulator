package objects;

import java.util.Random;

public class Car {
	///// Attributes /////
	public String brand;
	public String model;
	public double quality;
	public double maxQuality;
	public double price;
	public double topSpeed;
	public double maxTopSpeed;
	public double maxPrice;
	///// Statistics /////
	public int totalWins=0;
	public int totalLoses = 0;
	public int tierWins=0;
	public int tierLoses=0;
	///// Tier/Evolves /////
	public int tier;
	public boolean canEvolve;
	public int evolveTier=0;
	public int maxEvolveTier;

	public Car(String brand, String model, double quality, double maxPrice, double maxTopSpeed, int tier){
		this.brand =brand;
		this.model=model;
		this.quality=quality;
		this.maxPrice=maxPrice;
		this.maxTopSpeed=maxTopSpeed;
		calculatePrice();
		calculateSpeed();
		calculateMaxQuality();
		this.tier = tier;
		this.canEvolve = false;

		this.maxEvolveTier=5-tier;
	}
	public void calculatePrice(){
		price = (quality/10)*maxPrice;
	}
	//calculate price based on quality and evolves
	public void calculatePrice(int evolveTier){
        price = (quality/10)*maxPrice;
        price = price + (price/10)*evolveTier;
    }
	/*public void calculateSpeed(){
		topSpeed = quality/10*maxTopSpeed;
	}*/
	void calculateSpeed(){
		if(quality<=10){
			topSpeed=quality/10*maxTopSpeed;
		}else {
			topSpeed = maxTopSpeed+(quality - 10) / 25 * maxTopSpeed;
		}
	}


	public void calculateMaxQuality(){
		maxQuality = 10+(10*evolveTier);
	}

	public double improveCar(boolean random){
		///// Chooses improve amount /////
		double upgr;
		if (random){
			Random rand = new Random();
			upgr=rand.nextDouble()*6-1;
		}else{
			upgr=1;
		}
		///// Checks if car can be improved /////
		///// else says car is due for evolve /////
		if(quality+upgr>=maxQuality){
			quality= maxQuality;
			if(evolveTier<maxEvolveTier){
				canEvolve=true;
			}
		}else{
			quality+=upgr;
		}
		///// Recalculates stats /////
		calculateSpeed();
		calculatePrice();
		return upgr;
	}

	public void evolveCar(){
		if(canEvolve){
			evolveTier++;
			tier++;
			calculateMaxQuality();
			canEvolve=false;

			//Statistics
			totalWins+=tierWins;
			totalLoses+=tierLoses;
			tierWins=0;
			tierLoses=0;
		}
	}

	public double repairPrice() {
		return (quality * 1000)+(quality*maxPrice/10)/10;
	}
	public double upgradeCost(){
		return price*0.1;
	}

	public double evolveCost(){
		return price*0.25;
	}

	public boolean isMaxed(){
		return evolveTier==maxEvolveTier && quality==maxQuality;
	}
	public double getWinRate(){
		//Calculate winrate
		return (double) tierWins/(double) (tierWins+tierLoses);
	}
}
