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
    Timer timerGame;
    Thread thread_MovePlayer;
    String fd_second = "0"; String fd_minute = "0"; // First digit of timer string
    byte seconds = 0; byte minutes = 0; byte lvl = 0;

    public GameView_Panel(Init_Game screen, byte level) {
        initComponents();
        this.setSize(screen.getWidth(), screen.getHeight());
        this.setBackground(Color.white);
        init_game = screen;
        player_Panel = new PlayerPanel(screen);
        score_Panel = new Score_Manager(screen);
        move_Player = new MovePlayer(player_Panel, screen);
        items_Panel = new ItemsPanel(screen, player_Panel, score_Panel);
        timerGame = timerGame();
        lvl = level;
        thread_MovePlayer = new Thread(move_Player);
        
        //TOP to BOTTOM
        this.add(score_Panel);
        this.add(items_Panel);
        this.add(player_Panel);
//        
        thread_MovePlayer.start();
        //items_Panel.generator_Items().start();
        timerGame.start();
    }
    
    //Timer for the game (100s)
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
                
                
                /*
                if(seconds <= 0){
                    //items_Panel.stopGeneratorItems();
                    move_Player.stopPlayer();
                    //init_game.getContentPane().removeAll();
                    //init_game.repaint();
                    
                    
                    
                    
                    
                    
                    timerGame.stop();
                    
                    
                    
                    //Call garbage collector for remove the unused actual
                    //objects used and saved in the last game
                    System.gc();
                }*/
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
