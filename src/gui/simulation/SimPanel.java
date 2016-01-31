package gui.simulation;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import gui.i18n.I18n;

/**
 * Class that may or may not be deleted.
 * 
 * @author jose
 *
 */
public class SimPanel extends JPanel{

    public SimPanel(){
        this.setBorder(new TitledBorder(""));
    }
    
    public SimPanel(String title){
        this.setBorder(new TitledBorder(title));
    }
}
