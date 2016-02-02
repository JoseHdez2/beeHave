package gui.util;

import java.awt.Component;
import java.awt.Container;

public abstract class GUIHelper {
    
    public static void cascadeEnable(Component comp, Boolean b){
        comp.setEnabled(b);
        
        if (!(comp instanceof Container)) return;

        Container comp2 = (Container) comp;

        for (Component c : comp2.getComponents()){
            cascadeEnable(c,b);
        }
    }
}
