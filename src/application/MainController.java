package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

	@FXML
	Button submitButton;
	@FXML
	Label textDisplay;
	@FXML
	TextField textInput;
	
	private FlashcardSet flashcards;
	boolean started;
	
	public MainController()
	{
		started = false;
		flashcards = new FlashcardSet();
		flashcards.setDefault();
		flashcards.startQuiz(Side.FRONT, true);
	}
	
	public void proceed(ActionEvent event) {
		if(flashcards.cardsRemain()) {
			submitButton.setText("Submit");
			String show = flashcards.show();
			textDisplay.setText(show);
			submitButton.setDisable(false);
		} else {
			submitButton.setText("Reset");
			String scores = "Correct: " + flashcards.getCardsCorrect() +
					"\nTotal: " + flashcards.getCardsSQuizzed();
			textDisplay.setText(scores);
		}
		textInput.clear();
	}
	
	public void submit(ActionEvent event) {
		boolean correct = flashcards.grade(textInput.getText());
		if(correct) {
			textDisplay.setText("CORRECT!");
		} else {
			textDisplay.setText("WRONG!\n" + flashcards.showAnswer());
		}
		submitButton.setDisable(true);
	}

}
