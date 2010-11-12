package beans;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.util.Drawable;
import engine.util.Position;

public class Rect extends Drawable {

	public Color color = Color.BLUE;
	
	public Rect(float x, float y, float width, float height) {
		this.position = new Position(x,y);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect((int)position.getX(), (int)position.getY(), (int)width, (int)height);
	}

	@Override
	public void simulate(long diffTime) {
		
	}

}
