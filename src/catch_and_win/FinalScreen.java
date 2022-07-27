package catch_and_win;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class FinalScreen extends JPanel{
    final PrincipalContainer PRINCIPAL_CONTAINER;
    final JButton btn_restart;
    final JButton btn_close;

    public FinalScreen(PrincipalContainer principalContainer){
        FlowLayout flowLayout = new FlowLayout(); // For the panel "options"

        this.PRINCIPAL_CONTAINER = principalContainer;
        this.setSize(this.PRINCIPAL_CONTAINER.getWidth(), this.PRINCIPAL_CONTAINER.getHeight());
        this.setLayout(new BorderLayout());
        this.setBackground(Color.green);

        //Load titleContainer in panel
        JPanel titleContainer = new JPanel(); 
        JPanel titleLogo = new JPanel();
        
        JPanel panel_options = new JPanel();
        JPanel options_stuffContainer = new JPanel();
        
        titleContainer.setPreferredSize(new Dimension(0,300));
        titleContainer.setBackground(Color.RED);
        titleContainer.setLayout(null);
        
        titleLogo.setSize(200, 200);
        titleLogo.setLocation(40,40);
        titleLogo.setBackground(Color.green);
        titleContainer.add(titleLogo);
        
        panel_options.setBackground(Color.CYAN);
        
        flowLayout.setHgap(20);
        flowLayout.setVgap(100);
        
        panel_options.setLayout(flowLayout);

        btn_restart = new JButton("Restart (Press SPACE)");
        btn_restart.setBounds(0,0,250,0);
        btn_restart.setHorizontalAlignment(JLabel.CENTER);
        btn_restart.setFocusable(false);
        
        btn_close = new JButton("Exit and go to menu (Press x)");
        btn_close.setBounds(0,0,250,0);
        btn_close.setHorizontalAlignment(JLabel.CENTER);
        btn_close.setFocusable(false);
        
        panel_options.add(btn_restart, null);
        panel_options.add(btn_close, null);
        
        options_stuffContainer.setPreferredSize(new Dimension(0,100));
        options_stuffContainer.setBackground(Color.BLACK);
        options_stuffContainer.setLayout(null);
        
        //Load all in panel LOGO
        this.add(titleContainer,BorderLayout.NORTH);
        this.add(panel_options, BorderLayout.CENTER);
        this.add(options_stuffContainer, BorderLayout.SOUTH);

        this.PRINCIPAL_CONTAINER.gameStarted = false;
        this.PRINCIPAL_CONTAINER.onFinalScreen = true;
    }
}
