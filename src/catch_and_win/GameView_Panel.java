package catch_and_win;

import Items.ItemsPanel;
import Player.PlayerPanel;
import Player.Score_Manager;
import Player.MovePlayer;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;

public class GameView_Panel extends javax.swing.JPanel {
    PlayerPanel player_Panel;
    Score_Manager score_Panel;
    ItemsPanel items_Panel;
    MovePlayer move_Player;
    Thread thread_MovePlayer;

    public GameView_Panel(Init_Game screen) {
        initComponents();
        this.setSize(screen.getWidth(), screen.getHeight());
        this.setBackground(Color.white);

        player_Panel = new PlayerPanel(screen);
        score_Panel = new Score_Manager(screen);
        move_Player = new MovePlayer(player_Panel, screen);
        items_Panel = new ItemsPanel(screen, player_Panel, score_Panel);
        thread_MovePlayer = new Thread(move_Player);
        
        //TOP to BOTTOM
        this.add(score_Panel);
        this.add(items_Panel);
        this.add(player_Panel);
//        
        thread_MovePlayer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
