package library.view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.Copy;

public class LibrarianCopiesView {
	
	private String isbn;
	
	@FXML
	private Button closeButton;
	
	@FXML
	private Button deleteCopyButton;
	
	@FXML
	private TableView<Copy> copiesTable;
		
	@FXML
	private TableColumn<Copy, Integer> copiesIdColumn;
	
	@FXML
	private TableColumn<Copy, String> publisherColumn;
	
	@FXML
	private TableColumn<Copy, Integer> yearColumn;
	
	@FXML
	private Text descriptionLabel;
		
	@FXML
	public void initializeSecondTime() {
		deleteCopyButton.setVisible(false);
		descriptionLabel.setText("Wszystkie egzemplarze książki o ISBN " + this.isbn + ". Wybierz, który egzemplarz chcesz usunąć.");
		
		showCopies();
		
		copiesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	handleRowSelected();
		    }
		    if (newSelection == null) {
		    	handleNoRowSelected();
		    }
		});
	}
	
	@FXML
	public void showCopies() {
		ArrayList<Copy> copiesList = null;	
		Copy copy = new Copy();
		copiesList = copy.generateListIsbn(isbn);
		ObservableList<Copy> data = FXCollections.observableArrayList();
			
		for (Copy i: copiesList) {
			data.add(new Copy(i.getCopyId(), i.getPublisher(), i.getYear(), i.getIsbn()));
		}
		copiesIdColumn.setCellValueFactory(new PropertyValueFactory<Copy, Integer>("copyId"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<Copy, Integer>("year"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<Copy, String>("publisher"));
		copiesTable.setItems(data);
	}
	
	@FXML
	public void handleRowSelected() {
		deleteCopyButton.setVisible(true);
	}
	
	@FXML
	public void handleNoRowSelected() {
		deleteCopyButton.setVisible(false);
	}	
	
	@FXML
	public void deleteCopyButtonClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Usuwanie egzemplarza");
		alert.setHeaderText(null);
		alert.setContentText("Czy na pewno chcesz usunąć egzemplarz książki o numerze identyfikacyjnym " + copiesTable.getSelectionModel().getSelectedItem().getCopyId() + "?");
		
		ButtonType buttonTypeOne = new ButtonType("Nie", ButtonData.CANCEL_CLOSE);
		ButtonType buttonTypeTwo = new ButtonType("Tak");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeTwo){
			Copy copy = new Copy();
			copy.deleteCopy(copiesTable.getSelectionModel().getSelectedItem().getCopyId(), copiesTable.getSelectionModel().getSelectedItem().getIsbn());
			initializeSecondTime();
		} else {
		    alert.close();
		}
	}
	
	@FXML
	public void closeButtonClick() {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
