package engine.control;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;




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

	//TODO retirar esses drawString ao final do projeto, eles só servem como verificação de desempenho
	void draw(Screen drawableObject){
		drawableObject.drawAll(dbg);
		dbg.setColor(Color.WHITE);
		dbg.drawString("Draw difftime "+Controller.getInstance().diffTime2, 12, 35);
		dbg.drawString("Simulate difftime "+Controller.getInstance().diffTime, 12, 75);
	}

	void passToWindow(){
		this.getBufferStrategy().show();
	}


} 

