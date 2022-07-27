package Items;

import Player.PlayerPanel;
import Player.ScoreManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Timer;
import catch_and_win.PrincipalContainer;
import java.util.Random;

public class ItemsPanel extends JPanel{
    
// Initialize
    Map<Integer, Item> moveItemsList = new Hashtable<Integer, Item>();
    Graphics2D global_Graphic;
    Random random = new Random();
    PrincipalContainer Screen;
    PlayerPanel playerPanel; ScoreManager scoreManager;
    Timer generator = generator_Items();
    
    int X = 0; int Y = 0; int counterItem = 0;
    
    byte type = 0;
    String itemName = null;
    
    String itemTypes[][] = {
                            {"/Img/g1.png","/Img/g2.png","/Img/g3.png"},
                            {"/Img/b1.png","/Img/b2.png","/Img/b3.png"}
                            };
    
    public ItemsPanel(PrincipalContainer Screen, PlayerPanel PP, ScoreManager SM){
        this.Screen = Screen;
        this.setBounds(0,0,Screen.getWidth(),Screen.getHeight());
        this.setOpaque(false);
        
        // this.setBackground(Color.red);
        this.playerPanel = PP;
        this.scoreManager = SM;
        
        //Start to generate items...
        generator.start();
    }
    
    @Override
    public void paint(Graphics graphic){
        super.paintComponent(graphic);
        this.global_Graphic = (Graphics2D)graphic;
        
        //It creates an iterator for understand the map of the items threads
        Iterator<Map.Entry<Integer, Item>> iterator = this.moveItemsList.entrySet().iterator();
        //System.out.println("\n");
        
        //While the iterator has more items...
        while(iterator.hasNext()){
            //Get the element in the current node, according the cycle.
            Map.Entry<Integer, Item> entry = iterator.next();
            
            //If the item is available; if is alive, if not, remove from the list, 
            //deleting him.
            if(entry.getValue().availableItem){
               //System.out.println(entry.getValue().id);
               
               //Draws it.
               this.global_Graphic.drawImage(entry.getValue().item, entry.getValue().X, 
                       entry.getValue().Y, 50, 50, null);
            } else {
                iterator.remove();
            }
        }
    }
    
    public Timer generator_Items(){
        return new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                X = (int) (Math.random() * 400);
                Y = 0;
                repaint();
                getItemType();
                //Create an item in moveItems...
                moveItemsList.put(counterItem, new Item(Screen, 
                                                        playerPanel, 
                                                        scoreManager,
                                                        ItemsPanel.this, 
                                                        X, 
                                                        -60, 
                                                        counterItem, 
                                                        itemName, 
                                                        type));
                //Start the item thread according the key (counterItem)
                moveItemsList.get(counterItem).start();
                counterItem++;
            }
        });
    }
    
    public void stopGeneratorItems(){
        generator.stop();
    }
    
    public void getItemType(){
        this.type = (byte)(this.random.nextInt(2));
        this.itemName = this.itemTypes[this.type][(byte)this.random.nextInt(3)];
        
    }
    
}