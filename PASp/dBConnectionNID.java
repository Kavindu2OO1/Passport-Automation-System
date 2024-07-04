package PASp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dBConnectionNID {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/nationaliddb", "root", "1234");
			System.out.println("Success");
		}catch(SQLException e) {
			System.out.println("Connection Failed");
		}
		

	
		
		
	
}

	public static Connection getConnection() {
		Connection conn2 = null;
		try {
			conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/nationaliddb", "root", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return conn2;
	}

	
}
