package ui.menus.aboutmenu;

import carvault.Garage;
import objects.Player;
import ui.Frame;
import ui.Handler;
import ui.genericpanels.JMenuPanel;
import utils.ImageHandler;
import utils.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class AboutMenu extends JPanel {
    ImageHandler icons;
    Handler handler;
    Frame frame;
    Player player;
    Garage garage;

    public AboutMenu(Frame frame, Handler handler) {
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

        String stringToShow="<html>" +
                "<h2 style=\"text-align:center;\"><strong>Authors: <br>Reinis Emīls Aļļis, Linards Sils</br></strong></h2>" +
                "" +
                "<p style=\"text-align:center; font-size: 12px;\">" +
                "<br>Lines of code: about 2.400</br>" +
                "<br>Total cars: "+handler.racing.getCarCount()+"</br>" +
                "<br>Time spent: 20-30 hours </br>" +
                "</p style=\"text-align:center;\">" +
                "</html>";
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
