package engine.util;

import java.awt.Point;

//================== Classe Position ===================
//
// Implementa métodos de um ponto qualquer
//
//======================================================
public class Position {
	private float x, y;
	private Point point;
	private Position relativePoint;
	//============ Position ===============
	//
	// Construtor da classe
	//
	//=======================================
	public Position(float x, float y) {
		point = new Point((int)x,(int)y);
		this.x = x;
		this.y = y;
	}
	public Position(Point p) {
		point = p;
		this.x = p.x+x;
		this.y = p.y+y;
	}
	
	public Position(Position pontoRelativo,float x, float y) {
		point = new Point((int)x,(int)y);
		relativePoint = pontoRelativo;
		this.x = x;
		this.y = y;
	}
	
	public Point toPoint()
	{	
		return point;
	}
	
	public void addX(float x){
		System.out.println(this.x+" add "+x);
		this.x += x;
		this.point.x += x;
	}
	
	public void addY(float y){
		this.y += y;
		this.point.y += y;
	}
	
	public void setX(float x) {
		if(relativePoint==null){
			point.x = (int)x;			
		}else{
			point.x = (int)(x+relativePoint.getX());
		}
		this.x = x;
	}
	public void setY(float y) {
		if(relativePoint==null){
			point.y = (int)y;			
		}else{
			point.y = (int)(y+relativePoint.getY());
		}
		this.y = y;
	}
	public void setRelativePoint(Position relativePoint) {
		this.relativePoint = relativePoint;
	}
	public float getX() {
		if(relativePoint == null){
			return x;
		}else{
			return x+relativePoint.x;
		}
	}
	public float getY() {
		if(relativePoint==null){
			return y;
		}else{
			return y+relativePoint.y;
		}
	}
}
