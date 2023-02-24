package ui;

import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.extras.FlatUIDefaultsInspector;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import javax.swing.*;

public class Frame extends JFrame {
    Frame(){

		  FlatNordIJTheme.setup();
          SwingUtilities.updateComponentTreeUI(this);
    }

    public void clearFrame(){
        getContentPane().removeAll();
        getContentPane().repaint();



    }
}
