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
		Main.showOtherViewAnchor("AddBookView");
	}

	@FXML
	public void addCopyButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}

	@FXML
	public void deleteBookButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}
	
	@FXML
	public void deleteCopyButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}
	
	@FXML
	public void searchBookButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}
	
	@FXML
	public void showDebtorsButtonClick() {
		Main.showOtherViewAnchor("ShowDebtorsView");
		ShowDebtorsView debtors = new ShowDebtorsView();
		debtors.showDebtors();
	}
	
	@FXML
	public void logoutButtonClick() {
		Main.showOtherViewBorder("LoginView");
		Main.setLibrarian(false);
	}
}
