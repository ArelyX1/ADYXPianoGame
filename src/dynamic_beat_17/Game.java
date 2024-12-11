package dynamic_beat_17;

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
	
	private int score = 0;
	private int combo = 0;
	
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
				combo = 0;
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
		
		//Score/Combo
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Score: "+Integer.toString(score), 400, 702); //565,702
		g.drawString("Combo: "+Integer.toString(combo), 700,702);
		
		//Flare
		g.drawImage(blueFlareImage,320,430,null);
		
		//Judgment
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
			int startTime = 7500 - Main.REACH_TIME * 1000;
			int gap = 120;
			beats = new Beat[] { //come back later
					new Beat(startTime,"S"),
					new Beat(startTime+gap*4, "D"),
					new Beat(startTime+gap*8, "S"),
					new Beat(startTime+gap*12, "D"),
					new Beat(startTime+gap*16, "S"),
					new Beat(startTime+gap*20, "L"),
					new Beat(startTime+gap*24, "K"),
					new Beat(startTime+gap*28, "L"),
					new Beat(startTime+gap*32, "K"),
					new Beat(startTime+gap*36, "L"),
					new Beat(startTime+gap*40, "Space"),
					new Beat(startTime+gap*44, "Space"),
					new Beat(startTime+gap*48, "Space"),
					new Beat(startTime+gap*52, "F"),
					new Beat(startTime+gap*56, "J"),
					new Beat(startTime+gap*60, "F"),
					new Beat(startTime+gap*64, "J"),
					new Beat(startTime+gap*68, "S"),
					new Beat(startTime+gap*72, "D"),
					new Beat(startTime+gap*76, "F"),
					new Beat(startTime+gap*80, "Space"),
					new Beat(startTime+gap*84, "J"),
					new Beat(startTime+gap*88, "K"),
					new Beat(startTime+gap*92, "L"),
					new Beat(startTime+gap*96, "Space"),
					new Beat(startTime+gap*100, "S"),
					new Beat(startTime+gap*104, "F"),
					new Beat(startTime+gap*108, "K"),
					new Beat(startTime+gap*112, "D"),
					new Beat(startTime+gap*116, "S"),
					new Beat(startTime+gap*120, "D"),
					new Beat(startTime+gap*124, "S"),
					new Beat(startTime+gap*128, "L"),
					new Beat(startTime+gap*132, "K"),
					new Beat(startTime+gap*136, "L"),
					new Beat(startTime+gap*140, "K"),
					new Beat(startTime+gap*144, "L"),
					new Beat(startTime+gap*148, "Space"),
					new Beat(startTime+gap*152, "Space"),
					new Beat(startTime+gap*156, "Space"),
					new Beat(startTime+gap*160, "F"),
					new Beat(startTime+gap*164, "J"),
					new Beat(startTime+gap*168, "F"),
					new Beat(startTime+gap*172, "J"),
					new Beat(startTime+gap*176, "S"),
					new Beat(startTime+gap*180, "D"),
					new Beat(startTime+gap*184, "F"),
					new Beat(startTime+gap*188, "Space"),
					new Beat(startTime+gap*192, "J"),
					new Beat(startTime+gap*196, "K"),
					new Beat(startTime+gap*200, "L"),
					new Beat(startTime+gap*204, "Space"),
					new Beat(startTime+gap*208, "S"),
					new Beat(startTime+gap*212, "F"),
					new Beat(startTime+gap*216, "K"),
					new Beat(startTime+gap*220, "J"),
					new Beat(startTime+gap*224, "S"),
					new Beat(startTime+gap*228, "Space"),
					new Beat(startTime+gap*232, "L"),
					new Beat(startTime+gap*236, "K"),
					new Beat(startTime+gap*240, "J"),
					new Beat(startTime+gap*244, "S"),
					new Beat(startTime+gap*248, "K"),
					new Beat(startTime+gap*252, "F"),
					new Beat(startTime+gap*256, "Space"),
					new Beat(startTime+gap*260, "Space"),
					new Beat(startTime+gap*264, "S"),
					new Beat(startTime+gap*268, "S"),
					new Beat(startTime+gap*272, "K"),
					new Beat(startTime+gap*276, "F"),
					new Beat(startTime+gap*280, "J"),
					new Beat(startTime+gap*284, "L"),
					new Beat(startTime+gap*288, "L"),
					new Beat(startTime+gap*292, "J"),
					new Beat(startTime+gap*296, "Space"),
					new Beat(startTime+gap*300, "J"),
					new Beat(startTime+gap*304, "K"),
					new Beat(startTime+gap*308, "F"),
					new Beat(startTime+gap*312, "Space"),
					new Beat(startTime+gap*316, "D"),
					new Beat(startTime+gap*320, "S"),
					new Beat(startTime+gap*324, "J"),
					new Beat(startTime+gap*328, "L"),
					new Beat(startTime+gap*332, "Space"),
					new Beat(startTime+gap*336, "D"),
					new Beat(startTime+gap*340, "F"),
					new Beat(startTime+gap*344, "S"),
					new Beat(startTime+gap*348, "K"),
					new Beat(startTime+gap*352, "J"),
					new Beat(startTime+gap*356, "L"),
					new Beat(startTime+gap*360, "Space"),
					new Beat(startTime+gap*364, "Space"),
					new Beat(startTime+gap*368, "Space"),
					new Beat(startTime+gap*372, "S"),
					new Beat(startTime+gap*376, "D"),
					new Beat(startTime+gap*380, "F"),
					new Beat(startTime+gap*384, "Space"),
					new Beat(startTime+gap*388, "J"),
					new Beat(startTime+gap*392, "K"),
					new Beat(startTime+gap*396, "L"),
					new Beat(startTime+gap*400, "L"),
					new Beat(startTime+gap*404, "K"),
					new Beat(startTime+gap*408, "J"),
					new Beat(startTime+gap*412, "Space"),
					new Beat(startTime+gap*416, "F"),
					new Beat(startTime+gap*420, "D"),
					new Beat(startTime+gap*424, "S"),
					new Beat(startTime+gap*428, "Space"),
					new Beat(startTime+gap*432, "Space"),
					new Beat(startTime+gap*436, "Space"),
					new Beat(startTime+gap*440, "K"),
					new Beat(startTime+gap*444, "D"),
					new Beat(startTime+gap*448, "S"),
					new Beat(startTime+gap*452, "L"),
					new Beat(startTime+gap*456, "K"),
					new Beat(startTime+gap*460, "L"),
					new Beat(startTime+gap*464, "J"),
					new Beat(startTime+gap*468, "L"),
					new Beat(startTime+gap*472, "Space"),
					new Beat(startTime+gap*476, "F"),
					new Beat(startTime+gap*480, "Space"),
					new Beat(startTime+gap*484, "F"),
					new Beat(startTime+gap*488, "S"),
					new Beat(startTime+gap*492, "F"),
					new Beat(startTime+gap*496, "J"),
					new Beat(startTime+gap*500, "S"),
					new Beat(startTime+gap*504, "J"),
					new Beat(startTime+gap*508, "F"),
					new Beat(startTime+gap*512, "Space"),
					new Beat(startTime+gap*516, "J"),
					new Beat(startTime+gap*520, "K"),
					new Beat(startTime+gap*524, "L"),
					new Beat(startTime+gap*528, "Space"),
					new Beat(startTime+gap*532, "S"),
					new Beat(startTime+gap*536, "F"),
					new Beat(startTime+gap*540, "L"),
					new Beat(startTime+gap*544, "S"),
					new Beat(startTime+gap*548, "D"),
					new Beat(startTime+gap*552, "K"),
					new Beat(startTime+gap*556, "J"),
					new Beat(startTime+gap*560, "L"),
					new Beat(startTime+gap*564, "K"),
					new Beat(startTime+gap*568, "L"),
					new Beat(startTime+gap*572, "Space"),
					new Beat(startTime+gap*576, "L"),
					new Beat(startTime+gap*580, "F"),
					new Beat(startTime+gap*584, "Space"),
					new Beat(startTime+gap*588, "J"),
					new Beat(startTime+gap*592, "F"),
					new Beat(startTime+gap*596, "J"),
					new Beat(startTime+gap*600, "F"),
					new Beat(startTime+gap*604, "S"),
					new Beat(startTime+gap*608, "D"),
					new Beat(startTime+gap*612, "D"),
					new Beat(startTime+gap*616, "D"),
					new Beat(startTime+gap*620, "Space"),
					new Beat(startTime+gap*624, "J"),
					new Beat(startTime+gap*628, "K"),
					new Beat(startTime+gap*632, "L"),
					new Beat(startTime+gap*636, "Space"),
					new Beat(startTime+gap*640, "S"),
					new Beat(startTime+gap*644, "F"),
					new Beat(startTime+gap*648, "K"),
					new Beat(startTime+gap*652, "D"),
					new Beat(startTime+gap*656, "S"),
					new Beat(startTime+gap*660, "D"),
					new Beat(startTime+gap*664, "S"),
					new Beat(startTime+gap*668, "S"),
					new Beat(startTime+gap*672, "D"),
					new Beat(startTime+gap*676, "J"),
					new Beat(startTime+gap*680, "L"),
					new Beat(startTime+gap*684, "F"),
					new Beat(startTime+gap*688, "Space"),
					new Beat(startTime+gap*692, "Space"),
					new Beat(startTime+gap*696, "Space"),
					new Beat(startTime+gap*700, "S"),
					new Beat(startTime+gap*704, "J"),
					new Beat(startTime+gap*708, "F"),
					new Beat(startTime+gap*712, "J"),
					new Beat(startTime+gap*716, "K"),
					new Beat(startTime+gap*720, "L"),
					new Beat(startTime+gap*724, "J"),
					new Beat(startTime+gap*728, "Space"),
					new Beat(startTime+gap*732, "D"),
					new Beat(startTime+gap*736, "K"),
					new Beat(startTime+gap*740, "L"),
					new Beat(startTime+gap*744, "Space"),
					new Beat(startTime+gap*748, "F"),
					new Beat(startTime+gap*752, "F"),
					new Beat(startTime+gap*756, "K"),
					new Beat(startTime+gap*760, "D"),
					new Beat(startTime+gap*764, "S"),
					new Beat(startTime+gap*768, "D"),
					new Beat(startTime+gap*772, "S"),
					new Beat(startTime+gap*776, "L"),
					new Beat(startTime+gap*780, "K"),
					new Beat(startTime+gap*784, "L"),
					new Beat(startTime+gap*788, "K"),
					new Beat(startTime+gap*792, "S"),
					new Beat(startTime+gap*796, "Space"),
					new Beat(startTime+gap*800, "Space"),
					new Beat(startTime+gap*804, "Space"),
					new Beat(startTime+gap*808, "J"),
					new Beat(startTime+gap*812, "J"),
					new Beat(startTime+gap*816, "F"),
					new Beat(startTime+gap*820, "J"),
					new Beat(startTime+gap*824, "D"),
					new Beat(startTime+gap*828, "D"),
					new Beat(startTime+gap*832, "F"),
					new Beat(startTime+gap*836, "Space"),
					new Beat(startTime+gap*840, "J"),
					new Beat(startTime+gap*844, "K"),
					new Beat(startTime+gap*848, "L"),
					new Beat(startTime+gap*852, "Space"),
					new Beat(startTime+gap*856, "D"),
					new Beat(startTime+gap*860, "F"),
					new Beat(startTime+gap*864, "K"),
					new Beat(startTime+gap*868, "D"),
					new Beat(startTime+gap*872, "K"),
					new Beat(startTime+gap*876, "D"),
					new Beat(startTime+gap*880, "K"),
					new Beat(startTime+gap*884, "L"),
					new Beat(startTime+gap*888, "F"),
					new Beat(startTime+gap*892, "Space"),
					new Beat(startTime+gap*896, "F"),
					new Beat(startTime+gap*900, "K"),
					new Beat(startTime+gap*904, "L"),
					new Beat(startTime+gap*908, "F"),
					new Beat(startTime+gap*912, "J"),
					new Beat(startTime+gap*916, "D"),
					new Beat(startTime+gap*920, "Space"),
					new Beat(startTime+gap*924, "F"),
					new Beat(startTime+gap*928, "Space"),
					new Beat(startTime+gap*932, "J"),
					new Beat(startTime+gap*936, "Space"),
					new Beat(startTime+gap*940, "L"),
					new Beat(startTime+gap*944, "Space"),
					new Beat(startTime+gap*948, "S"),
					new Beat(startTime+gap*952, "Space"),
					new Beat(startTime+gap*956, "K"),
					new Beat(startTime+gap*960, "K"),
					new Beat(startTime+gap*964, "S"),
					new Beat(startTime+gap*968, "F"),
					new Beat(startTime+gap*972, "S"),
					new Beat(startTime+gap*976, "L"),
					new Beat(startTime+gap*980, "K"),
					new Beat(startTime+gap*984, "L"),
					new Beat(startTime+gap*988, "D"),
					new Beat(startTime+gap*992, "J"),
					new Beat(startTime+gap*996, "Space"),
					new Beat(startTime+gap*1000, "L"),
					new Beat(startTime+gap*1004, "Space"),
					new Beat(startTime+gap*1008, "F"),
					new Beat(startTime+gap*1012, "J"),
					new Beat(startTime+gap*1016, "F"),
					new Beat(startTime+gap*1020, "J"),
					new Beat(startTime+gap*1024, "S"),
					new Beat(startTime+gap*1028, "D"),
					new Beat(startTime+gap*1032, "F"),
					new Beat(startTime+gap*1036, "Space"),
					new Beat(startTime+gap*1040, "F"),
					new Beat(startTime+gap*1044, "D"),
					new Beat(startTime+gap*1048, "S"),
					new Beat(startTime+gap*1052, "F"),
					new Beat(startTime+gap*1056, "D"),
					new Beat(startTime+gap*1060, "Space"),
					new Beat(startTime+gap*1064, "F"),
					new Beat(startTime+gap*1068, "J"),
					new Beat(startTime+gap*1072, "Space"),
					new Beat(startTime+gap*1076, "K"),
					new Beat(startTime+gap*1080, "J"),
					new Beat(startTime+gap*1084, "L"),
					new Beat(startTime+gap*1088, "L"),
					new Beat(startTime+gap*1092, "J"),
					new Beat(startTime+gap*1096, "K"),
					new Beat(startTime+gap*1100, "Space"),
					new Beat(startTime+gap*1104, "J"),
					new Beat(startTime+gap*1108, "F"),
					new Beat(startTime+gap*1112, "Space"),
					new Beat(startTime+gap*1116, "D"),
					new Beat(startTime+gap*1120, "F"),
					new Beat(startTime+gap*1124, "S"),
					new Beat(startTime+gap*1128, "D"),
					new Beat(startTime+gap*1132, "Space"),
					new Beat(startTime+gap*1136, "Space"),
					new Beat(startTime+gap*1140, "Space"),
					new Beat(startTime+gap*1144, "D"),
					new Beat(startTime+gap*1148, "S"),
					new Beat(startTime+gap*1152, "F"),
					new Beat(startTime+gap*1156, "K"),
					new Beat(startTime+gap*1160, "D"),
					new Beat(startTime+gap*1164, "S"),
					new Beat(startTime+gap*1168, "D"),
					new Beat(startTime+gap*1172, "S"),
					new Beat(startTime+gap*1176, "L"),
					new Beat(startTime+gap*1180, "K"),
					new Beat(startTime+gap*1184, "L"),
					new Beat(startTime+gap*1188, "K"),
					new Beat(startTime+gap*1192, "L"),
					new Beat(startTime+gap*1196, "Space"),
					new Beat(startTime+gap*1200, "Space"),
					new Beat(startTime+gap*1204, "J"),
					new Beat(startTime+gap*1208, "F"),
					new Beat(startTime+gap*1212, "J"),
					new Beat(startTime+gap*1216, "S"),
					new Beat(startTime+gap*1220, "D"),
					new Beat(startTime+gap*1224, "F"),
					new Beat(startTime+gap*1228, "Space"),
					new Beat(startTime+gap*1232, "J"),
					new Beat(startTime+gap*1236, "K"),
					new Beat(startTime+gap*1240, "L"),
					new Beat(startTime+gap*1244, "Space"),
					new Beat(startTime+gap*1248, "S"),
					new Beat(startTime+gap*1252, "F"),
					new Beat(startTime+gap*1256, "K"),
					new Beat(startTime+gap*1260, "D"),
					new Beat(startTime+gap*1264, "S"),
					new Beat(startTime+gap*1268, "D"),
					new Beat(startTime+gap*1272, "D"),
					new Beat(startTime+gap*1276, "Space"),
					new Beat(startTime+gap*1280, "K"),
					new Beat(startTime+gap*1284, "F"),
					new Beat(startTime+gap*1288, "D"),
					new Beat(startTime+gap*1292, "L"),
					new Beat(startTime+gap*1296, "Space"),
					new Beat(startTime+gap*1300, "Space"),
					new Beat(startTime+gap*1304, "J"),
					new Beat(startTime+gap*1308, "S"),
					new Beat(startTime+gap*1312, "J"),
					new Beat(startTime+gap*1316, "S"),
					new Beat(startTime+gap*1320, "D"),
					new Beat(startTime+gap*1324, "F"),
					new Beat(startTime+gap*1328, "Space"),
					new Beat(startTime+gap*1332, "J"),
					new Beat(startTime+gap*1336, "K"),
					new Beat(startTime+gap*1340, "L"),
					new Beat(startTime+gap*1344, "Space"),
					new Beat(startTime+gap*1348, "S"),
					new Beat(startTime+gap*1352, "F"),
					new Beat(startTime+gap*1356, "K"),
					new Beat(startTime+gap*1360, "D"),
					new Beat(startTime+gap*1364, "S"),
					new Beat(startTime+gap*1368, "J"),
					new Beat(startTime+gap*1372, "D"),
					new Beat(startTime+gap*1376, "L"),
					new Beat(startTime+gap*1380, "K"),
					new Beat(startTime+gap*1384, "L"),
					new Beat(startTime+gap*1388, "S"),
					new Beat(startTime+gap*1392, "K"),
					new Beat(startTime+gap*1396, "Space"),
					new Beat(startTime+gap*1400, "L"),
					new Beat(startTime+gap*1404, "Space"),
					new Beat(startTime+gap*1408, "Space"),
					new Beat(startTime+gap*1412, "J"),
					new Beat(startTime+gap*1416, "F"),
					new Beat(startTime+gap*1420, "J"),
					new Beat(startTime+gap*1424, "S"),
					new Beat(startTime+gap*1428, "D"),
					new Beat(startTime+gap*1432, "F"),
					new Beat(startTime+gap*1436, "Space"),
					new Beat(startTime+gap*1440, "J"),
					new Beat(startTime+gap*1444, "K"),
					new Beat(startTime+gap*1448, "L"),
					new Beat(startTime+gap*1452, "Space"),
					new Beat(startTime+gap*1456, "S"),
					new Beat(startTime+gap*1460, "F"),
					new Beat(startTime+gap*1464, "K"),
					new Beat(startTime+gap*1468, "D"),
					new Beat(startTime+gap*1472, "S"),
					new Beat(startTime+gap*1476, "D"),
					new Beat(startTime+gap*1480, "S"),
					new Beat(startTime+gap*1484, "F"),
					new Beat(startTime+gap*1488, "J"),
					new Beat(startTime+gap*1492, "L"),
					new Beat(startTime+gap*1496, "K"),
					new Beat(startTime+gap*1500, "Space"),
					new Beat(startTime+gap*1504, "J"),
					new Beat(startTime+gap*1508, "F"),
					new Beat(startTime+gap*1512, "J"),
					new Beat(startTime+gap*1516, "S"),
					new Beat(startTime+gap*1520, "D"),
					new Beat(startTime+gap*1524, "F"),
					new Beat(startTime+gap*1528, "Space"),
					new Beat(startTime+gap*1532, "Space"),
					new Beat(startTime+gap*1536, "K"),
					new Beat(startTime+gap*1540, "L"),
					new Beat(startTime+gap*1544, "Space"),
					new Beat(startTime+gap*1548, "J"),
					new Beat(startTime+gap*1552, "F"),
					new Beat(startTime+gap*1556, "L"),
					new Beat(startTime+gap*1560, "D"),
					new Beat(startTime+gap*1564, "S"),
					new Beat(startTime+gap*1568, "D"),
					new Beat(startTime+gap*1572, "J"),
					new Beat(startTime+gap*1576, "L"),
					new Beat(startTime+gap*1580, "K"),
					new Beat(startTime+gap*1584, "L"),
					new Beat(startTime+gap*1588, "Space"),
					new Beat(startTime+gap*1592, "L"),
					new Beat(startTime+gap*1596, "Space"),
					new Beat(startTime+gap*1600, "Space"),
					new Beat(startTime+gap*1604, "J"),
					new Beat(startTime+gap*1608, "F"),
					new Beat(startTime+gap*1612, "J"),
					new Beat(startTime+gap*1616, "S"),
					new Beat(startTime+gap*1620, "D"),
					new Beat(startTime+gap*1624, "F"),
					new Beat(startTime+gap*1628, "Space"),
					new Beat(startTime+gap*1632, "J"),
					new Beat(startTime+gap*1636, "K"),
					new Beat(startTime+gap*1640, "L"),
					new Beat(startTime+gap*1644, "Space"),
					new Beat(startTime+gap*1648, "S"),
					new Beat(startTime+gap*1652, "F"),
					new Beat(startTime+gap*1656, "K"),
					new Beat(startTime+gap*1660, "D"),
					new Beat(startTime+gap*1664, "S"),
					new Beat(startTime+gap*1668, "D"),
					new Beat(startTime+gap*1672, "S"),
					new Beat(startTime+gap*1676, "L"),
					new Beat(startTime+gap*1680, "K"),
					new Beat(startTime+gap*1684, "L"),
					new Beat(startTime+gap*1688, "K"),
					new Beat(startTime+gap*1692, "L"),
					new Beat(startTime+gap*1696, "Space"),
					new Beat(startTime+gap*1700, "Space"),
					new Beat(startTime+gap*1704, "J"),
					new Beat(startTime+gap*1708, "F"),
					new Beat(startTime+gap*1712, "J"),
					new Beat(startTime+gap*1716, "S"),
					new Beat(startTime+gap*1720, "D"),
					new Beat(startTime+gap*1724, "F"),
					new Beat(startTime+gap*1728, "Space"),
					new Beat(startTime+gap*1732, "J"),
					new Beat(startTime+gap*1736, "K"),
					new Beat(startTime+gap*1740, "L"),
					new Beat(startTime+gap*1744, "Space"),
					new Beat(startTime+gap*1748, "S"),
					new Beat(startTime+gap*1752, "F"),
					new Beat(startTime+gap*1756, "K"),
					new Beat(startTime+gap*1760, "J"),
					new Beat(startTime+gap*1764, "L"),
					new Beat(startTime+gap*1768, "Space"),
					new Beat(startTime+gap*1772, "S"),
					new Beat(startTime+gap*1776, "D"),
					new Beat(startTime+gap*1780, "F"),
					new Beat(startTime+gap*1784, "Space"),
					new Beat(startTime+gap*1788, "J"),
					new Beat(startTime+gap*1792, "K"),
					new Beat(startTime+gap*1796, "L"),
					new Beat(startTime+gap*1800, "Space"),
					new Beat(startTime+gap*1804, "Space"),
					new Beat(startTime+gap*1808, "S"),
					new Beat(startTime+gap*1812, "L"),
					new Beat(startTime+gap*1816, "D"),
					new Beat(startTime+gap*1820, "K"),
					new Beat(startTime+gap*1824, "F"),
					new Beat(startTime+gap*1828, "J"),
					new Beat(startTime+gap*1832, "Space"),
					new Beat(startTime+gap*1836, "J"),
					new Beat(startTime+gap*1840, "F"),
					new Beat(startTime+gap*1844, "K"),
					new Beat(startTime+gap*1848, "D"),
					new Beat(startTime+gap*1852, "L"),
					new Beat(startTime+gap*1856, "S"),
					new Beat(startTime+gap*1860, "Space"),
					new Beat(startTime+gap*1864, "S"),
					new Beat(startTime+gap*1868, "D"),
					new Beat(startTime+gap*1872, "S"),
					new Beat(startTime+gap*1876, "L"),
					new Beat(startTime+gap*1880, "K"),
					new Beat(startTime+gap*1884, "L"),
					new Beat(startTime+gap*1888, "K"),
					new Beat(startTime+gap*1892, "L"),
					new Beat(startTime+gap*1896, "Space"),
					new Beat(startTime+gap*1900, "Space"),
					new Beat(startTime+gap*1904, "J"),
					new Beat(startTime+gap*1908, "F"),
					new Beat(startTime+gap*1912, "J"),
					new Beat(startTime+gap*1916, "S"),
					new Beat(startTime+gap*1920, "D"),
					new Beat(startTime+gap*1924, "F"),
					new Beat(startTime+gap*1928, "Space"),
					new Beat(startTime+gap*1932, "J"),
					new Beat(startTime+gap*1936, "K"),
					new Beat(startTime+gap*1940, "L"),
					new Beat(startTime+gap*1944, "Space"),
					new Beat(startTime+gap*1948, "S"),
					new Beat(startTime+gap*1952, "F"),
					new Beat(startTime+gap*1956, "K"),
					new Beat(startTime+gap*1960, "D"),
					new Beat(startTime+gap*1964, "K"),
					new Beat(startTime+gap*1968, "S"),
					new Beat(startTime+gap*1972, "D"),
					new Beat(startTime+gap*1976, "L"),
					new Beat(startTime+gap*1980, "K"),
					new Beat(startTime+gap*1984, "L"),
					new Beat(startTime+gap*1988, "K"),
					new Beat(startTime+gap*1992, "L"),
					new Beat(startTime+gap*1996, "Space"),
					new Beat(startTime+gap*2000, "Space"),
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
			score += 5;
			combo = 0;
		}
		else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
			score += 20;
			combo += 1;
		}
		else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
			score += 50;
			combo += 1;
		}
		else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
			score += 100;
			combo += 1;
		}
		else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
			score += 5;
			combo = 0;
		}
		
	}

}
