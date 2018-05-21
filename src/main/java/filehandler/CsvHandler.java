package main.java.filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser.ExtensionFilter;
import main.java.model.Quiz;
import main.java.model.QuizState;

public class CsvHandler implements FileHandler{

	@Override
	public Quiz read(File importCsv) {
        Quiz quiz = new Quiz();
        Scanner scanner = null;
		try {
			scanner = new Scanner(importCsv);
		    while(scanner.hasNextLine()){
		    	processLine(scanner.nextLine(), quiz);
		    }
		    quiz.setState(QuizState.READY);
		} catch (FileNotFoundException | IllegalArgumentException e) {
			quiz.setState(QuizState.LOAD_ERROR);
		} finally {
			if (null != scanner) {
				scanner.close();
			}
		}
		return quiz;
	}
	
	private void processLine(String line, Quiz flashcards) {
		ArrayList<String> cardWords = new ArrayList<String>();
		String[] words = line.split(",");
		for(String word : words) {
			if(!"".equals(word)) {
				cardWords.add(word);
			}
		}
		if(cardWords.size() == 2) {
			flashcards.addCard(cardWords.get(0), cardWords.get(1)); 
		} else if(cardWords.size() != 0) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public ExtensionFilter getExtensionFilter() {
		return new ExtensionFilter("CSV files", "*.csv");
	}	
}
