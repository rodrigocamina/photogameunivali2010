package beans;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import engine.util.Drawable;
import engine.util.Position;

public abstract class Button extends Drawable {
	
	BufferedImage image;
	int state = 0;
	
	public Button(BufferedImage buttonImageSet, Position position){
		image = buttonImageSet;	
		width = image.getWidth()/3;
		height = image.getHeight();
		this.position = position;
	}
	
	public Button(BufferedImage buttonImageSet, float x, float y){
		image = buttonImageSet;	
		width = image.getWidth()/3;
		height = image.getHeight();	
		position = new Position(x, y);
	}
	
	
	public Button(Position position,int width,int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics2D graphics) {
		if(image!=null){
			graphics.drawImage(image, (int)position.getX(), (int)position.getY(), (int)(position.getX()+((float)image.getWidth()/3)), (int)position.getY()+image.getHeight(), image.getWidth()*state/3, 0, image.getWidth()*(state+1)/3, image.getHeight(), null);
		}else{
			if(state==0){
				graphics.setColor(new Color(255,255,255,255));
				graphics.fillRoundRect((int)position.getX(), (int)position.getY(), (int)width, (int)height, 10, 10);
				graphics.setColor(new Color(0,0,0,255));
				graphics.drawRoundRect((int)position.getX(), (int)position.getY(), (int)width, (int)height, 10, 10);
			}else if(state == 1){
				graphics.setColor(new Color(255,0,0,255));
				graphics.fillRoundRect((int)position.getX()+(int)(width*0.1), (int)position.getY()+(int)(height*0.1), (int)(width*0.8), (int)(height*0.8), 10, 10);
				graphics.setColor(new Color(0,0,0,255));
				graphics.drawRoundRect((int)position.getX()+(int)(width*0.1), (int)position.getY()+(int)(height*0.1), (int)(width*0.8), (int)(height*0.8), 10, 10);
			}else if(state == 2){
				graphics.setColor(new Color(255,120,0,255));
				graphics.fillRoundRect((int)position.getX(), (int)position.getY(), (int)width, (int)height, 10, 10);
				graphics.setColor(new Color(0,0,0,255));
				graphics.drawRoundRect((int)position.getX(), (int)position.getY(), (int)width, (int)height, 10, 10);
			}
		}
	}
	
	public void mouseClicked() {
		state = 1;
		new Thread(){
			public void run() {
				try {
					sleep(200);
					state = 2;
					sleep(100);
					state = 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		onClick();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	public void simulate(long diffTime) {
		
	}
	
	public abstract void onClick();
	
}
