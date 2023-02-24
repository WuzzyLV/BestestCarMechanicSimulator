package ui.menus.racemenuv1;

import objects.Car;
import ui.Handler;
import ui.genericpanels.JMenuPanel;
import utils.ImageHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;


public class RaceSummary extends JPanel {
    Car player;
    Car opponent;

    double playerTime;
    double opponentTime;

    //Constructor
    public RaceSummary(Car player, Car opponent, double playerTime, double opponentTime, Handler handler){
        this.player = player;
        this.opponent = opponent;
        this.playerTime = playerTime;
        this.opponentTime = opponentTime;

        ImageHandler icons = handler.imageHandler;
        DecimalFormat df = handler.df;

        boolean won = playerTime < opponentTime;

        System.out.println(won);

        //Design
        //Set warp layout
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //Create panel for player
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        if(won) {
            playerPanel.setBorder(BorderFactory.createLineBorder(new Color(124, 208, 124), 5));
        }else {
            playerPanel.setBorder(BorderFactory.createLineBorder(new Color(208, 124, 124), 5));
        }

        JLabel label; Font font;

        label = new JLabel(icons.getLogo(player.brand.toLowerCase()));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel.add(label);

        label = new JLabel(player.model);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        font = label.getFont();
        label.setFont(font.deriveFont(font.getStyle() | Font.BOLD, 15f));
        playerPanel.add(label);

        label = new JLabel("Tier: "+ player.tier);
        label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel.add(label);

        label = new JLabel("Speed: "+ player.topSpeed+ " km/h");
        label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel.add(label);

        label = new JLabel("Time: "+ playerTime + " seconds");
        label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        //playerPanel.setBorder(new EmptyBorder(new Insets(0, 20, 0, 0)));
        playerPanel.add(label);
        ////////////////////////////////////////////
        // Create panel for opponent
        JPanel opponentPanel = new JPanel();
        opponentPanel.setLayout(new BoxLayout(opponentPanel, BoxLayout.Y_AXIS));
        if(won) {
            opponentPanel.setBorder(BorderFactory.createLineBorder(new Color(208, 124, 124), 5));
        }else {
            opponentPanel.setBorder(BorderFactory.createLineBorder(new Color(124, 208, 124), 5));
        }

        label = new JLabel(icons.getLogo(opponent.brand.toLowerCase()));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        opponentPanel.add(label);

        label = new JLabel(opponent.model);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        font = label.getFont();
        label.setFont(font.deriveFont(font.getStyle() | Font.BOLD, 15f));
        opponentPanel.add(label);

        label = new JLabel("Tier: "+ opponent.tier);
        label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        opponentPanel.add(label);

        label = new JLabel("Speed: "+ opponent.topSpeed+" km/h");
        label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        opponentPanel.add(label);

        label = new JLabel("Time: "+ opponentTime + " seconds");
        label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        opponentPanel.add(label);

        ////////////////////////////////////////////
        // VS IN MIDDLE
        JPanel VSPanel = new JPanel();
        VSPanel.setLayout(new BoxLayout(VSPanel, BoxLayout.Y_AXIS));
        if (won) {
            label = new JLabel("<html>" +

                    "<h1><center>VS</center></h1>" +
                    "<p color = green>+"+7500*player.tier+"$</p>" +
                    "</html>");
        }else {
            label = new JLabel("<html>" +
                    "<head>"+
                    "<h1><center>VS</center></h1>" +
                    "<p color=red>-"+ 2500*player.tier+"$</p>" +
                    "</html>");
        }
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        VSPanel.setPreferredSize(new Dimension(50, 100));
        VSPanel.add(label);

        JPanel mainPanel = new JPanel();


        mainPanel.add(playerPanel);

        mainPanel.add(VSPanel);

        mainPanel.add(opponentPanel);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JMenuPanel(handler,"garage"));

        add(mainPanel);





    }

}
