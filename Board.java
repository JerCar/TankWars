 
/**
 * Jer's Tank Wars
 *
 * @author Jeremy Carrothers
 * @version 1.0
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.List;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener
{
    private Timer timer;
    protected static Tank tank;
    private List<BadTank> badtanks;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private boolean inGame;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    public static List<Wall> walls;
    // private boolean canMove;
    private int tankLives = 3;
    private boolean tankHit = false;
    
    private final int[][] badtankPos = {
        {450, 250}, {350, 200}, {100, 100}, {150, 120}
    };
    
    
    private final int[][] wallPos = { // wall positions ** UPGRADE TO RANDOM
        {200, 200}, {200, 160}, {200, 120}, {200, 80}, // vertical string of wall
        {300, 250}, {300, 210}, {300, 170}, {300, 130}, // vertical string of wall
        {500, 330}, {500, 290}, {500, 250}, {500, 210}, {500, 170}, {500, 130}, {500, 90}, {500, 50}, // vertical string of wall
        {400, 330}, {400, 290}, {400, 250}, {400, 210}, {400, 170}, {400, 130}, {400, 90}, {400, 50}, // vertical string of wall
        {360, 330}, {320, 330}, {280, 330}, {240, 330}, {200, 330}, {160, 330}, {120, 330}, {80, 330}, // horizontal
        {360, 430}, {320, 430}, {280, 430}, {240, 430}, {200, 430}, {160, 430}, {120, 430}, {80, 430}, // horizontal
    };
    
    public Board() {
        initBoard();
    }
    
    private void initBoard() {
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setBackground(Color.GREEN);
        setFocusable(true); // must be used for KeyListener
        inGame = true;
        // canMove = true;
        
        tank = new Tank(ICRAFT_X, ICRAFT_Y);
        
        initWalls();
        initBadTanks();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void initWalls() {    
        walls = new ArrayList<>();
        
        for (int[] p : wallPos) { // cycling through the wallPos array and adding walls to the walls array
            walls.add(new Wall(p[0], p[1]));
        }
    }
    
    private void initBadTanks() {
        badtanks = new ArrayList<>();
        
        for (int[] b : badtankPos) {
            badtanks.add(new BadTank(b[0], b[1]));            
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (tankLives < 1) { inGame = false; }
        
        if (inGame) {
            drawObjects(g);
        }
        else {
            drawGameOver(g);
        }
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics g) {
        if (tank.isVisible()) {
            if (tankHit == false) {
                g.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
            }
            if (tankHit == true) {
                tankLives = tankLives - 1;
                tankHit = false;
            }
        }
        
        List<Missile> ms = tank.getMissiles();
        
        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);                
            }
        }
        
        for (Wall wall: walls) {
            if (wall.isVisible()) {
                g.drawImage(wall.getImage(), wall.getX(), wall.getY(), this);
            }
        }
        
        for (BadTank badtank: badtanks){
        
            if(badtank.isVisible()) {
                g.drawImage(badtank.getImage(), badtank.getX(), badtank.getY(), this);
            }
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Tanks left: " + tankLives, 5, 15);
    }    
    
    private void drawGameOver(Graphics g) {
        String msg = "[+]Game Over[+]";
        Font small = new Font("Helvetica", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);
        
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT /2);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        updateTank();
        updateMissiles();
        
        updateWalls();
        updateBadTanks();
        
        checkCollisions();
        
        repaint();
    }
    
    private void inGame() {
        if (!inGame) {
            timer.stop();
        }
    }
    
    private void updateTank() {
        if (tank.isVisible()) {
            tank.move();        
        }        
    }    
    
    private void updateBadTanks() {
        for (int i = 0; i < badtanks.size(); i++) {
            BadTank bt = badtanks.get(i);
            
            if (bt.isVisible()) {
                bt.move();
            }
            else {
                badtanks.remove(i);
            }
        }    
    }
    
    private void updateMissiles() {
    
        List<Missile> ms = tank.getMissiles();
        
        for (int i = 0; i < ms.size(); i++) {
            Missile m = ms.get(i);
            
            if (m.isVisible()) {
                m.move();                
            }
            else {
                ms.remove(i);
            }
        }
    }
    
    private void updateWalls() {
    
        if (walls.isEmpty()) {}
        
        for (int i = 0; i < walls.size(); i++) {
            Wall w = walls.get(i);
            
            if (w.isVisible()) { }
            else { walls.remove(i); }
        }
    }
    
    public void checkCollisions() {
        Rectangle r3 = tank.getBounds();        
        
        // check for tank hitting walls
        for (Wall wall : walls) {
            Rectangle r2 = wall.getBounds();
            if (r3.intersects(r2)) {
                //tank.setVisible(false);
                //wall.setVisible(false);
                if (tank.heading == "R") {
                    tank.x += -10;
                    wall.setVisible(false);
                }
                
                if (tank.heading == "L") {
                    tank.x += 10;
                    wall.setVisible(false);
                }
                
                if (tank.heading == "U") {
                    tank.y += 10;
                    wall.setVisible(false);                
                }
                
                if (tank.heading == "D") {
                    tank.y += -10;
                    wall.setVisible(false);                
                }
            }
            
            // check for tank hitting badtanks
            // player can die from this
            for (BadTank badtank : badtanks) {
                Rectangle r5 = badtank.getBounds();
                if (r3.intersects(r5)) {
                    badtank.setVisible(false);
                    tankHit = true;
                }
                
            }
            


            // check for badtanks hitting walls
            for (BadTank badtank : badtanks) {
                Rectangle r4 = badtank.getBounds();
                if (r4.intersects(r2)) {
                    if (badtank.heading == "L") {
                        badtank.x += 2;
                        // badtank.heading = "U";
                        badtank.heading = RNG.getRandomDirection(1,5);
                    }
                    else if (badtank.heading == "R") {
                        badtank.x += -2;
                        // badtank.heading = "D";
                        badtank.heading = RNG.getRandomDirection(1,5);
                    }
                    
                    else if (badtank.heading == "D") {
                        badtank.y += -2;
                        // badtank.heading = "L";
                        badtank.heading = RNG.getRandomDirection(1,5);
                    }
                    
                    else if (badtank.heading == "U") {
                        badtank.y += 2;
                        // badtank.heading = "R";
                        badtank.heading = RNG.getRandomDirection(1,5);
                    }
                }           
            }
        }
        
        // check for badtanks hitting outer edges
        for (BadTank badtank : badtanks) {
            
            if (badtank.x > 755) { // working
                badtank.x += -2;
                // badtank.heading = "D";
                badtank.heading = RNG.getRandomDirection(1,5);
            }
            else if (badtank.x < 5) { // working
                badtank.x += 2;
                // badtank.heading = "U";
                badtank.heading = RNG.getRandomDirection(1,5);
            }
            
            else if (badtank.y > 560) { // working
                badtank.y += -2;
                // badtank.heading = "L";
                badtank.heading = RNG.getRandomDirection(1,5);
            }
            
            else if (badtank.y < 5) { // working
                badtank.y += +2;
                // badtank.heading = "R";
                badtank.heading = RNG.getRandomDirection(1,5);
            }                         
        }
        
        // make the badtanks chase the tank around
        // check for badtanks crossing axis with tank            
        for (BadTank badtank : badtanks) {
            
            // x axis
            if (badtank.x == tank.x) {
                if (badtank.y <= tank.y) {
                    badtank.heading = "D";
                    continue;
                }
                else if (badtank.y >= tank.y) { 
                    badtank.heading = "U";
                    continue;
                }
            }
            
            // y axis            
            if (badtank.y == tank.y) {
                if (badtank.x <= tank.x) {
                    badtank.heading = "R";
                    continue;
                }
                else if (badtank.x >= tank.x) {
                    badtank.heading = "L";
                    continue;
                }
            }
        }
        
        // check if missile hits badtanks or walls
        List<Missile> ms = tank.getMissiles();        
        for (Missile m : ms) {
            
            Rectangle r1 = m.getBounds();
            
            for (Wall wall : walls) {
                Rectangle r2 = wall.getBounds();
                if (r1.intersects(r2)) {
                    wall.setVisible(false);
                    m.setVisible(false);
                }                
            }            
            
            for (BadTank badtank : badtanks) {
                Rectangle r2 = badtank.getBounds();
                if (r1.intersects(r2)) {
                    badtank.setVisible(false);
                    m.setVisible(false);
                }
            }
        }   
        
    }
    
    
    private class TAdapter extends KeyAdapter {
    
        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }
    }
    
}


















