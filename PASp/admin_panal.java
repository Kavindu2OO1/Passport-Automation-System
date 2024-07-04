package PASp;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class admin_panal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable application_table;
	private JTextField applicationid_txt;
	private JTextField statustpdate_txt;
	private JTable status_table;

	private admin_panal_cl adminclass;
	
	private JLabel Ibl_photo2;
	private JLabel Nid_photo;
	private JLabel Bth_certificate;
	private JTextField email_txt;
	private JTextField mailmsg_txt;
	/**
	 * Create the panel.
	 */
	private void fetchAndDisplayData() {
	    DefaultTableModel model = new DefaultTableModel();
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");
	        String sql = "SELECT * FROM Applications"; 
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();

	        // Get result
	        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        // Add columns to the table model
	        for (int column = 1; column <= columnCount; column++) {
	            model.addColumn(metaData.getColumnLabel(column));
	        }

	        // Add rows to the table model
	        while (rs.next()) {
	            Object[] row = new Object[columnCount];
	            for (int i = 0; i < columnCount; i++) {
	                row[i] = rs.getObject(i + 1);
	            }
	            model.addRow(row);
	            
	        
	            
	        }
	        
	        

	        rs.close();
	        pstmt.close();
	        conn.close();

	        // update table
	        application_table.setModel(model);
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	private void fetchAndDisplayDocuments() {
	    String IDd = applicationid_txt.getText();
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");
	        String sql = "SELECT * FROM applications WHERE Application_ID = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, IDd);
	        ResultSet rs = pstmt.executeQuery();

	        
	        if (rs.next()) {
	            
	            Blob image = (Blob) rs.getBlob(12);
	            String path = "C:\\Users\\kavin\\eclipse-workspace\\PAS\\src\\PASp\\documants\\img.jpg";
	            byte [] bytea = image.getBytes(1, (int)image.length());
	            try (FileOutputStream fos = new FileOutputStream(path)) {
					fos.write(bytea);
				}
	            ImageIcon icon = new ImageIcon(bytea);
	            
	            
	            
	            Ibl_photo2.setIcon(icon);
	             
	            //showID photo
	            Blob imageId = (Blob) rs.getBlob(13);
	            String pathid = "C:\\Users\\kavin\\eclipse-workspace\\PAS\\src\\PASp\\documants2\\imgid.jpg";
	            byte [] byteaid = imageId.getBytes(1, (int)imageId.length());
	            try (FileOutputStream fos = new FileOutputStream(pathid)) {
					fos.write(byteaid);
				}
	            ImageIcon iconid = new ImageIcon(byteaid);
	            Image imageid = iconid.getImage().getScaledInstance(134, 172, Image.SCALE_SMOOTH);
	            
	            //Image imageid = iconid.getImage().getScaledInstance(Nid_photo.getWidth(), Nid_photo.getHeight(), Image.SCALE_SMOOTH);
	            
	            Nid_photo.setIcon(iconid);
	            
	            //Nid_photo.setIcon(iconid);
	            
	            //show birth certificate
	            Blob imagebs = (Blob) rs.getBlob(14);
	            String pathbs = "C:\\Users\\kavin\\eclipse-workspace\\PAS\\src\\PASp\\documants2\\imgbs.jpg";
	            byte [] byteabs = imagebs.getBytes(1, (int)imagebs.length());
	            try (FileOutputStream fos = new FileOutputStream(pathbs)) {
					fos.write(byteabs);
				}
	            ImageIcon iconbs = new ImageIcon(byteabs);
	            Bth_certificate.setIcon(iconbs);
	            
	            
	        } else {
	            // error
	            
	            JOptionPane.showMessageDialog(null, "No records found for Application_ID: " + IDd);
	        }

	        rs.close();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


	
	
	private void fetchAndDisplaystatus() {
	    DefaultTableModel model = new DefaultTableModel();
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");
	        String sql = "SELECT * FROM Application_Status"; 
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();

	        
	        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        // update table
	        for (int column = 1; column <= columnCount; column++) {
	            model.addColumn(metaData.getColumnLabel(column));
	        }

	        // Add rows 
	        while (rs.next()) {
	            Object[] row = new Object[columnCount];
	            for (int i = 0; i < columnCount; i++) {
	                row[i] = rs.getObject(i + 1);
	            }
	            model.addRow(row);
	        }

	        rs.close();
	        pstmt.close();
	        conn.close();

	        // set table
	        status_table.setModel(model);
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	

	
	
	public static void main(String args[]) {
		
		
		
		
	}
	
	
	public admin_panal() {
		
		application_table = new JTable();
		application_table.setFillsViewportHeight(true);
		application_table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		application_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, application_table, 26, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, application_table, 195, SpringLayout.NORTH, this);
		setLayout(springLayout);
		add(application_table);
		
		JLabel lblNewLabel = new JLabel("Admin Panal");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 355, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 465, SpringLayout.WEST, this);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblNewLabel);
		
		JLabel lblApplications = new JLabel("Applications");
		springLayout.putConstraint(SpringLayout.NORTH, lblApplications, 29, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblApplications, 24, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblApplications, 134, SpringLayout.WEST, this);
		lblApplications.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblApplications);
		
		JLabel lblSetStatus = new JLabel("Set status");
		springLayout.putConstraint(SpringLayout.NORTH, lblSetStatus, 259, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSetStatus, 24, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblSetStatus, 134, SpringLayout.WEST, this);
		lblSetStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblSetStatus);
		
		applicationid_txt = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, applicationid_txt, 307, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, applicationid_txt, 130, SpringLayout.WEST, this);
		add(applicationid_txt);
		applicationid_txt.setColumns(10);
		
		JLabel lblApplicationId = new JLabel("Application Id");
		springLayout.putConstraint(SpringLayout.NORTH, lblApplicationId, 303, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblApplicationId, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblApplicationId, 120, SpringLayout.WEST, this);
		lblApplicationId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblApplicationId);
		
		JLabel lblSetApplicationStatus = new JLabel("Set Application Status");
		springLayout.putConstraint(SpringLayout.NORTH, lblSetApplicationStatus, 303, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSetApplicationStatus, 250, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblSetApplicationStatus, 420, SpringLayout.WEST, this);
		lblSetApplicationStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblSetApplicationStatus);
		
		statustpdate_txt = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, statustpdate_txt, 307, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, statustpdate_txt, 426, SpringLayout.WEST, this);
		statustpdate_txt.setColumns(10);
		add(statustpdate_txt);
		
		status_table = new JTable();
		springLayout.putConstraint(SpringLayout.NORTH, status_table, 427, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, status_table, 27, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, status_table, 524, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, status_table, 0, SpringLayout.EAST, application_table);
		status_table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(status_table);
		
		JButton update_btn = new JButton("Update status");
		springLayout.putConstraint(SpringLayout.NORTH, update_btn, 562, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, update_btn, 27, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, update_btn, 154, SpringLayout.WEST, this);
		update_btn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String applicationID = applicationid_txt.getText();
		            String newStatus = statustpdate_txt.getText();

		            // Establish database connection
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");

		            // Prepare SQL statement
		            String sql = "UPDATE Application_Status SET Application_Status = ? WHERE Application_ID = ?";
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, newStatus);
		            pstmt.setString(2, applicationID);

		            
		            int rowsUpdated = pstmt.executeUpdate();
		            if (rowsUpdated > 0) {
		                JOptionPane.showMessageDialog(null, "Application Status updated successfully.");
		                // Refresh status table after update
		                fetchAndDisplaystatus();
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to update Application Status. Application ID not found.");
		            }

		            
		            pstmt.close();
		            conn.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		        }
		    }
		});
		add(update_btn);
		
		JButton delete_btn = new JButton("Delete application");
		springLayout.putConstraint(SpringLayout.NORTH, delete_btn, 562, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, delete_btn, 164, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, delete_btn, 317, SpringLayout.WEST, this);
		delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            String applicationID = applicationid_txt.getText();
			            String newStatus = statustpdate_txt.getText();

			            // Establish database connection
			            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");

			         // Prepare SQL statement
			            String sql1 = "DELETE FROM Application_Status WHERE Application_ID= ?";
			            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			            pstmt1.setString(1, applicationID);
			            
			            // Prepare SQL statement
			            String sql = "DELETE FROM Applications WHERE Application_ID= ?";
			            PreparedStatement pstmt = conn.prepareStatement(sql);
			            pstmt.setString(1, applicationID);

			            
			            int rowsUpdated1 = pstmt1.executeUpdate();
			            int rowsUpdated = pstmt.executeUpdate();
			            if (rowsUpdated1 > 0) {
			                JOptionPane.showMessageDialog(null, "Application deleted from status successfully.");
			                
			                fetchAndDisplaystatus();
			            } else {
			                JOptionPane.showMessageDialog(null, "Failed to delete Application from Status");
			            }
			            
			            if (rowsUpdated > 0) {
			                JOptionPane.showMessageDialog(null, "Application deleted successfully.");
			                
			                fetchAndDisplaystatus();
			            } else {
			                JOptionPane.showMessageDialog(null, "Failed to delete Application Status");
			            }

			            
			            pstmt.close();
			            conn.close();
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
			        }
				
				
			}
		});
		add(delete_btn);
		
		JButton btnNewButton = new JButton("Refresh Applications");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 34, SpringLayout.SOUTH, application_table);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 341, SpringLayout.WEST, this);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchAndDisplayData();
				
			}
		});
		add(btnNewButton);
		
		JButton btnRefreshStatus = new JButton("Refresh Status");
		springLayout.putConstraint(SpringLayout.NORTH, btnRefreshStatus, 528, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnRefreshStatus, 293, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnRefreshStatus, 423, SpringLayout.WEST, this);
		btnRefreshStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fetchAndDisplaystatus();
			}
		});
		add(btnRefreshStatus);
		
		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 562, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 627, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 762, SpringLayout.WEST, this);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
admin_login adpage = new admin_login();
				
				
				home_page hmpage1 = new home_page();
				hmpage1.setVisible(false);
				adpage.setVisible(false);
				//Thread. sleep(10);
				hmpage1.setVisible(true);
			}
		});
		add(btnBack);
		
		LineBorder border1 = new LineBorder(Color.BLACK, 2);
        // In your constructor, initialize Ibl_photo2
        
        
		
		Ibl_photo2 = new JLabel("");
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -74, SpringLayout.WEST, Ibl_photo2);
		springLayout.putConstraint(SpringLayout.NORTH, Ibl_photo2, 19, SpringLayout.SOUTH, application_table);
		springLayout.putConstraint(SpringLayout.WEST, Ibl_photo2, 23, SpringLayout.EAST, statustpdate_txt);
        Ibl_photo2.setForeground(new Color(0, 0, 0));
        Ibl_photo2.setBackground(new Color(0, 0, 0));
        Ibl_photo2.setBorder(border1);
        add(Ibl_photo2);
        
        Nid_photo = new JLabel("");
        springLayout.putConstraint(SpringLayout.NORTH, Nid_photo, 19, SpringLayout.SOUTH, application_table);
        springLayout.putConstraint(SpringLayout.WEST, Nid_photo, 703, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, Nid_photo, -235, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, Ibl_photo2, -28, SpringLayout.WEST, Nid_photo);
        springLayout.putConstraint(SpringLayout.EAST, Nid_photo, -680, SpringLayout.EAST, this);
        Nid_photo.setForeground(new Color(0, 0, 0));
        Nid_photo.setBackground(new Color(0, 0, 0));
        Nid_photo.setBorder(border1);
        add(Nid_photo);
        
        
		
        Bth_certificate = new JLabel("");
        springLayout.putConstraint(SpringLayout.NORTH, Bth_certificate, 19, SpringLayout.SOUTH, application_table);
        springLayout.putConstraint(SpringLayout.EAST, application_table, 0, SpringLayout.EAST, Bth_certificate);
        springLayout.putConstraint(SpringLayout.WEST, Bth_certificate, 29, SpringLayout.EAST, Nid_photo);
        springLayout.putConstraint(SpringLayout.EAST, Bth_certificate, -524, SpringLayout.EAST, this);
        Bth_certificate.setForeground(new Color(0, 0, 0));
        Bth_certificate.setBackground(new Color(0, 0, 0));
        Bth_certificate.setBorder(border1);
        add(Bth_certificate);
		
		
		
		JButton btnViewDocumants = new JButton("View Documants");
		springLayout.putConstraint(SpringLayout.NORTH, btnViewDocumants, 562, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnViewDocumants, 335, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnViewDocumants, 465, SpringLayout.WEST, this);
		btnViewDocumants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fetchAndDisplayDocuments();
				
			}
		});
		add(btnViewDocumants);
		
		JLabel lblNewLabel_2 = new JLabel("Ap id");
		springLayout.putConstraint(SpringLayout.NORTH, application_table, 7, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 30, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 75, SpringLayout.WEST, this);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nat ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1, 74, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1, 154, SpringLayout.WEST, this);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("First Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1, 144, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1, 204, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Last Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1_1, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_1, 214, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1_1, 274, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Other Names");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1_2, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_2, 275, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_2_1_1_2_1 = new JLabel("Gender");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1_2_1, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_2_1, 355, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1_2_1, 415, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_2_1_1 = new JLabel("Born Locatoin");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1_2_1_1, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_2_1_1, 410, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1, 484, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_2_1_1);
		
		JLabel lblNewLabel_2_1_1_2_1_1_1 = new JLabel("Occupation");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_2_1_1_1, 6, SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2_1_1_2_1_1_1, -6, SpringLayout.NORTH, application_table);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1_1, 550, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2_1_1_1_1 = new JLabel("Phone Number");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_2_1_1_1_1, 6, SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2_1_1_2_1_1_1_1, -6, SpringLayout.NORTH, application_table);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1_1_1, 647, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_2_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_2_1_1_1_1_1 = new JLabel("Email");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1_2_1_1_1_1_1, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_1_2_1_1_1_1_1, 681, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1_1_1_1, 741, SpringLayout.WEST, this);
		add(lblNewLabel_2_1_1_2_1_1_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_3_1_3 = new JLabel("National ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_1_1_1_3_1_3, 396, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_1_1_1_3_1_3, 565, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1_1_1_1_1_3_1_3, -858, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, Ibl_photo2, -10, SpringLayout.NORTH, lblNewLabel_1_1_1_1_1_1_3_1_3);
		lblNewLabel_1_1_1_1_1_1_3_1_3.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1_1_3_1_3.setBackground(new Color(0, 255, 64));
		add(lblNewLabel_1_1_1_1_1_1_3_1_3);
		
		JLabel lblNewLabel_1_1_1_1_1_1_3_1_3_1 = new JLabel("Birth certificate");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_1_1_1_3_1_3_1, 6, SpringLayout.SOUTH, Nid_photo);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_1_1_1_3_1_3_1, 30, SpringLayout.WEST, Nid_photo);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1_1_1_1_1_3_1_3_1, 0, SpringLayout.EAST, Nid_photo);
		lblNewLabel_1_1_1_1_1_1_3_1_3_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1_1_3_1_3_1.setBackground(new Color(0, 255, 64));
		add(lblNewLabel_1_1_1_1_1_1_3_1_3_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_3_1_3_2 = new JLabel("Passportphoto");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_1_1_1_3_1_3_2, 396, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_1_1_1_3_1_3_2, 57, SpringLayout.EAST, lblNewLabel_1_1_1_1_1_1_3_1_3_1);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1_1_1_1_1_3_1_3_2, -524, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, Bth_certificate, -10, SpringLayout.NORTH, lblNewLabel_1_1_1_1_1_1_3_1_3_2);
		lblNewLabel_1_1_1_1_1_1_3_1_3_2.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1_1_3_1_3_2.setBackground(new Color(0, 255, 64));
		add(lblNewLabel_1_1_1_1_1_1_3_1_3_2);
		
		JButton btnSendEmail = new JButton("Send email");
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String emailAddress = email_txt.getText();
		        String messageText = mailmsg_txt.getText();
				
				
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
				 JOptionPane.showMessageDialog(null, "Email sent successfully");
				 System.out.println("Email sent successfully");
				 
			 	
				 
				 
				 
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSendEmail, 0, SpringLayout.NORTH, update_btn);
		springLayout.putConstraint(SpringLayout.WEST, btnSendEmail, 12, SpringLayout.EAST, btnViewDocumants);
		add(btnSendEmail);
		
		JLabel lblEmail = new JLabel("Email");
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblApplicationId);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEmail, 0, SpringLayout.SOUTH, Ibl_photo2);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblEmail);
		
		email_txt = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, email_txt, 17, SpringLayout.EAST, lblEmail);
		springLayout.putConstraint(SpringLayout.SOUTH, email_txt, 0, SpringLayout.SOUTH, Ibl_photo2);
		springLayout.putConstraint(SpringLayout.EAST, email_txt, 0, SpringLayout.EAST, lblNewLabel_2_1_1);
		email_txt.setColumns(10);
		add(email_txt);
		
		mailmsg_txt = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, mailmsg_txt, 2, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.SOUTH, mailmsg_txt, 0, SpringLayout.SOUTH, lblNewLabel_1_1_1_1_1_1_3_1_3);
		springLayout.putConstraint(SpringLayout.EAST, mailmsg_txt, 0, SpringLayout.EAST, lblNewLabel_2_1_1_2_1_1);
		mailmsg_txt.setColumns(10);
		add(mailmsg_txt);
		
		JLabel lblSetApplicationStatus_1 = new JLabel("Set Application Status");
		lblSetApplicationStatus_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblSetApplicationStatus_1);
		
		JLabel lblMailSubject = new JLabel("Mail text");
		springLayout.putConstraint(SpringLayout.WEST, mailmsg_txt, 18, SpringLayout.EAST, lblMailSubject);
		springLayout.putConstraint(SpringLayout.NORTH, lblMailSubject, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, lblMailSubject, 0, SpringLayout.WEST, lblSetApplicationStatus);
		lblMailSubject.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblMailSubject);

	}
}
