package engine.util;

import java.awt.Graphics2D;


//============= Classe Drawable ====================
//
// Classe abstrata para objetos desenhaveis
//
//==================================================
public abstract class Drawable {
	protected Position position;
	protected float width;
	protected float height;
	
	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;
	
	public abstract void draw(Graphics2D graphics);
	public abstract void simulate(long diffTime);
		
	public float getHeight() {
		return height;
	}
	public Position getPosition() {
		return position;
	}
	public float getWidth() {
		return width;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public void setWidth(float width) {
		this.width = width;
	}
}
