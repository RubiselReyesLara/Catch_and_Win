package catch_and_win;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import Player.MovePlayer;

public class Init_Game extends javax.swing.JFrame{
    MainScreen MainScreen;
    //Are you in main or game screen? 
    Byte ControlScreenManager = 0;
    GameView_Panel GameViewPanel;
    
    
    public Init_Game() {
        initComponents();
        initComponents_v2();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(ControlScreenManager == 0){ 
            //If the user press ESC in main screen
            if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                int exit = JOptionPane.showConfirmDialog(this, "You want exit?", "Exit or not?", JOptionPane.YES_NO_OPTION);
                if(exit == 0){
                    System.exit(0);
                }
            } else {
                //Generate game screen
                ControlScreenManager = 1;
                GameViewPanel = new GameView_Panel(this);
                
                /*Read score*/
                //readCurrentScore();
                
                this.getContentPane().removeAll();
                this.getContentPane().add(GameViewPanel);

                this.repaint();
                this.revalidate();
            }
         }else if(evt.getKeyCode() == KeyEvent.VK_A){
            //1 para moverlo a la izquierda
            GameViewPanel.move_Player.movePlayer((byte)1);
        } else if(evt.getKeyCode() == KeyEvent.VK_D){
            //2 para moverlo a la derecha
            GameViewPanel.move_Player.movePlayer((byte)2);
        } 
        
            //1 para moverlo a la izquierda
//            temporizadorJugador.moverJugador((byte)1);
//        } else if(evt.getKeyCode() == KeyEvent.VK_D){
//            //2 para moverlo a la derecha
//            temporizadorJugador.moverJugador((byte)2);
//        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        
    }//GEN-LAST:event_formKeyTyped
    
    private void initComponents_v2() {
//        this.requestFocus(true);
        this.setLayout(new BorderLayout());
        this.setSize(530, 630);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
        /*Load main screen*/
        //Load the first panel
        MainScreen = new MainScreen(this);
        
        /*Load in frame*/
        this.getContentPane().add(MainScreen);
    }

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Init_Game().setVisible(true);
            }
        });
    }

}

