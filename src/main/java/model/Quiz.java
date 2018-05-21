package main.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Quiz {
	
	private FlashcardSide questionSide;
	private ArrayList<Flashcard> cards;
	private Iterator<Flashcard> iter;
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
	 * Clears any history, shuffles if specified, and sets initial quiz state
	 * 
	 * @param questionSide
	 * @param shuffle
	 * @return
	 */
	public QuizState startQuiz(FlashcardSide questionSide, boolean shuffle) {
		reset();
		if (shuffle) Collections.shuffle(cards);
		this.questionSide = questionSide;
		iter = cards.iterator();
		return QuizState.QUESTION;
	}
	
	/**
	 * Register question result and proceed
	 * 
	 * @param user's answer input
	 * @return next state based on result
	 */
	public QuizState gradeQuestion(String input) {
		cardsQuizzed++;
		if (current.gradeCard(input, questionSide.flip())) {
			cardsCorrect++;
			return QuizState.ANSWER_CORRECT;
		} else return QuizState.ANSWER_INCORRECT;
	}
	
	/**
	 * Proceed to next question or quiz results following completion of a question
	 */
	public QuizState proceedToNext() {
		return iter.hasNext() ? QuizState.QUESTION : QuizState.COMPLETE;
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
	
	public int getCardsSQuizzed() {
		return cardsQuizzed;
	}

	public int getCardsCorrect() {
		return cardsCorrect;
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
	}
}
