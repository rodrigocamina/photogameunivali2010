package engine;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;

import engine.control.Controller;
import engine.control.GamePanel;

public class Main extends JApplet
{

   /**
	 * 
	 */
	private static final long serialVersionUID = 5877114903951358776L;

private GamePanel gp;
   
   int mousecontext = 0;

   public static Main instance;
   public static final int WIDTH = 800;
   public static final int HEIGHT = 600;

   public void init()
   {
     instance = this;
     
     setFocusable(true);
     
     Container c = getContentPane();
     c.setLayout( new BorderLayout() );   

     gp = new GamePanel();
     c.add(gp, "Center");
     
     resize(WIDTH,HEIGHT);
          
     gp.startGame();
   }
   
 
   public void start()
   {  }

   public void stop()
   {  Controller.getInstance().stopGame();  }

   public void destroy()
   {  Controller.getInstance().stopGame();  }

}