package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AuthorsList {
	
	private Map<Integer, Author> authorsMap;

	public Map<Integer, Author> getAuthorsMap() {
		return this.authorsMap;
	}

	public void generateAuthorsList() {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select surname, name, authorId from authors");	
			this.authorsMap = new HashMap<Integer, Author>();
			while (rs.next()) {
				Author author = new Author(rs.getString("SURNAME"), rs.getString("NAME"));
				authorsMap.put(rs.getInt("AUTHORID"), author);
			}
			
		} catch (SQLException e) {
			System.out.println("Generating authors list exception");
			e.printStackTrace();
		}
	}
}
