package dynamic_beat_17.View;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dynamic_beat_17.Main;
import dynamic_beat_17.Controller.KeyListener;
import dynamic_beat_17.Model.Track;

// JFrame -> needed in order to develop GUI based applications in Java
// Inherit an already existing Library(class) in Java

public class DynamicBeat extends JFrame {

	// Background panel for custom painting
	private BackgroundPanel backgroundPanel;

	// retrieve background.jpg and put into background variable
	// Having Main.class as a reference point get the resource from file path; reset
	// variable background to that file																											
	private Image background = new ImageIcon(Main.class.getResource("/images/introBackground.jpg")).getImage(); 																									
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("/images/menuBar.png"))); 
	

	
	//Buttons	
	//Exit Button
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("/images/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage); //basic default button is exitButtonBasicImage

	//Start Button
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("/images/startButtonBasic.png"));
	private JButton startButton = new JButton(startButtonBasicImage);
	
	//Quit Button
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("/images/quitButtonBasic.png"));
	private JButton quitButton = new JButton(quitButtonBasicImage);
	
	//Left Button
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("/images/leftButtonBasic.png"));
	private JButton leftButton = new JButton(leftButtonBasicImage);
	
	//Right Button
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("/images/rightButtonBasic.png"));
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	//Easy Button
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("/images/easyButtonBasic.png"));
	private JButton easyButton = new JButton(easyButtonBasicImage);
	
	//Hard Button 
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("/images/hardButtonBasic.png"));
	private JButton hardButton = new JButton(hardButtonBasicImage);
	
	//Back Button
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("/images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);
	



	//Make the screen move when we drag menu bar
	private int mouseX, mouseY;
	
	//Screen
	private boolean isMainScreen = false; //initially not on main screen therefore false
	private boolean isGameScreen = false; 
	
	//Track class
	//make an arraylist that can keep track of the title and music of a track
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true); 
	private int nowSelected = 0; //index 0 (first track)
	
	//Using the Game class
	public static Game game; //the variable game can be used throughout the project
	
	
	// DynamicBeat() is a constructor
	// Constructor is a method that has the same name as the class
	// A constructor in Java is a special method that is used to initialize objects.
	// The constructor is called when
	// an object of a class is created. It can be used to set initial values for
	// object attributes
	public DynamicBeat() {
		//Index 0: Cool-Tobu
		trackList.add(new Track("Cool Title Image.png", "Cool Start Image.png", "Cool Game Image.png",
				"Cool-Tobu Selected.mp3", "Cool-Tobu.mp3", "Cool-Tobu"));
		//Index 1: Dreams-Joakim Karud
		trackList.add(new Track("Dreams Title Image.png", "Dreams Start Image.png", "Dreams Game Image.png",
				"Dreams-Joakim Karud Selected.mp3", "Dreams-Joakim Karud.mp3", "Dreams-Joakim Karud"));
		//Index 2: We Are One-Vexento
		trackList.add(new Track("We Are One Title Image.png", "We Are One Start Image.png", "We Are One Game Image.png",
				"We Are One-Vexento Selected.mp3", "We Are One-Vexento.mp3", "We Are One-Vexento"));
		
		trackList.add(new Track("", "the few and the many.jpg", "the few and the many v2.jpg", "The Few & The Many cut.mp3", 
				"The Few & The Many - S3RL.mp3", "The Few & The Many"));

		setUndecorated(true); // when first executed, menubar doesn't show
		setTitle("Dynamic Beat"); // the name of our game becomes "Dynamic Beat"
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // user cannot redefine the screen size
		setLocationRelativeTo(null); // when you run the project, the screen will appear right on the centre of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // need to declare this; otherwise the program continues to run in computer even after we close screen
		
		// Create and add background panel
		backgroundPanel = new BackgroundPanel();
		backgroundPanel.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		backgroundPanel.setLayout(null);
		setContentPane(backgroundPanel);
		
		setFocusable(true); // Enable focus for keyboard input
		addKeyListener(new KeyListener()); //from KeyListener class
		
		// Add intro music
		introMusic.start();

		//Exit button
		//Notice that the exit button must be declared before menu bar so that it gets placed on top of the menu bar
		exitButton.setBounds(1245, 0, 30, 30); //put exit button on the rightmost side of the menu bar
		exitButton.setBorderPainted(false); //need to set JButton so that it fits our button image
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			//@Override
			//When mouse is on top of the exit button
			public void mouseEntered(MouseEvent e) { 
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			//When mouse gets out of the exit button
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			//When exit button is pressed
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//In order to prevent music not being heard (becaue the program exits immediately, make it so that the program 
				//terminates 1 sec later the music plays
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //exit the game
			}
		});
		backgroundPanel.add(exitButton);
		
		//Start Button
		startButton.setBounds(40, 200, 400, 100); 
		startButton.setBorderPainted(false); 
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			//When mouse gets out of the start button
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			//When button is pressed
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//Start Button Event
				enterMain();
			}
		});
		backgroundPanel.add(startButton);
		
		//Quit Button
		quitButton.setBounds(40, 330, 400, 100); 
		quitButton.setBorderPainted(false); 
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			//When mouse gets out of the exit button
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			//When quit button is pressed
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //exit the game
			}
		});
		backgroundPanel.add(quitButton);
		
		//Left Button
		leftButton.setVisible(false); //make it so that it's not visible in the beginning
		leftButton.setBounds(140, 310, 60, 60); 
		leftButton.setBorderPainted(false); 
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//Left Button Event
				selectLeft();
				
			}
		});
		backgroundPanel.add(leftButton);
		
		//Right Button
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); 
		rightButton.setBorderPainted(false); 
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//Right Button Event
				selectRight();
				
			}
		});
		backgroundPanel.add(rightButton);
		
		//Easy Button
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67); 
		easyButton.setBorderPainted(false); 
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//Easy Button Event
				gameStart(nowSelected,"Easy");

				
				
			}
		});
		backgroundPanel.add(easyButton);
		
		//Hard Button
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67); 
		hardButton.setBorderPainted(false); 
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//hardButton Event
				gameStart(nowSelected,"Hard");
				
				
			}
		});
		backgroundPanel.add(hardButton);
		
		//Back Button
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60); 
		backButton.setBorderPainted(false); 
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseEntered(MouseEvent e) { 
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //change the icon of the mouse cursor 
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
			}
			
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); //play only once
				buttonEnteredMusic.start();
				//Event going back to main screen
				backMain();
				

			}
		});
		backgroundPanel.add(backButton);
		
		//Menubar
		menuBar.setBounds(0, 0, 1280, 30); // declares position and size of menubar
		menuBar.addMouseListener(new MouseAdapter() {
//			@Override
			public void mousePressed(MouseEvent e) { //when a mouse event occurs, retrieve the x and y coordinates of the mouse
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			//@Override
			public void mouseDragged(MouseEvent e) { // whenever mouse is dragged, get x,y position of the mouse and move the screen accordingly
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY);
			}
		});
		backgroundPanel.add(menuBar); // adds menubar to jframe

		// Start repaint thread
		new Thread(() -> {
			while(true) {
				backgroundPanel.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		setVisible(true); // make the window visible - MUST BE LAST

		



	}
	
	//function for Track
	//input the # of the currently selected track
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) 
			selectedMusic.close();
		//change titleImage and selectedImage to the one that corresponds to the selected track
		String titleImgPath = trackList.get(nowSelected).getTitleImage();
		String startImgPath = trackList.get(nowSelected).getStartImage();
		
		if (titleImgPath != null && !titleImgPath.isEmpty()) {
			titleImage = new ImageIcon(Main.class.getResource("/images/" + titleImgPath)).getImage();
		} else {
			titleImage = null;
		}
		
		if (startImgPath != null && !startImgPath.isEmpty()) {
			selectedImage = new ImageIcon(Main.class.getResource("/images/" + startImgPath)).getImage();
		} else {
			selectedImage = null;
		}
		
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	//Select Left
	public void selectLeft() {
		if (nowSelected == 0) 
			nowSelected = trackList.size()-1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	//Select Right
	public void selectRight() {
		if (nowSelected == trackList.size()-1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	//Game Start
	public void gameStart(int nowSelected, String difficuly) {
		if (selectedMusic != null) 
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("/images/"+trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = Game.getInstance
		(
			trackList.get(nowSelected).getTitleName(), difficuly, 
			trackList.get(nowSelected).getGameMusic()
		);

		/*
		 * Singelton pattern |||||||||||||||||||||||||||||||||||||||||||||||||||
		 */
		//game = new Game(trackList.get(nowSelected).getTitleName(),difficuly, trackList.get(nowSelected).getGameMusic());
		game.start(); //run function is activated; note drops
		requestFocusInWindow(); // Request focus when game starts
		setFocusable(true); //for keyboard focus
	}
	
	//Function to go back to the main screen
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("/images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
		Game.resetInstance(); // Reset the singleton instance
	}
	
	//Game Start Event
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("/images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
	
	// Inner class for background panel with custom painting
	class BackgroundPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			// Draw background
			if (background != null) {
				g2d.drawImage(background, 0, 0, null);
			}
			
			// Draw main screen elements
			if (isMainScreen) {
				if (selectedImage != null) {
					g2d.drawImage(selectedImage, 340, 100, null);
				}
				if (titleImage != null) {
					g2d.drawImage(titleImage, 340, 70, null);
				}
			}
			
			// Draw game screen
			if (isGameScreen && game != null) {
				game.screenDraw(g2d);
			}
		}
	}

}
