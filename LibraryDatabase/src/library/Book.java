package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Book {

	private String isbn;
	private String title; 
	private String genre;
	private int numberOfCopies;
	private int authorId;
	private String authorName;

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNumberOfCopies() {
		return this.numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public Book(String isbn, String title, String genre, int authorId) {
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.authorId = authorId;
		this.numberOfCopies = 0;
	}
	
	public Book(String isbn, String title, String genre, String authorName) {
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.authorName = authorName;
		this.numberOfCopies = 0;
	}

	public Book() {};
	
	public void addBook () {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			statement.executeUpdate("insert into books(isbn, title, genre, authorId) values (" +
							isbn + ", '" + title + "', '" + genre + "', " + authorId + ");");	
			statement.close();			
		} catch (SQLException e) {
			System.out.println("Adding book exception");
			e.printStackTrace();
		}
	}

	public ArrayList<Book> generateList() {
		ArrayList<Book> booksList = new ArrayList<Book>();
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * from books");
			while (rs.next()) {
				booksList.add(new Book(rs.getString("ISBN"), rs.getString("TITLE"), rs.getString("GENRE"), rs.getInt("AUTHORID")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of books error");
			e.printStackTrace();
		}

		return booksList;
	}
}
