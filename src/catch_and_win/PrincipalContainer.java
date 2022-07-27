package catch_and_win;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class PrincipalContainer extends javax.swing.JFrame{
    MainScreen MainScreen;
    //Are you in main or game screen? 
    Byte ControlScreenManager = 0;
    public boolean gameStarted = false;
    public boolean onFinalScreen = false;
    public GameView_Panel GameViewPanel;
    
    public PrincipalContainer(){
        initComponents();
        //writeUserData("00:45", "142");
        //readUserData();
    }
    
    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setSize(530, 630);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        LOAD_MAIN_SCREEN();
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if(this.gameStarted){
            if(evt.getKeyCode() == KeyEvent.VK_A){
                //1 for left move
                GameViewPanel.move_Player.movePlayer((byte)1);
            }
            if(evt.getKeyCode() == KeyEvent.VK_D){
                //2 for right move
                GameViewPanel.move_Player.movePlayer((byte)2);
            }
            if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                //1 for left move
                GameViewPanel.move_Player.movePlayer((byte)1);
            }
            if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                //2 for right move
                GameViewPanel.move_Player.movePlayer((byte)2);
            }
        } else {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_SPACE){
                START_GAME();
                this.gameStarted = true;
            }
        }
        // FINAL SCREEN OPTIONS WITH KEYBOARD
        if(this.onFinalScreen){
            if(evt.getKeyCode() == KeyEvent.VK_X){
                this.getContentPane().removeAll();
                LOAD_MAIN_SCREEN();
            }
        }
    }
    

    public void LOAD_MAIN_SCREEN(){
        this.gameStarted = false;
        MainScreen = new MainScreen(this);
        this.getContentPane().add(MainScreen);
        this.revalidate();
        this.repaint();
    }
    

    public void actionPerformedStartBtn(ActionEvent e ){
        START_GAME();
        this.gameStarted = true;
    }
    

    public void START_GAME(){
        //Panel that do all about the game
        GameViewPanel = new GameView_Panel(this, readCurrentLevel());

        this.getContentPane().removeAll();
        this.getContentPane().add(GameViewPanel);
        this.revalidate();
        this.repaint();
    }
    

    // Read the lvl file for load data according the lvl
    private byte readCurrentLevel(){
        
        return 0;
    }


    public void writeUserData(String time, String score){
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); // New instance XML
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();             // Creates a new builder for a document
            DOMImplementation DOM_Implementation = docBuilder.getDOMImplementation();        // Implements DOM for the document builder    

            Document doc = DOM_Implementation.createDocument(null, "data", null); // Creates a doc DOM
            doc.setXmlVersion("1.0");

            Element user = doc.createElement("user");               // First tag USER

            Element best_time = doc.createElement("best_time");     // Tag time
            Text txt_time = doc.createTextNode(time); 
            best_time.appendChild(txt_time);

            Element best_score = doc.createElement("best_score");   // Tag score
            Text txt_score = doc.createTextNode(score); 
            best_score.appendChild(txt_score);

            // Append time and score in user tag
            user.appendChild(best_time);
            user.appendChild(best_score);

            doc.getDocumentElement().appendChild(user); // Add user to the XML root

            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File("user.xml"));

            Transformer transform = TransformerFactory.newInstance().newTransformer();
            transform.transform(source, result);
        }catch(Exception ex){

        }
    }


    private String[] readUserData(){
        String userData[] = new String[1];
        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); // New instance XML
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();             // Creates a new builder for a document
            Document doc = docBuilder.parse(new File("user.xml"));
            NodeList userPackage = doc.getDocumentElement().getElementsByTagName("user");
                Element userUnpack = (Element) userPackage.item(0);
                /*System.out.println(userUnpack.getElementsByTagName("best_time").item(0).getTextContent());
                System.out.println(userUnpack.getElementsByTagName("best_score").item(0).getTextContent());*/
            userData[0] = userUnpack.getElementsByTagName("best_time").item(0).getTextContent();
            userData[1] = userUnpack.getElementsByTagName("best_score").item(0).getTextContent();
            return (userData);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}

