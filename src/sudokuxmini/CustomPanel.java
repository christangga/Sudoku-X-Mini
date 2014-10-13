package sudokuxmini;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {
    
    String path="back.jpg";

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();        
        Image img = new ImageIcon(getClass().getResource(path)).getImage();
        int imgX = img.getWidth(null);
        int imgY = img.getHeight(null);
        graphics.drawImage(img, (getWidth() - imgX) / 2, (getHeight() - imgY) / 2, imgX, imgY, null);
    }
}
