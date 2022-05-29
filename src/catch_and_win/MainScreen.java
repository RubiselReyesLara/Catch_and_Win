
package catch_and_win;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MainScreen extends JPanel{
    public MainScreen(Init_Game screen) {
        this.setSize(screen.getWidth(), screen.getHeight());
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
        instructionContainer.setLayout(new BorderLayout());
        JLabel startTitle = new JLabel("Press any key to start");
        startTitle.setHorizontalAlignment(JLabel.CENTER);
        instructionContainer.add(startTitle, BorderLayout.CENTER);
        
        options_stuffContainer.setPreferredSize(new Dimension(0,100));
        options_stuffContainer.setBackground(Color.BLACK);
        options_stuffContainer.setLayout(null);
        
        //Load all in panel LOGO
        this.add(titleContainer,BorderLayout.NORTH);
        this.add(instructionContainer, BorderLayout.CENTER);
        this.add(options_stuffContainer, BorderLayout.SOUTH);
    }
}
