import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Time extends Rectangle {

  static int GAME_WIDTH;
  static int GAME_HEIGHT;
  int time  = 0;
 
  boolean dead = false;

  
  Time(int GAME_WIDTH, int GAME_HEIGHT) {
    Time.GAME_HEIGHT = GAME_HEIGHT;
    Time.GAME_WIDTH = GAME_WIDTH;
  }

  public void draw(Graphics g) {
    
    g.setFont(new Font("Consolas", Font.PLAIN, 40));
    //g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
    
      g.setColor(Color.WHITE);
      g.drawString("Score: " + String.valueOf(time/10) + String.valueOf(time%10), GAME_WIDTH - 400, 50);
  }
}