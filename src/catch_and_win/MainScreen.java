
package catch_and_win;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MainScreen extends JPanel{
    final Init_Game INIT_GAME;
    final JButton btn_startTitle;
    
    public MainScreen(Init_Game screen) {
        INIT_GAME = screen;
        this.setSize(INIT_GAME.getWidth(), INIT_GAME.getHeight());
        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);
        
        //Load titleContainer in panel
        JPanel titleContainer = new JPanel(); 
        JPanel titleLogo = new JPanel();
        
        JPanel instructionContainer = new JPanel();
        JPanel options_stuffContainer = new JPanel();
        
        titleContainer.setPreferredSize(new Dimension(0,300));
        titleContainer.setBackground(Color.BLUE);
        titleContainer.setLayout(null);
        
        titleLogo.setSize(200, 200);
        titleLogo.setLocation(40,40);
        titleLogo.setBackground(Color.yellow);
        titleContainer.add(titleLogo);
        
        instructionContainer.setBackground(Color.CYAN);
        instructionContainer.setLayout(null);
        btn_startTitle = new JButton("Press any key to start");
        btn_startTitle.setBounds(INIT_GAME.getHeight()/2 - 180,100,250,60);
        
        btn_startTitle.setHorizontalAlignment(JLabel.CENTER);
        
        instructionContainer.add(btn_startTitle, BorderLayout.CENTER);
        
        options_stuffContainer.setPreferredSize(new Dimension(0,100));
        options_stuffContainer.setBackground(Color.BLACK);
        options_stuffContainer.setLayout(null);
        
        //Load all in panel LOGO
        this.add(titleContainer,BorderLayout.NORTH);
        this.add(instructionContainer, BorderLayout.CENTER);
        this.add(options_stuffContainer, BorderLayout.SOUTH);
        
        buttonEvent();
    }
    
    private void buttonEvent(){
        btn_startTitle.setFocusable(false);
        btn_startTitle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                INIT_GAME.actionPerformedStartBtn(evt);
            }
        });
    }
    
}
