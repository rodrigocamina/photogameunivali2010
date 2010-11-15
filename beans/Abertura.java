package beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Abertura {
	
	int x,y;
	int x2,y2;
	BufferedImage ladoEsquerdo,ladoDireito;
	int timer=0;
int vel=20;
	boolean animacao=false;
	boolean fechado=false;
	public Abertura(BufferedImage ladoEsquerdo,BufferedImage ladoDireito){
		this.ladoDireito=ladoDireito;
		this.ladoEsquerdo=ladoEsquerdo;
		x=-ladoEsquerdo.getWidth();
		y=0;
		x2=800;
		
		y2=0;
	}
	
	public void Go(){
		vel=20;
		animacao=true;
	}
	
	public boolean animando(){
		if(animacao){
			return true;
		}else{
			return false;
		}
	}
	public void Simular(long diftime){
		
		timer+=diftime;
		System.out.println( "time "+timer);
		if(timer>vel ){
			timer=0;
			if(animacao){
				if(!fechado){
					x+=4;
					x2-=4;
					
				}else{
					x-=4;
					x2+=4;
				}
			}
			
			
			
			if(x>=-200){
				vel=10;
			}
			if(x>=0){
				x=0;
			}
			
			if(x2<=400){
				x2=400;
			}
			if(x>=0 && x2<=800 && !fechado){
				animacao=!animacao;
				fechado=!fechado;
				
			}else{
			if(x<=0 && x2>=800 &&fechado){
				animacao=!animacao;
				fechado=!fechado;
			}
			}
			
			
		}
		
	}
	
	public void Desenhar(Graphics2D dbg){
		dbg.drawImage(ladoEsquerdo,null,x,y);
		dbg.drawImage(ladoDireito,null,x2,y2);
	}
}
