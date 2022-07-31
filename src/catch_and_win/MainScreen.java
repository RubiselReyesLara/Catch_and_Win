package catch_and_win;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainScreen extends JPanel{
    final PrincipalContainer PRINCIPAL_CONTAINER;
    final JButton BTN_START_TITLE;
    
    public MainScreen(PrincipalContainer screen) {
        PRINCIPAL_CONTAINER = screen;
        this.setSize(PRINCIPAL_CONTAINER.getWidth(), PRINCIPAL_CONTAINER.getHeight());
        this.setLayout(new BorderLayout());
        
        //Title container
        JPanel titleContainer = new DrawableJPanel("/Img/title.png", 0,0,10,10);
        titleContainer.setPreferredSize(new Dimension(0,300));
        titleContainer.setBackground(Color.BLUE);
        titleContainer.setLayout(null);
        titleContainer.setOpaque(false);
        
        // Instruction container
        JPanel instructionContainer = new JPanel();
        instructionContainer.setLayout(null);
        instructionContainer.setOpaque(false);
        BTN_START_TITLE = new JButton();
        BTN_START_TITLE.setHorizontalAlignment(JLabel.CENTER);
        buttonSettings();
        instructionContainer.add(BTN_START_TITLE, BorderLayout.CENTER);
        
        // Credits container
        JPanel options_stuffContainer = new JPanel();
        options_stuffContainer.setPreferredSize(new Dimension(0,50));
        options_stuffContainer.setOpaque(false);
        JLabel credits = new JLabel("Created by Rubisel RL");
        credits.setFont(new Font("Tw Cent MT", Font.BOLD, 16));
        credits.setForeground(Color.YELLOW);
        options_stuffContainer.add(credits);
        
        //Load all in panel MainScreen
        this.add(titleContainer,BorderLayout.NORTH);
        this.add(instructionContainer, BorderLayout.CENTER);
        this.add(options_stuffContainer, BorderLayout.SOUTH);
        
    }
    
    private void buttonEvent(){
        BTN_START_TITLE.setFocusable(false);
        BTN_START_TITLE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PRINCIPAL_CONTAINER.actionPerformedStartBtn(evt);
            }
        });
    }
    
    private void buttonSettings(){
        this.BTN_START_TITLE.setBounds(PRINCIPAL_CONTAINER.getHeight()/2 - 180,100,250,60);
        this.BTN_START_TITLE.setBorderPainted(false);
        this.BTN_START_TITLE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.BTN_START_TITLE.setFocusPainted(false);
        this.BTN_START_TITLE.setContentAreaFilled(false);
        this.BTN_START_TITLE.setIcon(new ImageIcon(getClass().getResource("/Img/p_e_s.png")));
        buttonEvent();
    }
    
    @Override
    protected void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        // Background
        graphic.drawImage(new ImageIcon(getClass().getResource("/Img/main_screen_bg.png"))
                        .getImage(), 0, 0, PRINCIPAL_CONTAINER.getWidth(), PRINCIPAL_CONTAINER.getHeight(), this);
    }
}
