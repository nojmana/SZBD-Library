package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import library.Main;

public class ShowDebtorsView {
	
	@FXML
	private Button backButton;

	@FXML 
	private TableColumn idColumnField;

	@FXML 
	private TableColumn nameColumnField;
	
	@FXML 
	private TableColumn surnameColumnField;
		
	@FXML 
	private TableColumn titleColumnField;
	
	@FXML 
	private TableColumn authorColumnField;
	
	@FXML 
	private TableColumn daysColumnField;
	
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianView");
	}
}
