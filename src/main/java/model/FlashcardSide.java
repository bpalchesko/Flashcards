package main.java.model;

public enum FlashcardSide {
	FRONT,
	BACK;
	
	public FlashcardSide flip() {
		return this == FRONT ? BACK : FRONT;
	}
}
