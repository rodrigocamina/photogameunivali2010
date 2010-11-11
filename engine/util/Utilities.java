package engine.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.control.Controller;

//================== Classe Utilities ====================
//
// Implementa ????
//
//========================================================
public class Utilities {

	public static double getAngulo(double aX, double aY, double bX, double bY) {  
		   return Math.atan2( bY - aY ,  bX - aX);  
	}
	
	public static double getAngulo(Point p1, Point p2) {  
		   return Math.atan2( p2.y - p1.y ,  p2.x - p1.x);  
	}
	
	//implementacao do algoritmo do Simas, matemática para jogos 2a fase univali
	public static boolean dentroPoligono(Point ponto, Poligono p){
        boolean colisao = false;
        int n = p.getCordenadas().length;
        int j = n-1;
        for (int i = 0; i < n; i++) {
            if(((p.getCordenadas()[j].getY()<=ponto.getY())&&(p.getCordenadas()[i].getY()>ponto.getY())&&(duasareaTriangulo(p.getCordenadas()[j], p.getCordenadas()[i], ponto))>0)||
                ((p.getCordenadas()[i].getY()<=ponto.getY())&&(p.getCordenadas()[j].getY()>ponto.getY())&&(duasareaTriangulo(p.getCordenadas()[i], p.getCordenadas()[j], ponto))>0)){
                colisao = !colisao;
            }
            j=i;
        }
        return colisao;
    }

	
	
	//implementacao do algoritmo do Simas, matemática para jogos 2a fase univali
    public static boolean dentroTriangulo(Point ponto, Poligono p)throws Exception{
        if(p.getCordenadas().length!=3){
                throw new Exception("Not a Triangle");
        }else{
            int soma1 = (int)((ponto.getX()*p.getCordenadas()[1].getY()-ponto.getY()*p.getCordenadas()[1].getX())
                    +(p.getCordenadas()[1].getX()*p.getCordenadas()[2].getY()-p.getCordenadas()[1].getY()*p.getCordenadas()[2].getX())
                    +(p.getCordenadas()[2].getX()*ponto.getY()-p.getCordenadas()[2].getY()*ponto.getX()));
            int soma2 = (int)((ponto.getX()*p.getCordenadas()[2].getY()-ponto.getY()*p.getCordenadas()[2].getX())
                    +(p.getCordenadas()[2].getX()*p.getCordenadas()[0].getY()-p.getCordenadas()[2].getY()*p.getCordenadas()[0].getX())
                    +(p.getCordenadas()[0].getX()*ponto.getY()-p.getCordenadas()[0].getY()*ponto.getX()));
            int soma3 = (int)((ponto.getX()*p.getCordenadas()[0].getY()-ponto.getY()*p.getCordenadas()[0].getX())
                    +(p.getCordenadas()[0].getX()*p.getCordenadas()[1].getY()-p.getCordenadas()[0].getY()*p.getCordenadas()[1].getX())
                    +(p.getCordenadas()[1].getX()*ponto.getY()-p.getCordenadas()[1].getY()*ponto.getX()));
            return ((soma1>0)&&(soma2>0)&&(soma3>0)||(soma1<0)&&(soma2<0)&&(soma3<0));
        }
    }

	//implementacao do algoritmo do Simas, matemática para jogos 2a fase univali
	public static int duasareaTriangulo(Point ponto1,Point ponto2,Point ponto3){
        int soma1 = (int)((ponto1.getX()*ponto2.getY()-ponto1.getY()*ponto2.getX())
                +(ponto2.getX()*ponto3.getY()-ponto2.getY()*ponto3.getX())
                +(ponto3.getX()*ponto1.getY()-ponto3.getY()*ponto1.getX()));
        return soma1;
	}

	//implementacao do algoritmo do Simas, matemática para jogos 2a fase univali
	public static int duasareaPoligono(Poligono p){
		int n = p.getCordenadas().length;
		int somatorio = 0;
		int j = n-1;
		for (int i = 0; i < n; i++) {
			somatorio += (p.getCordenadas()[j].getX()*p.getCordenadas()[i].getY()-p.getCordenadas()[j].getY()*p.getCordenadas()[i].getX());
			j=i;
		}
		return somatorio;
	}

	//checagem retangular
	public static boolean insideOf(Drawable component, Position point){
		return point.getX()>component.getPosition().getX()&&point.getX()<component.getPosition().getX()+component.getWidth()&&point.getY()>component.getPosition().getY()&&point.getY()<component.getPosition().getY()+component.getHeight();
	}
	public static boolean insideOf(int areaX, int areaY,int areaWidth, int areaHeight, int X, int Y){
		return X>areaX&&X<areaX+areaWidth&&Y>areaY&&Y<areaY+areaHeight;
	}
	
	//checagem circular
	public static boolean insideOf(Position point,Position point2,double range){
		double difx = point.getX()-point2.getX();
		double dify = point.getY()-point2.getY();
		difx *= difx;
		dify *= dify;
		range *= range;
		return range>difx+dify;
	}
	public static boolean insideOf(Point point,Point point2,double range){
		double difx = point.getX()-point2.getX();
		double dify = point.getY()-point2.getY();
		difx *= difx;
		dify *= dify;
		range *= range;
		return range>difx+dify;
	}
	
	public static BufferedImage loadImage(String img) throws IOException{
		try{
			BufferedImage imgTmp = ImageIO.read(Controller.getInstance().getClass().getResourceAsStream(img));
			BufferedImage imgFinal = new BufferedImage(imgTmp.getWidth(), imgTmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			imgFinal.createGraphics().drawImage(imgTmp,0,0,null);
			return imgFinal;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//desenha retangulo em degrade
	public static void drawBorderLightInShadowOut(Graphics2D graphics,int x, int y, float height, float width, int borderwidth, int r, int g, int b, float fator){
		for (int i = 1; i <= borderwidth; i++) {
			graphics.setColor(new Color(r, g, b));
			graphics.drawRect(x-i, y-i, (int)width+i+i, (int)height+i+i);
			r = (int)(r*fator);
			g = (int)(g*fator);
			b = (int)(b*fator);
			if(r>255){
				r = 255;
			}
			if(g>255){
				g = 255;
			}
			if(b>255){
				b = 255;
			}
		}
	}
	//desenha retangulo em degrade
	public static void drawBorderLightOutShadowIn(Graphics2D graphics,int x, int y, float height, float width, int borderwidth, int r, int g, int b, float fator){
		for (int i = borderwidth; i > 0; i--) {
			graphics.setColor(new Color(r, g, b));
			graphics.drawRect(x-i, y-i, (int)width+i+i, (int)height+i+i);
			r = (int)(r*fator);
			g = (int)(g*fator);
			b = (int)(b*fator);
			if(r>255){
				r = 255;
			}
			if(g>255){
				g = 255;
			}
			if(b>255){
				b = 255;
			}
		}
	}
	public static void setTexture(Graphics dbg,int x, int y, int width, int height, BufferedImage imageComForSprite){
		int widthT;
		int heightT = imageComForSprite.getWidth();
		
		int px = 0;
		int py = 0;
		int pxF = 0;
		int pyF = 0;
		
		while (py < height) {
			px = 0;
			pyF = py+heightT;
			
			if(pyF > height){
				pyF  = height;
				heightT = pyF - py;
			}
			
			widthT = imageComForSprite.getHeight();
			
			while (px < width) {
				pxF = px + widthT;
				
				if(pxF > width){
					pxF  = width;
					widthT = pxF - px;
				}
				dbg.drawImage(imageComForSprite, px+x, py+y, pxF+x, pyF+y, 0, 0, widthT, heightT, null);
				
				px += widthT;
			}
			py+=heightT;
		}
	}
}
