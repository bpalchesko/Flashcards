package application;

import java.util.ArrayList;
import java.util.List;

public class FlashcardSide {
	
	private String value;
	private int timesGuessed;
	private int timesCorrect;
	private List<String> guessHistory;
	
	public FlashcardSide(String value) {
		this.value = value;
		timesGuessed = 0;
		timesCorrect = 0;
		guessHistory = new ArrayList<String>();
	}
	
	public boolean grade(String input) {
		timesGuessed++;
		guessHistory.add(input.toLowerCase());
		boolean correct = value.equalsIgnoreCase(input);
		if (correct) {
			timesCorrect++;
		}
		return correct;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getTimesGuessed() {
		return timesGuessed;
	}
	
	public int getTimesCorrect() {
		return timesCorrect;
	}
	
	public void reset() {
		timesGuessed = 0;
		timesCorrect = 0;
		guessHistory.clear();
	}	
}
