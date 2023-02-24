package ui;

import carvault.Garage;
import carvault.Racing;
import carvault.Scrapyard;
import objects.Car;
import objects.Player;
import ui.menus.aboutmenu.AboutMenu;
import ui.menus.allscrapyards.AllScrapyardsMenu;
import ui.menus.garagemenu.GarageMenu;
import ui.menus.mainmenu.MainMenu;
import ui.menus.racemenuv1.RaceManager;
import ui.menus.racemenuv1.RaceMenu;
import ui.menus.scrapyard.ScrapyardMenu;
import ui.menus.sellCarMenu.SellCarMenu;
import ui.menus.statsmenu.StatsMenu;
import utils.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Handler {
    public static Frame frame= new Frame();
    //static PanelConstructor panelConstructor = new PanelConstructor();
    public static DecimalFormat df = new DecimalFormat("#,##0.00");


    public ImageHandler imageHandler;
    public Player player;
    public Garage garage;
    public Scrapyard scrapyard;
    public Racing racing;

    public Handler(ImageHandler imageHandler, Player player, Garage garage, Scrapyard scrapyard, Racing racing){
        this.imageHandler =imageHandler;
        this.player =player;
        this.garage =garage;
        this.scrapyard =scrapyard;
        this.racing =racing;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setIconImage(imageHandler.getIcon("icon").getImage());
    }


    public void showMainMenu(){
        frame.clearFrame();

        JPanel mainMenu = new MainMenu(frame, this);
        //JPanel mainMenu = new MainMenuOld(frame, this);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(mainMenu,BorderLayout.CENTER);

        frame.setTitle("Bestest Car Mechanic Simulator");

        frame.setVisible(true);
    }

    public void showAboutMenu(){
        frame.clearFrame();

        JPanel aboutMenu = new AboutMenu(frame, this);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(aboutMenu,BorderLayout.CENTER);

        frame.setTitle("About");

        frame.setVisible(true);
    }

    public void showStatsMenu(){
        frame.clearFrame();

        JPanel statsMenu = new StatsMenu(frame, this);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(statsMenu,BorderLayout.CENTER);

        frame.setTitle("Statistics");

        frame.setVisible(true);
    }

    public void showAllScrapYards(){
        frame.clearFrame();

        JPanel scrapyardMenu = new AllScrapyardsMenu(frame, this);
        frame.getContentPane().add(scrapyardMenu);

        frame.setTitle("Scrapyard Selection");

        frame.setVisible(true);
    }

    public void showScrapyardMenu(int tier){
        frame.clearFrame();

        ScrapyardMenu scrapyardMenu = new ScrapyardMenu(tier, frame,this);

        frame.getContentPane().add(scrapyardMenu);
        frame.setTitle("Scrapyard");
        frame.setVisible(true);
    }

    public void showGarageMenu(){
        frame.clearFrame();

        GarageMenu garageMenu= new GarageMenu(frame,this);

        frame.getContentPane().add(garageMenu);
        frame.setTitle("Garage");
        frame.setVisible(true);
    }
    public void showSellCarMenu(){
        frame.clearFrame();

        SellCarMenu sellCarMenu= new SellCarMenu(frame,this);

        frame.getContentPane().add(sellCarMenu);
        frame.setTitle("Sell Car");
        frame.setVisible(true);
    }

    public void showRaceMenu(Car player,Car opponent){
        frame.clearFrame();

        RaceManager raceManager=new RaceManager(player,opponent);

        RaceMenu racingMenu= new RaceMenu(frame,this,raceManager,player,opponent);

        frame.getContentPane().add(racingMenu);
        frame.setTitle("Racing");
        frame.setVisible(true);
    }



    /////////////////////   Getters     /////////////////////
    //Get player
    public Player getPlayer(){
        return player;
    }
    //Get garage
    public Garage getGarage(){
        return garage;
    }
    //Get scrapyard
    public Scrapyard getScrapyard(){
        return scrapyard;
    }
    //Get racing
    public Racing getRacing(){
        return racing;
    }

}
