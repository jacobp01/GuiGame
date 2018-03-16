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
    Image sword;
    Image oasis;
    Image oasisicon;
    Image hermit;
    Image riddleScreen;
    Image riddleAns1;
    ArrayList<String> inventoryString = new ArrayList<String>();
    boolean gameOver1 = false;
    boolean gromboDone = false;
    boolean inBini = false;
    boolean biniDone = false;
    boolean inOverworld = true;
    boolean solved;
    boolean inRiddle = false;
    
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
        if(gameOver1 == false){
        // have game over screen print if grombo catches you
        // game over if grombo comes within 50 units of player
        if(inGrombo == true){
           try {
                inside1 = ImageIO.read(new File("src/resources/inside1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics ag = inside1.getGraphics();
            g.drawImage(inside1,0,0,this);
             try {
                grombo = ImageIO.read(new File("src/resources/grombo.png"));
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
                lad = ImageIO.read(new File("src/resources/lad.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
              Graphics zg = lad.getGraphics();
              g.drawImage(lad,myX,myY,this);
             try {
                key = ImageIO.read(new File("src/resources/key.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics tg = key.getGraphics();
            g.drawImage(key,350,350,this);
            if((Math.abs(myX - gromboX) < 50) && (Math.abs(myY - gromboY) < 50)){
                System.out.println("You have been caught by Grombo. He will stew you for his brunch. Yum.");
                try {
                gameOver = ImageIO.read(new File("src/resources/gameOver.png"));
                gameOver1 = true;
             } catch (IOException e) {
                e.printStackTrace();
              }
              Graphics lg = gameOver.getGraphics();
              g.drawImage(gameOver,0,0,this);
            }
        }
        if(inBini == true){
            try {
                oasis = ImageIO.read(new File("src/resources/oasis.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics rg = oasis.getGraphics();
            g.drawImage(oasis,0,0,this);
              try {
                hermit = ImageIO.read(new File("src/resources/hermit.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Graphics hg = hermit.getGraphics();
            g.drawImage(hermit, 0, 0, this);
                         try {
                lad = ImageIO.read(new File("src/resources/lad.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
              Graphics zg = lad.getGraphics();
              g.drawImage(lad,myX,myY,this);
       }
        if(inRiddle == true){
            try{
                riddleScreen = ImageIO.read(new File("src/resources/blackB.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Graphics ig = riddleScreen.getGraphics();
            g.drawImage(riddleScreen,0,0,this);
            
             try{
                riddleAns1= ImageIO.read(new File("src/resources/riddle1.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Graphics qg = riddleAns1.getGraphics();
            g.drawImage(riddleAns1, 100, 100, this);
        }
        if(inOverworld == true){
          if(background == null){
            try {
                background = ImageIO.read(new File("src/resources/desert.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Graphics og = background.getGraphics();
        g.drawImage(background,0,0,this);
                if(inventory == 0){
        try {
                sword = ImageIO.read(new File("src/resources/sword.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        Graphics ig = sword.getGraphics();
        g.drawImage(sword,0,50,this);
        }
        else{
            g.drawImage(background,0,0,this);
        }
                try {
                oasisicon = ImageIO.read(new File("src/resources/oasisicon.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        Graphics pg = oasisicon.getGraphics();
        g.drawImage(oasisicon, 400, 350, this);
        if( lad == null){
            try {
                lad = ImageIO.read(new File("src/resources/lad.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Graphics mg = lad.getGraphics();
        g.drawImage(lad,myX,myY,this);
        

        // g.fillOval(myX, myY, 30, 30);
    }
}
else{
    g.drawImage(gameOver,0,0,this);
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
       if((myX == 0 && myY == 50) && inOverworld == true){
           System.out.println("Your Trusty Sword lies at your feet. You pick it up.");
           inventoryString.add("Trusty Sword");
           inventory++;
           return;
         }
         
       if(gromboDone == false && inOverworld == true){
        if(myX == 150 && myY == 150){
           System.out.println("THE TEMPLE OF GROMBO THE FIFTH, ENTER IF YOU DARE");
           System.out.println("If you wish to continue, grab the key and avoid Grombo. If he catches you,it is game over!");
           inGrombo = true;
           inOverworld = false;
           repaint();
           return;
        }
       }
        else{
             if((myX == 150 && myY == 150) && (inOverworld == true)){
            System.out.println("You've already defeated Grombo. You have no need to enter his lair again.");
        }
               else{
           System.out.println("Nothing to interact with here"); 
        }
       }
    
         if(biniDone == false && inOverworld == true){
         if(myX == 400 && myY == 350){
            System.out.println("The oasis of The Childish Chombino, wisest man in the land.");
            System.out.println("You must solve his riddles and obtain the Orb of Bingo");
            inBini = true;
            inOverworld = false;
            repaint();
            return;
        }
       }
       if(biniDone == true && inOverworld == true){
         if(myX == 400 && myY == 350){
            System.out.println("You've already solved Chombino's riddles. The oasis is vacant.");
        }
       }
  
       if(inBini == true){
        if(myX == 0 && myY == 0){
        System.out.println("Chombino speaks");
        System.out.println("Chombino: Foolish lad. For entering my oasis you must solve my riddle");
        inRiddle = true;
        inBini = false;
        repaint();
        System.out.println("You find yourself in a dark room suddenly, answers to riddles gleaming on the floor");
        
        if(solved == true){
            System.out.println("Impossible! You solved my riddle!");
            System.out.println("Take this: It's the mystical Orb of Bingo");
            inventoryString.add("Orb of Bingo");
            System.out.println("Good luck in your quest");
            inBini = false;
            inOverworld = true;
            biniDone = true;
            myX = 0;
            myY = 0;
            repaint();
            System.out.println("In a flash, Chombino vanishes from the oasis.");
            System.out.println("You exit, clutching the orb of bingo.");
        }
        else{
            System.out.println("Wrong! I knew you were a fool. Try again lad!");
        }
    }
    }
       if(inGrombo == true){
           if(myX == 350 && myY == 350){
           System.out.println("You pick up the key!");
           inventoryString.add("Key");
           inGrombo = false;
           inOverworld = true; 
           gromboDone = true;
           myX = 150;
           myY = 150;
           repaint();
           System.out.println("You narrowly escape Grombo's lair, the beast shouting at you as you flee,"); 
           System.out.println("unable to exit through the tiny door to his own lair.");
        }
        else{
            System.out.println("Nothing to do here! Go Grab the Key! Also grombo is immune to swords.");
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
        JFrame frame = new JFrame("Adventure! Knightman Quest Land XXV");
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