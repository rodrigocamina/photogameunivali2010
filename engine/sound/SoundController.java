package engine.sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;


//================== Classe SoundController ===================
//
// Implementa o modulo de controle dos sons do jogo
//
//=============================================================
public class SoundController {

	List<ThreadMP3> musics = new ArrayList<ThreadMP3>();
	//============ SoundController ===============
	//
	// Construtor da classe
	//
	//============================================
	public SoundController()
	{
		try {
			musics.add(new ThreadMP3(getClass().getResourceAsStream("/snd/castle.mp3"), "castle"));
			musics.add(new ThreadMP3(getClass().getResourceAsStream("/snd/battle.mp3"), "battle"));
			musics.add(new ThreadMP3(getClass().getResourceAsStream("/snd/city.mp3"), "city"));
			musics.add(new ThreadMP3(getClass().getResourceAsStream("/snd/teste.mp3"), "mp3GameOver"));
			
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void play(String name){
		try{
			ThreadMP3 mp3 = new ThreadMP3(getClass().getResourceAsStream("/snd/"+name+".mp3"), name);
			musics.add(mp3);
			mp3.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playnonstop(String name){
		try{

			ThreadMP3 mp3 = new ThreadMP3(getClass().getResourceAsStream("/snd/"+name+".mp3"), name);
			musics.add(mp3);
			mp3.setReplay(true);
			mp3.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopPlaying(String name){
		for (int i = 0; i < musics.size(); i++) {
			if(musics.get(i).toString().equals(name)){
				musics.get(i).stopMP3();
				musics.remove(i);
				i--;
			}
		}
	}
	
	public void stopPlayingAll(){
		for (int i = 0; i < musics.size(); i++) {
			musics.get(i).stopMP3();
			musics.remove(i);
			i--;
		}
	}
}
