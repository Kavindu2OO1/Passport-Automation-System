package PASp;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JRadioButton;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Box;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.io.InputStream;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.SwingConstants;
//import Main.dBConnectionPAS;
import java.sql.Connection;


public class passport_Application_window extends JFrame {
	
	
	private JLabel Ibl_photo2;
	private JLabel Nid_photo;
	private JLabel Bth_certificate;
	
	private JLabel id_txt;
	private JLabel firstname_txt;
	private JLabel lastname_txt;
	private JLabel othernames_txt;
	private JLabel gender;
	private JLabel placeofbirth_txt;
	private JLabel job_txt;
	private JLabel mobilenumber_txt;
	private JLabel email_txt;

	
	/*
	String id = id_txt.getText();
    String firstName = firstname_txt.getText();
    String lastName = lastname_txt.getText();
    String otherNames = othernames_txt.getText();
    String gender = male.isSelected() ? "Male" : "Female"; // Determine selected gender
    String placeOfBirth = placeofbirth_txt.getText();
    String job = job_txt.getText();
    String mobileNumber = mobilenumber_txt.getText();
    String email = email_txt.getText();
    */
	
	
	

    private static final long serialVersionUID = 1L;
	protected static final AbstractButton Ibl_photo = null;
    private JPanel contentPane;
    
    
    private application_class appClass;
    private JRadioButton male;
    private JRadioButton female;
    /*
    String idImagePath = null;
    String passportPhotoImagePath = null;
    String birthCertificateImagePath = null;
    */
    String idImagePath = null;
    String passportPhotoImagePath = null;
    String birthCertificateImagePath = null;
    

    /**
     * Launch the application.\
     */
    
    public class applicant{
    	
    	String firstName; 
    	String email;
    }
    
    
    public static void main(String[] args) {
    	
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	passport_Application_window frame = new passport_Application_window();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    

    /**
     * Create the frame.
     */
    public passport_Application_window() {
    	setBackground(new Color(255, 255, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kavin\\OneDrive\\Pictures\\OIG2.jpeg"));
        setTitle("Passport Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 459);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Passport automation  System");
        lblNewLabel.setFont(new Font("Ebrima", Font.PLAIN, 15));
        lblNewLabel.setBackground(new Color(0, 255, 64));
        lblNewLabel.setBounds(139, 10, 204, 13);
        contentPane.add(lblNewLabel);
        
        LineBorder border = new LineBorder(Color.BLACK, 1);
        
        JTextArea id_txt = new JTextArea();
        id_txt.setBackground(new Color(192, 192, 192));
        id_txt.setBounds(139, 61, 86, 15);
        id_txt.setBorder(border);
        contentPane.add(id_txt);

        JLabel lblNewLabel_1 = new JLabel("National ID Number");
        lblNewLabel_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(11, 59, 124, 13);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("First Name");
        lblNewLabel_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1.setBounds(11, 86, 69, 13);
        contentPane.add(lblNewLabel_1_1);

        JTextArea firstname_txt = new JTextArea();
        firstname_txt.setBackground(new Color(192, 192, 192));
        firstname_txt.setBounds(78, 88, 86, 15);
        firstname_txt.setBorder(border);
        
        contentPane.add(firstname_txt);

        JTextArea lastname_txt = new JTextArea();
        lastname_txt.setBackground(new Color(192, 192, 192));
        lastname_txt.setBounds(241, 88, 86, 15);
        lastname_txt.setBorder(border);
        
        contentPane.add(lastname_txt);

        JLabel lblNewLabel_1_1_1 = new JLabel("Last Name");
        lblNewLabel_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1.setBounds(174, 86, 69, 13);
        contentPane.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Other Names");
        lblNewLabel_1_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1.setBounds(11, 113, 79, 13);
        contentPane.add(lblNewLabel_1_1_1_1);

        JTextArea othernames_txt = new JTextArea();
        othernames_txt.setBackground(new Color(192, 192, 192));
        othernames_txt.setBounds(100, 115, 86, 15);
        contentPane.add(othernames_txt);
        othernames_txt.setBorder(border);

        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Gender");
        lblNewLabel_1_1_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1.setBounds(11, 155, 69, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1);

        JTextArea placeofbirth_txt = new JTextArea();
        placeofbirth_txt.setBackground(new Color(192, 192, 192));
        placeofbirth_txt.setBounds(101, 174, 86, 15);
        placeofbirth_txt.setBorder(border);
        contentPane.add(placeofbirth_txt);

        JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Place Of Birth");
        lblNewLabel_1_1_1_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1.setBounds(11, 176, 101, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1);

        JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Job");
        lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_1.setBounds(11, 196, 69, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_1);

        JTextArea job_txt = new JTextArea();
        job_txt.setBackground(new Color(192, 192, 192));
        job_txt.setBounds(101, 194, 86, 15);
        job_txt.setBorder(border);
        contentPane.add(job_txt);

        JLabel lblNewLabel_1_1_1_1_1_1_2 = new JLabel("Mobile Number");
        lblNewLabel_1_1_1_1_1_1_2.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_2.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_2.setBounds(11, 219, 101, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_2);

        JTextArea mobilenumber_txt = new JTextArea();
        mobilenumber_txt.setBackground(new Color(192, 192, 192));
        mobilenumber_txt.setBounds(111, 219, 86, 15);
        mobilenumber_txt.setBorder(border);
        contentPane.add(mobilenumber_txt);

        JLabel lblNewLabel_1_1_1_1_1_1_3 = new JLabel("Email");
        lblNewLabel_1_1_1_1_1_1_3.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3.setBounds(11, 242, 69, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3);

        JTextArea email_txt = new JTextArea();
        email_txt.setBackground(new Color(192, 192, 192));
        email_txt.setBounds(101, 240, 175, 15);
        email_txt.setBorder(border);
        contentPane.add(email_txt);

        // Button to save the data to the database
        JButton btnSave = new JButton("Apply");
        btnSave.setForeground(new Color(0, 0, 0));
        btnSave.setFont(new Font("Ebrima", Font.PLAIN, 13));
        btnSave.setBackground(new Color(255, 255, 255));
        btnSave.addActionListener(new ActionListener() {
            
        	
        	
        	
        	public void actionPerformed(ActionEvent e) {
            	
            	String id = id_txt.getText();
                String firstName = firstname_txt.getText();
                String lastName = lastname_txt.getText();
                String otherNames = othernames_txt.getText();
                String gender = male.isSelected() ? "Male" : "Female"; // Determine selected gender
                String placeOfBirth = placeofbirth_txt.getText();
                String job = job_txt.getText();
                String mobileNumber = mobilenumber_txt.getText();
                String email = email_txt.getText();
                
                
                ApplicantObj ob = new ApplicantObj(id, firstName, lastName, otherNames, gender, placeOfBirth, job, mobileNumber, email,idImagePath,birthCertificateImagePath,passportPhotoImagePath);
                
                ob.saveToDatabase(id, firstName, lastName, otherNames, gender, placeOfBirth, job, mobileNumber, email,idImagePath,birthCertificateImagePath,passportPhotoImagePath);

             
			    //ob.saveToDatabase(id, firstName, lastName, otherNames, gender, placeOfBirth, job, mobileNumber, email,idImagePath,birthCertificateImagePath,passportPhotoImagePath);
                
                //appClass.saveToDatabase(id, firstName, lastName, otherNames, gender, placeOfBirth, job, mobileNumber, email,idImagePath,birthCertificateImagePath,passportPhotoImagePath);
                
                
                
            }
        });
        btnSave.setBounds(78, 321, 86, 23);
        contentPane.add(btnSave);
        
        ButtonGroup genderButtonGroup = new ButtonGroup();
        
        male = new JRadioButton("male");
        male.setFont(new Font("Ebrima", Font.PLAIN, 13));
        male.setBackground(new Color(255, 255, 255));
        male.setBounds(100, 149, 59, 21);
        contentPane.add(male);
        genderButtonGroup.add(male);

        female = new JRadioButton("female");
        female.setFont(new Font("Ebrima", Font.PLAIN, 13));
        female.setBackground(new Color(255, 255, 255));
        female.setBounds(154, 151, 103, 21);
        contentPane.add(female);
        genderButtonGroup.add(female);
        
        JButton sts = new JButton("Check Application Status");
        sts.setForeground(new Color(0, 0, 0));
        sts.setFont(new Font("Ebrima", Font.PLAIN, 13));
        sts.setBackground(new Color(255, 255, 255));
        sts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Status button clicked");

                // Instantiate passport_Application_window
                JFrame applicationWin = new passport_Application_window();

                // Close the passport_Application_window
                if (applicationWin != null) {
                    System.out.println("applicationWin is not null");
                    applicationWin.dispose();
                    System.out.println("applicationWin disposed");
                } /*else {
                    System.out.println("applicationWin is null");
                }*/

                // Open the Application_Status_Window
                JFrame applicationstatus = new Application_Status_Window();
                applicationstatus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                applicationstatus.setVisible(true);
            }
        });


        sts.setBounds(223, 321, 169, 23);
        contentPane.add(sts);
        
        JButton btnSave_1_1 = new JButton("Home page");
        btnSave_1_1.addActionListener(new ActionListener() {
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
        btnSave_1_1.setForeground(new Color(0, 0, 0));
        btnSave_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        btnSave_1_1.setBackground(new Color(255, 255, 255));
        btnSave_1_1.setBounds(435, 321, 108, 23);
        contentPane.add(btnSave_1_1);
        
        JLabel lblNewLabel_1_1_1_1_1_1_3_1 = new JLabel("Passport photo");
        lblNewLabel_1_1_1_1_1_1_3_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3_1.setBounds(327, 249, 103, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3_1);
        
        JButton btnBrowse = new JButton("Browse");
        btnBrowse.setFont(new Font("Ebrima", Font.PLAIN, 13));
        btnBrowse.setBackground(new Color(255, 255, 255));
        btnBrowse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		JFileChooser chooser = new JFileChooser();
        		chooser.showOpenDialog(null);
        		File f = chooser.getSelectedFile();
        		String path = f.getAbsolutePath();
        		try {
					BufferedImage bi = ImageIO.read(new File(path));
					Image img = bi.getScaledInstance(134, 172, Image.SCALE_SMOOTH);
					ImageIcon icon = new ImageIcon(img);
					Ibl_photo2.setIcon(icon);
					idImagePath=path;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnBrowse.setBounds(425, 244, 86, 23);
        contentPane.add(btnBrowse);
        
        JLabel lblNewLabel_1_1_1_1_1_1_3_1_1 = new JLabel("National ID");
        lblNewLabel_1_1_1_1_1_1_3_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3_1_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3_1_1.setBounds(534, 249, 69, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3_1_1);
        
        JButton ntBrowse = new JButton("Browse");
        ntBrowse.setFont(new Font("Ebrima", Font.PLAIN, 13));
        ntBrowse.setBackground(new Color(255, 255, 255));
        ntBrowse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFileChooser chooser = new JFileChooser();
        		chooser.showOpenDialog(null);
        		File f = chooser.getSelectedFile();
        		String path = f.getAbsolutePath();
        		try {
					BufferedImage bi = ImageIO.read(new File(path));
					Image img = bi.getScaledInstance(134, 172, Image.SCALE_SMOOTH);
					ImageIcon icon = new ImageIcon(img);
					Nid_photo.setIcon(icon);
					passportPhotoImagePath=path;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        ntBrowse.setBounds(613, 244, 86, 23);
        contentPane.add(ntBrowse);
        
        JLabel lblNewLabel_1_1_1_1_1_1_3_1_2 = new JLabel("Birth certificate");
        lblNewLabel_1_1_1_1_1_1_3_1_2.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3_1_2.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3_1_2.setBounds(714, 249, 86, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3_1_2);
        
        JButton Btbrowse = new JButton("Browse");
        Btbrowse.setFont(new Font("Ebrima", Font.PLAIN, 13));
        Btbrowse.setBackground(new Color(255, 255, 255));
        Btbrowse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFileChooser chooser = new JFileChooser();
        		chooser.showOpenDialog(null);
        		File f = chooser.getSelectedFile();
        		String path = f.getAbsolutePath();
        		try {
					BufferedImage bi = ImageIO.read(new File(path));
					Image img = bi.getScaledInstance(134, 172, Image.SCALE_SMOOTH);
					ImageIcon icon = new ImageIcon(img);
					Bth_certificate.setIcon(icon);
					birthCertificateImagePath=path;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        	}
        });
        Btbrowse.setBounds(810, 244, 86, 23);
        contentPane.add(Btbrowse);
        
     // Add this line as an instance variable of your class
        

        // In your constructor, initialize Ibl_photo2
        

        LineBorder border1 = new LineBorder(Color.BLACK, 2);
        // In your constructor, initialize Ibl_photo2
        Ibl_photo2 = new JLabel("");
        Ibl_photo2.setForeground(new Color(0, 0, 0));
        Ibl_photo2.setBackground(new Color(0, 255, 64));
        Ibl_photo2.setBounds(361, 33, 134, 172);
        Ibl_photo2.setBorder(border1);
        contentPane.add(Ibl_photo2);
        
        
        
        Nid_photo = new JLabel("");
        Nid_photo.setForeground(new Color(0, 0, 0));
        Nid_photo.setBackground(new Color(0, 255, 64));
        Nid_photo.setBounds(548, 33, 134, 172);
        Nid_photo.setBorder(border1);
        contentPane.add(Nid_photo);
        
        
        
        //JLabel Bth_certificate = new JLabel("");
        //Bth_certificate.setBounds(674, 34, 134, 172);
        //contentPane.add(Bth_certificate);
        
        Bth_certificate = new JLabel("");
        Bth_certificate.setForeground(new Color(0, 0, 0));
        Bth_certificate.setBackground(new Color(0, 255, 64));
        Bth_certificate.setBounds(717, 33, 134, 172);
        Bth_certificate.setBorder(border1);
        contentPane.add(Bth_certificate);
        
        JLabel lblNewLabel_1_1_1_1_1_1_3_1_3 = new JLabel("National ID");
        lblNewLabel_1_1_1_1_1_1_3_1_3.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3_1_3.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3_1_3.setBounds(383, 220, 92, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3_1_3);
        
        JLabel lblNewLabel_1_1_1_1_1_1_3_1_3_1 = new JLabel("Birth Cirtificate");
        lblNewLabel_1_1_1_1_1_1_3_1_3_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3_1_3_1.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3_1_3_1.setBounds(577, 220, 86, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3_1_3_1);
        
        JLabel lblNewLabel_1_1_1_1_1_1_3_1_3_2 = new JLabel("Passport Photo");
        lblNewLabel_1_1_1_1_1_1_3_1_3_2.setFont(new Font("Ebrima", Font.PLAIN, 13));
        lblNewLabel_1_1_1_1_1_1_3_1_3_2.setBackground(new Color(0, 255, 64));
        lblNewLabel_1_1_1_1_1_1_3_1_3_2.setBounds(747, 220, 84, 13);
        contentPane.add(lblNewLabel_1_1_1_1_1_1_3_1_3_2);

        
        
        
        appClass = new application_class();
    }
}
