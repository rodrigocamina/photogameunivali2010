package beans;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import engine.util.Drawable;
import engine.util.Position;
import engine.util.Utilities;

public class SpriteSystem extends Drawable{

	public List<List<SpriteMap>> sprites = new ArrayList<List<SpriteMap>>();
	public int currentMode = 0;
	public int nextMode = 0;
	public int currentSprite = 0;
	public int spriteTime = 300;
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
		setPosition(new Position(0,0));
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
		sprites.add(new ArrayList<SpriteMap>());
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
		
		BufferedReader dados = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/database/madimbu.csv"), Charset.forName("UTF-8")));
		//Formato:
		//{level};{pathDeImagem}
		String str = " ";
		List<SpriteMap> list = sprites.get(mode);
		try {
			while ((str = dados.readLine()) != null){
				if(str.charAt(0)!='#'){
					String parametros[] = str.split(";");
					if(Integer.parseInt(parametros[0])>=begin && Integer.parseInt(parametros[0])<= end){
						//TODO, ajustar detalhes do path da imagem
						list.add(new SpriteMap(Utilities.loadImage(parametros[9]),Integer.parseInt(parametros[1]),Integer.parseInt(parametros[2]),Integer.parseInt(parametros[3]),Integer.parseInt(parametros[4]),Integer.parseInt(parametros[5]),Integer.parseInt(parametros[6]),Integer.parseInt(parametros[7]),Integer.parseInt(parametros[8]),getPosition()));
					}
				}
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		System.out.println(sprites);
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
				currentSprite = 0;
				if(currentMode>1){
					if(nextMode==0){
						if(down){
							currentMode = DOWN;
						}else{
							currentMode = STANDING;
						}
						spriteTime = 300/sprites.get(currentMode).size();
					}else{
						currentMode = nextMode;
						nextMode = 0;
						if(currentMode==DEFENSEDOWN||currentMode==DEFENSEUP){
							spriteTime = 500;
						}else{
							spriteTime = 300/sprites.get(currentMode).size();
						}
					}
				}
			}
			if(turn){
				curSpr = new SpriteMap(sprites.get(currentMode).get(currentSprite),getPosition());
			}else{
				curSpr = sprites.get(currentMode).get(currentSprite);
			}
		}
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		if(turn){
			graphics.drawImage(curSpr.getImage(), (int)position.getX()+curSpr.getImage().getWidth(), (int)position.getY(),(int)position.getX(),(int)position.getY()+curSpr.getImage().getHeight(),0,0,curSpr.getImage().getWidth(),curSpr.getImage().getHeight(), null);
		}else{
			graphics.drawImage(curSpr.getImage(), (int)position.getX(), (int)position.getY(), null);
		}
		//TODO deletar codigos seguintes antes de release
		curSpr.getBody().draw(graphics);
		curSpr.getAttack().draw(graphics);
	}
	
	public SpriteMap getCurrentSprite(){
		if(curSpr==null){
			curSpr = sprites.get(currentMode).get(currentSprite);
		}
		return curSpr;
	}
	
	public void setNextMode(int action){
		if(currentMode<4 || action==DAMAGE && action>currentMode){
			currentMode = action;
			spriteTime = 300/sprites.get(currentMode).size();
			return;
		}else{
			nextMode = action;
		}
	}
	
	@Override
	public void setPosition(Position position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
	}
}
