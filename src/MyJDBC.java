import java.sql.*;
import java.util.ArrayList;

public class MyJDBC {
	
    public static void main(String[] args) {
        Connection connection = connection();
        //insert(connection, "alem", 1000, "INR");

		printAll();
		
		update("Homer", "Alex", 50.5, "USD");
		
		printAll();
    }
    
    public static Connection connection() {
    	try {
			Connection connection = DriverManager.getConnection(
			        "jdbc:mysql://127.0.0.1:3306/FinanceTracker",
			        "root",
			        "User@123"
			);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public static void printAll() {
    	String query = "SELECT * FROM ACCOUNTS";
        try (Statement statement = connection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println(
                		resultSet.getString("name") + " " +
                		resultSet.getDouble("amount") + " " +
                		resultSet.getString("currency"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static String[] getAllNamesArray() {
    	String query = "SELECT * FROM ACCOUNTS";
    	ArrayList<String> accounts = new ArrayList<>();
        try (Statement statement = connection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                		accounts.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CurrencyDatabase.arrayListToArray(accounts);
    }
    
    public static String[] getAllAccountsArray() {
    	String query = "SELECT * FROM ACCOUNTS";
    	ArrayList<String> accounts = new ArrayList<>();
        try (Statement statement = connection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                		accounts.add(resultSet.getString("name") +
                		": " + resultSet.getDouble("amount") + " " +
                		resultSet.getString("currency"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CurrencyDatabase.arrayListToArray(accounts);
    }
    
    public static String getAmount(String name) {
    	String amount = "";
    	String query = "SELECT amount FROM Accounts WHERE name = ?";
    	PreparedStatement preparedStatement;
        try {
        	preparedStatement = connection().prepareStatement(query);
            preparedStatement.setString(1, name);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	amount += resultSet.getDouble("amount");
            }
        } catch (SQLException e){
    		e.printStackTrace();
    	}
        return amount;
    }
    
    public static void insert(String name, double amount, String currency) {
    	String insert = "INSERT INTO ACCOUNTS(name, amount, currency) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection().prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, currency);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void delete(String name) {
        String delete = "DELETE FROM ACCOUNTS WHERE name = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection().prepareStatement(delete);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void update(String oldName, String newName, double amount, String currency) {
    	updateAmount(oldName, amount);
    	updateCurrency(oldName, currency);
    	updateName(oldName, newName);
    }
    
    private static void updateName(String oldName, String newName) {
    	String update = "UPDATE ACCOUNTS SET name = ? WHERE name = ?";
		PreparedStatement preparedStatement;
        try {
            preparedStatement = connection().prepareStatement(update);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	private static void updateAmount(String name, double newAmount) {
			String update = "UPDATE ACCOUNTS SET amount = ? WHERE name = ?";
			PreparedStatement preparedStatement;
	        try {
	            preparedStatement = connection().prepareStatement(update);
	            preparedStatement.setDouble(1, newAmount);
	            preparedStatement.setString(2, name);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	private static void updateCurrency(String name, String newCurrency) {
		String update = "UPDATE ACCOUNTS SET currency = ? WHERE name = ?";
		PreparedStatement preparedStatement;
        try {
            preparedStatement = connection().prepareStatement(update);
            preparedStatement.setString(1, newCurrency);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
}
