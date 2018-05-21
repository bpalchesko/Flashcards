package main.java.filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser.ExtensionFilter;
import main.java.model.Quiz;

public class CsvHandler implements FileHandler{

	@Override
	public Quiz read(File importCsv)
			throws FileNotFoundException, IllegalArgumentException {
        Scanner scanner = new Scanner(importCsv);
        Quiz flashcards = new Quiz();
	    while(scanner.hasNextLine()){
	    	processLine(scanner.nextLine(), flashcards);
	    }
	    scanner.close();
		return flashcards;
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
