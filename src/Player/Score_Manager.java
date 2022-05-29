package Player;
import catch_and_win.Init_Game;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* PANEL FOR THE SCORE OF THE GAME */

public class Score_Manager extends JPanel{
    //Label Score
    JLabel jl_score;
    int score = 0;
    
    public Score_Manager(Init_Game screen) {
        //Size, taking the screen width and height
        this.setBounds(0,0,screen.getWidth(),screen.getHeight());
        //Set transparency to the panel
        this.setOpaque(false);
        //this.setLayout(new BorderLayout());
        //this.setBackground(Color.red);
        jl_score = new JLabel("Score: " + score);
        jl_score.setHorizontalAlignment(JLabel.CENTER);
        //Adding
        this.add(jl_score, BorderLayout.NORTH);
    }
    
    public void increaseScore(){
        score++;
        this.jl_score.setText("Score: " + score);
    }
    
    public void decreaseScore(){
        score--;
        this.jl_score.setText("Score: " + score);
    }
    
    
    
}