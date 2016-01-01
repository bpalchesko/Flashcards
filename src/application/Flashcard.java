package application;

public class Flashcard {
	
	private final String front;
	private final String back;
	private int timesQuizzed;
	private int timesCorrect;
	
	public Flashcard(String front, String back) {
		this.front = front;
		this.back = back;
		timesQuizzed = 0;
		timesCorrect = 0;
	}
	
	public String getValue(Side side) {
		if(side == Side.FRONT) {
			return front;
		} else {
			return back;
		}
	}

	//TODO compare inexact algorithm
	public boolean gradeCard(Side showingSide, String input) {
		timesQuizzed++;
		boolean correctInput;
		if(showingSide == Side.FRONT) {
			correctInput = input.toLowerCase().equals(back.toLowerCase());
		} else {
			correctInput = input.toLowerCase().equals(front.toLowerCase());
		}
		if(correctInput) {
			timesCorrect++;
		}
		return correctInput;
	}
	
	public int getTimesQuizzed() {
		return timesQuizzed;
	}
	
	public int getTimesCorrect() {
		return timesCorrect;
	}
	
}
