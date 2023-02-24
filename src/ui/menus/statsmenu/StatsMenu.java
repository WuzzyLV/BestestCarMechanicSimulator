package ui.menus.statsmenu;

import carvault.Garage;
import com.formdev.flatlaf.extras.FlatInspector;
import objects.Player;
import ui.Frame;
import ui.Handler;
import ui.genericpanels.JMenuPanel;
import utils.ImageHandler;
import utils.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class StatsMenu extends JPanel {
    ImageHandler icons;
    Handler handler;
    Frame frame;
    Player player;
    Garage garage;

    public StatsMenu(Frame frame, Handler handler) {
        this.frame = frame;
        this.handler = handler;
        ///////////////////////////
        player=handler.player;
        garage=handler.garage;
        icons =handler.imageHandler;
        DecimalFormat df = new DecimalFormat("#,##0.00");



        menuPanel=new JMenuPanel(handler,"");



        initPanel();
    }
    //Dependencies
    DecimalFormat df = Handler.df;
    //Components
    public JMenuPanel menuPanel;
    public JPanel main= new JPanel();


    public void initPanel(){

/*        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        //titlePanel.add(title,SwingConstants.CENTER);

        JLabel title = new JLabel("<html><h1>Statistics</h1></html>");
       //title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title,Component.CENTER_ALIGNMENT);

        JLabel level = new JLabel("<html><li>Level: " + player.playerLevel+"</li></html");
        level.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(level,Component.CENTER_ALIGNMENT);

        //label experience
        JLabel exp = new JLabel("   Experience: " + player.experience + "/" + player.experienceRequired);
        titlePanel.add(exp,Component.CENTER_ALIGNMENT);

        JLabel racesWon = new JLabel("  Races won: " + player.wins);
        titlePanel.add(racesWon,Component.CENTER_ALIGNMENT);

        JLabel moneyEarned = new JLabel("   Money earned: " + df.format(player.moneyWon) + " $");
        titlePanel.add(moneyEarned,Component.CENTER_ALIGNMENT);

        JLabel moneySpent = new JLabel("    Money spent: " + df.format(player.moneySpent) + " $");
        titlePanel.add(moneySpent,Component.CENTER_ALIGNMENT)*/;

        String stringToShow="<html>" +
                "<h2 style=\"text-align:center;\"><strong>Statistics</strong></h2>" +
                "" +
                "<p style=\"text-align:center; font-size: 10px;\"><br>Level: " + player.playerLevel + "</br>" +
                "<br>Experience: " + player.experience + " / "+player.experienceRequired+"</br>" +
                "<br>Races won: " + player.wins + "</br>" +
                "<br>Races lost: " + player.loses + "</br>" +
                "<br>Winrate: " + df.format(player.getWinRate()*100) + " % </br>" +
                "<br>Money earnt: " + df.format(player.moneyWon) + " $ </br>" +
                "<br>Money spent: " + df.format(player.moneySpent) + " $ </br>" +
                "</p style=\"text-align:center;\"></html>";
        JLabel mainLabel = new JLabel(stringToShow);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        mainLabel.setLayout(new WrapLayout());

        menuPanel.updateMoney();
        main.add(mainLabel);
        mainLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(main);
        //Allow only vertical scrolling
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        add(menuPanel);
        add(scrollPane);


    }



}
