package beans;

import java.awt.Graphics2D;
import java.util.List;

import engine.util.Drawable;
import engine.util.Position;

public class Personagem extends Drawable{

	SpriteSystem sprite;
	float speed = 100.0f;
	int life = 100;
	boolean hit = false;
	int powerPunchUP = 10;
	int powerPunchDOWN = 10;
	int powerKickUP = 10;
	int powerKickDOWN = 10;
	int powerDefenseUP = 10;
	int powerDefenseDOWN = 10;
	int powerSpecial = 30;
	
	
	
	
	public Personagem(SpriteSystem sprite) {
		this.sprite = sprite;
		position = new Position(0,0);
		sprite.setPosition(position);
		
		
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void takeDamage(Personagem player2){
		if(!hit){
			if(sprite.currentMode==SpriteSystem.DEFENSEUP){
				switch (player2.sprite.currentMode) {
				case SpriteSystem.PUNCHUP:
					life-= player2.powerPunchUP/10.0f;
					break;
				case SpriteSystem.KICKUP:
					life-= player2.powerKickUP/10.0f;
					break;
				case SpriteSystem.PUNCHDOWN:
					life-= player2.powerPunchDOWN;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.KICKDOWN:
					life-= player2.powerKickDOWN;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.SPECIAL:
					life-= player2.powerSpecial/10.0f;
					break;

				default:
					break;
				}
			}else if(sprite.currentMode==SpriteSystem.DEFENSEDOWN){
				switch (player2.sprite.currentMode) {
				case SpriteSystem.PUNCHUP:
					life-= player2.powerPunchUP;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.KICKUP:
					life-= player2.powerKickUP;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.PUNCHDOWN:
					life-= player2.powerPunchDOWN/10.0f;
					break;
				case SpriteSystem.KICKDOWN:
					life-= player2.powerKickDOWN/10.0f;
					break;
				case SpriteSystem.SPECIAL:
					life-= player2.powerSpecial/10.0f;
					break;

				default:
					break;
				}
			}else{
				switch (player2.sprite.currentMode) {
				case SpriteSystem.PUNCHUP:
					life-= player2.powerPunchUP;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.PUNCHDOWN:
					life-= player2.powerPunchDOWN;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.KICKUP:
					life-= player2.powerKickUP;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.KICKDOWN:
					life-= player2.powerKickDOWN;
					action(SpriteSystem.DAMAGE);
					break;
				case SpriteSystem.SPECIAL:
					life-= player2.powerSpecial;
					action(SpriteSystem.DAMAGE);
					break;

				default:
					break;
				}
			}
		}
		hit = true;
		
	}
	
	//usar mode de SpriteMap como acoes
	public void action(int action){
		sprite.setNextMode(action);
	}
	
	public void setTurn(boolean turn){
		sprite.turn = turn;
	}
	
	public boolean isTurn(){
		return sprite.turn;
	}
	public void setDown(boolean down){
		sprite.down = down;
	}
	
	public boolean isDown(){
		return sprite.down;
	}
	
	public boolean isStanding(){
		return !isDown();
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		sprite.draw(graphics);
	}
	
	@Override
	public void simulate(long diffTime) {
		if(hit&&sprite.currentMode!=SpriteSystem.DAMAGE){
			hit = false;
		}
		sprite.simulate(diffTime);
		if(sprite.turn){
			if(sprite.currentMode==SpriteSystem.FRONTWALK){
				sprite.getPosition().addX(speed*diffTime/1000);
			} 
			if(sprite.currentMode==SpriteSystem.BACKWALK){
				sprite.getPosition().addX(-speed*diffTime/1000);
			}
		}else{
			if(sprite.currentMode==SpriteSystem.FRONTWALK){
				sprite.getPosition().addX(speed*diffTime/1000);
			}
			if(sprite.currentMode==SpriteSystem.BACKWALK){
				sprite.getPosition().addX(-speed*diffTime/1000);
			}
		}
		if(life<=0){
			action(SpriteSystem.DAMAGE);
		}
	}
	
	public void checkPosition(){
		sprite.setPosition(this.position);
		int sz1 = sprite.sprites.size();
		int sz2;
		List<SpriteMap> s;
		SpriteMap sm;
		for (int i = 0; i < sz1; i++) {
			s = sprite.sprites.get(i);
			sz2 = s.size();
			for (int j = 0; j < sz2; j++) {
				sm = s.get(j);
				sm.body.getPosition().setRelativePoint(position);
				sm.attack.getPosition().setRelativePoint(position);
			}
		}
	}
	
	public SpriteMap getCurrentSprite(){
		return sprite.getCurrentSprite();
	}
	
	public boolean isAlive(){
		return life>0;
	}
}
