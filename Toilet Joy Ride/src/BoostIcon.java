import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class BoostIcon extends Rectangle {

    int cornerX = 450;
    int cornerY = 20;


    BoostIcon() {
        //super(0, 0, 100, 100);    
        
    }

    public void draw(Graphics g, boolean boosted) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        System.out.println(boosted);

        if (!boosted) {
            Path2D.Double path = new Path2D.Double();
            path.moveTo(cornerX, cornerY);
            path.lineTo(cornerX + 50, cornerY);
            path.lineTo(cornerX + 50, cornerY - 15);
            path.lineTo(cornerX + 70, cornerY + 15);
            path.lineTo(cornerX + 50, cornerY + 45);
            path.lineTo(cornerX + 50, cornerY + 30);
            path.lineTo(cornerX, cornerY + 30);
            path.closePath();
            g2d.draw(path);
            //g2d.fill(path);
        } else {
            Path2D.Double path = new Path2D.Double();
            path.moveTo(cornerX, cornerY);
            path.lineTo(cornerX + 50, cornerY);
            path.lineTo(cornerX + 50, cornerY - 15);
            path.lineTo(cornerX + 70, cornerY + 15);
            path.lineTo(cornerX + 50, cornerY + 45);
            path.lineTo(cornerX + 50, cornerY + 30);
            path.lineTo(cornerX, cornerY + 30);
            path.closePath();
            g2d.fill(path);
        }

    }
}

    
