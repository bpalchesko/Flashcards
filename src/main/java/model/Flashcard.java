package main.java.model;

import java.util.HashMap;
import java.util.Map;

public class Flashcard {
	
	private final Map<FlashcardSide, FlashcardWord> SIDES;
	
	public Flashcard(String frontValue, String backValue) {
		SIDES = new HashMap<>();
		SIDES.put(FlashcardSide.FRONT, new FlashcardWord(frontValue));
		SIDES.put(FlashcardSide.BACK, new FlashcardWord(backValue));
	}
	
	public FlashcardWord getSide(FlashcardSide side) {
		return SIDES.get(side);
	}
	
	public boolean gradeCard(String input, FlashcardSide side) {
		return getSide(side).grade(input);
	}
	
	public void reset() {
		for (FlashcardWord word : SIDES.values()) {
			word.reset();
		}
	}
}
