import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class HighScore extends Rectangle {

  static int GAME_WIDTH;
  static int GAME_HEIGHT;
  int time  = 0;
  int highest;
 
  boolean dead = false;

  
  
  HighScore(int GAME_WIDTH, int GAME_HEIGHT) {
    Time.GAME_HEIGHT = GAME_HEIGHT;
    Time.GAME_WIDTH = GAME_WIDTH;

    try { 
      BufferedReader reader = new BufferedReader(new FileReader("HighScore.txt"));
      while (true) {
        String num = reader.readLine();
        if (num == null) {
          break;
        }
        if (Integer.parseInt(num) > highest) {
          highest = Integer.parseInt(num);
        }
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update(int score) {
    if (score > highest) {
      try {
        PrintWriter writer = new PrintWriter(new FileWriter("HighScore.txt", true));
        writer.println(score);
        writer.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void draw(Graphics g) {
    
    g.setFont(new Font("Consolas", Font.PLAIN, 20));
    //g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
    
      g.setColor(Color.WHITE);
      g.drawString("High Score: " + String.valueOf(highest), 100, 50);
  }
}