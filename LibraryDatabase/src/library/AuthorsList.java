package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorsList {
	private ArrayList<Author> authorsList;

	public ArrayList<Author> getAuthorsList() {
		return authorsList;
	}

	public void generateAuthorsList() {
		authorsList = new ArrayList<Author>();
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select surname, name, authorId from authors");	
			
			while (rs.next()) {
				Author author = new Author(rs.getString("SURNAME"), rs.getString("NAME"), rs.getInt("AUTHORID"));
				authorsList.add(author);
			}
			
		} catch (SQLException e) {
			System.out.println("Adding book exception");
			e.printStackTrace();
		}
	}
}
