/**
 * Jer's Tank Wars
 * @author Jeremy Carrothers
 * @version 1.0
 */

// this class handles the random direction generator for BadTank headings

import java.util.Random;

public class RNG
{
    public static String getRandomDirection(int min, int max) {
        Random random = new Random();
        int ranNum = random.nextInt(max - min) + min;
        
        if (ranNum == 1) { return "U"; }
        if (ranNum == 2) { return "D"; }
        if (ranNum == 3) { return "L"; }
        if (ranNum == 4) { return "R"; }
        
        return "Error";        
    }    
}
