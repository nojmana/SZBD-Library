package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import library.Main;

public class LibrarianView {

	@FXML
	private Button booksViewButton;
	
	@FXML
	private Button showDebtorsButton;
	
	@FXML 
	private Button logoutButton;
	
	@FXML
	public void booksViewButtonClick() {
		Main.showOtherViewBorder("LibrarianBooksMenuView");
	}
	
	@FXML
	public void showDebtorsButtonClick() {
		Main.showOtherViewAnchor("LibrarianReadersView");
	}
	
	@FXML
	public void logoutButtonClick() {
		Main.showOtherViewBorder("LoginView");
		Main.setLibrarian(false);
	}
}
