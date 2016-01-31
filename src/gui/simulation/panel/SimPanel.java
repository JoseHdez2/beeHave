package gui.simulation.panel;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import gui.i18n.GUI_Helper;

/**
 * Class that may or may not be deleted.
 * 
 * @author jose
 *
 */
public class SimPanel extends JPanel{

    // TODO: default serial version id
    private static final long serialVersionUID = 1L;

    public SimPanel(){
        this.setBorder(new TitledBorder(""));
    }
    
    public SimPanel(String title){
        this.setBorder(new TitledBorder(title));
    }
    
    /**
     * @param str i18n String.
     * @return Created JLabel that was already added into the Component hierarchy of this SimPanel.
     */
    public JLabel addNewJLabel(String str){
        JLabel label = GUI_Helper.createJLabel(str);
        this.add(label);
        return label;
    }
    
    /**
     * @return Created JTextField that was already added into the Component hierarchy of this SimPanel.
     */
    public JTextField addNewJTextField(String text, int columns){
        JTextField textField = new JTextField(text, columns);
        this.add(textField);
        return textField;
    }
    
    /**
     * @param str i18n String.
     * @return Created JLabel that was already added into the Component hierarchy of this SimPanel.
     */
    public JButton addNewJButton(String str){
        JButton button = GUI_Helper.createJButton(str);
        this.add(button);
        return button;
    }
    
    /**
     * @param str i18n String.
     * @return Created JRadioButton that was already added into the Component hierarchy of this SimPanel.
     */
    public JRadioButton addNewJRadioButton(String str, int mnemonic){
        JRadioButton radioButton = GUI_Helper.createJRadioButton(str);
        radioButton.setMnemonic(mnemonic);
        this.add(radioButton);
        return radioButton;
    }
    
    public void addNewJRadioButtonGroup(HashMap<String, Integer> radioButtons, ActionListener listener){
        
        ButtonGroup buttonGroup = new ButtonGroup();
        
        // HashMap iteration: http://stackoverflow.com/a/1066603/3399416
        Iterator it = radioButtons.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next(); // HashMap iteration.
            
            JRadioButton rb = addNewJRadioButton((String)pair.getKey(), (int)pair.getValue());
            buttonGroup.add(rb);
            rb.addActionListener(listener);
            
            // Set first button created as the default selection.
            if(buttonGroup.getButtonCount() == 1)
                buttonGroup.setSelected(rb.getModel(), true);
            
            it.remove(); // HashMap iteration: avoids a ConcurrentModificationException
        }
            
    }
}
