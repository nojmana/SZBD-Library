package library.view;

import java.util.ArrayList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import library.Author;
import library.AuthorsList;
import library.Book;
import library.Main;

public class SearchBookView {
	
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
	private ButtonBar buttonBar;
	
	@FXML
	private TableView<Book> booksTable;
		
	@FXML
	private TableColumn<Book,String> titleColumn;
	
	@FXML
	private TableColumn<Book,String> authorColumn;
	
	@FXML
	private TableColumn<Book,String> isbnColumn;
	
	@FXML
	private TableColumn<Book,String> genreColumn;

	
	@FXML 
	public void searchButtonClick() {
		if (titleTextfield.getText().length() == 0 && authorTextfield.getText().length() == 0 && isbnTextfield.getText().length() == 0) {
			ArrayList<Book> booksList = new Book().generateList();
			ObservableList<Book> data = FXCollections.observableArrayList();
			
			AuthorsList authors = new AuthorsList();
			authors.generateAuthorsList();
			Map<Integer, Author> authorsMap = authors.getAuthorsMap();
			
			for (Book i: booksList) {
				Author author = authorsMap.get(i.getAuthorId());
				data.add(new Book(i.getIsbn(), i.getTitle(), i.getGenre(), author.getSurname().toUpperCase() + " " + author.getName().toUpperCase()));
			}
			isbnColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("isbn"));
			titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
			genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
			authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("authorName"));
			booksTable.setItems(data);
		}
		titleTextfield.clear();
		authorTextfield.clear();
		isbnTextfield.clear();
	}
	
	@FXML
	public void backButtonClick() {
		if (Main.isLibrarian())
			Main.showOtherViewBorder("LibrarianView");
		else if (Main.isUser())
			Main.showOtherViewBorder("UserView");
	}
}
