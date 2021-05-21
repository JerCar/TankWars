
/**
 * Jer's Tank Wars
 *
 * @author Jeremy Carrothers
 * @version 1.0
 */


// the walls
import java.util.*;
import javax.swing.ImageIcon;

public class Wall extends Sprite
{
    private int x;
    private int y;
    
    
    public Wall(int x, int y) {
        super(x,y);
        initWall();
    }
    
    @Override
    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName); 
        image = ii.getImage();
        //image = image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH); // scale the image in place
    }
    private void initWall(){
        loadImage("crateWood.png");
        getImageDimensions();
    }
}
