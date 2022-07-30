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
    Thread thread_MovePlayer;

    public GameView_Panel(PrincipalContainer principalContainer, String bestScore, String bestTime) {
        initComponents();
        this.setSize(principalContainer.getWidth(), principalContainer.getHeight());
        this.setBackground(Color.white);
        PRINCIPAL_CONTAINER = principalContainer;
        player_Panel = new PlayerPanel(principalContainer);
        score_Panel = new ScoreManager(principalContainer, bestScore, bestTime);
        move_Player = new MovePlayer(player_Panel, principalContainer);
        items_Panel = new ItemsPanel(principalContainer, player_Panel, score_Panel);
        thread_MovePlayer = new Thread(move_Player);
        
        //TOP to BOTTOM
        this.add(score_Panel);
        this.add(items_Panel);
        this.add(player_Panel);

        thread_MovePlayer.start();
    }
    
    // METHOD USED BY LIFEPANEL FOR ADD THE FINALSCREEN
    public void ENDGAME(){
        // Save user data in XML file
        this.PRINCIPAL_CONTAINER.writeUserData(this.score_Panel.jl_timer.getText().replace("Time: ", ""), 
                                               this.score_Panel.jl_score.getText().replace("Score: ", ""));
        // Ending game actions
        move_Player.stopPlayer();
        score_Panel.timer_game.stop();

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
