package main.java.parser;

import java.io.File;

import main.java.model.FlashcardSet;

public interface Parser {
	public FlashcardSet read(File file) throws Exception;
}
