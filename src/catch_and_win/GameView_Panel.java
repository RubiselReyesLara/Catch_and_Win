package catch_and_win;

import Items.ItemsPanel;
import Player.LifePanel;
import Player.PlayerPanel;
import Player.ScoreManager;
import Player.MovePlayer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameView_Panel extends javax.swing.JPanel {
    protected final PrincipalContainer PRINCIPAL_CONTAINER;

    PlayerPanel player_Panel;
    ScoreManager score_Panel;
    ItemsPanel items_Panel;
    MovePlayer move_Player;
    Timer timerGame;
    Thread thread_MovePlayer;
    String fd_second = "0"; String fd_minute = "0"; // First digit of timer string
    byte seconds = 0; byte minutes = 0; byte lvl = 0;

    public GameView_Panel(PrincipalContainer principalContainer, byte i) {
        initComponents();
        this.setSize(principalContainer.getWidth(), principalContainer.getHeight());
        this.setBackground(Color.white);
        PRINCIPAL_CONTAINER = principalContainer;
        player_Panel = new PlayerPanel(principalContainer);
        score_Panel = new ScoreManager(principalContainer);
        move_Player = new MovePlayer(player_Panel, principalContainer);
        items_Panel = new ItemsPanel(principalContainer, player_Panel, score_Panel);
        timerGame = timerGame();
        lvl = i;
        thread_MovePlayer = new Thread(move_Player);
        
        //TOP to BOTTOM
        this.add(score_Panel);
        this.add(items_Panel);
        this.add(player_Panel);

        thread_MovePlayer.start();
        //items_Panel.generator_Items().start();
        timerGame.start();
    }
    
    //TIMER FOR THE GAME
    public Timer timerGame(){
        return new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Timer Game Clock
                seconds++;
                if (seconds > 9) { // 00:01 -> 00:10 
                    score_Panel.jl_timer.setText("Time: "  + fd_minute + minutes + ":" + seconds);
                    if (seconds > 59){ // 00:59 -> 01:00
                        seconds = 0;
                        minutes++;
                        if(minutes > 9){ // 01:10
                            fd_minute = "";
                            score_Panel.jl_timer.setText("Time: " + minutes + ":0" + seconds);
                        } else { // 01:01
                            score_Panel.jl_timer.setText("Time: 0" + minutes + ":0" + seconds);
                        }
                    }
                } else { // 00:01
                    score_Panel.jl_timer.setText("Time: "  + fd_minute + minutes + ":0" + seconds);
                }
            }
        });
    }
    
    // METHOD USED BY LIFEPANEL FOR ADD THE FINALSCREEN
    public void ENDGAME(){
        // Save user data in XML file
        this.PRINCIPAL_CONTAINER.writeUserData(this.score_Panel.jl_timer.getText().replace("Time: ", ""), 
                                               this.score_Panel.jl_score.getText().replace("Score: ", ""));
        // Ending game actions
        move_Player.stopPlayer();
        timerGame.stop();

        // Remove graphics elements and push the final screen
        this.removeAll();
        this.add(new FinalScreen(this.PRINCIPAL_CONTAINER));
        this.revalidate();
        this.repaint();
        System.gc();
    }
    
    
    // INICIALIZER
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
    
}
