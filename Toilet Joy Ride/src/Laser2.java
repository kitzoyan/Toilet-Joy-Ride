import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Laser2 extends Rectangle {

  int id;
  int yVelocity;
  int speed = 10;
  boolean reset = false;
  int laserWidth = 50;
  int laserHeight = 50;


    Laser2(int x, int y) {

    super(x, y, 50, 430);
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
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(x, y, laserWidth, laserHeight);
        g2d.fillRect(x+15, y, 20, 400);
        g2d.fillRect(x, y+385, laserWidth, laserHeight);
        

        //Rectangle2D.Double rec = new Rectangle2D.Double();
        
            
        //g2d.fill(rec);
    }
}

    
