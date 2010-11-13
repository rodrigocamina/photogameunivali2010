package beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Botao {

	
	public int x,y,largura,altura;
	BufferedImage sprite;
	int coluna=0;
	public Botao (int x, int y,BufferedImage sprite){
		this.x=x;
		this.y=y;
		this.sprite=sprite;
		this.largura=sprite.getWidth()/3;
		this.altura=sprite.getHeight();
		
	}
	
	
	public void Desenhar(Graphics2D dbg){
		
		dbg.drawImage(sprite.getSubimage(coluna*largura, 0, largura, altura),null,x,y);
		 
	}
	
	public boolean Colidiu(int x,int y){
		
		if(x>this.x && x<this.x+largura && y>this.y && y<this.y+altura){
			
			return true;
		}else{
			return false;
		}
		
	}
	
	public void setSelecionado(){
		coluna=1;
	}
	
	public boolean getSelecionado(){
		if(coluna==1){
			return true;
		}else{
			return false;
		}
	}
	public void setNselecionado(){
		coluna=0;
	}
	
	public boolean mouseOver(int x,int y){
		if(Colidiu(x, y)){
			coluna=1;
			return true;
		}else{
			coluna=0;
			return false;
		}
	}
}
