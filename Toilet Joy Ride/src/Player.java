import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class Player extends Rectangle {
    int MAXIMUM_VELOCITY;
    int yVelocity;
    final int MAX_SPEED = 4;
    boolean booster = false;
    boolean boostReset = false;
    boolean hurt = false;
    boolean heal = false;
  
    Player(int x, int y, int PLAYER_WIDTH, int PLAYER_HEIGHT, int MAXIMUM_VELOCITY) {
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        this.MAXIMUM_VELOCITY = MAXIMUM_VELOCITY;
        yVelocity  = MAXIMUM_VELOCITY;
    }

    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            yVelocity = MAXIMUM_VELOCITY;
            //System.out.println(MAXIMUM_VELOCITY);
        }
        if(e.getKeyCode() == KeyEvent.VK_W) {
            if (boostReset) {
                booster = true;
                // System.out.println("Dash " + booster);
                // System.out.println("-----------Boosted");
                
                boostReset = false;
            }
            

        }
                
    }

    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            
            
        }
        if(e.getKeyCode() == KeyEvent.VK_W) {
            booster = false;
        }

        
    }

    public void boost(int boostY) {
        
        if (booster) {
            if(x <= 50) {
                
                //System.out.println("Boost!");
                y = boostY;
                yVelocity = 0;
                x += 400;
            } 
            System.out.println("Player ------------- " + x + " " + y);
        }         

    }

    public void jump() {
        if(yVelocity > MAXIMUM_VELOCITY) {
            yVelocity = MAXIMUM_VELOCITY;
        }
        move(yVelocity);
        yVelocity -= MAX_SPEED;
    }

    public void move(int currentVelocity) {        
        //y += yVelocity;
        y -= currentVelocity;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
            if (hurt) {
                g2d.setColor(Color.RED);
            } else if (heal) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.WHITE);
                
            }

            //g2d.fillRect(x, y, width, height);

            g2d.fillRect(x, y+50, 20, 50);
            g2d.fillRect(x+10, y+105, 60, 10);
            g2d.fillRoundRect(x+15, y+110, 50, 25, 20, 20);
            g2d.fillRect(x+32, y+130, 15, 20);

            g2d.fillOval(x+30, y, 30, 30);            
            g2d.fillRoundRect(x+30, y+35, 25, 65, 10, 10);
            g2d.fillRect(x+40, y + 65, 45, 10);
            g2d.fillRect(x+30, y+90, 55, 10);
            g2d.fillRect(x+75, y+90, 15, 50);
        // Rectangle2D.Double rec = new Rectangle2D.Double(x + 15, y - 40, 20, 20);
        // g2d.fill(rec);
    }
    
    
}
