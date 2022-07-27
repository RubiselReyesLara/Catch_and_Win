package Items;

import Player.PlayerPanel;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import catch_and_win.PrincipalContainer;
import Player.ScoreManager;
import java.util.Random;

public class Item extends Thread{
    
    int X = 0; int Y = 0; int id = 0;
    byte failCounter = 0;
    PrincipalContainer Screen;
    PlayerPanel playerPanel;
    ScoreManager score_Manager;
    ItemsPanel itemsPanel;
    //Graphics
    Rectangle2D hitBox = new Rectangle2D.Double(X, Y, 50, 50);
    Image item;
    //State of the current item
    byte type = 0; boolean stateOn = true; boolean availableItem = true;  
    
    Item(PrincipalContainer IG, PlayerPanel PP, ScoreManager SM, ItemsPanel IP, 
            int X, int Y, int position, String itemName, byte type){
        //Instance for communication with the other panels.
        this.Screen = IG;
        this.playerPanel = PP;
        this.score_Manager = SM;
        this.itemsPanel = IP;
        //Asign X or Y.
        this.X = X;
        this.Y = Y;
        //Asign the ID of the item thread current state.
        this.id = position;
        Random random = new Random();
        //Asign if the item is good(1), or bad(0) according the byte value received.
        this.type = type;
        
        this.item = new ImageIcon(getClass().getResource(itemName)).getImage();
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
                
                // When an item intersects with player...
                if(playerPanel.player_Hitbox.intersects(this.hitBox)){
                    stop_moveItem();
                    
                    // Good item
                    if(this.type == 0){
                    this.score_Manager.increaseScore();
                    } else {
                        // Bad item == Less life... decrement life 1 by 1
                        this.score_Manager.lifePanel.decreaseLives += 1;
                    }
                }
                try{
                    Thread.sleep(12);
                }catch(InterruptedException ex){}
                } else {
                stop_moveItem();
            }
        }
    }
    
    //Stops item thread.
    public void stop_moveItem(){
        stateOn = false;
        availableItem = false;
    }
}
