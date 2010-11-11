package engine.sound;

import javazoom.jl.player.Player;



public class MP3 {
    private String filename;
    private Player player; 

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
    }

    public void close() { if (player != null) player.close(); }

    // play the MP3 file to the sound card
    public void play() {
        try {
            player = new Player(getClass().getResourceAsStream(filename));
        }
        catch (Exception e) {
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) {  }
            }
        }.start();




    }


    // test client
    public static void main(String[] args) {
        String filename = "/snd/Taiko_Battle.mp3";
        MP3 mp3 = new MP3(filename);
        mp3.play();

        // do whatever computation you like, while music plays
        
        new Thread(){
        	public void run() {
        		try {
					sleep(20000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	};
        }.run();
        // when the computation is done, stop playing it
        mp3.close();

        // play from the beginning
        mp3 = new MP3(filename);
        mp3.play();

    }

}
