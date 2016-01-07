package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Panel que representa el entorno (cuadricula).
 */
public class EnvironmentPanel extends JPanel {
    
    private int x, y;
    Matrix<EnvironmentLabel> elements;
    ArrayList<ArrayList<JLabel>> labels = new ArrayList<ArrayList<JLabel>>();
    
    Point agentPos = new Point(); // Agent's position in x and y.
    ImageIcon agentIcon = new ImageIcon("media/image/agent.png"); // Icon representing the agent.
    
    ArrayList<Point> foodPositions = new ArrayList<Point>();
    ImageIcon foodIcon = new ImageIcon("media/image/meat.png"); // Icon representing food.
    
    EnvironmentPanel(int width, int height){
        setLayout(new GridLayout(width,height));
        this.x = width;
        this.y = height;
        this.elements = new Matrix<EnvironmentLabel>(new EnvironmentLabel[x][y]);

        for (int j = 0; j < elements.height(); j++){
            for (int i = 0; i < elements.width(); i++){
                Color color = (i+j)%2 == 0 ? new Color(150,150,150) : new Color(200,200,200);
                EnvironmentLabel label = new EnvironmentLabel("",i,j);
                label.setOpaque(true);
                label.setBackground(color);
                
                label.addMouseListener(new MouseListener(){

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        EnvironmentLabel el = ((EnvironmentLabel)e.getSource());
                        foodPositions.add(new Point(el.x, el.y));
                        el.getParent().repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                    
                });
                
                elements.set(i, j, label);
                add(elements.get(i, j));
            }
        }
        
        Random rand = new Random();
        agentPos.setLocation(rand.nextInt(x), rand.nextInt(y));
        generateFoodPortion();
        generateFoodPortion();
    }
    
    /**
     * Create a single food item randomly in the environment.
     */
    public void generateFoodPortion(){
        Random rand = new Random();
        foodPositions.add(new Point(rand.nextInt(x), rand.nextInt(y)));
    }
    
    @Override
    public void paint(Graphics g) {
        for (int j = 0; j < elements.height(); j++){
            for (int i = 0; i < elements.width(); i++){
                // Erase previous frame.
                elements.get(i, j).setIcon(null);
                
                // Draw food.
                for (Point food : foodPositions)
                    if (i == food.x && j == food.y) elements.get(i, j).setIcon(foodIcon);
                
                // Draw agent.
                if (i == agentPos.x && j == agentPos.y) elements.get(i, j).setIcon(agentIcon);
            }
        }
        super.paint(g);
    }
}
