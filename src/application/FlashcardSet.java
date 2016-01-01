package application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FlashcardSet {
	
	private ArrayList<Flashcard> set;
	private Side showSide;
	private Iterator<Flashcard> iter;
	private Flashcard current;
	private int cardsQuizzed;
	private int cardsCorrect;
	
	public FlashcardSet() {
		set = new ArrayList<Flashcard>();
		showSide = Side.FRONT;
	}
	
	public void add(String front, String back) {
		set.add(new Flashcard(front,back));	
	}
	
	public void startQuiz(Side side, boolean shuffle) {
		if(shuffle) shuffle();
		showSide = side;
		iter = set.iterator();
	}
	
	public void shuffle() {
		Collections.shuffle(set);
	}
	
	public String show() {
		current = iter.next();
		return current.getValue(showSide);
	}
	
	public String showAnswer() {
		return current.getValue(showSide.not());
	}

	public boolean cardsRemain() {
		return iter.hasNext();
	}
	
	public boolean grade(String input) {
		cardsQuizzed++;
		if(current.gradeCard(showSide, input)) {
			cardsCorrect++;
			return true;
		} else return false;
	}
	
	public void setDefault() {
		add("one", "eins");
		add("two", "zwei");
		add("three", "drei");
	}
	
	public int getCardsSQuizzed() {
		return cardsQuizzed;
	}

	public int getCardsCorrect() {
		return cardsCorrect;
	}

}
