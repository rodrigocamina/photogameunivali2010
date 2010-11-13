package engine.control;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

import view.ScreenStart;





//================== Classe GamePanel ===================
//
// Implementa ????
//
//=======================================================
public class GamePanel extends Canvas
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8449076094604991140L;
	private static final int PWIDTH = 800; // size of panel
	private static final int PHEIGHT = 600;
	private Graphics2D dbg;
	
	

	
	
	//============= GamePanel ===============
	//
	// Construtor da classe
	//
	//=======================================
	public GamePanel()
	{
		setBackground(Color.white); // white background
		setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
	} // end of GamePanel()

	public void startGame(){
		// create the buffer
		
		
		setFocusable(true); // cria os componentes do jogo
		requestFocus(); // JPanel recebe key events
		new Controller(this); // Adiciona um Key Listener
		this.createBufferStrategy(2);
		
		
		
	}
	
	
	
	void cleanRenderer(){
		dbg = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
		dbg.setColor(Color.BLACK);
		dbg.fillRect (0, 0, PWIDTH, PHEIGHT);
	}

	//TODO retirar esses drawString ao final do projeto, eles s� servem como verifica��o de desempenho
	void draw(Screen drawableObject){
		
		drawableObject.drawAll(dbg);

	}

	
	
	void passToWindow(){
		this.getBufferStrategy().show();
	}


} 

