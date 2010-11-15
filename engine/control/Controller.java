package engine.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import view.SplashScreen;
import beans.Personagem;
import beans.SpriteSystem;
import engine.sound.SoundController;
import engine.util.Utilities;




//================== Classe Controller ===================
//
// Implementa o modulo de controle do jogo
//
//========================================================
public class Controller implements Runnable {

	private GamePanel gamePanel;
	private boolean running = false; // encerra a animacao
	//private boolean gameOver = false; // encerra o jogo
	private Thread animator; // para a animacao nao parar
	private Screen actualScreen;

	
	
	

	private SoundController soundControler = new SoundController();
	private static Controller instance;
	public long diffTime, lastTime;
	public long diffTime2, lastTime2;
	private int speed = 10;
	List<Personagem> personagens = new ArrayList<Personagem>();
	List<BufferedImage> stages = new ArrayList<BufferedImage>();

	public Personagem getPersonagens(int i) {
		return personagens.get(i);
	}

	public BufferedImage getStages(int i) {
		return stages.get(i);
	}
	
	private void loadImage()
	{
		try 
		{
			//=========Carrega os Cenarios do jogos
			stages.add(Utilities.loadImage("/image/Cenarios/cenario_0.png"));
			stages.add(Utilities.loadImage("/image/Cenarios/cenario_1.png"));
			stages.add(Utilities.loadImage("/image/Cenarios/cenario_2.png"));
			stages.add(Utilities.loadImage("/image/Cenarios/cenario_3.png"));
			
			
			
			
			//==============personagem 0 fire head=======================
			SpriteSystem sprite = new SpriteSystem();
			
			sprite.init(0, 2, SpriteSystem.FRONTWALK);
			sprite.init(0, 2, SpriteSystem.BACKWALK);
			sprite.init(3, 4, SpriteSystem.STANDING);
			sprite.init(5, 5, SpriteSystem.DEFENSEUP);
			sprite.init(6, 8, SpriteSystem.DAMAGE);			
			sprite.init(9, 11, SpriteSystem.PUNCHUP);
			sprite.init(12, 15, SpriteSystem.KICKUP);
			sprite.init(16, 18, SpriteSystem.SPECIAL);
			sprite.init(21, 21, SpriteSystem.DOWN);
			sprite.init(22, 22, SpriteSystem.DEFENSEDOWN);
			sprite.init(23, 25, SpriteSystem.PUNCHDOWN);
			sprite.init(26, 30, SpriteSystem.DEFENSEUP);
									
			personagens.add(new Personagem(sprite));
			//=============================================================
			
			
			//==============personagem 1 madinbu =======================
			sprite = new SpriteSystem();
			
			sprite.init(0, 2, SpriteSystem.FRONTWALK);
			sprite.init(0, 2, SpriteSystem.BACKWALK);
			sprite.init(3, 5, SpriteSystem.STANDING);
			sprite.init(6, 6, SpriteSystem.DEFENSEUP);
			sprite.init(7, 7, SpriteSystem.DAMAGE);			
			sprite.init(8, 11, SpriteSystem.PUNCHUP);
			sprite.init(12, 14, SpriteSystem.KICKUP);
			sprite.init(15, 20, SpriteSystem.SPECIAL);
			sprite.init(21, 21, SpriteSystem.DOWN);
			sprite.init(22, 22, SpriteSystem.DEFENSEDOWN);
			sprite.init(23, 25, SpriteSystem.PUNCHDOWN);
			sprite.init(26, 28, SpriteSystem.DEFENSEUP);
									
			personagens.add(new Personagem(sprite));
			//=============================================================
			//====== personagem 2 big boss ========================
			sprite = new SpriteSystem();
			sprite.init(0, 1, SpriteSystem.FRONTWALK);
			sprite.init(0, 1, SpriteSystem.BACKWALK);
			sprite.init(2, 4, SpriteSystem.STANDING);
			sprite.init(5, 5, SpriteSystem.DEFENSEUP);
			sprite.init(6, 6, SpriteSystem.DAMAGE);			
			sprite.init(7, 9, SpriteSystem.PUNCHUP);
			sprite.init(10, 11, SpriteSystem.KICKUP);
			sprite.init(12, 15, SpriteSystem.SPECIAL);
			sprite.init(17, 17, SpriteSystem.DOWN);
			sprite.init(18, 18, SpriteSystem.DEFENSEDOWN);
			sprite.init(19, 20, SpriteSystem.PUNCHDOWN);
			sprite.init(21, 21, SpriteSystem.DEFENSEUP);		
			
			personagens.add(new Personagem(sprite));
			//=======================================================
			
			//====== personagem 3 monitora ========================
			sprite = new SpriteSystem();
			sprite.init(0, 4, SpriteSystem.FRONTWALK);
			sprite.init(0, 4, SpriteSystem.BACKWALK);
			sprite.init(5, 8, SpriteSystem.STANDING);
			sprite.init( 9, 9, SpriteSystem.DEFENSEUP);
			sprite.init( 10, 11, SpriteSystem.DAMAGE);			
			sprite.init(12, 15, SpriteSystem.PUNCHUP);
			sprite.init(16, 20, SpriteSystem.KICKUP);
			sprite.init(21, 25, SpriteSystem.SPECIAL);
			sprite.init(27, 27, SpriteSystem.DOWN);
			sprite.init(28, 28, SpriteSystem.DEFENSEDOWN);
			sprite.init(29, 32, SpriteSystem.PUNCHDOWN);
			sprite.init(33, 37, SpriteSystem.DEFENSEUP);		
			
			personagens.add(new Personagem(sprite));
			//=======================================================
			
			//====== personagem 4 tche ========================
			sprite = new SpriteSystem();
			sprite.init(0, 3, SpriteSystem.FRONTWALK);
			sprite.init(0, 3, SpriteSystem.BACKWALK);
			sprite.init(4, 7, SpriteSystem.STANDING);
			sprite.init( 8, 8, SpriteSystem.DEFENSEUP);
			sprite.init( 9, 11, SpriteSystem.DAMAGE);			
			sprite.init(12, 14, SpriteSystem.PUNCHUP);
			sprite.init(15, 17, SpriteSystem.KICKUP);
			sprite.init(18, 21, SpriteSystem.SPECIAL);
			sprite.init(23, 23, SpriteSystem.DOWN);
			sprite.init(24, 24, SpriteSystem.DEFENSEDOWN);
			sprite.init(25, 27, SpriteSystem.PUNCHDOWN);
			sprite.init(28, 31, SpriteSystem.DEFENSEUP);		
			
			personagens.add(new Personagem(sprite));
			//=======================================================
			
			//====== personagem 5 mallock ========================
			sprite = new SpriteSystem();
			sprite.init(0, 2, SpriteSystem.FRONTWALK);
			sprite.init(0, 2, SpriteSystem.BACKWALK);
			sprite.init(3, 6, SpriteSystem.STANDING);
			sprite.init( 7, 7, SpriteSystem.DEFENSEUP);
			sprite.init( 8, 10, SpriteSystem.DAMAGE);			
			sprite.init(11, 14, SpriteSystem.PUNCHUP);
			sprite.init(15, 18, SpriteSystem.KICKUP);
			sprite.init(19, 22, SpriteSystem.SPECIAL);
			sprite.init(23, 23, SpriteSystem.DOWN);
			sprite.init(24, 24, SpriteSystem.DEFENSEDOWN);
			sprite.init(25, 28, SpriteSystem.PUNCHDOWN);
			sprite.init(29, 32, SpriteSystem.DEFENSEUP);		
			
			personagens.add(new Personagem(sprite));
			//=======================================================
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	
	
	
	//============ Controller ===============
	//
	// Construtor da classe
	//
	//=======================================
	Controller(GamePanel gamePanel) 
	{
		instance = this;
		this.gamePanel = gamePanel;
		//TODO
	

		this.actualScreen = new SplashScreen();

		loadImage();
		
		//============= Eventos de Mouse =================
		//
		// Direciona os eventos de mouse do GamePanel para
		//a Screen apresentada
		//
		//================================================
		gamePanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				actualScreen.mouseReleased(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				actualScreen.mousePressed(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				actualScreen.mouseExited(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				actualScreen.mouseEntered(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				actualScreen.mouseClicked(e);
			}
		});
		
		gamePanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				actualScreen.mouseMoved(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				actualScreen.mouseDragged(e);
			}
		});
		
		//============= Eventos de Teclado =================
		//
		// Direciona os eventos de teclado do GamePanel para
		//a Screen apresentada
		//
		//==================================================
		gamePanel.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				actualScreen.keyTyped(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				actualScreen.keyReleased(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				actualScreen.keyPressed(e);
				/*if(e.getKeyCode()==KeyEvent.VK_RIGHT&&speed<30){
					speed+=2;
				}else if(e.getKeyCode()==KeyEvent.VK_LEFT&&speed>0){
					speed-=2;
					if(speed<0){
						speed=0;
					}
				}*/
			}
		});
		
		startGame();
	}

	public static Controller getInstance() {
		return instance;
	}
		
	public void startGame() {
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	}

	public void stopGame() {
		running = false;
	}


	public void run() {
		running = true;
		diffTime = 0;
		lastTime = System.currentTimeMillis();
		diffTime2 = 0;
		lastTime2 = System.currentTimeMillis();


		new Thread(){
			public void run() {
				while (running) {					
					try {
						//if(speed>0){
						//	Thread.sleep((long)(200/speed));
						//}else{
							Thread.sleep(15);
						//}
						actualScreen.simulateAll(diffTime); // game state is updated
						if(speed>0){
							diffTime = (long)((System.currentTimeMillis() - lastTime)*speed/10);
						}else{
							diffTime = 0;
						}
						lastTime = System.currentTimeMillis();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		while (running) {
			try{
				if(gamePanel.hasFocus()){
					gamePanel.cleanRenderer(); // render to a buffer
					gamePanel.draw(actualScreen);
					gamePanel.passToWindow();
					diffTime2 = System.currentTimeMillis() - lastTime2;
					lastTime2 = System.currentTimeMillis();
					Thread.sleep(20);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.exit(0); // so enclosing JFrame/JApplet exits
	} // end of run()

	public void goToScreen(Screen screen) {
		this.actualScreen = screen;
	}

	public SoundController getSoundControler() {
		return soundControler;
	}
	public Screen getActualScreen() {
		return actualScreen;
	}

	public void setActualScreen(Screen actualScreen) {
		this.actualScreen = actualScreen;
	}
	
}
