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
	
	public Book(String isbn, String title, String genre, int authorId, int numberOfCopies) {
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.authorId = authorId;
		this.numberOfCopies = numberOfCopies;
	}
	
	public Book(String isbn, String title, String genre, String authorName, int numberOfCopies) {
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.authorName = authorName;
		this.numberOfCopies = numberOfCopies;
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
	
	public void deleteBook (String isbn) {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			statement.executeUpdate("delete from copies where isbn = '" + isbn + "';");	
			statement.close();			
		} catch (SQLException e) {
			System.out.println("Deleting copies of book exception");
			e.printStackTrace();
		}
		try {
			statement = Main.getConnection().createStatement();
			statement.executeUpdate("delete from books where isbn = '" + isbn + "';");	
			statement.close();			
		} catch (SQLException e) {
			System.out.println("Deleting book exception");
			e.printStackTrace();
		}
	}

	public ArrayList<Book> generateListAll() {
		ArrayList<Book> booksList = new ArrayList<Book>();
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select books.isbn, title, genre, authorId, count(*) as numberOfCopies from books join copies on books.isbn=copies.isbn;");
			while (rs.next()) {
				booksList.add(new Book(rs.getString("ISBN"), rs.getString("TITLE"), rs.getString("GENRE"), rs.getInt("AUTHORID"), rs.getInt("numberOfCopies")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of books error");
			e.printStackTrace();
		}
		return booksList;
	}
	
	public ArrayList<Book> generateListTitle(String title) {
		ArrayList<Book> booksList = new ArrayList<Book>();
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select books.isbn, title, genre, authorId, count(*) as numberOfCopies from books join copies on books.isbn=copies.isbn where title = '" + title + "'");
			while (rs.next()) {
				booksList.add(new Book(rs.getString("ISBN"), rs.getString("TITLE"), rs.getString("GENRE"), rs.getInt("AUTHORID"), rs.getInt("numberOfCopies")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of books error");
			e.printStackTrace();
		}
		return booksList;
	}
	
	public ArrayList<Book> generateListAuthor(String author) {
		ArrayList<Book> booksList = new ArrayList<Book>();
		Statement statement;
		
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select books.isbn, title, genre, books.authorId, count(*) as numberOfCopies from books "
					+ "join copies on books.isbn=copies.isbn join authors on books.authorId=authors.authorId where authors.surname = '" 
					+ author + "'");
			while (rs.next()) {
				booksList.add(new Book(rs.getString("ISBN"), rs.getString("TITLE"), rs.getString("GENRE"), rs.getInt("AUTHORID"), rs.getInt("numberOfCopies")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of books error");
			e.printStackTrace();
		}
		return booksList;
	}
	
	public ArrayList<Book> generateListTitleAndAuthor(String author, String title) {
		ArrayList<Book> booksList = new ArrayList<Book>();
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select books.isbn, title, genre, books.authorId, count(*) as numberOfCopies from books "
					+ "join copies on books.isbn=copies.isbn join authors on books.authorId=authors.authorId where authors.surname = '" 
					+ author + "' and books.title = '" + title + "'");
			while (rs.next()) {
				booksList.add(new Book(rs.getString("ISBN"), rs.getString("TITLE"), rs.getString("GENRE"), rs.getInt("AUTHORID"), rs.getInt("numberOfCopies")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of books error");
			e.printStackTrace();
		}
		return booksList;
	}
}
