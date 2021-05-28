/**
 * Jer's Tank Wars
 * @author Jeremy Carrothers
 * @version 1.0
 */

// this class handles the tank and it's operations

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Tank extends Sprite
{
    public int dx;
    public int dy;
    private List<Missile> missiles; // all shots fired by the tank are stored here
    
    
    public Tank(int x, int y) {
        super(x,y);
        initTank();
        
    }
    
    @Override
    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName); 
        image = ii.getImage();
        
    }
    
        
    private void initTank() {
        missiles = new ArrayList<>();
        loadImage("tankDown.png");
        getImageDimensions();
        loadImage("tankLeft.png");
        getImageDimensions();
        loadImage("tankUp.png");
        getImageDimensions();
        loadImage("tankRight.png");
        getImageDimensions();
        heading = "R"; 
                      
    }
    
        
    public void move() {
       
        x += dx;
        y += dy;    
        
        if (x < 1) {
            x = 1;
        }
        
        if (y < 1) {
            y = 1;
        }
        
        if (x > 750) {
            x = 750;
        }
        
        if (y > 550) {
            y = 550;
        }
        
        
    }
    
    public List<Missile> getMissiles() {
        return missiles;
    }
    
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
        
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            loadImage("tankLeft.png");
            getImageDimensions();
            heading = "L";            
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            loadImage("tankRight.png");
            getImageDimensions();
            heading = "R";
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = -1;
            loadImage("tankUp.png");
            getImageDimensions();
            heading = "U";
            
        }
        
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            loadImage("tankDown.png");
            //getImageDimensions();
            heading = "D";
        }        
    }
    
    // when we fire a missile, a new missile object is added to the 
    // missiles list. it is kept in the list until collision or goes out of window
    protected void fire() {
        String h = heading;
        missiles.add(new Missile(x + width, y + height / 2, h));
    }
    
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            heading = "L";
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            heading = "R";
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = 0;
            heading = "U";
        }
        
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            heading = "D";
        }
        
    }
}



















