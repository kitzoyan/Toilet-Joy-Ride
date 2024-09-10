import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class GameFrame extends JFrame { //Has the J Frame components

  GamePanel panel;
  
  GameFrame() { //Default Constructor
    panel = new GamePanel(); // New Panel
    this.add(panel); //Add the panel to JFrame
    this.setTitle("Toilet Joy Ride"); 
    this.setResizable(false); //Do not allow anyone to change size
    this.setBackground(Color.BLACK);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close Jframe when program stopped
    this.pack();
    this.setVisible(true); // Make it visible
    this.setLocationRelativeTo(null);
  }
}