package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	private File importCsv;
	@FXML
	private Button submitButton;
	@FXML
	private Label textDisplay;
	@FXML
	private TextField textInput;

	public MainController()
	{
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
	    importCsv = chooser.showOpenDialog(new Stage());
	    readCsv();
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
	
	private void readCsv() {
        Scanner scanner;
        flashcards = new FlashcardSet();
        showingCard = false;
		try {
			scanner = new Scanner(importCsv);
	        while(scanner.hasNextLine()){
	        	processLine(scanner.nextLine());
	        }
			scanner.close();
			flashcards.startQuiz(FRONT, true);
			textDisplay.setText("Press Enter to begin");
		} catch (FileNotFoundException e) {
			textDisplay.setText("Error opening file. Please try another file");
		} catch (IllegalArgumentException e) {
			textDisplay.setText("Invalid file format");
		}
	}
	
	private void processLine(String line) {
		ArrayList<String> cardWords = new ArrayList<String>();
		String[] words = line.split(",");
		for(String word : words) {
			if(!"".equals(word)) {
				cardWords.add(word);
			}
		}
		if(cardWords.size() == 2) {
			flashcards.add(cardWords.get(0), cardWords.get(1)); 
		} else if(cardWords.size() != 0) {
			throw new IllegalArgumentException();
		}
	}
}
