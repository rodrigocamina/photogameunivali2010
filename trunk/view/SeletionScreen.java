package view;
/*
 * Autor Bruno da Silva 
 * 
 * Data: 12/11/2010
 */


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import beans.Button;
import engine.control.Controller;
import engine.control.Screen;
import engine.util.Position;
import engine.util.Utilities;



public class SeletionScreen extends Screen{
	private List<Button> lutadores = new ArrayList<Button>();
	private BufferedImage fundo;
	private List<Button> lutadoresSelcionado = new ArrayList<Button>();
	private List<BufferedImage>animacaoDeSelecaoP1 = new ArrayList<BufferedImage>();
	private List<BufferedImage>animacaoDeSelecaoP2 = new ArrayList<BufferedImage>();
//	private BufferedImage []animacaoDeSelecaoP1 = new BufferedImage[6];
//	private BufferedImage []animacaoDeSelecaoP2 = new BufferedImage[6];
	public int player1=0;
	public int player2=3;
	private int sizeHeightImage;// tamanho  X 114
	private int sizeWidhtImage;// tamanho  Y 147
	private int sizeHeightImageSelecionado;// tamanho  X 146
	private int sizeWidhtImageSelecionado;//tamanho  Y 181
	private Point[] posicao;
	private String []enderecoImage;
	private String []enderecoImageSelecaoP1;
	private String []enderecoImageSelecaoP2;
	private Random rand = new Random();
	private boolean player1Selecionado = false;
	private boolean player2Selecionado = false;

	/*
	 * tenho que passar um int 
	 */


	public SeletionScreen() {
		height = 600;
		width = 800;

		posicao = new Point[9];
		enderecoImage = new String[8];
		
		enderecoImageSelecaoP1 = new String[7];
		enderecoImageSelecaoP2 = new String[7];

		sizeHeightImage = 147;			
		sizeWidhtImage =114;

		sizeHeightImageSelecionado = 183;
		sizeWidhtImageSelecionado = 146;
		
		player1 = 0;
		player2 = 3;


		//personagem superior da tela
		posicao[0]= new Point(99,41);
		posicao[1]= new Point(264, 41);
		posicao[2]= new Point(429, 41);
		posicao[3]= new Point(594, 41);
		//personagem inferior da tela 
		posicao[4]= new Point(175, 206);
		posicao[5]= new Point(340, 206);
		posicao[6]= new Point(505, 206);	//posicao do randon
		//posicao do personagem escolhido
		posicao[7]= new Point(77, 374);
		posicao[8]= new Point(578, 374);		

		/*
		 * endereco das imagens que vao ser caregadas para mostrar os lutadores
		 */
		enderecoImage[0]= "/image/fireHead/fireHead_menu.png";
		enderecoImage[1]= "/image/madimbu/madimbu_menu.png";
		enderecoImage[2]= "/image/bigboss/bigBoss_Menu.png";
		enderecoImage[3]= "/image/monitora/monitora_menu.png";
		enderecoImage[4]= "/image/tche/tche_menu.png";
		enderecoImage[5]= "/image/random.png";
		enderecoImage[6]= "/image/mallock/mallock_menu.png";
		enderecoImage[7]= "/image/Screens/screenSelect.png";//fundo

//========================Carrega o endereco de imagem  da animacao de selacao do Player 1 =================================
		enderecoImageSelecaoP1[0] = "/image/fogo/fogo_0.png";
		enderecoImageSelecaoP1[1] = "/image/fogo/fogo_1.png";
		enderecoImageSelecaoP1[2] = "/image/fogo/fogo_2.png";
		enderecoImageSelecaoP1[3] = "/image/fogo/fogo_3.png";
		enderecoImageSelecaoP1[4] = "/image/fogo/fogo_4.png";
		enderecoImageSelecaoP1[5] = "/image/fogo/fogo_5.png";
		enderecoImageSelecaoP1[6] = "/image/fogo/fogo_6.png";
//======================================================================================================================

//=================Carrega o endereco de imagem  da animacao de selacao do Player 2=========================================
		enderecoImageSelecaoP2[0] = "/image/fogo/fogo_azul_0.png";
		enderecoImageSelecaoP2[1] = "/image/fogo/fogo_azul_1.png";
		enderecoImageSelecaoP2[2] = "/image/fogo/fogo_azul_2.png";
		enderecoImageSelecaoP2[3] = "/image/fogo/fogo_azul_3.png";
		enderecoImageSelecaoP2[4] = "/image/fogo/fogo_azul_4.png";
		enderecoImageSelecaoP2[5] = "/image/fogo/fogo_azul_5.png";
		enderecoImageSelecaoP2[6] = "/image/fogo/fogo_azul_6.png";
//====================================================================================================================
		try {
			for (int i = 0; i < 7; i++) {
				BufferedImage ani = ImageIO.read(Controller.getInstance().getClass().getResourceAsStream(enderecoImageSelecaoP1[i]));
//			
				animacaoDeSelecaoP1.add(ani);
			}
			for (int i = 0; i < 7; i++) {
				BufferedImage ani2 = ImageIO.read(Controller.getInstance().getClass().getResourceAsStream(enderecoImageSelecaoP2[i]));
//				
				animacaoDeSelecaoP2.add(ani2);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		
		try {
			/*
			 * Carregar todas as imagem dos jogadores
			 */
//================================Carrega  a imagem de Fundo ====================================================			

			fundo = ImageIO.read(Controller.getInstance().getClass().getResourceAsStream(enderecoImage[7]));

//===============================================================================================================
//=============================Carrega as imagens dos lutadores =================================================
			for(int i=0;i<7; i++)
			{
				lutadores.add(new Button(Utilities.loadImage(enderecoImage[i]),posicao[i].x,posicao[i].y){
					

					public void onClick() {
						

					}
					@Override
					public void draw(Graphics2D graphics) {
						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImage, position.toPoint().y+sizeHeightImage, 0,0,getImage().getWidth(), getImage().getHeight(), null);

					}
					
				});
			
			}
//==========================================================================================================================================================		

//===============================Adiciona os lutadores para ser mostrado na tela===========================================================================================================================			
			for (int i = 0; i < lutadores.size(); i++) {
				addComponent(lutadores.get(i));
			}
//==============================================================================================================================================================================			


//================================== Imagem do lutador selecionado do player 1 ==========================================================================================================			
			lutadoresSelcionado.add(new Button(Utilities.loadImage(enderecoImage[0]),posicao[7].x,posicao[7].y){

				@Override
				public void onClick() {
					// TODO Auto-generated method stub

				}
				@Override
				public void draw(Graphics2D graphics) {
//==================selecao feita atraves do mouse o primeiro click ============================================================================================================
//					if(player1!=-1)
//					{
//						this.setImage(lutadores.get(player1).getImage());
//						graphics.setColor(Color.RED);
//						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImageSelecionado+4, sizeHeightImageSelecionado+4);
//						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImageSelecionado, position.toPoint().y+sizeHeightImageSelecionado, 0,0,getImage().getWidth(), getImage().getHeight(), null);
//					}
//					else
//					{							
//						graphics.setColor(Color.RED);
//						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImageSelecionado+4, sizeHeightImageSelecionado+4);
//						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImageSelecionado, position.toPoint().y+sizeHeightImageSelecionado, 0,0,getImage().getWidth(), getImage().getHeight(), null);
//					}
//=============================================================================================================================================================================================================================================					
//============================Selecao do personagem atraves do teclado com as teclas A, D e E para confirmar selecao =================================================================================================================================================================================================================					
						
						this.setImage(lutadores.get(player1).getImage());
						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImageSelecionado, position.toPoint().y+sizeHeightImageSelecionado, 0,0,getImage().getWidth(), getImage().getHeight(), null);
					

				}

			});

//=============================================================================================================================================================================================================================================					

//================================== Imagem do lutador selecionado do player 2 ============================================================================			
			lutadoresSelcionado.add(new Button(Utilities.loadImage(enderecoImage[3]),posicao[8].x,posicao[8].y){

				@Override
				public void onClick() {
					// TODO Auto-generated method stub

				}
				public void draw(Graphics2D graphics) {
					
//=============================Selecao do personagem Atraves do mouse, selecao feita por ordem ==================================
//					if(player2!=-1)
//					{
//						this.setImage(lutadores.get(player2).getImage());
//						graphics.setColor(Color.RED);
//						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImageSelecionado+4, sizeHeightImageSelecionado+4);
//						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImageSelecionado, position.toPoint().y+sizeHeightImageSelecionado, 0,0,getImage().getWidth(), getImage().getHeight(), null);
//					}
//					else
//					{
//						graphics.setColor(Color.RED);
//						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImageSelecionado+4, sizeHeightImageSelecionado+4);
//						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImageSelecionado, position.toPoint().y+sizeHeightImageSelecionado, 0,0,getImage().getWidth(), getImage().getHeight(), null);
//					}
//===========================================================================================================================================
//================================Selecao do personagem feita atraves do teclado teclas LEFT  , RIGHT e Confirmar selacao com ENTER 
						this.setImage(lutadores.get(player2).getImage());
						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImageSelecionado, position.toPoint().y+sizeHeightImageSelecionado, 0,0,getImage().getWidth(), getImage().getHeight(), null);
					
				}

			});
//==============================================================================================================================================================================================================================================

//===============================Adiciona os lutadores Selecionado para ser mostrado na tela===========================================================================================================================			
			for (int i = 0; i < lutadoresSelcionado.size(); i++) {
				addComponent(lutadoresSelcionado.get(i));
			}
//======================================================================================================================================================================================================================================			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro");
		}

	}

	@Override
	public void simulate(long diffTime) {
		// TODO Auto-generated method stub
		if(player1Selecionado && player2Selecionado){
//			System.out.println("passa para proxima tela ");
			Controller.getInstance().goToScreen(new LoadScreen(player1, player2, enderecoImage[player1], enderecoImage[player2]));
		}


	}
	float time = 0.2f;
	float time1 = 0.3f;
	int aux = 0;
	int aux2 = 0;
	@Override
	public void draw(Graphics2D graphics) {
		
		
		time -=Controller.getInstance().diffTime/1000.0f;
		time1 -=Controller.getInstance().diffTime/1000.0f;
		if(time<0){
			time = 0.2f;	
			 aux = rand.nextInt(6);			
			
		}
		if(time<0){
			time1 = 0.3f;			
			 aux2 = rand.nextInt(6);
			
		}
//=====================================desenha animacao no personagem selecionado pelo jogadores============================================================================================================================================================================
		graphics.drawImage(animacaoDeSelecaoP1.get(aux), posicao[player1].x, posicao[player1].y, posicao[player1].x+sizeWidhtImage, posicao[player1].y+sizeHeightImage, 0,0,animacaoDeSelecaoP1.get(aux).getWidth(), animacaoDeSelecaoP1.get(aux).getHeight(), null);
		graphics.drawImage(animacaoDeSelecaoP2.get(aux2), posicao[player2].x, posicao[player2].y, posicao[player2].x+sizeWidhtImage, posicao[player2].y+sizeHeightImage, 0,0,animacaoDeSelecaoP2.get(aux2).getWidth(), animacaoDeSelecaoP2.get(aux2).getHeight(), null);
		
		graphics.drawImage(animacaoDeSelecaoP1.get(aux2), posicao[player1].x, posicao[player1].y, posicao[player1].x+sizeWidhtImage, posicao[player1].y+sizeHeightImage, 0,0,animacaoDeSelecaoP1.get(aux2).getWidth(), animacaoDeSelecaoP1.get(aux2).getHeight(), null);
		graphics.drawImage(animacaoDeSelecaoP2.get(aux), posicao[player2].x, posicao[player2].y, posicao[player2].x+sizeWidhtImage, posicao[player2].y+sizeHeightImage, 0,0,animacaoDeSelecaoP2.get(aux).getWidth(), animacaoDeSelecaoP2.get(aux).getHeight(), null);
//==========================================================================================================================================================================================================================================================================		
//===================================desenha animacao onde mostra o personagem selecionado pelo jogador ===================================================================================================================================================================
		graphics.drawImage(animacaoDeSelecaoP1.get(aux), posicao[7].x, posicao[7].y, posicao[7].x+sizeWidhtImageSelecionado, posicao[7].y+sizeHeightImageSelecionado, 0,0,animacaoDeSelecaoP1.get(aux).getWidth(), animacaoDeSelecaoP1.get(aux).getHeight(), null);
		graphics.drawImage(animacaoDeSelecaoP2.get(aux2), posicao[8].x, posicao[8].y, posicao[8].x+sizeWidhtImageSelecionado, posicao[8].y+sizeHeightImageSelecionado, 0,0,animacaoDeSelecaoP2.get(aux2).getWidth(), animacaoDeSelecaoP2.get(aux2).getHeight(), null);
		
		graphics.drawImage(animacaoDeSelecaoP1.get(aux2), posicao[7].x, posicao[7].y, posicao[7].x+sizeWidhtImageSelecionado, posicao[7].y+sizeHeightImageSelecionado, 0,0,animacaoDeSelecaoP1.get(aux2).getWidth(), animacaoDeSelecaoP1.get(aux2).getHeight(), null);
		graphics.drawImage(animacaoDeSelecaoP2.get(aux), posicao[8].x, posicao[8].y, posicao[8].x+sizeWidhtImageSelecionado, posicao[8].y+sizeHeightImageSelecionado, 0,0,animacaoDeSelecaoP2.get(aux).getWidth(), animacaoDeSelecaoP2.get(aux).getHeight(), null);
	//==========================================================================================================================================================================================================================================================================		
		
		graphics.drawImage(fundo, 0, 0, (int)width,(int) height, 0, 0, (int)fundo.getWidth(),  (int)fundo.getHeight(), null);

	}

	
	@Override
	public void pressed(MouseEvent e) {
		for (int i = 0; i < lutadores.size(); i++) {
			if(Utilities.insideOf(lutadores.get(i), new Position(e.getPoint()))){
				lutadores.get(i).onClick();
			}
		}
	}
	public void pressedKey(KeyEvent e){
		/*
		 * tramento do teclado do player 1
		 */
		if(!player1Selecionado){
			if(e.getKeyCode()== KeyEvent.VK_E)
			{
				int aux = 0;
				if(player1==5){
					do{
						aux = rand.nextInt(7);								
					}while(aux==5);
					player1 = aux;
				}
	
				player1Selecionado = true;
//				System.out.println(" E presionado");
			}
	
			if(e.getKeyCode()== KeyEvent.VK_D)
			{
				player1++;
				if(player1== player2){
					player1++;
										
				}
				if(player1>6)
					player1=0;
				if(player1== player2){
					player1++;
										
				}
//				System.out.println(" D presionado");
			}
			if(e.getKeyCode()== KeyEvent.VK_A)
			{
				player1--;					
				if(player1 == player2){
					player1--;				
					
				}
				if(player1<0)
					player1=6;
				if(player1 == player2){
					player1--;				
					
				}
//				System.out.println(" A presionado");
			}
		}
		
		if(!player2Selecionado){
			if(e.getKeyCode()== KeyEvent.VK_ENTER)
			{
				int aux = 0;
				if(player2==5){
					do{
						aux = rand.nextInt(7);								
					}while(aux==5);				
					player2 = aux;
				}
				player2Selecionado = true;
			}
			if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			{
				player2++;
				if(player2==player1){
					player2++;
				}
				if(player2>6)
					player2=0;
				if(player2==player1){
					player2++;
				}
//				System.out.println(" D presionado");
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				player2--;
				if(player2==player1){
					player2--;
				}
				if(player2<0)
					player2=6;
				if(player2==player1){
					player2--;
				}
//				System.out.println(" A presionado");
			}
		}

	}
	


}
