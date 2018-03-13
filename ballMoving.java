import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JFrame;
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.applet.*;
import java.util.Vector;
import java.util.Enumeration;
import javax.imageio.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.List;
public class ballMoving extends Canvas {
 // Plans:
 // Put in a side panel with items, just make another panel that's thin
 // make things happen when you press interact key when lad is in specific position
    // starting pos
    int myX = 0;
    int myY = 0;
    int gromboX = 300;
    int gromboY = 300;
    int inventory = 0;
    boolean inGrombo = false; 
    Image background;
    Image lad;
    Image grombo;
    Image inside1;
    Image key;
    Image gameOver;
    ArrayList<String> inventoryString = new ArrayList<String>();

    public ballMoving() {
        setSize(new Dimension(500, 500));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt(evt);
            }
        });
    }
 
    public void paint(Graphics g) {
        // have game over screen print if grombo catches you
        // game over if grombo comes within 30 units of player
        if(inGrombo == true){
        try {
                inside1 = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/inside1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics ag = inside1.getGraphics();
            g.drawImage(inside1,0,0,this);
             try {
                grombo = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/grombo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics mg = grombo.getGraphics();
            if(gromboX < myX){
                gromboX += 20;
            }
            else{
                gromboX -= 20;
            }
            if(gromboY < myY){
                gromboY += 20;
            }
            else{
                gromboY -= 20;
            }
            
            g.drawImage(grombo,gromboX,gromboY,this);
             try {
                lad = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/lad.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
              Graphics zg = lad.getGraphics();
              g.drawImage(lad,myX,myY,this);
             try {
                key = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/key.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics tg = key.getGraphics();
            g.drawImage(key,350,350,this);
            if((Math.abs(myX - gromboX) < 20) && (Math.abs(myY - gromboY) < 20)){
                System.out.println("You have been caught by Grombo. He will stew you for his brunch. Yum.");
                try {
                gameOver = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/gameOver.png"));
             } catch (IOException e) {
                e.printStackTrace();
              }
              Graphics lg = gameOver.getGraphics();
              g.drawImage(gameOver,0,0,this);
            }
        }
            else{
          if(background == null){
            try {
                background = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/desert.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Graphics og = background.getGraphics();
        g.drawImage(background,0,0,this);
        if( lad == null){
            try {
                lad = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/lad.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Graphics mg = lad.getGraphics();
        g.drawImage(lad,myX,myY,this);
        // g.fillOval(myX, myY, 30, 30);
    }
}
    public void moveIt(KeyEvent evt) {
     switch (evt.getKeyCode()) {
            case KeyEvent.VK_S:
                myY += 50;
                break;
            case KeyEvent.VK_W:
                myY -= 50;
                break;
            case KeyEvent.VK_A:
                myX -= 50;
                break;
            case KeyEvent.VK_D:
                myX += 50;
                break;
            case KeyEvent.VK_E:
                Interact();
                break;
            case KeyEvent.VK_I:
                Inventory();
                break;
        }
 
        repaint();
    }
    public void Interact(){
       if((myX == 0 && myY == 0) && (inventory == 0)){
           System.out.println("Your Trusty Sword lies at your feet. You pick it up.");
           inventoryString.add("Trusty Sword");
           inventory++;
        }
       else if(myX == 150 && myY == 150){
           System.out.println("THE TEMPLE OF GROMBO THE FIFTH, ENTER IF YOU DARE");
           System.out.println("If you wish to continue, grab the key and avoid Grombo. If he catches you,it is game over!");
           inGrombo = true;
           repaint();
        }
       else{
       System.out.println("Nothing to interact with here"); 
    }
    }
    public void drawLair(Graphics g){
        if(inGrombo == true){
            try {
                background = ImageIO.read(new File("C:/Users/fhsplab/Desktop/GuiGame/src/resources/desert.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Inventory(){
        
        if(inventory == 0){
            System.out.println("You have no items in your inventory!");
        }
        else{
            System.out.println("Your Inventory:");
            for(String s : inventoryString){
                System.out.println(s);
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Basic Game");
                System.out.println("Use WASD To Move and E to interact");
                System.out.println("Press I to open your Inventory!");
                System.out.println("");
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ballMoving ex = new ballMoving();
        frame.getContentPane().add(ex);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        ex.requestFocus();
    }
}