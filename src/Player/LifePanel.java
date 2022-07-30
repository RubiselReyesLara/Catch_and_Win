package Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// THIS CLASS DETERMINES THE END OF GAME

/*** This class formalizes the box of lives. It's basically a panel that draws 
 * lives through a for loop. Iterates according to the result of the subtraction 
 * of the total lives minus the variable decreaseLives, which indicates how many 
 * lives have been lost. This value receives an increment through another class 
 * such as the Item class, which directly dictates the event caused by the collision 
 * of an item and the player. In the same way, make a comparison in each paint 
 * verifying that the player has not lost the 6 lives. If he lost them, his game 
 * ends.
/*** Esta clase formaliza la caja de las vidas. Es basicamente un panel que dibuja
 * las vidas a través de un ciclo for. Itera según el resultado de la resta del
 * total de vidas menos la variable decreaseLives, que indica cuantas vidas se
 * han perdido. Este valor recibe un incremento a traves de otra clase cómo la
 * clase Item, que directamente dicta el evento ocasionado por la colisión de un
 * item y el jugador. De igual forma realiza una comparación en cada paint verificando
 * que el jugador no haya perdido las 6 vidas. Si las perdio, su partida termina.
 * @author RubiselRL
 */

public class LifePanel extends JPanel{
    Graphics2D heart;
    public byte decreaseLives = 0;
    public final ScoreManager scoreManager;
    
    public LifePanel(ScoreManager scoreManager){
        this.scoreManager = scoreManager;
        this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(100,100));
        this.setOpaque(false);
    }
 
    
    @Override
    public void paint(Graphics graphic){
        super.paintComponent(graphic);
        this.heart = (Graphics2D) graphic;
        int increaseX = 10;
        if(this.decreaseLives < 6){
            for(byte i = 0; i <= 5 - this.decreaseLives; i++){
                this.heart.drawImage(new ImageIcon(getClass().getResource("/Img/heart.png"))
                        .getImage(), increaseX, 10, 20, 20, this);
                increaseX += 30;
            }
        } else {
            ////////////////////////////// END GAME /////////////////////////////////
            this.scoreManager.initGame.GameViewPanel.ENDGAME();
        }
    }
}
