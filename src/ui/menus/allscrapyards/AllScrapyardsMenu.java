package ui.menus.allscrapyards;

import carvault.Garage;
import objects.Player;
import ui.Frame;
import ui.Handler;
import ui.genericpanels.JMenuPanel;
import utils.ImageHandler;

import javax.swing.*;
import java.awt.*;

public class AllScrapyardsMenu extends JPanel {
    ImageHandler icons;
    Handler handler;
    Frame frame;
    Player player;
    Garage garage;

    JMenuPanel menuPanel;

    public AllScrapyardsMenu(Frame frame, Handler handler) {
        this.frame = frame;
        this.handler = handler;
        ///////////////////////////
        player=handler.player;
        garage=handler.garage;
        icons =handler.imageHandler;

        menuPanel=new JMenuPanel(handler,"");

        initPanel();
    }

    public void initPanel(){
        int requiredLVL;

        menuPanel.updateMoney();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel mainPanel=new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        mainPanel.setLayout(layout);

        JButton scrapyard1=new JButton("Scrapyard T1");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrapyard1, gbc);

        scrapyard1.setPreferredSize(new Dimension(150,75));
        scrapyard1.addActionListener(e -> {
            handler.showScrapyardMenu(1);
        });

        JButton scrapyard2=new JButton("Scrapyard T2");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        scrapyard2.setPreferredSize(new Dimension(150,75));

        requiredLVL=2;
        if (!(player.playerLevel>=requiredLVL)){
            scrapyard2.setEnabled(false);
            scrapyard2.setText("Level "+requiredLVL+" required");
        }else{
            scrapyard2.addActionListener(e -> {
                handler.showScrapyardMenu(2);
            });
        }
        mainPanel.add(scrapyard2, gbc);
        //////Y 1
        JButton scrapyard3=new JButton("Scrapyard T3");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        scrapyard3.setPreferredSize(new Dimension(150,75));

        requiredLVL=5;
        if (!(player.playerLevel>=requiredLVL)){
            scrapyard3.setEnabled(false);
            scrapyard3.setText("Level "+requiredLVL+" required");
        }else{
            scrapyard3.addActionListener(e -> {
                handler.showScrapyardMenu(3);
            });
        }
        mainPanel.add(scrapyard3, gbc);

        JButton scrapyard4=new JButton("Scrapyard T4");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        scrapyard4.setPreferredSize(new Dimension(150,75));

        requiredLVL=7;
        if (!(player.playerLevel>=requiredLVL)){
            scrapyard4.setEnabled(false);
            scrapyard4.setText("Level "+requiredLVL+" required");
        }else{
            scrapyard4.addActionListener(e -> {
                handler.showScrapyardMenu(4);
            });
        }
        mainPanel.add(scrapyard4, gbc);

        JButton scrapyard5=new JButton("Scrapyard T5");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        scrapyard5.setPreferredSize(new Dimension(150,75));

        requiredLVL=10;
        if (!(player.playerLevel>=requiredLVL)){
            scrapyard5.setEnabled(false);
            scrapyard5.setText("Level "+requiredLVL+" required");
        }else{
            scrapyard5.addActionListener(e -> {
                handler.showScrapyardMenu(5);
            });
        }
        mainPanel.add(scrapyard5, gbc);

        JScrollPane scrollBar=new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.getVerticalScrollBar().setUnitIncrement(16);

        add(menuPanel);
        add(scrollBar);
    }
}
