package Player;
import catch_and_win.PrincipalContainer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/* PANEL FOR THE SCORE OF THE GAME */
public class ScoreManager extends JPanel{
    //Label Score
    public JLabel jl_score, jl_timer, jl_bestScore, jl_bestTime;
    public Timer timer_game;
    public final LifePanel lifePanel;
    public final PrincipalContainer initGame;
    private final int MOUNT = 10;
    private final JPanel panel_scoreContainer;
    private int score = 0;
    String fd_second = "0"; String fd_minute = "0"; // First digit of timer string
    byte seconds = 0; byte minutes = 0; byte lvl = 0;
    
    public ScoreManager(PrincipalContainer initGame, String bestScore, String bestTime) {
        this.setLayout(new BorderLayout()); // The class layout
        this.initGame = initGame;
        this.timer_game = timerGame();

        // The life panel
        this.lifePanel = new LifePanel(this);

        // GridLayout panel that has score data (time and score)
        JPanel panel_scoreData = new JPanel();

        // Best last score and time
        this.jl_bestScore = jLabelTags(bestScore, Color.GRAY);
        this.jl_bestTime = jLabelTags(bestTime, Color.GRAY);

        // The score and time labels
        this.jl_score = jLabelTags("Score: 0", Color.BLACK);
        this.jl_timer = jLabelTags("Time: 00:00", Color.BLACK);
        
        //The panel that containts the life panel and score data.
        this.panel_scoreContainer = new JPanel();
        this.panel_scoreContainer.setOpaque(false);

        this.panel_scoreContainer.setLayout(new BoxLayout(this.panel_scoreContainer, BoxLayout.Y_AXIS));
        this.panel_scoreContainer.setSize(new Dimension(400,400));
        
        //Size, taking the screen width and height, the width is less 20px for
        //aesthetic aspect.
        this.setBounds(0,0,this.initGame.getWidth(),130);
        
        //Set transparency to the panel
        this.setOpaque(false);

        panel_scoreData.setOpaque(false);
        panel_scoreData.setLayout(new GridLayout(2,2, 8, 8));
        panel_scoreData.add(this.jl_timer);
        panel_scoreData.add(this.jl_bestTime);
        panel_scoreData.add(this.jl_score);
        panel_scoreData.add(this.jl_bestScore);
        
        panel_scoreContainer.add(lifePanel);
        panel_scoreContainer.add(panel_scoreData);

        panel_scoreContainer.repaint();
        this.revalidate();
        this.repaint();
        this.add(panel_scoreContainer, BorderLayout.WEST);
        
        this.timer_game.start();
    }


    private JLabel jLabelTags(String text, Color foreground){
        JLabel jlabel = new JLabel(text);
        jlabel.setFont(new Font("Tw Cent MT", Font.BOLD, 16));
        jlabel.setForeground(foreground);
        jlabel.setBorder(new EmptyBorder(10,10,10,10));
        jlabel.setHorizontalAlignment(SwingConstants.LEFT);
        return jlabel;
    }
    
    public Timer timerGame(){
        return new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Timer Game Clock
                seconds++;
                if (seconds > 9) { // 00:01 -> 00:10 
                    ScoreManager.this.jl_timer.setText("Time: "  + fd_minute + minutes + ":" + seconds);
                    if (seconds > 59){ // 00:59 -> 01:00
                        seconds = 0;
                        minutes++;
                        if(minutes > 9){ // 01:10
                            fd_minute = "";
                            ScoreManager.this.jl_timer.setText("Time: " + minutes + ":0" + seconds);
                        } else { // 01:01
                            ScoreManager.this.jl_timer.setText("Time: 0" + minutes + ":0" + seconds);
                        }
                    }
                } else { // 00:01
                    ScoreManager.this.jl_timer.setText("Time: "  + fd_minute + minutes + ":0" + seconds);
                }
            }
        });
    }
    

    public void increaseScore(){
        score += MOUNT;
        this.jl_score.setText("Score: " + score);
    }

}