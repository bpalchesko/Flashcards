package main.java;

import main.java.filehandler.CsvHandler;
import main.java.filehandler.FileHandler;
import main.java.model.Quiz;
import main.java.model.FlashcardSide;
import main.java.model.QuizState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainView {
	
	private final FileHandler FILE_HANDLER = new CsvHandler();
	private Quiz quiz;
	private QuizState state;
	@FXML
	private Label textDisplay;
	@FXML
	private TextField textInput;
	
	@FXML
	private void loadFile(ActionEvent event) {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Load File");
	    chooser.getExtensionFilters().add(FILE_HANDLER.getExtensionFilter());
	    try {
			quiz = FILE_HANDLER.read(chooser.showOpenDialog(new Stage()));
		    state = QuizState.READY;
		} catch (Exception e) {
			state = QuizState.LOAD_ERROR;
		}
	    textDisplay.setText(state.getDisplayText(quiz, textInput.getText()));
	}
	
	@FXML
	private void enterKeyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			switch(state) {
			case READY:
				state = quiz.startQuiz(FlashcardSide.FRONT, true);
				break;
			case QUESTION:
				state = quiz.gradeQuestion(textInput.getText());
				break;
			case ANSWER_CORRECT:
			case ANSWER_INCORRECT:
				state = quiz.proceedToNext();
				break;
			case COMPLETE:
				state = QuizState.READY;
				break;
			default:
				break;
			}
			textDisplay.setText(state.getDisplayText(quiz, textInput.getText()));
			textInput.clear();
			keyEvent.consume();
		}
	}
}
