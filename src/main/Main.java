package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.simulation.FrameSimulation;

public class Main {
    
    public static void main(String[] args) {
       System.out.println("Hola mundo.");
       
       boolean guiGuapa = true;
       
       if(guiGuapa){
           try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                   | UnsupportedLookAndFeelException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           } 
       }
       
       FrameSimulation sf = new FrameSimulation();
       System.out.println("Hola");
       sf.repaint();
    }
    
}
