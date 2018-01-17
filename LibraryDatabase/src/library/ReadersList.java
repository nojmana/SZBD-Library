package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ReadersList {

	private Map<String, Reader> readersMap;
	
	public Map<String, Reader> getReadersMap() {
		return this.readersMap;
	}
	
	public Map<String, Reader> generateDebtorsList() {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select idNumber, name, surname, balance from readers where balance<0");	
			this.readersMap = new HashMap<String, Reader>();
			while (rs.next()) {
				Reader reader = new Reader(rs.getString("idNumber"), rs.getString("NAME"), rs.getString("SURNAME"), rs.getFloat("BALANCE"));
				readersMap.put(rs.getString("idNumber"), reader);
			}
			
		} catch (SQLException e) {
			System.out.println("Generating debtors list exception");
			e.printStackTrace();
		}
		return this.readersMap;
	}

	public Map<String, Reader> generateReadersList() {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select idNumber, name, surname, balance from readers");	
			this.readersMap = new HashMap<String, Reader>();
			while (rs.next()) {
				Reader reader = new Reader(rs.getString("NAME"), rs.getString("SURNAME"), rs.getFloat("BALANCE"));
				readersMap.put(rs.getString("idNumber"), reader);
			}
			
		} catch (SQLException e) {
			System.out.println("Generating readers list exception");
			e.printStackTrace();
		}
		return this.readersMap;
	}
	
}
