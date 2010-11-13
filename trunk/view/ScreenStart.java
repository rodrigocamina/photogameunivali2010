package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import beans.Abertura;
import beans.Botao;
import beans.Button;
import beans.Seta;

import engine.control.Controller;
import engine.control.Screen;
import engine.util.Position;
import engine.util.Utilities;

public class ScreenStart extends Screen {

	int bla = 0;
	

	Botao botaoIniciar,botaoSobre,botaoAjuda;
	public static Seta seta;
	int selecao=0;
	public static Abertura abertura;
	BufferedImage fundo;
	public static boolean volta=false;
	public boolean toIndoProJogo=false;
	SeletionScreen selection;
public static	boolean abrindoTela=false;
	public ScreenStart() {
		try {
			
			fundo = Utilities.loadImage("/img/menuPrincipal4.PNG");
			abertura=new Abertura(Utilities.loadImage("/img/ladoEsquerdo.jpg"), Utilities.loadImage("/img/ladoDireito.jpg"));
		//abertura.Go();
			BufferedImage tmp = Utilities.loadImage("/img/btiniciar.png");
			botaoIniciar = new Botao(800-tmp.getWidth()/2, 100+tmp.getHeight(), tmp);
			tmp = Utilities.loadImage("/img/sobre.png");
			botaoSobre = new Botao(800-tmp.getWidth()/2, 200+tmp.getHeight(), tmp);
			tmp = Utilities.loadImage("/img/ajuda.png");
			botaoAjuda = new Botao(800-tmp.getWidth()/2, 300+tmp.getHeight(), tmp);
			botaoIniciar.setSelecionado();
			
			tmp = Utilities.loadImage("/img/seta2.png");
			seta = new Seta(botaoIniciar.x+5, (botaoIniciar.y+botaoIniciar.altura/4)+10, 10, tmp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	ScreenAjuda telaAjuda;

	public void draw(Graphics2D dbg) {

		dbg.setColor(Color.black);
		dbg.fillRect(0, 0, 800, 600);
		dbg.setColor(Color.black);

		
		dbg.drawImage(fundo, null, 0, 0);
		dbg.setColor(Color.white);
		botaoAjuda.Desenhar(dbg);
		botaoIniciar.Desenhar(dbg);
		botaoSobre.Desenhar(dbg);
		seta.Desenhar(dbg);
		if(telaAjuda!=null){
			telaAjuda.draw(dbg);
		}
		if(toIndoProJogo){
			
				if(selection!=null){
					selection.draw(dbg);
				}
			
		}
		abertura.Desenhar(dbg);
	}

	@Override
	public void simulate(long diffTime) {
		// TODO Auto-generated method stub
		seta.Simular(diffTime);
		abertura.Simular(diffTime);
		
		if(toIndoProJogo){
			if(!abertura.animando()){
				
				Controller.getInstance().goToScreen(selection);
			}
		}
		if(volta){
		//	System.out.println("entrei volta");
			
				
				if(!abertura.animando()){
					//System.out.println("ja eh");
					volta=false;
					abrindoTela=false;
					telaAjuda=null;
					abertura.Go();
				}
			}
		
		if(telaAjuda!=null){
			telaAjuda.simulate(diffTime);
		}
		if(abrindoTela){
			if(!abertura.animando()){
				if(telaAjuda==null){
				switch(selecao){
				case 0:
					//Go to screen -> seleciona personagem
					abertura.Go();
					toIndoProJogo=true;
					break;
				case 1:
					//Go to screen -> ajuda
					telaAjuda = new ScreenAjuda(0);
					//Controller.getInstance().goToScreen(telaAjuda);
					break;
				case 2:
					//Go to screen -> sobre
					telaAjuda = new ScreenAjuda(1);
					//Controller.getInstance().goToScreen(telaAjuda);
					break;
				}
			}
			}
		}
	}

	@Override
	public void pressedKey(KeyEvent e) {
		// TODO Auto-generated method stub

		super.pressedKey(e);
		if(telaAjuda!=null){
			telaAjuda.pressedKey(e);
		}else{
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_E) {
				
			} else if (keyCode == KeyEvent.VK_SPACE) {
				
				if(!abertura.animando()){
					abertura.Go();
					abrindoTela=true;
				}
			}else {
				tecladoMove(keyCode);
			}
		}
	

	}

	private void tecladoMove(int keyCode) {
		// TODO Auto-generated method stub
		
		if(keyCode == KeyEvent.VK_DOWN){
			
			if(selecao+1>2){
				selecao=2;
			}else{
				selecao++;
			}
		}else{
			if(keyCode == KeyEvent.VK_UP){
				if(selecao-1<0){
					selecao=0;
				}else{
					selecao--;
				}
			}
		}
	
		switch(selecao){
		case 0:
			seta.mudaPos(botaoIniciar.x+5, (botaoIniciar.y+botaoIniciar.altura/4)+10);
			botaoIniciar.setSelecionado();
			botaoAjuda.setNselecionado();
			botaoSobre.setNselecionado();
			break;
		case 1:
			seta.mudaPos(botaoSobre.x+5, (botaoSobre.y+botaoSobre.altura/4)+10);
			botaoIniciar.setNselecionado();
			botaoAjuda.setNselecionado();
			botaoSobre.setSelecionado();
			break;
		case 2:
			seta.mudaPos(botaoAjuda.x+5, (botaoAjuda.y+botaoAjuda.altura/4)+10);
			botaoIniciar.setNselecionado();
			botaoAjuda.setSelecionado();
			botaoSobre.setNselecionado();
			break;
		}
	}

	@Override
	public void moved(MouseEvent e) {
		// TODO Auto-generated method stub
		super.moved(e);

	}

	@Override
	public void pressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.pressed(e);

	}
}

class ScreenAjuda extends Screen {

	
	ScreenStart inicio;
	BufferedImage fundo;
	int tipo=0;
	long timer=0;
	Seta seta;
	Botao btvolta;
	public ScreenAjuda(int i){
		tipo=i;
		
		if(i==1){
			try {
				
				fundo=Utilities.loadImage("/img/menuAjuda.PNG");
				BufferedImage teste;
				teste=Utilities.loadImage("/img/voltar.png"); 
				btvolta= new Botao(-10, 10, teste);
				btvolta.setSelecionado();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				fundo=Utilities.loadImage("/img/menuSobre.PNG");
				BufferedImage teste;
				teste=Utilities.loadImage("/img/voltar.png"); 
				btvolta= new Botao(-10, 10, teste);
				btvolta.setSelecionado();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		seta = new Seta(btvolta.x-168, btvolta.y+9, 10, ScreenStart.seta.sprite);
		
	}
	@Override
	public void draw(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.blue);
		dbg.fillRect(0, 0, 800, 600);
		dbg.drawImage(fundo,null,0,0);
		btvolta.Desenhar(dbg);
		seta.Desenhar(dbg);
	}

	@Override
	public void simulate(long diffTime) {
	seta.Simular(diffTime);
		timer+=diffTime;	
		
		
				if(timer>200){
				if(ScreenStart.abrindoTela){
					ScreenStart.abertura.Go();
					ScreenStart.abrindoTela=false;
				}
				}
			}
		
	
	public void pressedKey(KeyEvent e) {
		// TODO Auto-generated method stub

		super.pressedKey(e);
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_SPACE){
			ScreenStart.abertura.Go();
			ScreenStart.abrindoTela=true;
			ScreenStart.volta=true;
			
		}
	
	}

}
