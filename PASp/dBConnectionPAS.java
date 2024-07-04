package PASp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dBConnectionPAS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB","root","1234");
			System.out.println("Success");
		}catch(SQLException e) {
			System.out.println("Connection Failed");
		}
		

	
		
		
	}

}



