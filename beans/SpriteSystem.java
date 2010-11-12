package beans;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import engine.util.Drawable;
import engine.util.Utilities;

public class SpriteSystem extends Drawable{

	public List<List<SpriteMap>> sprites = new ArrayList<List<SpriteMap>>();
	public int currentMode = 0;
	public int nextMode = 0;
	public int currentSprite = 0;
	public int spriteTime = 100;
	public int timer = spriteTime;
	public boolean turn;
	public boolean down;
	public static final int STANDING = 0;
	public static final int DOWN = 1;
	public static final int FRONTWALK = 2;
	public static final int BACKWALK = 3;
	public static final int PUNCHUP = 4;
	public static final int PUNCHDOWN = 5;
	public static final int KICKUP = 6;
	public static final int KICKDOWN = 7;
	public static final int SPECIAL = 8;
	public static final int DAMAGE = 9;
	public static final int DEFENSEUP = 10;
	public static final int DEFENSEDOWN = 11;
	public SpriteMap curSpr;
	
	public SpriteSystem() {
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
	}
	
	public void init(int begin, int end, int mode){
		
		BufferedReader dados = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/database/tutorial.csv"), Charset.forName("UTF-8")));
		//Formato:
		//{level};{pathDeImagem}
		String str;
		List<SpriteMap> list = sprites.get(mode);
		try {
			while ((str = dados.readLine()) != null){
				if(str.charAt(0)!='#'){
					String parametros[] = str.split(";");
					if(Integer.parseInt(parametros[0])>=begin&&Integer.parseInt(parametros[0])<=end){
						//TODO, ajustar detalhes do path da imagem
						list.add(new SpriteMap(Utilities.loadImage(parametros[9]),Integer.parseInt(parametros[1]),Integer.parseInt(parametros[2]),Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]),Integer.parseInt(parametros[5]),Integer.parseInt(parametros[6]),Integer.parseInt(parametros[7]),Integer.parseInt(parametros[8]),getPosition().toPoint()));
					}
				}
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(mode==FRONTWALK){
			List<SpriteMap> listB = sprites.get(BACKWALK);
			for (int i = 1; i <= list.size(); i++) {
				listB.add(list.get(list.size()-i));
			}
		}
	}
	
	@Override
	public void simulate(long diffTime) {
		timer-= diffTime;
		if(timer<=0){
			timer+=spriteTime;
			currentSprite++;
			if(currentSprite>=sprites.get(currentMode).size()){
				if(currentMode>1){
					if(nextMode==0){
						if(down){
							currentMode = DOWN;
						}else{
							currentMode = STANDING;
						}
					}else{
						currentMode = nextMode;
						nextMode = 0;
					}
				}
			}
			if(turn){
				curSpr = new SpriteMap(sprites.get(currentMode).get(currentSprite),getPosition().toPoint());
			}else{
				curSpr = sprites.get(currentMode).get(currentSprite);
			}
		}
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		if(turn){
			graphics.drawImage(curSpr.getImage(), position.toPoint().x+curSpr.getImage().getWidth(), position.toPoint().y,position.toPoint().x, position.toPoint().y+curSpr.getImage().getHeight(),0,0,curSpr.getImage().getWidth(),curSpr.getImage().getHeight(), null);
		}else{
			graphics.drawImage(curSpr.getImage(), position.toPoint().x, position.toPoint().y, null);
		}
		//TODO deletar codigos seguintes antes de release
		curSpr.getBody().draw(graphics);
		curSpr.getAttack().draw(graphics);
	}
	
	public SpriteMap getCurrentSprite(){
		return curSpr;
	}
	
	public void setNextMode(int action){
		if(currentMode<4||action==DAMAGE&&action>currentMode){
			currentMode = action;
			return;
		}else{
			nextMode = action;
		}
	}
}
