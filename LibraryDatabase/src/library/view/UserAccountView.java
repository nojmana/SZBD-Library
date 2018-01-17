package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import library.Main;

public class UserAccountView {
	
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
	private Button confirmButton;
	
	@FXML
	private TableView booksTable;

	
	@FXML
	public void handleEnterPressed(KeyEvent event) {
	    if (event.getCode() == KeyCode.ENTER) {
	    	confirmButtonClick();
	    }
	}
	
	@FXML
	public void confirmButtonClick() {
		
	}
	
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("UserView");
	}
}
