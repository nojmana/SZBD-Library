package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import library.Main;

public class LibrarianBooksMenuView {

	@FXML
	private Button searchBookButton;
	
	@FXML
	private Button addBookButton;
	
	@FXML 
	private Button backButton;
	
	@FXML
	public void searchBookButtonClick() {
		Main.showOtherViewAnchor("LibrarianSearchBookView");
	}
	
	@FXML
	public void addBookButtonClick() {
		Main.showOtherViewAnchor("LibrarianAddBookView");
	}
	
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianView");
	}
}
