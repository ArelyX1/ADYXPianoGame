package dynamic_beat_17.Controller;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import dynamic_beat_17.Main;

import dynamic_beat_17.Model.*;
import dynamic_beat_17.View.*;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580-(1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; //so that the note reaches the note judgement line after 1 second
	private String noteType;
	private boolean proceeded = true; //process of the note 
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	//Constructor
	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;
		}
		else if (noteType.equals("D")) {
			x = 332;
		}
		else if (noteType.equals("F")) {
			x = 436;
		}
		else if (noteType.equals("Space")) {
			x = 540;
		}
		else if (noteType.equals("J")) {
			x = 744;
		}
		else if (noteType.equals("K")) {
			x = 848;
		}
		else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType; //reset note type
	}

	//Draw the note
	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage,x,y,null);
		}
		else { 
			g.drawImage(noteBasicImage,x,y,null);
			g.drawImage(noteBasicImage,x+100,y,null);
		}
	}
	
	public void drop() { 
		y += Main.NOTE_SPEED;
		if (y>620) { //if note goes beyond the judge bar
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() { //run the thread
		try {
			while (true) {
				drop(); 
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);//sleep is based on 0.001 seconds; for us sleep time is set to 10; while statement runs 100 times in 1 sec; in 1 sec, the note drops 700 px per second
				}
				else {
					interrupt();
					break;
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if (y >= 613) {
			System.out.println("Late");
			close();
			return "Late";
		}
		else if (y >= 600) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if (y >= 587) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if (y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if (y >= 565) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if (y >= 550) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if (y >= 535) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
