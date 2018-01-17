package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Copy {

	private int copyId;
	private String publisher;
	private int year;
	private String isbn;
	
	
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Copy(int copyId, String publisher, int year, String isbn) {
		this.copyId = copyId;
		this.publisher = publisher;
		this.year = year;
		this.isbn = isbn;
	}
	
	public Copy(String publisher, int year, String isbn) {
		this.publisher = publisher;
		this.year = year;
		this.isbn = isbn;
	}
	
	public Copy() {}
	
	public ArrayList<Copy> generateList(String isbn) {
		ArrayList<Copy> copiesList = new ArrayList<Copy>();
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * from copies");
			while (rs.next()) {
				copiesList.add(new Copy(rs.getInt("copyId"), rs.getString("publisher"), rs.getInt("year"), rs.getString("isbn")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of copies exception");
			e.printStackTrace();
		}
		return copiesList;
	}

	public void deleteCopy (int id, String isbn) {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			statement.executeUpdate("delete from copies where isbn = '" + isbn + "' and copyId = '" + id + "';");	
			statement.close();			
		} catch (SQLException e) {
			System.out.println("Deleting book copy exception");
			e.printStackTrace();
		}
	}
	
	public void addCopy () {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			statement.executeUpdate("insert into copies(publisher, isbn, year) values('" + publisher + "', '" + isbn + "', " + year + ");");	
			statement.close();			
		} catch (SQLException e) {
			System.out.println("Adding book copy exception");
			e.printStackTrace();
		}
	}

}
