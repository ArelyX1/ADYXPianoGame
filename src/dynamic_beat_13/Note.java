package dynamic_beat_13;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580-1000 / Main.SLEEP_TIME * Main.NOTE_SPEED; //so that the note reaches the note judgement line after 1 second
	private String noteType;
	
	//Constructor
	public Note(int x,String noteType) {
		this.x = x;
		this.noteType = noteType;
	}

	//Draw the note
	public void screenDraw(Graphics2D g) {
		if (noteType.equals("short")) {
			g.drawImage(noteBasicImage,x,y,null);
		}
		else if (noteType.equals("long")) { //spacebar
			g.drawImage(noteBasicImage,x,y,null);
			g.drawImage(noteBasicImage,x+100,y,null);
		}
	}
	
	public void drop() { 
		y += Main.NOTE_SPEED;
	}
	
	@Override
	public void run() { //run the thread
		try {
			while (true) {
				drop(); 
				Thread.sleep(Main.SLEEP_TIME); //sleep is based on 0.001 seconds; for us sleep time is set to 10; while statement runs 100 times in 1 sec; in 1 sec, the note drops 700 px per second
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
