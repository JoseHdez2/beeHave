package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Panel que representa el entorno (cuadricula).
 */
public class EnvironmentPanel extends JPanel {
    
    private int x, y;
    Matrix<JLabel> elements;
    ArrayList<ArrayList<JLabel>> labels = new ArrayList<ArrayList<JLabel>>();
    
    EnvironmentPanel(int width, int height){
        setLayout(new GridLayout(width,height));
        this.x = width;
        this.y = height;
        this.elements = new Matrix<JLabel>(new JLabel[x][y]);

        for (int i = 0; i < elements.height(); i++){
            for (int j = 0; j < elements.width(); j++){
                Random rand = new Random();
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                JLabel label = new JLabel("hola");
                label.setOpaque(true);
                label.setBackground(new Color(r,g,b));
                elements.set(i, j, label);
                add(elements.get(i, j));
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
