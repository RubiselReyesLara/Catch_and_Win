package Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimationPlayer implements Runnable{
    private MovePlayer movePlayerThread;
    private PlayerPanel playerPanel;
    private String []statesAnimationLeft = {"leftStateSprite1","leftStateSprite","leftStateSprite2"};
    private String []statesAnimationRight = {"rightStateSprite1","rightStateSprite","rightStateSprite2"};
    private int i = 0;
    
    Thread t;

    public AnimationPlayer(MovePlayer mv, PlayerPanel playerPanel){
        this.movePlayerThread = mv;
        this.playerPanel = playerPanel;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                if(this.movePlayerThread.leftRight_currentMove == 0 && i <= 2){
                    //Change animation in playerPanel to current state however right or left.
                    this.playerPanel.changeAnimation(statesAnimationLeft[i]);
                    i++;
                } else if(this.movePlayerThread.leftRight_currentMove == 1 && i <= 2){
                    this.playerPanel.changeAnimation(statesAnimationRight[i]);
                    i++;
                } else {
                    i = 0;
                }
                
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(AnimationPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
