package objects;

public class Player {

	public double money;
	public int wins =0;
	public int loses = 0;
	public double moneySpent=0;
	public double moneyWon=0;
	public int experience=0;
	public int experienceRequired=100;
	public int playerLevel=1;
	public Player(double money){
		this.money=money;
	}

	public void takeMoney(double money){
		this.money-=money;
		//Adds statistics
		moneySpent+=money;
	}
	public void giveMoney(double money){

		this.money+=money;

		moneyWon+=money;
	}

	public boolean canAfford(double price){
		return money>=price;
	}

	//level up if 100 experience
    public void addExperience(int experience){

        if((this.experience+experience)>=experienceRequired){
			//Takes away the experience required to level up
            experience-=(experienceRequired-this.experience);

			experienceRequired = (int) (experienceRequired/0.5);
           	playerLevel++;
        }
		this.experience+=experience;
    }
	public double getWinRate(){
		//Calculate winrate
		return (double) wins/(double) (wins+loses);
	}



}
