package beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Seta {
	
	int x,y;
	public BufferedImage sprite;
	int timer=0;
	int coluna=0;
	int nro;
	public Seta(int x, int y, int nro,BufferedImage sprite){
		this.x=x;
		this.y=y;
		this.sprite=sprite;
		this.nro=nro;
	}
	
	public void Desenhar(Graphics2D dbg){
		
		dbg.drawImage(sprite.getSubimage((sprite.getWidth()/nro)*(coluna), 0	, (sprite.getWidth()/nro), sprite.getHeight()),null,x,y);
	}
	
	public void mudaPos(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void Simular(long diftime){
		timer+=diftime;
		if(timer>75){
			timer=0;
			if(coluna+1<nro){
				coluna++;
			}else{
				coluna=0;
			}
		}
	}
	

}
