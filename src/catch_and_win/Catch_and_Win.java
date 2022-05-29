package catch_and_win;

public class Catch_and_Win {
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Init_Game().setVisible(true);
            }
        });
    }
}
