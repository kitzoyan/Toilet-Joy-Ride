import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {

  static int GAME_WIDTH;
  static int GAME_HEIGHT;
  int player1  = 100;
 
  boolean dead = false;

  
  Score(int GAME_WIDTH, int GAME_HEIGHT) {
    Score.GAME_HEIGHT = GAME_HEIGHT;
    Score.GAME_WIDTH = GAME_WIDTH;
  }

  public void draw(Graphics g) {
    
    g.setFont(new Font("Consolas", Font.PLAIN, 60));
    //g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
    if(player1 > 100) {
      player1 = 100;
    }

    if (player1 > 50) {
      g.setColor(new Color(104, 252, 30));
      g.drawString("Health: " + String.valueOf(player1/10) + String.valueOf(player1%10), GAME_WIDTH/2 - 200, 55);

    } else if (player1 <= 50 && player1 >= 20) {
      g.setColor(new Color(255, 205, 56));
      g.drawString("Health: " + String.valueOf(player1/10) + String.valueOf(player1%10), GAME_WIDTH/2 - 200, 55);

    } else if (player1 < 20 && player1 > 0) {
      g.setColor(new Color(255, 86, 56));
      g.drawString("Health: " + String.valueOf(player1/10) + String.valueOf(player1%10), GAME_WIDTH/2 - 200, 55);
    } else {
      g.setColor(new Color(156, 37, 37));
      g.setFont(new Font("Impact", Font.PLAIN, 300));
      g.drawString("Game Over", GAME_WIDTH/2 - 650, 500);
      dead = true;
    }
    
  }

}