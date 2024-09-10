import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Heal extends Rectangle {

  int id;
  int yVelocity;
  int speed = 10;
  boolean reset = false;
  int laserWidth = 50;
  int laserHeight = 50;


    Heal(int x, int y) {
        
        super(x, y, 50, 50);
        //System.out.println(x);
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        x -= 20;
        if(x <= -50) {
            reset = true;
        }
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fillOval(x, y, 50, 50);
        g2d.fillOval(x+10, y+10, 30, 30);
        

        //Rectangle2D.Double rec = new Rectangle2D.Double();
        
            
        //g2d.fill(rec);
    }
}

    
