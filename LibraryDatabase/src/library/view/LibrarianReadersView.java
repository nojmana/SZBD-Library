package library.view;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.Main;
import library.Reader;
import library.ReadersList;

public class LibrarianReadersView {
	
	@FXML
	private Button backButton;

	@FXML
	private CheckBox debtorsCheckbox;
	
	@FXML
	private TableView<Reader> debtorsTable;
	
	@FXML 
	private TableColumn<Reader, String> idColumn;

	@FXML 
	private TableColumn<Reader, String> nameColumn;
	
	@FXML 
	private TableColumn<Reader, String> surnameColumn;
		
	@FXML 
	private TableColumn<Reader, Float> balanceColumn;
	
    @FXML
    public void initialize() {
    	loadPeople(false);
    }
	
    @FXML
    public void debtorsCheckboxClick() {
    	if (debtorsCheckbox.isSelected()) {
    		loadPeople(true);
    	}
    	else {
    		loadPeople(false);
    	}
    }
    
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianView");
	}
		
	public void loadPeople(boolean ifDebtors) {
		ObservableList<Reader> data = FXCollections.observableArrayList();
		Map<String, Reader> readersMap = null;
		if (ifDebtors) 
			readersMap = new ReadersList().generateDebtorsList();
		else 
			readersMap = new ReadersList().generateReadersList();
		for (Map.Entry<String, Reader> entry : readersMap.entrySet()) {
			data.add(new Reader(entry.getKey(), entry.getValue().getName(), entry.getValue().getSurname(), entry.getValue().getBalance()));
		}
		idColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("surname"));
		balanceColumn.setCellValueFactory(new PropertyValueFactory<Reader, Float>("balance"));
		debtorsTable.setItems(data);
	}
	
}
