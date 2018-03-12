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
    int inventory = 0;
    Image background;
    Image lad;  
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
       if(myX == 0 && myY == 0){
           System.out.println("Your Trusty Sword lies at your feet. You pick it up.");
           inventoryString.add("Trusty Sword");
           inventory++;
        }
       else if(myX == 150 && myY == 150){
           System.out.println("THE TEMPLE OF GROMBO THE FIFTH, ENTER IF YOU DARE");
           //Grombo(evt);
        }
       else{
       System.out.println("Nothing to interact with here"); 
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
    public void Grombo(KeyEvent evt){
        System.out.println("Do you wish to enter Grombo's lair: Y/N");
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_Y:
                System.out.println("WORK IN PROGRESS");
                break;
            case KeyEvent.VK_N:
                System.out.println("You leave the entrance to Grombo's lair, relief coursing through you.");
                break;
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