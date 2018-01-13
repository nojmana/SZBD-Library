package library.view;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.Main;
import library.Reader;
import library.ReadersList;

public class ShowDebtorsView {
	
	@FXML
	private Button backButton;

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
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianView");
	}
	
	@FXML
	public void showDebtors() {
		ObservableList<Reader> data = FXCollections.observableArrayList();
		Map<String, Reader> readersMap = new ReadersList().generateReadersList();
		for (Map.Entry<String, Reader> entry : readersMap.entrySet()) {
			data.add(new Reader(entry.getKey(), entry.getValue().getName(), entry.getValue().getSurname(), entry.getValue().getBalance()));
		}
		idColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("idNumber"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Reader,String>("surname"));
		balanceColumn.setCellValueFactory(new PropertyValueFactory<Reader, Float>("balance"));
		debtorsTable.setItems(data);
	}
}
