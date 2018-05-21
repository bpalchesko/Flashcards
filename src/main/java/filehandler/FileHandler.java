package main.java.filehandler;

import java.io.File;

import javafx.stage.FileChooser.ExtensionFilter;
import main.java.model.Quiz;

public interface FileHandler {
	
	public Quiz read(File file);
	public ExtensionFilter getExtensionFilter();

}
