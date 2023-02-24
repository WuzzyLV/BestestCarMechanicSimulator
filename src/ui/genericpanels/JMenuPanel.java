package ui.genericpanels;

import objects.Player;
import ui.Handler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class JMenuPanel extends JPanel {
    static DecimalFormat df = Handler.df;
    static Player player;

    JLabel moneyLabel;
    JLabel levelLabel;
    public JMenuPanel(Handler handler, String target){
        player=handler.player;

        JButton backButt= new JButton("Back");
        //Define buy event
        backButt.addActionListener(e ->{
            switch (target) {
                case "garage" -> handler.showGarageMenu();
                case "scrapyard" -> handler.showAllScrapYards();
                default -> handler.showMainMenu();
            }
        });

        levelLabel=new JLabel("Level: "+player.playerLevel+" ("+player.experience+"/"+player.experienceRequired+")");
        levelLabel.setBorder(new EmptyBorder(new Insets(0, 50, 0, 0)));

        moneyLabel= new JLabel("Money: "+df.format(player.money)+" $");
        moneyLabel.setBorder(new EmptyBorder(new Insets(0, 50, 0, 0)));

        add(backButt);
        add(levelLabel);
        add(moneyLabel);
        setMaximumSize(new Dimension(1000, 20));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void updateMoney(){
        moneyLabel.setText("Money: "+df.format(player.money)+" $");
        levelLabel.setText("Level: "+player.playerLevel+" ("+player.experience+"/"+player.experienceRequired+")");
    }
}
