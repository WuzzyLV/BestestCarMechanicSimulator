package ui.menus.scrapyard;

import carvault.Garage;
import carvault.Scrapyard;
import events.Events;
import objects.Car;
import objects.Player;
import ui.Frame;
import ui.Handler;
import ui.genericpanels.JMenuPanel;
import utils.ImageHandler;
import utils.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ScrapyardMenu extends JPanel {
    ImageHandler icons;
    Handler handler;
    Frame frame;
    Scrapyard scrapyard;
    Garage garage;
    Player player;
    int tier;

    public ScrapyardMenu(int tier,Frame frame, Handler handler) {
        this.frame = frame;
        this.handler = handler;
        this.tier=tier;
        ///////////////////////////
        scrapyard=handler.scrapyard;
        garage=handler.garage;
        player=handler.player;
        icons =handler.imageHandler;


        menuPanel=new JMenuPanel(handler,"scrapyard");

        initPanel();
    }
    //Dependencies
    DecimalFormat df = Handler.df;
    //Components
    public JMenuPanel menuPanel;
    public JPanel carPanel= new JPanel();


    public void initPanel(){
        generateCarPanel();

        menuPanel.updateMoney();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane scrollBar=new JScrollPane(carPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.getVerticalScrollBar().setUnitIncrement(16);

        add(menuPanel);
        add(scrollBar);
    }

    public void refresh(){
        menuPanel.updateMoney();

        carPanel.removeAll();
        generateCarPanel();

        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void generateCarPanel(){
        ArrayList<Car> carsToDisplay=scrapyard.getCars(tier);

        for (Car currCar : carsToDisplay) {
            JPanel panel = new JPanel();
            JLabel label;
            Font font;

            label = new JLabel(icons.getLogo(currCar.brand.toLowerCase()));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label = new JLabel(currCar.model);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            font = label.getFont();
            label.setFont(font.deriveFont(font.getStyle() | Font.BOLD, 15f));
            panel.add(label);

            label = new JLabel("Tier: "+ currCar.tier);
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label = new JLabel("Top Speed:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
            label = new JLabel(df.format(currCar.topSpeed) + " / " + df.format(currCar.maxTopSpeed) + " km/h");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            panel.add(label);

            label = new JLabel("Quality:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label = new JLabel(df.format(currCar.quality) + " / 10");
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label = new JLabel("Price:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
            label = new JLabel(df.format(currCar.price) + " $ / " + df.format(currCar.maxPrice) + " $");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            panel.add(label);

            JButton buyButt = new JButton("Buy");
            //Make button unclickable if too poor
            if (player.canAfford(currCar.price)) {
                buyButt.setForeground(new Color(86, 219, 6, 230));
            } else {
                buyButt.setEnabled(false);
                buyButt.setBackground(new Color(220, 77, 77));
            }

            //Center button
            buyButt.setAlignmentX(Component.CENTER_ALIGNMENT);
            //Define buy event
            buyButt.addActionListener(e -> {
                if (Events.boughtCar(currCar, player, garage)) {
                    //If car was bought successfully
                    scrapyard.removeCar(currCar, tier);
                    //Refresh
                    refresh();
                }
            });
            panel.add(buyButt);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.setBorder(new LineBorder(new Color(84, 46, 46), 1, true));
            panel.setPreferredSize(new Dimension(160, 215));


            carPanel.add(panel);
        }

        if(carsToDisplay.size()==0){
            JLabel label=new JLabel("<html><h2>This scrapyard is empty!</h2></html>");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            carPanel.add(label);
        }

        carPanel.setLayout(new WrapLayout());
    }


}
