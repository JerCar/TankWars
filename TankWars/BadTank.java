/**
 * Jer's Tank Wars
 * @author Jeremy Carrothers
 * @version 1.0
 */

// this class handles each badtank and it's operations

public class BadTank extends Sprite
{
    
    //private final int INITIAL_X = 400;
    //private final int INITIAL_Y = 400;
    protected String heading;
    
    public BadTank(int x, int y) {    
        super(x,y);        
        initBadTank();        
    }
    
    private void initBadTank() {    
        loadImage("redTankRight.png");
        getImageDimensions();        
        loadImage("redTankLeft.png");
        getImageDimensions();
        loadImage("redTankUp.png");
        getImageDimensions();
        loadImage("redTankDown.png");
        getImageDimensions();
        heading = "R";        
    }
    
    public void move() {
        
        if (heading == "R") {            
            loadImage("redTankRight.png");
            getImageDimensions();          
            x += 1;            
        }        
        
        if (heading == "L") {
            loadImage("redTankLEFT.png");
            getImageDimensions();
            x += -1;            
        }
        
        if (heading == "U") {
            loadImage("redTankUp.png");
            getImageDimensions();
            
            y -= 1;
        }
        
        if (heading == "D") {
            loadImage("redTankDown.png");
            getImageDimensions();
            y += 1;
        }
    }   
}
