package catch_and_win;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if(evt.getKeyCode() == KeyEvent.VK_A){
            //1 for left move
            GameViewPanel.move_Player.movePlayer((byte)1);
        } else if(evt.getKeyCode() == KeyEvent.VK_D){
            //2 for right move
            GameViewPanel.move_Player.movePlayer((byte)2);
        } 
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
        //Load the first panel (Mainscreen) this includes the firsts visuals 
        //components, though this class (Init Game) controls the game typing.
        MainScreen = new MainScreen(this);
        
        /*Load in frame*/
        this.getContentPane().add(MainScreen);
    }
    
    public void actionPerformedStartBtn(ActionEvent e ){
        try {
            //Panel that do all about the game
            GameViewPanel = new GameView_Panel(this, readCurrentLevel());
        } catch (IOException ex) {
            System.out.println("Error in file read for load level: " + 
                    ex.toString());
            GameViewPanel = new GameView_Panel(this, (byte)0);
        }

        this.getContentPane().removeAll();
        this.getContentPane().add(GameViewPanel);
        this.repaint();
        this.revalidate();
        System.gc();
    }
    
    

    // Read the lvl file for load data according the lvl
    private byte readCurrentLevel() throws FileNotFoundException, IOException{
        URL path = this.getClass().getResource("lvl.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(path.getFile()));
        byte lvl = Byte.parseByte(buffer.readLine());
        if(lvl > 0 && lvl < 3){
            return lvl;
        } else {
            return 0;
        }
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

