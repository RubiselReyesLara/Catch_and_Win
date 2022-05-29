package Items;

import Player.PlayerPanel;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import catch_and_win.Init_Game;

public class MoveItems extends Thread{
    
    int X = 0; int Y = 0; int id = 0;
    Init_Game Screen;
    PlayerPanel playerPanel;
    ItemsPanel itemsPanel;
    //Graphics
    Rectangle2D hitBox = new Rectangle2D.Double(X, Y, 50, 50);
    Image item;
    //State of the current item
    boolean type = true; boolean stateOn = true; boolean availableItem = true;  
    
    MoveItems(Init_Game IG, PlayerPanel PP, ItemsPanel IP, int X, int Y, int position, boolean type){
        //Instance for communication with the other panels.
        this.Screen = IG;
        this.playerPanel = PP;
        this.itemsPanel = IP;
        //Asign X or Y.
        this.X = X;
        this.Y = Y;
        //Asign the ID of the item thread current state.
        this.id = position;
        this.item = new ImageIcon(getClass().getResource("/Img/Lvl1/b1.png")).getImage();
    }
    //Stops item thread.
    public void stop_moveItem(){
        stateOn = false;
        availableItem = false;
    }
    
    @Override
    public void run(){
        while(stateOn){
            //If the item is visible in screen...
            if(Y <= Screen.getHeight()){
                Y += 3;
                //Draw the hitbox of the current item that will follows the item.
                hitBox = new Rectangle2D.Double(X, Y, 50, 50);
                itemsPanel.repaint();
                
                if(playerPanel.player_Hitbox.intersects(this.hitBox)){
                    stop_moveItem();
                }
                
                try{
                    Thread.sleep(12);
                }catch(InterruptedException ex){}
                } else {
                stop_moveItem();
            }
        }
    }
}
