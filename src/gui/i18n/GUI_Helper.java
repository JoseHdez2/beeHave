package gui.i18n;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import gui.i18n.I18n;

public abstract class GUI_Helper {
    
    /**
     * @param str i18n String.
     * @return JLabel with text and tooltip from bundles.
     */
    public static JLabel createJLabel(String str){
        JLabel label = new JLabel(I18n.getString(str));
        label.setToolTipText(I18n.getToolTipString(str));
        return label;
    }
    
    /**
     * @param str i18n String.
     * @return JButton with text and tooltip from bundles.
     */
    public static JButton createJButton(String str){
        JButton button = new JButton(I18n.getString(str));
        button.setToolTipText(I18n.getToolTipString(str));
        return button;
    }
    
    /**
     * @param str i18n String.
     * @return JRadioButton with text and tooltip from bundles.
     */
    public static JRadioButton createJRadioButton(String str){
        String bundleStr = I18n.getString(str);
        JRadioButton radioButton = new JRadioButton(bundleStr);
        radioButton.setToolTipText(I18n.getToolTipString(str));
        radioButton.setActionCommand(str);
        return radioButton;
    }
}
