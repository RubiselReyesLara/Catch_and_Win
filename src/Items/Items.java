package Items;

import Player.PlayerPanel;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import catch_and_win.Init_Game;
import Player.Score_Manager;
import java.util.Random;

public class Items extends Thread{
    
    int X = 0; int Y = 0; int id = 0;
    Init_Game Screen;
    PlayerPanel playerPanel;
    Score_Manager score_Manager;
    ItemsPanel itemsPanel;
    //Graphics
    Rectangle2D hitBox = new Rectangle2D.Double(X, Y, 50, 50);
    Image item;
    //State of the current item
    byte type = 0; boolean stateOn = true; boolean availableItem = true;  
    
    Items(Init_Game IG, PlayerPanel PP, Score_Manager SM, ItemsPanel IP, 
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
        int answer = random.nextInt(10);
        //Asign if the item is good(1), or bad(0) according the byte value received.
        this.type = type;
        
        this.item = new ImageIcon(getClass().getResource(itemName)).getImage();
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
                    if(this.type == 0){
                    this.score_Manager.increaseScore();
                    } else {
                        this.score_Manager.decreaseScore();
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
}
