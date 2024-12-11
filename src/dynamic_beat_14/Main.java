package dynamic_beat_14;

public class Main {
	//Public static--sharable variable throughout the project
	//final--value does not change once initialized
	//In general, for variable with "final", name them with all caps
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3; //the speed at which the note drops
	public static final int SLEEP_TIME = 10; //in order to drop notes in evenly spanned time
	public static final int REACH_TIME = 2; //time needed for note to reach the judge bar
	
	public static void main(String[] args) {

		//Make a new instance using DynamicBeat class
		new DynamicBeat();
	}

}

