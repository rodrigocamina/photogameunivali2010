package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import engine.control.Controller;
import engine.control.Screen;
import engine.util.Utilities;

public class SplashScreen extends Screen{

	BufferedImage fundo;
	int timer=0;
	public SplashScreen() {
		try {
			fundo=Utilities.loadImage("/img/menuPrincipal4.PNG");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void simulate(long diffTime) {
		// TODO Auto-generated method stub
		timer+=diffTime;
		if(timer>500){
			ScreenStart inicio = new ScreenStart();
			Controller.getInstance().goToScreen(inicio);
		}
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.CYAN);
		graphics.fillRect(150, 150, 300, 300);
		graphics.drawImage(fundo,null,0,0);
	}
	
}