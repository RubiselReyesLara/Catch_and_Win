package catch_and_win;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class DrawableJPanel extends JPanel{
    private final String imageName;
    
    public DrawableJPanel(String imageName, int x, int y, int width, int height){
        this.imageName = imageName;
    }
    
    @Override
    protected void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        graphic.drawImage(new ImageIcon(getClass().getResource(this.imageName))
                        .getImage(), 50, 50, 430, 180, this);
    }
}
