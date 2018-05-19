package main.java;

import main.java.model.FlashcardSet;
import main.java.parser.CsvParser;
import main.java.parser.Parser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainController {

	private final boolean FRONT = true;
	private FlashcardSet flashcards;
	private boolean showingCard;
	private Parser fileParser;
	@FXML
	private Button submitButton;
	@FXML
	private Label textDisplay;
	@FXML
	private TextField textInput;

	public MainController() {
		fileParser = new CsvParser();
	}
	
	@FXML
	private void enterKeyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			submit();
			keyEvent.consume();
		}
	}
	
	@FXML
	private void submitButtonClicked(ActionEvent event) {
		submit();
	}
	
	@FXML
	private void openCsv(ActionEvent event) {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    chooser.getExtensionFilters().add(new ExtensionFilter("CSV files", "*.csv"));
	    try {
			flashcards = fileParser.read(chooser.showOpenDialog(new Stage()));
		    showingCard = false;
			flashcards.startQuiz(FRONT, true);
			textDisplay.setText("Press Enter to begin");
		} catch (Exception e) {
			textDisplay.setText("Error opening file. Please try another file");
		}
	}

	private void submit() {
		if(showingCard) {
			grade();
		} else if(flashcards.cardsRemain()) {
			nextCard();
		} else {
			showResult();
		}
		textInput.clear();
	}

	private void grade() {
		showingCard = false;
		if(flashcards.grade(textInput.getText())) {
			textDisplay.setText("CORRECT!");
		} else {
			textDisplay.setText("WRONG!\n" + flashcards.showAnswer() + " not " + textInput.getText());
		}
	}

	private void nextCard() {
		showingCard = true;
		submitButton.setText("Grade");
		textDisplay.setText(flashcards.show());
	}

	private void showResult() {
		submitButton.setText("Reset");
		String scores = "Correct: " + flashcards.getCardsCorrect() +
				"\nTotal: " + flashcards.getCardsSQuizzed();
		textDisplay.setText(scores);
	}
}
