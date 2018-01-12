package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import library.Main;

public class SearchBookView {
	
	@FXML
	private TextField titleTextfield;
	
	@FXML
	private TextField authorTextfield;
	
	@FXML 
	private TextField isbnTextfield;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private ButtonBar buttonBar;
	
	@FXML
	private TableView booksTable;
	
	@FXML 
	public void searchButtonClick() {
		titleTextfield.clear();
		authorTextfield.clear();
		isbnTextfield.clear();
	}
	
	@FXML
	public void backButtonClick() {
		if (Main.isLibrarian())
			Main.showOtherViewBorder("LibrarianView");
		else if (Main.isUser())
			Main.showOtherViewBorder("UserView");
	}
}