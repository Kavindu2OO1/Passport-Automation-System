package PASp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class admin_panal_cl {
	
	private String id;
	private String status;
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	
	public void updateStatus(String id,String status) {
		try {
	        
	        // Establish connection with PASdB again
	        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");

	        // Create SQL query to insert data
	        String sqlInsert = "UPDATE Application_Status SET Application_Status = '?' WHERE Application_ID = '?'";
	        PreparedStatement insertStatement = conn2.prepareStatement(sqlInsert);
	        insertStatement.setString(1, id);
	        insertStatement.setString(2, status);
	      
	        
	        
	        

	        // Execute the insert query
	        int rowsInserted = insertStatement.executeUpdate();
	        if (rowsInserted > 0)  {
	            // Retrieve the Application_ID using the National_ID
	            String sqlRetrieveID = "SELECT Application_ID FROM Applications WHERE National_ID = ?";
	            PreparedStatement retrieveIDStatement = conn2.prepareStatement(sqlRetrieveID);
	            retrieveIDStatement.setString(1, id);
	            ResultSet resultSet1 = retrieveIDStatement.executeQuery();
	            if (resultSet1.next()) {
	                int applicationID = resultSet1.getInt("Application_ID");
	                JOptionPane.showMessageDialog(null, "Data inserted successfully! Application ID: " + applicationID);
	            } else {
	                JOptionPane.showMessageDialog(null, "Failed to retrieve Application ID!");
	                
	                
	                
	                
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Failed to insert data!");
	        }

	        // Close the connection to PASdB
	        insertStatement.close();
	        conn2.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, ex);
	    }
		
	}


	public static void main(String[] args) {
		
		

		

	}
	
	
	
	

}
