package main.java;

import main.java.filehandler.CsvHandler;
import main.java.filehandler.FileHandler;
import main.java.model.Quiz;
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
	@FXML
	private Label textDisplay;
	@FXML
	private TextField textInput;
	
	@FXML
	private void loadFile(ActionEvent event) {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Load File");
	    chooser.getExtensionFilters().add(FILE_HANDLER.getExtensionFilter());
	    quiz = FILE_HANDLER.read(chooser.showOpenDialog(new Stage()));
	    textDisplay.setText(quiz.getTextForDisplay(textInput.getText()));
	}
	
	@FXML
	private void enterKeyPressed(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			quiz.proceed(textInput.getText());
			textDisplay.setText(quiz.getTextForDisplay(textInput.getText()));
			textInput.clear();
			keyEvent.consume();
		}
	}
}
