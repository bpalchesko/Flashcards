package main.java.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.model.FlashcardSet;

public class CsvParser implements Parser{

	public FlashcardSet read(File importCsv)
			throws FileNotFoundException, IllegalArgumentException {
        Scanner scanner = new Scanner(importCsv);
        FlashcardSet flashcards = new FlashcardSet();
	    while(scanner.hasNextLine()){
	    	processLine(scanner.nextLine(), flashcards);
	    }
	    scanner.close();
		return flashcards;
	}
	
	private void processLine(String line, FlashcardSet flashcards) {
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
