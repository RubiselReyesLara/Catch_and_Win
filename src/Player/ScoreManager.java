package Player;
import Items.Item;
import catch_and_win.PrincipalContainer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/* PANEL FOR THE SCORE OF THE GAME */
public class ScoreManager extends JPanel{
    //Label Score
    public JLabel jl_score, jl_timer;
    public final LifePanel lifePanel;
    public final PrincipalContainer initGame;
    JPanel panel;
    int score = 0;
    int time = 100;
    final int MOUNT = 10;
    
    public ScoreManager(PrincipalContainer initGame) {
        this.initGame = initGame;
        this.setLayout(new BorderLayout()); 
        //The panel that containts the score and timer.
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        //Size, taking the screen width and height, the width is less 30px for
        //aesthetic aspect.
        this.setBounds(0,0,initGame.getWidth() - 20,130);
        
        //Set transparency to the panel
        this.setOpaque(false);
        
        jl_score = new JLabel("Score: " + score);
        jl_timer = new JLabel("Time: 00:00");
        jl_score.setFont(new Font("Tw Cent MT", Font.BOLD, 20));
        jl_timer.setFont(new Font("Tw Cent MT", Font.BOLD, 20));
        jl_score.setBorder(new EmptyBorder(10,10,10,10));
        jl_timer.setBorder(new EmptyBorder(10,10,10,10));
        
        lifePanel = new LifePanel(this);
        panel.add(lifePanel);
        panel.add(jl_score);
        panel.add(jl_timer);
        panel.repaint();
        this.add(panel, BorderLayout.WEST);
    }
    
    public void increaseScore(){
        score += MOUNT;
        this.jl_score.setText("Score: " + score);
        this.jl_score.setForeground(Color.GREEN);
    }
    
    public void decreaseScore(){
        score -= MOUNT;
        this.jl_score.setText("Score: " + score);
        this.jl_score.setForeground(Color.RED);
    }
}