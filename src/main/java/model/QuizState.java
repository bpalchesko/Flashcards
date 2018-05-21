package main.java.model;

public enum QuizState {
	INITIAL,
	LOAD_ERROR,
	READY,
	QUESTION,
	ANSWER_CORRECT,
	ANSWER_INCORRECT,
	COMPLETE;
	
	public String getDisplayText(Quiz flashcards, String textInput) {
		switch(this) {
		case INITIAL:
			return "Load quiz from file";
		case LOAD_ERROR:
			return "Error opening file. Please try another file";
		case READY:
			return "Press Enter to begin";
		case QUESTION:
			return flashcards.showQuestion();
		case ANSWER_CORRECT:
			return "CORRECT!";
		case ANSWER_INCORRECT:
			return "WRONG!\nInput: " + textInput
					+ "\nCorrect: " + flashcards.showAnswer();
		case COMPLETE:
			return "Quiz complete.\nCorrect: " + flashcards.getCardsCorrect()
				+ "\nTotal: " + flashcards.getCardsSQuizzed();
		default:
			return "";
		}
	}
}
