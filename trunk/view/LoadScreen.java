package view;
/*
 * Autor Bruno da Silva 
 * 
 * Data: 12/11/2010
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import beans.Button;

import engine.control.Controller;
import engine.control.Screen;
import engine.util.Utilities;

public class LoadScreen extends Screen{

	private int indicePlayer1;
	private int indicePlayer2;
	private int sizeHeightImage;// tamanho  X ?
	private int sizeWidhtImage;// tamanho  Y ?
	private BufferedImage fundo;
	private List<Button> lutadores = new ArrayList<Button>();
	private Point[] posicao;
	private String []enderecoImage;
	private double tempoLoading;
	private Random rand = new Random();


	public LoadScreen(int player1, int player2, String enderecoImagePlayer1, String enderecoImagePlayer2) {
		// TODO Auto-generated constructor stub
		height = 600;
		width = 800;
		
				
		sizeWidhtImage = 285;
		sizeHeightImage = 265;	

		posicao = new Point[2];
		enderecoImage = new String[3];

		posicao[0]= new Point(22, 85);
		posicao[1]= new Point(495, 85);

		enderecoImage[0] = enderecoImagePlayer1;
		enderecoImage[1] = enderecoImagePlayer2;
		enderecoImage[2] = "/image/Screens/telaVs.png ";//fundo
		
		tempoLoading =4;

		try {
			//================================Carrega  a imagem de Fundo ====================================================			

			fundo = ImageIO.read(Controller.getInstance().getClass().getResourceAsStream(enderecoImage[2]));

			//===============================================================================================================

			for(int i=0; i<2; i++){

				lutadores.add(new Button(Utilities.loadImage(enderecoImage[i]),posicao[i].x,posicao[i].y){
					@Override
					public void onClick() {


					}
					@Override
					public void draw(Graphics2D graphics) {
//						graphics.setColor(Color.RED);
//						graphics.fillRect( position.toPoint().x-2, position.toPoint().y-2, sizeWidhtImage+4, sizeHeightImage+4);
						graphics.drawImage(getImage(), position.toPoint().x, position.toPoint().y, position.toPoint().x+sizeWidhtImage, position.toPoint().y+sizeHeightImage, 0,0,getImage().getWidth(), getImage().getHeight(), null);

					}

				});

			}
			for (int i = 0; i <lutadores.size(); i++) {
				addComponent(lutadores.get(i));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void draw(Graphics2D graphics) {
		// TODO Auto-generated method stub
		graphics.drawImage(fundo, 0, 0, (int)width,(int) height, 0, 0, (int)fundo.getWidth(),  (int)fundo.getHeight(), null);

	}

	@Override
	public void simulate(long diffTime) {
		// TODO Auto-generated method stub
		tempoLoading-= Controller.getInstance().diffTime/1000.0f;
		/*
		 * quando o tempo acabar passa para a tela da luta
		 */
		if(tempoLoading<0){
			System.out.println("proxima tela eh a da luta");
			
			Controller.getInstance().goToScreen(new BattleScreen(indicePlayer1, indicePlayer2,0 ));
			
			
		}

	}

}
