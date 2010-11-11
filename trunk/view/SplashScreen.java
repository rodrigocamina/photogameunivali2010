package view;

import java.awt.Color;
import java.awt.Graphics2D;

import engine.control.Screen;

public class SplashScreen extends Screen{

	public SplashScreen() {

	}
	
	@Override
	public void simulate(long diffTime) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.CYAN);
		graphics.fillRect(150, 150, 300, 300);
	}
	
}
