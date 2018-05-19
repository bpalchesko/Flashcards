package application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FlashcardSet {
	
	private boolean showSide;
	private ArrayList<Flashcard> set;
	private Iterator<Flashcard> iter;
	private Flashcard current;
	private int cardsQuizzed;
	private int cardsCorrect;
	
	public FlashcardSet() {
		set = new ArrayList<Flashcard>();
		showSide = true;
	}
	
	public void add(String front, String back) {
		set.add(new Flashcard(front,back));	
	}
	
	public void startQuiz(boolean showSide, boolean shuffle) {
		if(shuffle) Collections.shuffle(set);
		this.showSide = showSide;
		iter = set.iterator();
	}
	
	public String show() {
		current = iter.next();
		return current.getSide(showSide).getValue();
	}
	
	public String showAnswer() {
		boolean answerSide = !showSide;
		return current.getSide(answerSide).getValue();
	}

	public boolean cardsRemain() {
		return iter.hasNext();
	}
	
	public boolean grade(String input) {
		cardsQuizzed++;
		boolean gradeSide = !showSide;
		if (current.gradeCard(input, gradeSide)) {
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
