package ui.menus.garagemenu;

import carvault.Garage;
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

public class GarageMenu extends JPanel {
    ImageHandler icons;
    Handler handler;
    Frame frame;
    Player player;
    Garage garage;

    public GarageMenu(Frame frame, Handler handler) {
        this.frame = frame;
        this.handler = handler;
        ///////////////////////////
        player=handler.player;
        garage=handler.garage;
        icons =handler.imageHandler;

        menuPanel=new JMenuPanel(handler,"");

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
        ArrayList<Car> carsToDisplay=garage.getMyCars();

        for(int i =0;i<carsToDisplay.size();i++){
            Car currCar=carsToDisplay.get(i);

            JPanel panel= new JPanel();
            JLabel label;
            Font font;

            label=new JLabel(icons.getLogo(currCar.brand.toLowerCase()));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label=new JLabel(currCar.model);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            font = label.getFont();
            label.setFont(font.deriveFont(font.getStyle() | Font.BOLD, 15f));
            panel.add(label);

            label = new JLabel("Tier: "+ currCar.tier);
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label=new JLabel("Top Speed:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
            label=new JLabel(df.format(currCar.topSpeed)+" / "+df.format(currCar.maxTopSpeed)+" km/h");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            panel.add(label);

            label=new JLabel("Quality:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label=new JLabel(df.format(currCar.quality)+" / "+df.format(currCar.maxQuality));
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            label=new JLabel("Price:");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
            label=new JLabel(df.format(currCar.price)+" $ / "+df.format(currCar.maxPrice)+" $");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(new EmptyBorder(new Insets(0, 0, 5, 0)));
            panel.add(label);

            //Repair button
            if (currCar.canEvolve){
            //Evolve button
                JButton evolveButt = new JButton("Evolve " + "(" + df.format(currCar.evolveCost()) + "$)");
                evolveButt.setBackground(new Color(112, 36, 234));
                evolveButt.setForeground(Color.WHITE);
                evolveButt.setAlignmentX(Component.CENTER_ALIGNMENT);
                evolveButt.setMaximumSize(new Dimension(140, 25));
                //Define buy event
                evolveButt.addActionListener(e -> {
                    if (Events.evolveCar(currCar, player)) {
                        refresh();
                    }
                });
                if (!player.canAfford(currCar.evolveCost())) {
                    evolveButt.setEnabled(false);
                }
                panel.add(evolveButt);
            }else if (currCar.maxQuality>10 && !currCar.isMaxed()) {
            //Upgrade button
                JButton upgradeButt = new JButton("Upgrade " + "(" + df.format(currCar.upgradeCost()) + "$)");
                upgradeButt.setBackground(new Color(36, 152, 234));
                upgradeButt.setAlignmentX(Component.CENTER_ALIGNMENT);
                upgradeButt.setMaximumSize(new Dimension(140, 25));
                //Define buy event
                upgradeButt.addActionListener(e -> {
                    if (Events.upgradeCar(currCar, player)) {
                        refresh();
                    }
                });
                if (!player.canAfford(currCar.upgradeCost())) {
                    upgradeButt.setEnabled(false);
                }
                panel.add(upgradeButt);
            }else if(currCar.quality<10){
            //Repair up to 10
                JButton repairButt = new JButton("Repair " + "(" + df.format(currCar.repairPrice()) + "$)");
                repairButt.setAlignmentX(Component.CENTER_ALIGNMENT);
                repairButt.setMaximumSize(new Dimension(140, 25));
                //Define buy event
                repairButt.addActionListener(e -> {
                    if (Events.repairCar(currCar, player)) {
                        refresh();
                    }
                });
                if (currCar.quality == 10 || !player.canAfford(currCar.repairPrice())) {
                    repairButt.setEnabled(false);
                }
                panel.add(repairButt);
            }else{
                //Show button that says max
                //Show maxed and set color to white with html
                JButton maxButt = new JButton("<html><font color='black'>Maxed</font></html>");
                maxButt.setBackground(new Color(185, 104, 208));
                maxButt.setAlignmentX(Component.CENTER_ALIGNMENT);
                maxButt.setMaximumSize(new Dimension(140, 25));
                maxButt.setEnabled(false);

                panel.add(maxButt);
            }

            double racePrice=1000;
            JButton raceButt= new JButton("Race "+"("+df.format(racePrice)+" $)");
            raceButt.setAlignmentX(Component.CENTER_ALIGNMENT);
            raceButt.setMaximumSize(new Dimension(140, 25));
            //Define buy event
            raceButt.addActionListener(e ->{
                //Logic
                handler.showRaceMenu(currCar, handler.racing.getRandomCar(currCar.tier));
            });
            if(!player.canAfford(racePrice)){
                raceButt.setEnabled(false);
            }
            if(currCar.getWinRate()>=0.8 && currCar.tierLoses+currCar.tierWins>=5){
                raceButt.setEnabled(false);
                raceButt.setText("No opponents!");
            }
            panel.add(raceButt);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.setBorder(new LineBorder(new Color(212, 218, 229), 1, true));
            panel.setPreferredSize( new Dimension( 160, 240 ) );

            carPanel.add(panel);
        }

        if(carsToDisplay.size()==0){
            JLabel label=new JLabel("<html><h2>You own no cars!</h2></html>");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            carPanel.add(label);
        }

        carPanel.setLayout(new WrapLayout());
    }


}
