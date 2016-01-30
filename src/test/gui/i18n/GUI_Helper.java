package test.gui.i18n;

import javax.swing.JButton;
import javax.swing.JLabel;

import test.gui.i18n.I18n;

public abstract class GUI_Helper {
    public static JLabel createJLabel(String str){
        JLabel label = new JLabel(I18n.getString(str));
        label.setToolTipText(I18n.getToolTipString(str));
        return label;
    }
    
    public static JButton createJButton(String str){
        JButton button = new JButton(I18n.getString(str));
        button.setToolTipText(I18n.getToolTipString(str));
        return button;
    }
}
