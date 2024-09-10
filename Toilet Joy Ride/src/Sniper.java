import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class Sniper extends Rectangle {

  int id;
  int yVelocity;
  int speed = 10;
  boolean reset = false;
  int laserWidth;
  int laserHeight = 50;
  boolean sniperMove =false;
  boolean flash;
  boolean start;



Sniper(int x, int y, int GAME_WIDTH) {
    
    super(x, y, GAME_WIDTH, 60);
    //System.out.println(x);
  }


    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        //System.out.println(x);
        if(x < width - 100) {
            //System.out.println("This was called");
            x = width-100;
            flash = false;
            start = true;
        } else {
            x -= 5;
            flash = true;
            //System.out.println("It moved!");
        }
         
        
    }

    public void draw(Graphics g, int id) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.MAGENTA);
    
        
        if (id == 0) {
            if(start) {
                if (flash) {
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(Color.WHITE);
                }
            }
            
            g2d.fillRoundRect(x, y+5, 70, 40, 30, 30); //shooter
            sniperMove = true;
        } else if (id == 1) {
            start = false;
            g2d.setColor(Color.MAGENTA);
            sniperMove = false;
            x = 0;
            g2d.fillRect(0, y, width, height); //laser beam
        } else if (id == 2) {
            g2d.fillRect(0, y+20, width, laserHeight/3); //small laser beam
        } else {

        }

        //Rectangle2D.Double rec = new Rectangle2D.Double();
        
            
        //g2d.fill(rec);
    }
}

    
