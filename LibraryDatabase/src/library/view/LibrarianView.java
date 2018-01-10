package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import library.Main;

public class LibrarianView {

	@FXML
	private Button addBookButton;
	
	@FXML
	private Button addCopyButton;
	
	@FXML
	private Button deleteBookButton;
	
	@FXML
	private Button deleteCopyButton;
	
	@FXML
	private Button searchBookButton;
	
	@FXML
	private Button showDebtorsButton;
	
	@FXML 
	private Button logoutButton;
	
	@FXML
	public void addBookButtonClick() {
		
	}

	@FXML
	public void addCopyButtonClick() {
		
	}

	@FXML
	public void deleteBookButtonClick() {
		
	}
	
	@FXML
	public void deleteCopyButtonClick() {
		
	}
	
	@FXML
	public void searchBookButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}
	
	@FXML
	public void showDebtorsButtonClick() {
		
	}
	
	@FXML
	public void logoutButtonClick() {
		System.out.println("nothing to do here dum dum dum");
	}
}
