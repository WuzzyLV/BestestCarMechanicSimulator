package ui.menus.mainmenu;

import carvault.Garage;
import objects.Player;
import ui.Frame;
import ui.Handler;
import utils.ImageHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

public class MainMenu extends JPanel {

    Frame frame;
    JLabel title;
    Dimension size;

    public MainMenu(Frame frame, Handler handler) {

        setMinimumSize(new Dimension(800, 225));
        //Dependencies
        this.frame = frame;
        Player player=handler.player;
        Garage garage=handler.garage;
        ImageHandler images= handler.imageHandler;
        DecimalFormat df = Handler.df;

        size =frame.getSize();


        JPanel buttonP = new JPanel();

        JButton garageButton = new JButton(images.getIcon("garage"));
        //Define event for garage button that opens garage through handler
        garageButton.addActionListener(e -> handler.showGarageMenu());

        JButton scrapyardButton = new JButton(images.getIcon("scrapyard"));
        //Define event for scrapyard button that opens scrapyard through handler
        scrapyardButton.addActionListener(e -> handler.showAllScrapYards());

        JButton sellCarButton = new JButton(images.getIcon("sell"));
        //Define event for sell car button that opens sell car through handler
        sellCarButton.addActionListener(e -> handler.showSellCarMenu());

        buttonP.add(scrapyardButton); buttonP.add(garageButton); buttonP.add(sellCarButton);
        buttonP.setAlignmentX(Component.CENTER_ALIGNMENT);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        title= new JLabel(images.getIcon("title"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateMargins();

        JPanel extra = new JPanel();

        JButton statsButt = new JButton("Stats");
        //Define event for stats button that opens stats through handler
        statsButt.addActionListener(e -> handler.showStatsMenu());
        //Give button padding
        statsButt.setBorder(new EmptyBorder(0, 5, 0, 5));
        statsButt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton aboutButt = new JButton("About");
        //Define event for stats button that opens stats through handler
        aboutButt.addActionListener(e -> handler.showAboutMenu());
        //Give button padding
        aboutButt.setBorder(new EmptyBorder(0, 5, 0, 5));
        aboutButt.setAlignmentX(Component.CENTER_ALIGNMENT);

        extra.add(statsButt);
        extra.add(aboutButt);

        add(title);
        add(buttonP);
        add(extra);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                size =frame.getSize();
                updateMargins();
            }
        });

    }

    void updateMargins(){
        Dimension minSize= getMinimumSize();
        if (minSize.getHeight()>frame.getHeight()){
            title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }else {
            title.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.25), 0, (int) (size.getHeight() * 0.1), 0));
        }
        frame.revalidate();
    }
}
