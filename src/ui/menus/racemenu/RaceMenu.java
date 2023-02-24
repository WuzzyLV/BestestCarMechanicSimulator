package ui.menus.racemenu;

import objects.Car;

import javax.swing.*;
import java.util.Random;

public class RaceMenu {
    Car player;
    boolean playerFinished = false;
    int playerNitro = 0;
    int playerSpeed;

    Car opponent;
    boolean opponentFinished = false;

    ////// Bar specifics ///////
    int MINIMUM;
    int MAXIMUM;

    RaceMenu(Car player, Car opponent) {
        this.player = player;
        this.opponent = opponent;

        playerSpeed = (int) (player.topSpeed*100);

        MAXIMUM = (25 * player.tier) * 1000;
    }

    ///////// Times ///////////
    long startTime = System.currentTimeMillis();

    double playerTime;


    public void startPlayerBar(JProgressBar bar) {
        Random rand = new Random();
        final Timer t = new Timer(1, e -> {
            int n = bar.getValue();
            if (n >= MAXIMUM) {
                //On race finish
                ((Timer) e.getSource()).stop();
                playerTime = (System.currentTimeMillis() - startTime) / 1000.0;

            finished(player);
            }
            if (rand.nextInt(100) == 0) {
                //showNitroButton();
            }
            n += playerDistance();
          //  updateBar(pbar, n);

        });
    }

    private int playerDistance() {


        return 0;
    }

    ///////// Misc ///////////
    public void finished(Car car) {
        if (car == player) {
            playerFinished = true;
        } else{
            opponentFinished = true;
        }

        if (playerFinished && opponentFinished) {
            //Race has ended code
        }
    }
}