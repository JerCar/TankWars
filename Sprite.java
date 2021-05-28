
/**
 *  Jer's Tank Wars 
 *  @author jer
 *  @version 1.0
 */


// this class is to handle all the shared functionality between sprites
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Sprite
{
    protected int x; // x coord
    protected int y; // y coord
    protected int width; // width of sprite
    protected int height; // height of sprite
    protected boolean visible; // currently seeable?
    protected Image image; // the sprite
    protected String heading; // trying to make the missiles work
    
    
    
    public Sprite(int x, int y) { // assign the instance variables and visibility
        this.x = x;
        this.y = y;
        visible = true;
        
    }
    
    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);        
    }
    
    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName); 
        image = ii.getImage();
        
    }
    
    public Image getImage() {
        return image;
    }
    
    public String getHeading() {
        return heading;
    }
    
    public int getX() {
        return x;    
    }
    
    public int getY() {
        return y;    
    }
    
    public boolean isVisible() {
        return visible;    
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;    
    }
    
    // for collision detection - returns the bounding rectangle of the sprite image
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);    
    }
}
