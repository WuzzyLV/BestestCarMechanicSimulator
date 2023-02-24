package ui.menus.racemenuv1;

import events.Events;
import objects.Car;
import ui.Frame;
import ui.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class RaceMenu extends JPanel {
    Frame frame;
    Handler handler;

    RaceManager raceManager;

    JProgressBar pbar;
    JProgressBar obar;

    Car player;
    Car opponent;

    JLabel nitro;
    int MINIMUM = 0;
    int MAXIMUM; //distance in meters

    long startTime=System.currentTimeMillis();

    double playerTime=0;
    double opponentTime=0;

    //top speeds in meters
    int pTopSpeed;
    int playerBonus=0;
    int oTopSpeed;

    public RaceMenu(Frame frame, Handler handler, RaceManager raceManager, Car player, Car opponent) {
        this.handler=handler;
        this.frame=frame;
        this.raceManager = raceManager;
        this.player = player;
        this.opponent = opponent;

        MAXIMUM=(30*player.tier)*1000;

        pTopSpeed = (int) ((player.topSpeed*100)/60);
        oTopSpeed = (int) ((opponent.topSpeed*100)/60);

        Dimension size= frame.getSize();
        //Player bar setup
        pbar = new JProgressBar();
        pbar.setString("You");
        pbar.setStringPainted(true);
        pbar.setSize(new java.awt.Dimension(size.width/2, 25));
        pbar.setLocation(size.width/4, size.height/2-30);
        pbar.setMinimum(MINIMUM);
        pbar.setMaximum(MAXIMUM);
        //Opponent bar setup
        obar = new JProgressBar();
        obar.setString("Opponent");
        obar.setStringPainted(true);
        obar.setSize(new java.awt.Dimension(size.width/2, 25));
        obar.setLocation(size.width/4, size.height/2);

        obar.setMinimum(MINIMUM);
        obar.setMaximum(MAXIMUM);

        //Nitro Button
        ImageIcon icon=handler.imageHandler.getIcon("nitro");
        nitro = new JLabel(icon);
        nitro.setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        nitro.setVisible(false);

        setLayout(null);

        add(pbar);
        add(obar);
        add(nitro);

        startPlayerBar();
        startOpponentBar(obar);

    }

    public void showNitroButton(){
        //Random location on screen
        Random rand = new Random();
        int x = rand.nextInt(frame.getWidth()- nitro.getWidth()*2);
            int y = rand.nextInt(frame.getHeight()- nitro.getHeight()*2);
            nitro.setLocation(x, y);

        nitro.repaint();
        nitro.setVisible(true);
        //add action listener to nitroButt
        final Timer t = new Timer(2500, e -> {
            //Stop timer
            ((Timer) e.getSource()).stop();
            nitro.setVisible(false);
        });
        t.start();


        nitro.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                t.stop();

                System.out.println("Nitro used");
                nitro.setVisible(false);
                if (player.maxTopSpeed> player.topSpeed) {
                    playerBonus = (int) (player.maxTopSpeed);
                }else{
                    playerBonus = (int) (player.topSpeed);
                }
            }
        });
    }

    public int playerDistance(){
        int distance=pTopSpeed+playerBonus;
        final int slowDown=10;

        if(playerBonus>0) {
            playerBonus -= slowDown;
        }else if (playerBonus<0){
            playerBonus=0;
        }
        return distance;
    }

    public void updateBar(JProgressBar bar, int newValue) {
        bar.setValue(newValue);
    }

    public void showResults(){
        Events.race(player, (playerTime<opponentTime), handler.player);
        RaceSummary raceSummary=new RaceSummary(player, opponent, playerTime, opponentTime, handler);

        frame.clearFrame();
        frame.getContentPane().add(raceSummary);
        frame.revalidate();
    }

    public void startPlayerBar(){
        Random rand = new Random();

        final Timer t = new Timer(1, e -> {
            int n = pbar.getValue();
            if(n >= MAXIMUM) {
                //On race finish
                ((Timer) e.getSource()).stop();
                playerTime = (System.currentTimeMillis()-startTime)/1000.0;

                //Register that opponent has finished race
                finish(player);
            }else{
                if (!nitro.isVisible()){
                    if (rand.nextInt(250) == 0){
                        showNitroButton();
                    }
                }
                n += playerDistance();
                updateBar(pbar, n);
            }
        });
        t.start();
    }

    public void startOpponentBar(JProgressBar bar){
        final Timer t = new Timer(1, e -> {
            int n = bar.getValue();
            if(n >= MAXIMUM) {
                ((Timer) e.getSource()).stop();
                opponentTime = (System.currentTimeMillis()-startTime)/1000.0;

                //Register that opponent has finished race
                finish(opponent);
            }else{
                n += oTopSpeed;
                updateBar(bar,n);
            }
        });
        t.start();
    }

    boolean playerFinished=false;
    boolean opponentFinished=false;

    public void finish(Car car){
        if(car==player) {
            playerFinished = true;
        }else if(car==opponent){
            opponentFinished = true;
        }
        //If both players have finished call the raceFinished method
        if (opponentFinished && playerFinished){
            showResults();
        }
    }
}
