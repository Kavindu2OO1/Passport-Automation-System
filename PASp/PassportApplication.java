package PASp;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class PassportApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtfirstname;
	private JTextField txtphone;
	private JTextField txtdob;
	private JTextField txtaddress;
	private JTextField txtlastname;
	private JTextField txtemail;
	private JTextField txtNIC;
	private JTextField txtoccupation;
	private JLabel lblPhoto_1;
	private JLabel lblPhoto_2;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassportApplication frame = new PassportApplication();
					frame.setVisible(true);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PassportApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 597, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhone = new JLabel("Contact No.");
		lblPhone.setBounds(10, 167, 73, 14);
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 244, 62, 14);
		contentPane.add(lblAddress);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 61, 73, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(10, 130, 62, 14);
		contentPane.add(lblDob);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 203, 62, 14);
		contentPane.add(lblEmail);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(259, 35, 46, 14);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		rdbtnMale.setBounds(345, 31, 62, 23);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		rdbtnFemale.setBounds(427, 31, 73, 23);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 99, 73, 14);
		contentPane.add(lblLastName);
		
		JLabel lblUploadDocs = new JLabel("Upload Images");
		lblUploadDocs.setBounds(259, 115, 86, 14);
		contentPane.add(lblUploadDocs);
		
		txtfirstname = new JTextField();
		txtfirstname.setBounds(93, 58, 123, 20);
		contentPane.add(txtfirstname);
		txtfirstname.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setBounds(92, 164, 123, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtdob = new JTextField();
		txtdob.setBounds(92, 127, 123, 20);
		contentPane.add(txtdob);
		txtdob.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setBounds(92, 244, 123, 58);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		txtlastname = new JTextField();
		txtlastname.setBounds(93, 96, 122, 20);
		contentPane.add(txtlastname);
		txtlastname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(92, 200, 123, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nic = txtNIC.getText();
				String fname = txtfirstname.getText();
				String lname = txtlastname.getText();
				String dob = txtdob.getText();
				String phone = txtphone.getText();
				String email = txtemail.getText();
				String address = txtaddress.getText();
				
				String gender = "";
				if(rdbtnMale.isSelected()) {
					gender = "Male";
				}
				if(rdbtnFemale.isSelected()) {
					gender = "Female";
				}
				String occupation  = txtoccupation.getText();
				
				
				Applicant ap = new Applicant(nic, fname, lname, dob, phone, email, address, gender,occupation);
				ap.submitApplication();
			}
		});
		btnSubmit.setBounds(61, 335, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNIC.setText("");
				txtfirstname.setText("");
				txtlastname.setText("");
				txtdob.setText("");
				txtphone.setText("");
				txtemail.setText("");
				txtaddress.setText("");
				txtoccupation.setText("");
				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(false);
			}
		});
		btnClear.setBounds(250, 335, 89, 23);
		contentPane.add(btnClear);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ApplicantDashboard applDashboardFrame = new ApplicantDashboard();
				applDashboardFrame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(439, 335, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNIC = new JLabel("NIC");
		lblNIC.setBounds(10, 30, 46, 14);
		contentPane.add(lblNIC);
		
		txtNIC = new JTextField();
		txtNIC.setBounds(92, 27, 123, 20);
		contentPane.add(txtNIC);
		txtNIC.setColumns(10);
		
		JButton btnUploadPP = new JButton("Upload");
		btnUploadPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				String path = file.getAbsolutePath();
				        try {
				            BufferedImage bi = ImageIO.read(new File(path));
				            Image img = bi.getScaledInstance(107, 130, Image.SCALE_SMOOTH);
				            ImageIcon icon = new ImageIcon(img);
				            lblPhoto_2.setIcon(icon);    
				        } 
				        catch (IOException e1) {
				            e1.printStackTrace();
				        }	    
			}
		});
		btnUploadPP.setBounds(345, 138, 89, 23);
		contentPane.add(btnUploadPP);
		
		JButton btnUploadNICBC = new JButton("Upload");
		btnUploadNICBC.addActionListener(new ActionListener() {
			

			

			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File file = chooser.getSelectedFile();
				if (file != null) { // Ensure a file is selected
		            try {
		                FileInputStream fis = new FileInputStream(file);
		                ByteArrayOutputStream baos = new ByteArrayOutputStream();
		                byte[] buffer = new byte[1024];
		                int bytesRead;
		                while ((bytesRead = fis.read(buffer)) != -1) {
		                    baos.write(buffer, 0, bytesRead);
		                }
		                String pdfContent = baos.toString("UTF-8"); // Convert byte array to string
		                
		                // Truncate the content to fit within the JTextField
		                int maxLength = 1000; // Maximum length of content to display
		                if (pdfContent.length() > maxLength) {
		                    pdfContent = pdfContent.substring(0, maxLength);
		                }
		                
		                // Set the PDF content to the JTextField
		                lblPhoto_1.setText(pdfContent);
		                
		                // Close the input stream
		                fis.close();
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        }
			}
		});
		btnUploadNICBC.setBounds(475, 138, 89, 23);
		contentPane.add(btnUploadNICBC);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setBounds(356, 115, 62, 14);
		contentPane.add(lblPhoto);
		
		JLabel lblNicAndBc = new JLabel("Documents");
		lblNicAndBc.setBounds(475, 115, 73, 14);
		contentPane.add(lblNicAndBc);
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(259, 74, 86, 14);
		contentPane.add(lblOccupation);
		
		txtoccupation = new JTextField();
		txtoccupation.setColumns(10);
		txtoccupation.setBounds(350, 71, 123, 20);
		contentPane.add(txtoccupation);
		
		lblPhoto_2 = new JLabel("");
		lblPhoto_2.setForeground(new Color(0, 0, 0));
		lblPhoto_2.setBackground(new Color(0, 0, 0));
		lblPhoto_2.setBounds(330,180,120,120);
		contentPane.add(lblPhoto_2);
		
		lblPhoto_1 = new JLabel("");
		lblPhoto_1.setForeground(Color.BLACK);
		lblPhoto_1.setBackground(Color.BLACK);
		lblPhoto_1.setBounds(455, 180, 120, 120);
		contentPane.add(lblPhoto_1);
	}
}





























package pas;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Applicant {
	private Connection conn;
	private String aplNic;
	private String aplPassword;
	private String aplFirstName;
	private String aplLastName;
	private String aplEmail;
	private String aplContact;
	private String aplGender;
	private String aplDOB;
	private String aplAddress;
	private String aplOccupation;
	private String aplPasPhoto;
	private Blob aplNicBc;
	private ByteArrayOutputStream baos;;
	
	public Applicant() {}
	
	public Applicant(String aplNic, String aplFirstName, String aplLastName, String aplDOB, String aplContact, String aplEmail, String aplAddress, String aplGender,String aplOccupation) {
		this.aplNic = aplNic;
		this.aplFirstName = aplFirstName;
		this.aplLastName = aplLastName;
		this.aplDOB = aplDOB;
		this.aplContact = aplContact;
		this.aplEmail = aplEmail;
		this.aplAddress = aplAddress;
		this.aplGender = aplGender;
		this.aplOccupation = aplOccupation;
	}
	
	
	public Applicant(String aplNic,String aplPassword) {
		this.aplNic = aplNic;
		this.aplPassword = aplPassword;
	}

	public void signUpApplicant(String aplNic, String aplPassword) {
		
		DatabaseConnection dbCon = new DatabaseConnection();
		conn = dbCon.createConnection();
		
	    	try {
		        String sql = "INSERT INTO LoginAndSignup (aplNic, aplPassword) VALUES (?,?)";
		        PreparedStatement pstatement = conn.prepareStatement(sql);
		        
		        pstatement.setString(1, aplNic);
		        pstatement.setString(2, aplPassword);
		      
		        int rowsInserted = pstatement.executeUpdate();
		        
		        if (rowsInserted > 0) {
		            JOptionPane.showMessageDialog(null, "Data inserted successfully");
		        } else {
		            JOptionPane.showMessageDialog(null, "Data insertion failed");
		        }
		    } 
	    	catch (SQLException e) {
		        System.out.println("Error: " + e.getMessage());
		    }
	  }
	
	
	public void submitApplication() {
		
		DatabaseConnection dbCon = new DatabaseConnection();
		conn = dbCon.createConnection();
		
		try {
	    	String sql = "INSERT INTO Applicant (aplNic, aplFirstName, aplLastName, aplDOB, aplContact, aplEmail, aplAddress, aplGender, aplOccupation) VALUES (?,?,?,?,?,?,?,?,?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        
	        pstmt.setString(1, aplNic);
	        pstmt.setString(2, aplFirstName);
	        pstmt.setString(3, aplLastName);
	        pstmt.setString(4, aplDOB);
	        pstmt.setString(5, aplContact);
	        pstmt.setString(6, aplEmail);
	        pstmt.setString(7, aplAddress);
	        pstmt.setString(8, aplGender);
	        pstmt.setString(9, aplOccupation);
              
	        int rowsInserted = pstmt.executeUpdate();
	        
	        if (rowsInserted > 0) {
	            JOptionPane.showMessageDialog(null, "Data inserted successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "Data insertion failed");
	        }
	    }
	    catch(SQLException e) {
	    	System.out.println("Error: " + e.getMessage());
	    }
	}
	
	public void uploadDocs(byte[] documentBytes) {
	    DatabaseConnection dbCon = new DatabaseConnection();
	    conn = dbCon.createConnection();

	    try {
	        String sql = "INSERT INTO Applicant (aplNicBc) VALUES (?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);

	        // Set the byte array directly to the Blob column
	        pstmt.setBytes(1, documentBytes);

	        int rowsInserted = pstmt.executeUpdate();

	        if (rowsInserted > 0) {
	            JOptionPane.showMessageDialog(null, "Uploaded successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "Uploading failed");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}

	private void insertImage(byte[] imageData) {
        try {
            String sql = "INSERT INTO Images (image_data) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBytes(1, imageData);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Image uploaded successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to upload image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public void viewStatus(String aplNic){
		DatabaseConnection dbcon = new DatabaseConnection();
	    Connection conn = dbcon.createConnection(); 
	    
	    try {
	    	String sql = "SELECT aplStatus FROM applicantStatus WHERE aplNic = ?";
	        PreparedStatement pstatement = conn.prepareStatement(sql);
	        pstatement.setString(1, aplNic);
	        ResultSet resultSet = pstatement.executeQuery();
	        while (resultSet.next()) {
	        	 String status = resultSet.getString("aplStatus");
	        	 
	        	 JOptionPane.showMessageDialog(null, "Your status is: "+ status);
	        	}
	    }
	    catch(SQLException e) {
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}
	
	public void checkAvailableDates(String aplNic){
		DatabaseConnection dbcon = new DatabaseConnection();
	    Connection conn = dbcon.createConnection(); 
	    
	    try {
	    	String sql = "SELECT aplAppointment FROM AppointmentDetails WHERE aplNic = ?";
	        PreparedStatement pstatement = conn.prepareStatement(sql);
	        pstatement.setString(1, aplNic);
	        ResultSet resultSet = pstatement.executeQuery();
	        while (resultSet.next()) {
	        	 String appointment = resultSet.getString("aplAppointment");
	        	 
	        	 JOptionPane.showMessageDialog(null, "Available Dates: "+ appointment);
	        	}
	    }
	    catch(SQLException e) {
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}
	
	public void updateAppointment(String aplNic, String aplAppointment) {
		
		DatabaseConnection dbcon = new DatabaseConnection();
	    Connection conn = dbcon.createConnection(); 
	    
	    try {
	    	String sql = "UPDATE AppointmentDetails SET aplAppointment = ? WHERE aplNic = ?";
	    	PreparedStatement pstatement = conn.prepareStatement(sql);
	    	
	    	pstatement.setString(1, aplAppointment);
	    	pstatement.setString(2, aplNic); 
	    	
	    	int rowsUpdated = pstatement.executeUpdate();
	    	if (rowsUpdated > 0) {
	    		JOptionPane.showMessageDialog(null, "Date reserved successfully!");
	    	} 
	    	else {
	    		JOptionPane.showMessageDialog(null, "Date reservation Failed!");
	    	}
	    }
	    catch(SQLException e) {
	    	JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}
	
}
