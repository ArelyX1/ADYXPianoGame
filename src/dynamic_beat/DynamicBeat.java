package dynamic_beat;

import javax.swing.JFrame;

// JFrame -> needed in order to develop GUI based applications in Java
// Inherit an already existing Library(class) in Java

public class DynamicBeat extends JFrame {
	//DynamicBeat() is a constructor
	//Constructor is a method that has the same name as the class
	// A constructor in Java is a special method that is used to initialize objects. The constructor is called when
	// an object of a class is created. It can be used to set initial values for object attributes
	public DynamicBeat() {
		setTitle("Dynamic Beat"); //the name of our game becomes "Dynamic Beat"
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //user cannot redefine the screen size
		setLocationRelativeTo(null); //when you run the project, the screen will appear right on the centre of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //need to declare this; otherwise the program continues to run in computer even after we close screen
		setVisible(true); //make the screen visible
		
		
	}

}
