import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Missle extends Rectangle {

    int id;
    int yVelocity;
    int speed = 10;
    boolean reset = false;
    int missleWidth = 80;
    int missleHeight = 30;

    Missle(int x, int y) {
        
        super(x, y, 70, 30);
        System.out.println(x);
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        //System.out.println("Missle move");
        x -= 50;
        if(x <= -50) {
            reset = true;
        }
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 85, 0));
        g2d.fillRoundRect(x, y, missleWidth, missleHeight, 20, 20);
        g2d.fillOval(x+90, y+2, 25, 25);
        g2d.fillOval(x+120, y+5, 20, 20);
        g2d.fillOval(x+150, y+8, 15, 15);
        g2d.fillOval(x+175, y+10, 10, 10);
        g2d.fillOval(x+190, y+12, 5, 5);

        //System.out.println("Drawn Missle");

        

        //Rectangle2D.Double rec = new Rectangle2D.Double();
        
            
        //g2d.fill(rec);
    }
}

    
