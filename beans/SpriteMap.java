package beans;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import engine.util.Drawable;
import engine.util.Position;

/**
 *
 * @author Seink
 */
public class SpriteMap {

	String label;
	String path;
	Rect body;
	Rect attack;
	BufferedImage image;

	public SpriteMap(BufferedImage image, int bodyX, int bodyY, int bodyX2, int bodyY2,int attackX, int attackY, int attackX2, int attackY2, Point point){
		this.image = image;
		body = new Rect(bodyX, bodyY, bodyX2, bodyY2);
		attack = new Rect(attackX, attackY, attackX2, attackY2);
		body.getPosition().setRelativePoint(point);
		attack.getPosition().setRelativePoint(point);
		body.color = new Color(255, 255, 0, 120);
		attack.color = new Color(0, 0, 255, 120);
	}

	public SpriteMap(SpriteMap sm, Point point) {
		image = sm.image;
		body = new Rect(sm.image.getWidth()-sm.body.getPosition().getX()-sm.body.getWidth(), sm.body.getPosition().getY(), sm.image.getWidth()-sm.body.getPosition().getX(), sm.body.getHeight());
		attack = new Rect(sm.image.getWidth()-sm.attack.getPosition().getX()-sm.attack.getWidth(), sm.attack.getPosition().getY(), sm.image.getWidth()-sm.attack.getPosition().getX(), sm.attack.getHeight());
		body.getPosition().setRelativePoint(point);
		attack.getPosition().setRelativePoint(point);
		body.color = new Color(255, 255, 0, 120);
		attack.color = new Color(0, 0, 255, 120);
	}
	
	@Override
	public String toString() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Drawable getBody() {
		return body;
	}
	
	public Drawable getAttack() {
		return attack;
	}

	public String getPath() {
		return path;
	}

	
	public void setPath(String path) {
		this.path = path;
	}



}

