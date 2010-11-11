package engine.sound;

import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class ThreadMP3 extends Thread{

	AdvancedPlayer advancedPlayer;
	public boolean replay = false;
	String definition;
	CyclicInputStream stream;
	public ThreadMP3(InputStream stream, String definition) throws JavaLayerException, IOException {
		this.stream = new CyclicInputStream(stream);
		this.definition = definition;
		this.advancedPlayer = new AdvancedPlayer(this.stream);
	}
	@Override
	public void run() {
		try {
			do{
				advancedPlayer.play();
				try{
					if(replay){
						stream.reset();
						advancedPlayer = new AdvancedPlayer(stream);
						advancedPlayer.play();
					}
				}catch (Exception e) {
				}
			}while(replay);
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	public void setReplay(boolean replay) {
		this.replay = replay;
	}
	
	@Override
	public String toString() {
		return definition;
	}
	
	public void stopMP3(){
		replay = false;
		advancedPlayer.stop();
		
	}
}
