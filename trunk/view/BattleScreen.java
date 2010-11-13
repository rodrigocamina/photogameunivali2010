package view;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import beans.Personagem;
import beans.SpriteSystem;
import engine.control.Controller;
import engine.control.Screen;
import engine.util.Utilities;

public class BattleScreen extends Screen{

	public Personagem player1;
	public Personagem player2;
	public BufferedImage stage;
	boolean battling = true;
	long timer = 2000;

	public BattleScreen(int p1, int p2, int stage){
		//TODO carregar stage
		player1 = Controller.getInstance().getPersonagens(p1);
		player2 = Controller.getInstance().getPersonagens(p2);
		player1.setTurn(true);
		this.stage = Controller.getInstance().getStages(stage);
		addComponent(player1);
		addComponent(player2);
		player1.getPosition().setX(50);
		player1.getPosition().setY(250);
		player2.getPosition().setX(550);
		player2.getPosition().setY(250);
		
		player1.checkPosition();
		player2.checkPosition();
		
	}

	@Override
	public void simulate(long diffTime) {
		if(battling){
			if(!player1.isHit()){
			if(Utilities.touches(player1.getCurrentSprite().getBody(), player2.getCurrentSprite().getAttack())){
				player1.takeDamage(player2);
				if(!player1.isAlive()){
					battling = false;
				}
			}
			if(Utilities.touches(player2.getCurrentSprite().getBody(), player1.getCurrentSprite().getAttack())){
				player2.takeDamage(player1);
				if(!player2.isAlive()){
					battling = false;
				}
			}
			}
		}else{
			//winner player who isAlive()! XD
			timer -= diffTime;
			if(timer<=0){
				//vai pra tela de selecao ou de comeco
//				Controller.getInstance().goToScreen(screen)
			}
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(stage, 0,0, null);
	}



	@Override
	public void pressedKey(KeyEvent e) {
		/*
==Jogador 1
Soco: Tecla "1"
Chute: Tecla "2"
Defesa: Tecla "3"
Especial: Tecla "0"
Ir para Baixo: "seta para cima"
Ir para a Esquerda: Tecla "seta para esquerda"
Ir para a Direita: Tecla "seta para direita"

==Jogador 2
Soco: Tecla "B"
Chute: Tecla "N"
Defesa: Tecla "M"
Especial: Tecla "Space"
Ir para Baixo: Tecla "S"
Ir para a Esquerda: Tecla "A"
Ir para a Direita: Tecla "D"
		 */
		if(battling){
			if(e.getKeyCode()==KeyEvent.VK_NUMPAD1){
				if(player1.isDown()){
					player1.action(SpriteSystem.PUNCHDOWN);
				}else{
					player1.action(SpriteSystem.PUNCHUP);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_NUMPAD2){
				if(player1.isDown()){
					player1.action(SpriteSystem.KICKDOWN);
				}else{
					player1.action(SpriteSystem.KICKUP);
				}			
			}else if(e.getKeyCode()==KeyEvent.VK_NUMPAD3){
				if(player1.isDown()){
					player1.action(SpriteSystem.DEFENSEDOWN);
				}else{
					player1.action(SpriteSystem.DEFENSEUP);
				}			
			}else if(e.getKeyCode()==KeyEvent.VK_NUMPAD0){
				player1.action(SpriteSystem.SPECIAL);
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				player1.setDown(true);
				player1.action(SpriteSystem.DOWN);
			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				if(player1.isTurn()){
					player1.action(SpriteSystem.FRONTWALK);
				}else{
					player1.action(SpriteSystem.BACKWALK);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
				if(player1.isTurn()){
					player1.action(SpriteSystem.BACKWALK);
				}else{
					player1.action(SpriteSystem.FRONTWALK);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_B){
				if(player2.isDown()){
					player2.action(SpriteSystem.PUNCHDOWN);
				}else{
					player2.action(SpriteSystem.PUNCHUP);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_N){
				if(player2.isDown()){
					player2.action(SpriteSystem.KICKDOWN);
				}else{
					player2.action(SpriteSystem.KICKUP);
				}			
			}else if(e.getKeyCode()==KeyEvent.VK_M){
				if(player2.isDown()){
					player2.action(SpriteSystem.DEFENSEDOWN);
				}else{
					player2.action(SpriteSystem.DEFENSEUP);
				}			
			}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
				player2.action(SpriteSystem.SPECIAL);
			}else if(e.getKeyCode()==KeyEvent.VK_S){
				player2.setDown(true);
				player2.action(SpriteSystem.DOWN);
			}else if(e.getKeyCode()==KeyEvent.VK_D){
				if(player2.isTurn()){
					player2.action(SpriteSystem.BACKWALK);
				}else{
					player2.action(SpriteSystem.FRONTWALK);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_A){
				if(player2.isTurn()){
					player2.action(SpriteSystem.FRONTWALK);
				}else{
					player2.action(SpriteSystem.BACKWALK);
				}
			} 
		}
	}
	//
	@Override
	public void releasedKey(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			player1.setDown(false);
			player1.action(SpriteSystem.STANDING);
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			player2.setDown(false);
			player2.action(SpriteSystem.STANDING);
		}
	}
}
