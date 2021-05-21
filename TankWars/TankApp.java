
/**
 * Jer's Tank Wars
 *
 * @author Jeremy Carrothers
 * @version 1.0
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

// the main class
public class TankApp extends JFrame
{
    public TankApp() {
        initUI();
    }
    
    private void initUI() {
    
        add(new Board());
        setResizable(false);
        pack();
        
        setTitle("Jer's Tank Wars");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            TankApp ta = new TankApp();
            ta.setVisible(true);
        });
    }
    
}
