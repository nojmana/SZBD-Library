package library.view;

import java.sql.Date;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import library.Author;
import library.Book;
import library.Borrowing;
import library.Copy;
import library.Main;

public class UserAccountView {
		
	@FXML
	private Button backButton;
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private Text balanceLabel;
	
	@FXML
	private Text warningLabel;
	
	@FXML
	private Text balanceAmountLabel;
	
	@FXML
	private TextField idTextfield;
	
	@FXML
	private TableView<Borrowing> borrowingsTable;

	@FXML
	private TableColumn<Borrowing, String> titleColumn;
	
	@FXML
	private TableColumn<Borrowing, String> authorColumn;

	@FXML
	private TableColumn<Borrowing, String> isbnColumn;

	@FXML
	private TableColumn<Borrowing, Integer> copyIdColumn;
	
	@FXML
	private TableColumn<Borrowing, Date> dateFirstColumn;
	
	@FXML
	private TableColumn<Borrowing, Date> dateSecondColumn;
	
	@FXML
	private TableColumn<Borrowing, Integer> daysColumn;
	
	@FXML
	public void handleEnterPressed(KeyEvent event) {
	    if (event.getCode() == KeyCode.ENTER) {
	    	confirmButtonClick();
	    }
	}
	
	@FXML
	public void confirmButtonClick() {
		if (idTextfield.getText().matches("[0-9]+") && idTextfield.getText().length() == 11 ) {
			warningLabel.setVisible(false);
			idTextfield.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			ArrayList<Borrowing> borrowingList = new Borrowing().generateList(idTextfield.getText());
			ObservableList<Borrowing> data = FXCollections.observableArrayList();
				
			ArrayList<Copy> copiesList = new Copy().generateListAll();
				
			for (Borrowing i: borrowingList) {				
				for (Copy j: copiesList) {
					if (j.getCopyId() == i.getCopyId()) {
						Book book = new Book().returnBookAndAuthorFromIsbn(j.getIsbn());
						data.add(new Borrowing(book.getTitle(), book.getAuthorName(), book.getIsbn(), i.getCopyId(), i.getDateFirst(), i.getDateSecond(), i.getDaysAmount()));
					}
				}
			} //author.getSurname().toUpperCase() + " " + author.getName().toUpperCase()
			titleColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, String>("title"));
			authorColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, String>("authorName"));
			isbnColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, String>("isbn"));
			copyIdColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, Integer>("copyId"));
			dateFirstColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, Date>("dateFirst"));
			dateSecondColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, Date>("dateSecond"));
			daysColumn.setCellValueFactory(new PropertyValueFactory<Borrowing, Integer>("daysAmount"));
			borrowingsTable.setItems(data);
			
		}
		else {
			warningLabel.setVisible(true);
			idTextfield.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			warningLabel.setText("PESEL powinien składać się z 11 cyfr.");
		}
	}
	
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("UserView");
	}
	

	
}
