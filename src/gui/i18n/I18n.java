package gui.i18n;

import java.util.ResourceBundle;

public abstract class I18n {
    
    private static ResourceBundle manage(){
        return ResourceBundle.getBundle("gui.i18n.bundle.String_es");
        // TODO restore
        /*
        if (System.getProperty("user.language") == "es")
            return ResourceBundle.getBundle("res/bundle/i18nBundle_es");
        else
            return ResourceBundle.getBundle("res/bundle/i18nBundle_en");
        */
    }
    
    private static ResourceBundle manageToolTip(){
        return ResourceBundle.getBundle("gui.i18n.bundle.Tooltip_es");
        // TODO restore
        /*
        if (System.getProperty("user.language") == "es")
            return ResourceBundle.getBundle("res/bundle/i18nBundle_es_tool");
        else
            return ResourceBundle.getBundle("res/bundle/i18nBundle_en_tool");
        */
    }
    
    public static String getString(String str){
        return manage().getString(str);
    }
    
    public static String getToolTipString(String str){
        return manageToolTip().getString(str);
    }
}
