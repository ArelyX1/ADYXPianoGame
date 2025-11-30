package dynamic_beat_17.View;

import java.io.BufferedInputStream;
import java.io.InputStream;

import dynamic_beat_17.Main;
import javazoom.jl.player.Player;



//Thread is a small program within a program
public class Music extends Thread {
	private Player player;
	private boolean isLoop; //isLoop tells whether the music plays only once or is keep playing 
	private BufferedInputStream bis;
	private String resourceName;
	
	//Constructor
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			this.resourceName = name;
			InputStream is = Main.class.getResourceAsStream("../music/" + name);
			if (is == null) {
				throw new RuntimeException("Resource not found: " + name);
			}
			bis = new BufferedInputStream(is); //puts the file into a buffer (so that it's readable)
			player = new Player(bis); //stream goes into player
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
				if (!isLoop) break;
				// Re-open a fresh stream for the next loop iteration
				InputStream is = Main.class.getResourceAsStream("../music/" + resourceName);
				if (is == null) {
					System.out.println("Cannot reopen resource for looping: " + resourceName);
					break;
				}
				bis = new BufferedInputStream(is);
				player = new Player(bis);
			} while (isLoop); //if isLoop == true, the music plays infinitely 
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
