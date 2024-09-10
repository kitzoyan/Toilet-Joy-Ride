import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import java.awt.geom.*;
import java.util.Timer;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1500;
    static final int GAME_HEIGHT = (int) (800);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int PLAYER_WIDTH = 90;
    static final int PLAYER_HEIGHT= 150;
    static final int MAXIMUM_VELOCITY = 30;
    static int playerVelocity = MAXIMUM_VELOCITY;
    long oldTime;
    long newTime;
    int tickTimer = 40000000;
    boolean drawBoost = false;
    boolean timerYes = false;
    double seconds = 1.5;
    long counter = 0;
    boolean laser2Starter = true;
    boolean laser2Ender = false;
    boolean startSniper = false;
    int sniperId = 0;
    int bombFlasher;
    boolean healPellet = false;
    int intervals = 1;






    Thread gameThread;
    Image image;
    Graphics2D graphics;
    Random random;
    Player player;
    Laser laser;
    Laser2 laser2;
    LaserH laserh;
    Missle missle;
    Sniper sniper;
    Time time;
    Bomb bomb;
    Heal heal;

    Score score;
    HighScore highscore;
    Boost boost;
    BoostIcon boostIcon;


    GamePanel() {
  

        //Makes new objects
        newPlayer();
        newLaser();
        newLaser2();
        newMissle();
        newSniper();
        newLaserH();
        newBomb();
        newHeal();
        newBoost();

        boostIcon = new BoostIcon();

        score = new Score(GAME_WIDTH,  GAME_HEIGHT);
        highscore = new HighScore(GAME_WIDTH, GAME_HEIGHT);
        time = new Time(GAME_WIDTH,  GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    //Calling the new objects

    public void newPlayer() {
        player = new Player(50, GAME_HEIGHT - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT, MAXIMUM_VELOCITY); //passes x, y, width, and height and Max velocity
    }

    public void newBoost() {        
       boost = new Boost(50, GAME_HEIGHT - PLAYER_HEIGHT, 400, PLAYER_HEIGHT, MAXIMUM_VELOCITY);
    }

    public void newHeal() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 20); //generators random Y position
        heal = new Heal(GAME_WIDTH, randomYDirection);
    }

    public void newBomb() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 100);
        bomb = new Bomb(GAME_WIDTH, randomYDirection);
    }

    public void newSniper() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 20);
        sniper = new Sniper(GAME_WIDTH, randomYDirection, GAME_WIDTH);
    }

    public void newLaser() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 400);
        laser = new Laser(GAME_WIDTH, randomYDirection);
    }

    public void newLaser2() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 400);
        laser2 = new Laser2(GAME_WIDTH, randomYDirection);
    }

    public void newLaserH() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 15);
        laserh = new LaserH(GAME_WIDTH, randomYDirection);
    }

    public void newMissle() {
        random = new Random();
        int randomYDirection = random.nextInt(GAME_HEIGHT - 20);
        missle = new Missle(GAME_WIDTH, randomYDirection);
    }
    
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = (Graphics2D) image.getGraphics();

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHints(rh);

        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    //Draw new graphics (called repeatedly)
    public void draw(Graphics g) {
        score.draw(g);
        highscore.draw(g);
        time.draw(g);
        heal.draw(g);
        boostIcon.draw(g, player.boostReset);

        if (time.time == 500*intervals) { //Spawn a heal pellet every 500 points
            System.out.println(heal);
            healPellet = true;
            intervals++;
        }

        //Stages
        laser.draw(g);
        if (time.time > 500) { //two lasers
            laser2.draw(g); 
        }
        if (time.time > 1000) { //three lasers
            laserh.draw(g);
        }
        if(time.time > 1500) { //Missles
            missle.draw(g);
        }
        if(time.time > 2000) { //Bombs
            if (bombFlasher == 1) {
                bomb.draw(g, bombFlasher);
                bombFlasher = 0;
            } else if (bombFlasher == 0) {
                bomb.draw(g, bombFlasher);
                bombFlasher = 1;
            } else if (bombFlasher == 2) {
                bomb.draw(g, bombFlasher);
                bombFlasher = 3;
                //bombFlasher = 0; 
            } else {
                bomb.draw(g, bombFlasher);
                bombFlasher = 2;
            }
        }

        if (startSniper) { //Sniping lasers
            sniper.draw(g, sniperId);
        }
        
       if(drawBoost) {
            boost.draw(g, player.y);
            drawBoost = false;
            
        } else {
            player.draw(g);
            //System.out.println(player.y);
        }
    }

    //Move the objects
    public void move() {
        if (sniper.sniperMove) { 
            sniper.move(); //Move sniper
        } 
  
        laser.move(); //Move laser

        if (healPellet) { //Move and reset pellet if need be
            heal.move();
            if(heal.reset) {
                heal.reset = false;
                newHeal();
                healPellet = false;
            }
        }
        
        if (time.time > 540) { //Move second stage
            if(heal.reset) {
                heal.reset = false;
                newHeal();
            }
            
            if (laser2Starter) { //Waits a few seconds before starting second laser
                laser2Starter = false;
                timerYes = true;
            } 
            if(laser2.reset) { //Reset laser
                laser2.reset = false;
                newLaser2();
            }
            if(laser2Ender) { //Move laser
                laser2.move();
            }
        }

        if (time.time > 1000) { //Move third stage, horizontal laser
            laserh.move();
            if(laserh.reset) {
                laserh.reset = false;
                newLaserH();
            }
        }

        if (time.time> 1500) { // Move fourth stage, missles
            missle.move();
            if(missle.reset) {
                missle.reset = false;
                newMissle();
            }
        }

        if (time.time > 2000) { // Move Fifth stage, bomb
            bomb.move();
                if(bomb.reset) {
                bomb.reset = false;
                newBomb();
                bombFlasher = 0;
            }
        }
        
        if(laser.reset) { // Move sixth stage, lasers
            laser.reset = false;
            newLaser();
        }

        if (player.booster) { //If player boosted
            if (!player.boostReset) {
                player.boost(player.y); //move player
                drawBoost = true; 
            }
        } else { //If not boosted, remain in jumping mode
            player.jump(); 
            boost.jump();
        }
    }

    //Checks for collision
    public void checkCollision() {
        if (player.y >= GAME_HEIGHT - PLAYER_HEIGHT) { //Bottom frame boundary
            player.y = GAME_HEIGHT - PLAYER_HEIGHT;
            player.yVelocity = 0;
        }
        if (player.y <= 0) { //Upper frame boundary
            player.y = 0;
        }

        if (boost.y >= GAME_HEIGHT - PLAYER_HEIGHT) { //Same goes for boost object (it's a shape)
            boost.y = GAME_HEIGHT - PLAYER_HEIGHT;
            boost.yVelocity = 0;
        }

        if (boost.y <= 0) {
            boost.y = 0;
        }

        if(laser.intersects(player)) { //if laser touches player
            score.player1 -= 2; //Lose 2 health
            player.hurt = true; //Turn the player red for visual indication
        } else if (laser2.intersects(player)) {
            score.player1 -= 2;
            player.hurt = true;
        } else if (missle.intersects(player)) {
            score.player1 -= 2;
            player.hurt = true;
        } else if (sniper.intersects(player)) {
            score.player1 -= 2;
            player.hurt = true;
        } else if (laserh.intersects(player)) {
            score.player1 -= 2;
            player.hurt = true;
        } else if (bomb.intersects(player)) {
            score.player1 -= 2;
            player.hurt = true;
            bombFlasher = 2;
        } else if (heal.intersects(player)) { //If heal pellet touches
            score.player1 += 20; //Gain 20 health
            player.heal = true; //Player turns green
            healPellet = false; //Reset pellet
            heal.reset = false;
            newHeal();
        } else if (heal.intersects(boost) && player.booster) { // If player dashes through pellet, do same
            score.player1 += 20;
            boost.heal = true;
            player.heal = true;
            healPellet = false;
            heal.reset = false;
            newHeal();
        } else { //If no collision, nothing happens
            player.hurt = false;
            player.heal = false;
            boost.heal = false;
            
        }
    }

    //Computer system loop
    public void run() {
        long lastTime = System.nanoTime(); //System timer
        double amountOfTicks = 30.0; //Frames or Ticks per second
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) { //To see if one tick has passed

                time.time++; //Score/distance travlled in game
                move(); // Constantly repeat these methods to perform animation
                checkCollision();
                repaint();
                delta--;
                if (player.x > 50) { //If player boosted
                    player.x -= 10; //Slowly return the player
                    player.booster = false;

                } else if (player.x <= 50) { //If player is returned
                    player.boostReset = true; //Boost cooldown deactivate
                }
                if(score.dead) { //If all health lost, dead
                    break;
                }

                if(timerYes) { //For delaying laser2 starting time
                    counter++;
                    if(counter >= seconds*30) { //Wait a few seconds to deploy laser
                        counter = 0;
                        timerYes = false;
                        laser2Ender = true; //start laser
                        
                    }
                }

                if(time.time > 2500) { //after 2500 seconds, start the sniper stage
                    startSniper = true;
                }

                if(startSniper) { //Sniper slowly moves into screen
                    counter++;
                    if (sniperId == 0) {
                        if(counter >= 2*30) {
                            counter = 0;
                            sniperId++; 
                        }
                    } else if (sniperId == 1) { //Sniper shoots
                        if(counter >= 5) {
                            counter = 0;
                            sniperId++;
                        }                    
                    } else { //Sniper beam dies off
                        if(counter >= 2) {
                            counter = 0;
                            sniperId++;
                            if (sniperId > 2) { //If sniper is finished one cycle
                                sniperId = 0;
                                newSniper(); //repeat
                            }  
                        }
                    }
                }
            }
        }
        highscore.update(time.time); //After death, if current score is larger than highscore, update HighScore.txt
    }
    

    public class AL extends KeyAdapter { //Reads any key input from the computer
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);//Sends info to these classes
            boost.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {//Reads when key is released
            player.keyReleased(e);
            boost.keyReleased(e);

        }
    }
}

