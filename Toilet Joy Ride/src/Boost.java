import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class Boost extends Rectangle {
    int MAXIMUM_VELOCITY = 30;
    int yVelocity;
    final int MAX_SPEED = 4;
    boolean booster = false;
    boolean boostReset = false;
    boolean hurt = false;
    boolean heal = false;  

    
  
    Boost(int x, int y, int widthX, int heightY, int MAXIMUM_VELOCITY) {
        
        super(x, y, widthX, heightY);
       

        this.MAXIMUM_VELOCITY = MAXIMUM_VELOCITY;
        //System.out.println(MAXIMUM_VELOCITY);
        //yVelocity  = MAXIMUM_VELOCIT
    }

    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            yVelocity = 30;
            //System.out.println(MAXIMUM_VELOCITY);
            // System.out.println(MAXIMUM_VELOCITY);
        }
        
                
    }

    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            
            
        }
        
        
    }

    public void jump() {
        if(yVelocity > MAXIMUM_VELOCITY) {
            yVelocity = MAXIMUM_VELOCITY;
        }
        //System.out.println(yVelocity);
        move(yVelocity);
        yVelocity -= MAX_SPEED;
    }

    public void move(int currentVelocity) {        
        //y += yVelocity;
        y -= currentVelocity;
        
    }


    public void draw(Graphics g, int playerY) {
        Graphics2D g2d = (Graphics2D) g;
        if (heal) {
            g2d.setColor(new Color(97, 255, 139));
        } else {
            g2d.setColor(Color.WHITE);
        }
        
        // g2d.fillRect(x-350, y+50 , width, 50);
        // g2d.fillRect(x-200, y+25 , width-200, 100);
        g2d.fillRect(x, y+50, width, 50);
        g2d.fillRect(x+200, y+25, width-200, 100);
        System.out.println("Boost ------------- " + x + " " + y + "\n");
        //System.out.println(x);
        
        
        
        // Rectangle2D.Double rec = new Rectangle2D.Double(x + 15, y - 40, 20, 20);
        // g2d.fill(rec);
    }
    
    
}
