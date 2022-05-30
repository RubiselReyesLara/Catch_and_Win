package Items;

import Player.PlayerPanel;
import Player.Score_Manager;
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
import catch_and_win.Init_Game;
import java.util.Random;

//public class ItemsPanel extends JPanel{
    
    //Initialize
//    Map<Integer, MoveItems> moveItemsList = new Hashtable<Integer, MoveItems>();
//    Graphics2D global_Graphic;
//    
//    Init_Game Screen;
//    ItemsPanel This; PlayerPanel playerPanel;
//    
//    int X = 0; int Y = 0; int counterItem = 0;
//    
//    public ItemsPanel(Init_Game Screen, PlayerPanel PP){
//        this.Screen = Screen;
//        this.setBounds(0,0,Screen.getWidth(),Screen.getHeight());
//        this.setOpaque(false);
////        this.setBackground(Color.red);
//        this.playerPanel = PP;
//        this.This = this;
//        //Start to generate items...
////        generator_Items().start();
//    }
//    
//    @Override
//    public void paint(Graphics graphic){
//        super.paintComponent(graphic);
//        global_Graphic = (Graphics2D)graphic;
//        //It creates an iterator for understand the map of the items threads
//        Iterator<Map.Entry<Integer, MoveItems>> iterator = moveItemsList.entrySet().iterator();
//        System.out.println("\n");
//        //While the iterator has more items...
//        while(iterator.hasNext()){
//            //Get the element in the current node, according the cycle.
//            Map.Entry<Integer, MoveItems> entry = iterator.next();
//            //If the item is available; if is alive, if not, remove from the list, deleting him.
//            if(entry.getValue().availableItem){
//               System.out.println(entry.getValue().id);
//               //Draws it.
//               global_Graphic.drawImage(entry.getValue().item, entry.getValue().X, entry.getValue().Y, 50, 50, null);
//            } else {
//                iterator.remove();
//            }
//        }
//    }
//    
//    public Timer generator_Items(){
//        return new Timer(500, new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//                X = (int) (Math.random() * 400);
//                Y = 0;
//                repaint();
//                //Create an item in moveItems...
//                moveItemsList.put(counterItem, new MoveItems(Screen, playerPanel, This, X, -60, counterItem, true));
//                //Start the item thread according the key (counterItem)
//                moveItemsList.get(counterItem).start();
//                counterItem++;
//            }
//        });
//    }
//}


//DIVISION

public class ItemsPanel extends JPanel{
    
// Initialize
    Map<Integer, Items> moveItemsList = new Hashtable<Integer, Items>();
    Graphics2D global_Graphic;
    Random random = new Random();
    Init_Game Screen;
    ItemsPanel This; PlayerPanel playerPanel; Score_Manager scoreManager;
    Timer generator = generator_Items();
    
    int X = 0; int Y = 0; int counterItem = 0;
    
    byte type = 0;
    String itemName = null;
    
    String itemTypes[][] = {
                            {"/Img/Lvl1/g1.png","/Img/Lvl1/g2.png","/Img/Lvl1/g3.png"},
                            {"/Img/Lvl1/b1.png","/Img/Lvl1/b2.png","/Img/Lvl1/b3.png"}
                            };
    
    public ItemsPanel(Init_Game Screen, PlayerPanel PP, Score_Manager SM){
        this.Screen = Screen;
        this.setBounds(0,0,Screen.getWidth(),Screen.getHeight());
        this.setOpaque(false);
        // this.setBackground(Color.red);
        this.playerPanel = PP;
        this.scoreManager = SM;
        this.This = this;
        //Start to generate items...
        generator.start();
    }
    
    @Override
    public void paint(Graphics graphic){
        super.paintComponent(graphic);
        global_Graphic = (Graphics2D)graphic;
        //It creates an iterator for understand the map of the items threads
        Iterator<Map.Entry<Integer, Items>> iterator = 
                moveItemsList.entrySet().iterator();
        System.out.println("\n");
        //While the iterator has more items...
        while(iterator.hasNext()){
            //Get the element in the current node, according the cycle.
            Map.Entry<Integer, Items> entry = iterator.next();
            //If the item is available; if is alive, if not, remove from the list, 
            //deleting him.
            if(entry.getValue().availableItem){
               System.out.println(entry.getValue().id);
               //Draws it.
               global_Graphic.drawImage(entry.getValue().item, entry.getValue().X, 
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
                moveItemsList.put(counterItem, new Items(Screen, playerPanel, 
                        scoreManager,This, X, -60, counterItem, itemName, type));
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
        System.out.println(this.type + " tipo ");
        this.itemName = this.itemTypes[this.type][(byte)this.random.nextInt(3)];
        
    }
    
}