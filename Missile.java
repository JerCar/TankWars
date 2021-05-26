
/**
 * Jer's Tank Wars
 *
 * @author jeremy carrothers
 * @version 1.0
 */

// handles functionality of the missile sprites

import javax.swing.ImageIcon;

public class Missile extends Sprite
{
    
    private final int BOARD_WIDTH = 780;
    private final int MISSILE_SPEED = 2;
    private String heading;
    
    public Missile(int x, int y, String heading) { 
        super(x - 40,y - 10);
        this.heading = heading;
        initMissile();
    }
    
    @Override
    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName); 
        image = ii.getImage();
        //image = image.getScaledInstance(15,15, java.awt.Image.SCALE_SMOOTH); // scale the image in place
    }
    
    private void initMissile() {
        loadImage("cannonballx.png");
        getImageDimensions();
    }
    
    // missile heading is fed from fire position of tank heading at fire time
    public void move() {    
        
        if (heading.equals("L")) {
            x -= MISSILE_SPEED;
        }
        
        if (heading.equals("R")) {
            x += MISSILE_SPEED;
        }
        
        if (heading.equals("U")) {
            y -= MISSILE_SPEED;
        }
        
        if (heading.equals("D")) {
            y += MISSILE_SPEED;
        }                
        
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
    
}
