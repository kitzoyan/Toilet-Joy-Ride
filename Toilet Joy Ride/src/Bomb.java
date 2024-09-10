import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Bomb extends Rectangle {

    int id;
    int yVelocity;
    int speed = 10;
    boolean reset = false;
    int bombWidth = 100;
    int bombHeight = 100;
  
  
      Bomb(int x, int y) {
          
          super(x, y, 100, 100);
          //System.out.println(x);
      }
  
      public void setYDirection(int yDirection) {
          yVelocity = yDirection;
      }
  
      public void move() {
          x -= 15;
          if(x <= -1000) {
              reset = true;
          }
      }
  
      public void draw(Graphics g, int flash) {
  
          Graphics2D g2d = (Graphics2D) g;
          g2d.setColor(new Color(255, 104, 4));
          g2d.fillOval(x, y, 100, 100);

          if (flash == 1) {
            g2d.setColor(Color.WHITE);
            g2d.fillOval(x+15, y+ 15, 70, 70);
          } else if (flash == 0) {
            g2d.setColor(Color.RED);
            g2d.fillOval(x+15, y+15, 70, 70);
          } else if (flash == 2) {
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x-200, y-200, 500, 500);
          } else {
            g2d.setColor(Color.WHITE);
            g2d.fillOval(x-250, y-250, 600, 600);
          }
          
   
          
  
          //Rectangle2D.Double rec = new Rectangle2D.Double();
          
              
          //g2d.fill(rec);
      }
  }