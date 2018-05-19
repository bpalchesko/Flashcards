package main.java.model;

public class Flashcard {
	
	private final FlashcardSide frontSide;
	private final FlashcardSide backSide;
	
	public Flashcard(String frontValue, String backValue) {
		frontSide = new FlashcardSide(frontValue);
		backSide = new FlashcardSide(backValue);
	}
	
	public FlashcardSide getSide(boolean isFront) {
		if(isFront) {
			return frontSide;
		} else {
			return backSide;
		}
	}
	
	public boolean gradeCard(String input, boolean isFront) {
		return getSide(isFront).grade(input);
	}
}
