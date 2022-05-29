package Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import catch_and_win.Init_Game;


public class PlayerPanel extends javax.swing.JPanel {
    public int X_coords;
    private Graphics2D square;
    public Rectangle2D player_Hitbox;
    public Image player;
    
    
    //Build the playerPanel
    public PlayerPanel(Init_Game Screen) {
        this.setBounds(0,0,Screen.getWidth(),Screen.getHeight());
        this.setOpaque(false);
        this.setBackground(Color.black);
        //Draw the player Character first state.
        this.player = new ImageIcon(getClass().getResource("/Player/Sprites/initImageState.png")).getImage();
        //Drop the player in mid - screen.
        X_coords = this.getWidth() / 2 - 40;
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        //Asign the square like the main graphic
        square = (Graphics2D) g;
        //Create the hitbox like a rectangle that follows the player.
        player_Hitbox = new Rectangle2D.Double(this.X_coords + 18, 450, 40, 100);
        //Draw the character.
        square.drawImage(this.player, this.X_coords, 430, 80, 130, this);
    }
    
    public void changeAnimation(String state){
        //Change the state of character according the orientation (right or left)
        this.player = new ImageIcon(getClass().getResource("/Player/Sprites/" + state + ".png")).getImage();
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
