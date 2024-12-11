package dynamic_beat_16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	//Images
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage(); 																									
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage(); 																									

	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage(); 	
	
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 																									
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	
	private Image blueFlareImage;
	private Image judgeImage;



	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	//To administer/organize the note blocks that are being created
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	//Constructor 
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	public void screenDraw(Graphics2D g){
		//noteRouteImage
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		
		//noteRouteLineImage
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		//Others
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
	
		//Notes
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		
		//Track Info
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //prevent printString from cracking
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		
		//Difficulty
		g.drawString(difficulty, 1190, 700);;	

		//Key Info
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S",270,609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K",889,609);
		g.drawString("L", 993, 609);
		
		//Score
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant",Font.BOLD,30));
		g.drawString("000000", 565, 702);
		
		//Flare
		g.drawImage(blueFlareImage,320,430,null);
		
		//Judgement
		g.drawImage(judgeImage,460,420,null);

		
	}
	
	//Events
	//S
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//D
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//F
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//Space
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumBig1.mp3",false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//J
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//K
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	//L
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage(); 
		new Music("drumSmall1.mp3",false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); 
	}
	
	@Override
	public void run() {
		dropNotes();
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes() {
		Beat[] beats = null;
		//Cool-Tobu
		if (titleName.equals("Cool-Tobu") && difficulty.equals("Easy")) {
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] { //come back later
					new Beat(startTime,"S"),
					new Beat(startTime+gap*2, "S"),
					new Beat(startTime+gap*4, "D"),
					new Beat(startTime+gap*6, "F"),
					new Beat(startTime+gap*8, "Space"),
					new Beat(startTime+gap*10, "J"),
					new Beat(startTime+gap*12, "K"),
					new Beat(startTime+gap*14, "L"),
					new Beat(startTime+gap*18, "Space"),
					new Beat(startTime+gap*20, "Space"),
					new Beat(startTime+gap*22, "Space"),
					new Beat(startTime+gap*24, "K"),
					new Beat(startTime+gap*26, "J"),
					new Beat(startTime+gap*28, "K"),
					new Beat(startTime+gap*30, "J"),
					new Beat(startTime+gap*32, "K"),
					new Beat(startTime+gap*36, "S"),
					new Beat(startTime+gap*38, "D"),
					new Beat(startTime+gap*40, "S"),
					new Beat(startTime+gap*42, "D"),
					new Beat(startTime+gap*44, "S"),
					new Beat(startTime+gap*46, "D"),
					new Beat(startTime+gap*48, "Space"),
					new Beat(startTime+gap*49, "J"),
					new Beat(startTime+gap*50, "K"),
					new Beat(startTime+gap*52, "L"),
					new Beat(startTime+gap*52, "J"),
					new Beat(startTime+gap*52, "J"),
			};
		}
		
		else if (titleName.equals("Cool-Tobu")&& difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
				new Beat(startTime,"Space"),
			};
		}
		
		//Dreams-Joakim Karud
		else if (titleName.equals("Dreams-Joakim Karud") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
				new Beat(startTime,"Space"),
			};
		}
		
		else if (titleName.equals("Dreams-Joakim Karud") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
				new Beat(startTime,"Space"),
			};
		}
		//We Are One- Vexento
		else if (titleName.equals("Dreams-Joakim Karud")&& difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
				new Beat(startTime,"Space"),
			};
		}
		
		else if (titleName.equals("Dreams-Joakim Karud")&& difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
				new Beat(startTime,"Space"),
			};
		}
		
		int i = 0;
		gameMusic.start();

		//get the time at which the current music is being played; make a note that is reasonable to the beat of the current music
		//Review! 
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//judge if the player press key on beat
	public void judge(String input) {
		for (int i = 0 ; i < noteList.size(); i++) { //index starts at 0 so it judges the notes that were created first
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}
		else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		}
		else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
		}
		else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		}
		else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		}
		
	}

}
