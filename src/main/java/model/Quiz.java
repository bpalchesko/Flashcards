package main.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Quiz {
	
	private FlashcardSide questionSide;
	private ArrayList<Flashcard> cards;
	private Iterator<Flashcard> iter;
	private QuizState state;
	private Flashcard current;
	private int cardsQuizzed;
	private int cardsCorrect;
	
	public Quiz() {
		cards = new ArrayList<Flashcard>();
		questionSide = FlashcardSide.FRONT;
	}
	
	/**
	 * Add a card with given values to the flashcard deck
	 * @param front
	 * @param back
	 */
	public void addCard(String front, String back) {
		cards.add(new Flashcard(front, back));	
	}
	
	/**
	 * Proceed to the next step in the quiz
	 * 
	 * @param textInput
	 */
	public void proceed(String textInput) {
		switch(state) {
		case READY:
			startQuiz(FlashcardSide.FRONT, true);
			break;
		case QUESTION:
			gradeQuestion(textInput);
			break;
		case ANSWER_CORRECT:
		case ANSWER_INCORRECT:
			proceedToNext();
			break;
		case COMPLETE:
			reset();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Get current text for display
	 * 
	 * @param textInput
	 * @return
	 */
	public String getTextForDisplay(String textInput) {
		return state.getDisplayText(this, textInput);
	}
	
	/**
	 * Clears any history, shuffles if specified, and sets initial quiz state
	 * 
	 * @param questionSide
	 * @param shuffle
	 */
	public void startQuiz(FlashcardSide questionSide, boolean shuffle) {
		if (shuffle) Collections.shuffle(cards);
		this.questionSide = questionSide;
		iter = cards.iterator();
		state = QuizState.QUESTION;
	}
	
	/**
	 * Register question result and proceed
	 * 
	 * @param user's answer input
	 */
	public void gradeQuestion(String input) {
		cardsQuizzed++;
		if (current.gradeCard(input, questionSide.flip())) {
			cardsCorrect++;
			state = QuizState.ANSWER_CORRECT;
		} else {
			state = QuizState.ANSWER_INCORRECT;
		}
	}
	
	/**
	 * Proceed to next question or quiz results following completion of a question
	 */
	public void proceedToNext() {
		state = iter.hasNext() ? QuizState.QUESTION : QuizState.COMPLETE;
	}
	
	/**
	 * Clears all quiz result history
	 */
	public void reset() {
		cardsQuizzed = 0;
		cardsCorrect = 0;
		for (Flashcard card : cards) {
			card.reset();
		}
		state = QuizState.READY;
	}
	
	/**
	 * Gives question text of current card
	 * 
	 * @return question
	 */
	public String showQuestion() {
		current = iter.next();
		return current.getSide(questionSide).getValue();
	}
	
	/**
	 * Gives answer text of current card
	 * 
	 * @return answer
	 */
	public String showAnswer() {
		return current.getSide(questionSide.flip()).getValue();
	}
	
	public QuizState getState() {
		return state;
	}
	
	public void setState(QuizState state) {
		this.state = state;
	}
	
	public int getCardsSQuizzed() {
		return cardsQuizzed;
	}

	public int getCardsCorrect() {
		return cardsCorrect;
	}
}
