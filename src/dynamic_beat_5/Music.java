package dynamic_beat_5;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

//Thread is a small program within a program
public class Music extends Thread {
	private Player player; 
	private boolean isLoop; //isLoop tells whether the music plays only once or is keep playing 
	private File file;
	private FileInputStream fls;
	private BufferedInputStream bis;
	
	//Constructor
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop; 
			file = new File(Main.class.getResource("../music/" + name).toURI()); //gets the file's location
			fls = new FileInputStream(file);
			bis = new BufferedInputStream(fls); //puts the file into a buffer (so that it's readable)
			player = new Player(bis); //file goes into player
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Methods
	//Tells the position(time) where the music is currently being played 
	//Accuracy to 3 decimal points
	//Will be used when we drop the beat and check to see if the player clicked block on time with the beat
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	//Allows user to close the music whenever the user wishes to 
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //Terminates the thread (one that plays the music)
	}
	
//	@Override
	//If you inherit thread you need to use this function 
	public void run() {
		try {
			do {
				player.play();
				fls = new FileInputStream(file);
				bis = new BufferedInputStream(fls); 
				player = new Player(bis); 	
			} while (isLoop); //if isLoop == true, the music plays infinitely 
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
