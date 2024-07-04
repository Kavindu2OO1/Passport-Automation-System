package PASp;

import java.io.IOException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class application_class {
	 // Method to save data to the database
     void saveToDatabase(String id, String firstName, String lastName, String otherNames, String gender, String placeOfBirth,
    		 String job, String mobileNumber, String email,String idImagePath, String birthCertificateImagePath, String passportPhotoImagePath) {
    	 try {
    		// Establish connection with PASdB
 	        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/nationaliddb", "root", "1234");
 	        
 	        // Check if the National_ID exists in id_info table in nationaliddb
 	        String sqlCheck = "SELECT ID_Num FROM id_info WHERE ID_Num = ?";
 	        PreparedStatement checkStatement = conn1.prepareStatement(sqlCheck);
 	        checkStatement.setString(1, id);
 	        ResultSet resultSet = checkStatement.executeQuery();
 	        
 	        if (!resultSet.next()) {
 	            JOptionPane.showMessageDialog(null, "Application is not valid. National ID not found.");
 	            
 	            
 	            return; // Exit the method if National ID is not found
 	        }
 	        
 	        // Close the connection to id_info table
 	        checkStatement.close();
 	        conn1.close();
    	        // Establish connection with PASdB
    	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");

    	        // Create SQL query to insert data
    	        String sqlInsert = "INSERT INTO Applications (National_ID, First_Name, Last_Name, Other_Names, Gender,"
    	        		+ " Place_Of_Birth, Job, Mobile_Number, Email, ID_img, Birth_certificate_img, Pas_photo_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	        PreparedStatement insertStatement = conn.prepareStatement(sqlInsert);
    	        insertStatement.setString(1, id);
    	        insertStatement.setString(2, firstName);
    	        insertStatement.setString(3, lastName);
    	        insertStatement.setString(4, otherNames);
    	        insertStatement.setString(5, gender);
    	        insertStatement.setString(6, placeOfBirth);
    	        insertStatement.setString(7, job);
    	        insertStatement.setString(8, mobileNumber);
    	        insertStatement.setString(9, email);
    	        
    	        // Read images from files and convert them to byte arrays
    	        byte[] idImageBytes = Files.readAllBytes(Paths.get(idImagePath));
    	        byte[] birthCertificateImageBytes = Files.readAllBytes(Paths.get(birthCertificateImagePath));
    	        byte[] passportPhotoImageBytes = Files.readAllBytes(Paths.get(passportPhotoImagePath));
    	        
    	        // Set the byte arrays to the prepared statement parameters
    	        insertStatement.setBytes(10, idImageBytes);
    	        insertStatement.setBytes(11, birthCertificateImageBytes);
    	        insertStatement.setBytes(12, passportPhotoImageBytes);

    	        // Execute the insert query
    	        int rowsInserted = insertStatement.executeUpdate();
    	        if (rowsInserted > 0) {
    	            // Retrieve the Application_ID using the National_ID
    	            String sqlRetrieveID = "SELECT Application_ID FROM Applications WHERE National_ID = ?";
    	            PreparedStatement retrieveIDStatement = conn.prepareStatement(sqlRetrieveID);
    	            retrieveIDStatement.setString(1, id);
    	            ResultSet resultSet1 = retrieveIDStatement.executeQuery();
    	            if (resultSet1.next()) {
    	                int applicationID = resultSet1.getInt("Application_ID");
    	                JOptionPane.showMessageDialog(null, "Application  inserted for verification, use the  Application ID: " + applicationID+ "  to check status.");
    	                
						String emailAddress = email;
    			        String messageText = firstName + " your passport appliocation with national ID:" + id + " is successfully registered and waiting for manual verification."
    			        		+ "check your status with application ID"+applicationID+" -Send via PAS system" ;
    					
    					
    					 //recivers email
    					 String to = emailAddress;
    					 //senders email
    					 String from = "mailtrap@kavindu.co";
    					 
    					 //provide mailtraps username
    					 final String username = "api";
    					 final String password = "7f301afac224f0bc23d1cb57e0071201";
    					 
    					 //provide mailtraps host address
    					 String host = "live.smtp.mailtrap.io";
    					 
    					 //configure Mailtrap's SMTP server details
    					 
    					 Properties props = new Properties();
    					 props.put("mail.smtp.auth", "true");
    					 props.put("mail.smtp.starttls.enable", "true");
    					 props.put("mail.smtp.host", host);
    					 props.put("mail.smtp.port", "2525");
    					 
    					 // create the session object
    					 
    					 Session session = Session.getInstance(props,
    							 
    							 new Authenticator() {
    						 @Override
    						 protected PasswordAuthentication getPasswordAuthentication() {
    							 return new PasswordAuthentication (username,password);
    						 }
    					 });
    					 
    					 try {
    					 //create a MimeMessage object
    					 Message message =  new MimeMessage(session);
    					 message.setFrom(new InternetAddress(from));
    					 message.setRecipient(Message.RecipientType.TO,  new InternetAddress((to)));
    					 
    					 message.setSubject("PAS Application updates");
    					 message.setText(messageText);
    					 
    					 Transport.send(message);
    					 } catch (MessagingException e1) {
    						 throw new RuntimeException(e1);
    					 }
    					 System.out.println("Email sent successfully");
    					 
    				 	
    					 
    					 
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Failed to retrieve Application ID!");
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Failed to insert data!");
    	        }

    	        // Close the connection to PASdB
    	        insertStatement.close();
    	        conn.close();
    	    } catch (SQLException | IOException ex) {
    	        ex.printStackTrace();
    	        JOptionPane.showMessageDialog(null, ex);
    	    }
    }

}
