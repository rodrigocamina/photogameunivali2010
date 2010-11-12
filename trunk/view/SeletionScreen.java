package view;
/*
 * Autor Bruno da Silva 
 * 
 * Data: 11/11/2010
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
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
	public int player1=-1;
	public int player2=-1;
	private int sizeHeightImage;// tamanho  X 114
	private int sizeWidhtImage;// tamanho  Y 147
	private int sizeHeightImageSelecionado;// tamanho  X 146
	private int sizeWidhtImageSelecionado;//tamanho  Y 181
	private Point[] posicao;
	private String []enderecoImage = new String[8];
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
		enderecoImage[0]= "/image/lutador0.png";
		enderecoImage[1]= "/image/lutador1.png";
		enderecoImage[2]= "/image/lutador2.png";
		enderecoImage[3]= "/image/lutador3.png";
		enderecoImage[4]= "/image/lutador4.png";
		enderecoImage[5]= "/image/lutador5.png";
		enderecoImage[6]= "/image/lutador6.png";
		enderecoImage[7]=  "/image/fundo.png";//fundo

		//		

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
					int index;

					public void onClick() {
						/*
						 * start game
						 */
						System.out.println("index "+index);
						if(player1 ==-1){
							int aux = 0;
							if(index==5){
								do{
									aux = rand.nextInt(7);								
								}while(aux==5);
								player1 = aux;
							}else{
								player1 = index;
							}

							System.out.println("player 1 "+player1);
						}else{
							int aux= 0;
							if(index==5){
								do{
									aux = rand.nextInt(7);
								}while(aux==5);
								player2 = aux;
							}else{							
								player2 = index;
							}
							System.out.println("player 2 "+player2);
							
						}

					}
					@Override
					public void draw(Graphics2D graphics) {
						graphics.setColor(Color.RED);
						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImage+4, sizeHeightImage+4);
						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImage, position.toPoint().y+sizeHeightImage, 0,0,getImage().getWidth(), getImage().getHeight(), null);

					}
					public Button init(int popooo){
						this.index = popooo;
						this.width = sizeWidhtImage;
						this.height= sizeHeightImage;
						return this;
					}
				}.init(i));
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
						graphics.setColor(Color.RED);
						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImageSelecionado+4, sizeHeightImageSelecionado+4);
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
						graphics.setColor(Color.RED);
						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImageSelecionado+4, sizeHeightImageSelecionado+4);
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
			System.out.println("passa para proxima tela ");
//			Controller.getInstance().goToScreen(BatleScreen);
		}


	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(fundo, 0, 0, (int)width,(int) height, 0, 0, (int)fundo.getWidth(),  (int)fundo.getHeight(), null);

	}

	//	@Override
	//	public void moved(MouseEvent e) {
	//		// TODO Auto-generated method stub
	//		for (int i = 0; i < lutadores.size(); i++) {
	//			if(Utilities.insideOf(lutadores.get(i), new Position(e.getPoint()))){
	//
	//			}
	//		}
	//
	//	}
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
			System.out.println(" E presionado");
		}

		if(e.getKeyCode()== KeyEvent.VK_D)
		{
			player1++;			
			if(player1>6)
				player1=0;
			System.out.println(" D presionado");
		}
		if(e.getKeyCode()== KeyEvent.VK_A)
		{
			player1--;			
			if(player1<0)
				player1=6;
			System.out.println(" A presionado");
		}
		/*
		 * tramento do teclado do player 2
		 */
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
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
		{
			player2++;			
			if(player2>6)
				player2=0;
			System.out.println(" D presionado");
		}
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			player2--;			
			if(player2<0)
				player2=6;
			System.out.println(" A presionado");
		}

	}
	public void releasedKey(KeyEvent e){

	}


}
