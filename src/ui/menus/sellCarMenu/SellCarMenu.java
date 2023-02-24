package ui.menus.sellCarMenu;

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

public class SellCarMenu extends JPanel {
    ImageHandler icons;
    Handler handler;
    Frame frame;
    Player player;
    Garage garage;

    public SellCarMenu(Frame frame, Handler handler) {
        this.frame = frame;
        this.handler = handler;
        ///////////////////////////
        player=handler.player;
        garage=handler.garage;
        icons =handler.imageHandler;

        menuPanel=new JMenuPanel(handler, "");

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
            label=new JLabel(df.format(currCar.quality)+" / 10");
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
            JButton sellButt= new JButton("Sell "+"("+df.format(currCar.price)+"$ +)");
            sellButt.setAlignmentX(Component.CENTER_ALIGNMENT);
            //Define buy event
            sellButt.addActionListener(e ->{
                if (Events.sellCar(currCar, player, garage)) {
                    refresh();
                }
            });
            sellButt.setBackground(new Color(86, 219, 6));
            sellButt.setForeground(Color.BLACK);
            //sellButt.setBorder(new LineBorder(new Color(86, 219, 6), 2, true));

            panel.add(sellButt);

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            panel.setBorder(new LineBorder(new Color(24, 111, 23), 1, true));
            panel.setPreferredSize( new Dimension( 160, 215 ) );


            carPanel.add(panel);
        }

        if(carsToDisplay.size()==0){
            JLabel label=new JLabel("<html><h2>You have no cars to sell!</h2></html>");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            carPanel.add(label);
        }

        carPanel.setLayout(new WrapLayout());
    }


}
