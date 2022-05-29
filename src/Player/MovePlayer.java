package Player;

import catch_and_win.Init_Game;

public class MovePlayer implements Runnable{
    public byte leftRight_currentMove = 0;
    private boolean stateOn = true;
    private boolean moving = false;
    private int moving_Value = 0;
    private byte keyboard_key = 0;
    private Thread thread_Anim;
    PlayerPanel player;
    Init_Game screenGame;
    AnimationPlayer animator;
    
    public MovePlayer(PlayerPanel player, Init_Game screenGame){
        this.player = player;
        this.screenGame = screenGame;
        this.animator = new AnimationPlayer(this, this.player);
        this.thread_Anim = new Thread(this.animator);
        this.thread_Anim.start();
    }

    @Override
    public void run() {
        while(stateOn){
            //If the players coord are older than -10 and key indicates left...
            if(player.X_coords >= -10 && keyboard_key == 1){
                //0 = left
                leftRight_currentMove = 0;
                player.X_coords -= moving_Value;
                
            //If the players coord are less than 435 and key indicates right...
            } else if(player.X_coords <= 435 && keyboard_key == 2){
                leftRight_currentMove = 1;
                player.X_coords += moving_Value;
            } else {
                leftRight_currentMove = 2;
                player.changeAnimation("initImageState");
            }
            player.repaint();
            try {
                Thread.sleep(6);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    //A = LEFT = 1, D = DERECHA = RIGHT
    public void movePlayer(byte key){
        moving_Value = 3;
        this.keyboard_key = key;
    }
//    public void stopPlayer(){
//        moving_Value = 0;
//    }
}
