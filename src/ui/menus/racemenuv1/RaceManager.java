package ui.menus.racemenuv1;

import objects.Car;

public class RaceManager {
    Car player;
    Car opponent;

    boolean playerFinished = false;
    boolean opponentFinished = false;

    boolean inProgress=true;
    boolean playerWon;

    public RaceManager(Car player, Car opponent){
        this.player = player;
        this.opponent = opponent;
    }

    public boolean isInProgress() {
        return inProgress;
    }
    //Returns true if is last aka race is over

}
