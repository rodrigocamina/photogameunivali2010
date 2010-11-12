package beans;

import java.awt.Graphics2D;

import engine.util.Drawable;

public class Personagem extends Drawable{

	SpriteSystem sprite;
	float speed;
	int life;
	boolean hit = false;
	int powerPunchUP;
	int powerPunchDOWN;
	int powerKickUP;
	int powerKickDOWN;
	int powerDefenseUP;
	int powerDefenseDOWN;
	int powerSpecial;
	
	
	
	
	public Personagem(SpriteSystem sprite) {
		this.sprite = sprite;
		sprite.setPosition(position);
		
		
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void takeDamage(Personagem player2){
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
				break;
			case SpriteSystem.KICKDOWN:
				life-= player2.powerKickDOWN;
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
				break;
			case SpriteSystem.KICKUP:
				life-= player2.powerKickUP;
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
				break;
			case SpriteSystem.PUNCHDOWN:
				life-= player2.powerPunchDOWN;
				break;
			case SpriteSystem.KICKUP:
				life-= player2.powerKickUP;
				break;
			case SpriteSystem.KICKDOWN:
				life-= player2.powerKickDOWN;
				break;
			case SpriteSystem.SPECIAL:
				life-= player2.powerSpecial;
				break;

			default:
				break;
			}
		}

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
		sprite.simulate(diffTime);
		if(sprite.turn){
			if(sprite.currentMode==SpriteSystem.FRONTWALK){
				sprite.getPosition().addX(speed*diffTime/1000);
			}else if(sprite.currentMode==SpriteSystem.BACKWALK){
				sprite.getPosition().addX(-speed*diffTime/1000);
			}
		}else{
			if(sprite.currentMode==SpriteSystem.FRONTWALK){
				sprite.getPosition().addX(-speed*diffTime/1000);
			}else if(sprite.currentMode==SpriteSystem.BACKWALK){
				sprite.getPosition().addX(speed*diffTime/1000);
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
