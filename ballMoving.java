import java.awt.*;
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
public class ballMoving extends Canvas {
 // Plans:
 // Put in a side panel with items, just make another panel that's thin
 // make things happen when you press interact key when lad is in specific position
    
    // starting pos
    int myX = 400;
    int myY = 400;
    Image background;
    Image lad;

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
                myY += 10;
                break;
            case KeyEvent.VK_W:
                myY -= 10;
                break;
            case KeyEvent.VK_A:
                myX -= 10;
                break;
            case KeyEvent.VK_D:
                myX += 10;
                break;
        }
 
        repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Basic Game");
                System.out.print("Use WASD To Move");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ballMoving ex = new ballMoving();
        frame.getContentPane().add(ex);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        ex.requestFocus();
    }
}