package catch_and_win;

import Items.ItemsPanel;
import Player.PlayerPanel;
import Player.Score_Manager;
import Player.MovePlayer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GameView_Panel extends javax.swing.JPanel {
    Init_Game init_game;
    PlayerPanel player_Panel;
    Score_Manager score_Panel;
    ItemsPanel items_Panel;
    MovePlayer move_Player;
    Thread thread_MovePlayer;
    byte time = 60;

    public GameView_Panel(Init_Game screen) {
        initComponents();
        this.setSize(screen.getWidth(), screen.getHeight());
        this.setBackground(Color.white);

        init_game = screen;
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
        //items_Panel.generator_Items().start();
        timerGame().start();
    }
    
    public Timer timerGame(){
        return new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                score_Panel.jl_timer.setText(time + "s");
                if(time <= 0){
                    
                    items_Panel.stopGeneratorItems();
                    move_Player.stopPlayer();
                    init_game.getContentPane().removeAll();
                    init_game.repaint();
                    
                    //Call garbage collector for remove the unused actual
                    //objects used and saved in the last game
                    System.gc();
                }
            }
        });
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
