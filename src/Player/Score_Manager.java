package Player;
import Items.Items;
import catch_and_win.Init_Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/* PANEL FOR THE SCORE OF THE GAME */
public class Score_Manager extends JPanel{
    //Label Score
    public JLabel jl_score, jl_timer;
    JPanel panel;
    int score = 0;
    int time = 100;
    final int MOUNT = 10;
    
    public Score_Manager(Init_Game screen) {
        this.setLayout(new BorderLayout()); 
        
        //The panel that containts the score and timer.
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        //Size, taking the screen width and height, the width is less 30px for
        //aesthetic aspect.
        this.setBounds(0,0,screen.getWidth() - 20,100);
        
        //Set transparency to the panel
        this.setOpaque(false);
        
        jl_score = new JLabel("Score: " + score);
        jl_timer = new JLabel("100s");
        jl_score.setFont(new Font("Tw Cent MT", Font.BOLD, 20));
        jl_timer.setFont(new Font("Tw Cent MT", Font.BOLD, 20));
        jl_score.setBorder(new EmptyBorder(10,10,10,10));
        jl_timer.setBorder(new EmptyBorder(10,10,10,10));
        
        panel.add(jl_score);
        panel.add(jl_timer);
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