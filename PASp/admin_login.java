package PASp;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class admin_login extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username_txt;
	private JTextField password_txt;
	private JPasswordField passwordField;
	
	private void setloginpannel() {
		
        getContentPane().removeAll();

        
        admin_login adminLoginPanel = new admin_login();
        adminLoginPanel.setSize(300, 200); 


        
        getContentPane().setLayout(null);

        
        getContentPane().add(adminLoginPanel);

        
        validate();
        repaint();
        
		
	}

	private Container getContentPane() {
		
		return null;
	}

	
	public static void main(String atgs[]) {
		
		
		
		
		
	}
	
	

	public JTextField getUsername_txt() {
		return username_txt;
	}



	public void setUsername_txt(JTextField username_txt) {
		this.username_txt = username_txt;
	}



	public JTextField getPassword_txt() {
		return password_txt;
	}



	public void setPassword_txt(JTextField password_txt) {
		this.password_txt = password_txt;
	}



	public admin_login() {
		setLayout(null);
		

		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Ebrima", Font.PLAIN, 18));
		lblUserName.setBounds(381, 117, 111, 26);
		add(lblUserName);
		
		username_txt = new JTextField();
		username_txt.setColumns(10);
		username_txt.setBounds(481, 124, 96, 22);
		add(username_txt);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Ebrima", Font.PLAIN, 18));
		lblPassword.setBounds(381, 160, 111, 26);
		add(lblPassword);
		
		
		
		JButton btnNewButton = new JButton("log in");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Ebrima", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String username = username_txt.getText();
			        String password = password_txt.getText();
			        
			        // Check if username and password are valid 
			        if (isValid(username, password)) {
			            // Login successful
			        	username_txt.setText("");
	                    password_txt.setText("");
			            JOptionPane.showMessageDialog(null, "Log in Successfull");
			         // Remove the components from the admin_login panel
			            removeAll();

			            
			            admin_panal adminPagePanel = new admin_panal();
			            adminPagePanel.setSize(1510,621);
			            
			        	

			            
			            setLayout(null);

			            // Add the admin_page panel to the frame
			            add(adminPagePanel);

			            
			            getParent().validate();
			            getParent().repaint();
			            
			            
			         
				        
			            
			        } else {
			            // Login failed
			        	username_txt.setText("");
	                    password_txt.setText("");
			        	JOptionPane.showMessageDialog(null, "Username or Password Incorrect");
			        	
			            
			        }
				
				
			}
		});
		
		
		btnNewButton.setBounds(369, 245, 102, 41);
		add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Ebrima", Font.PLAIN, 13));
		
		btnBack.setFont(new Font("Ebrima", Font.PLAIN, 18));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Syadpage.dispose();stem.exit(0);
				
				admin_login adpage = new admin_login();
				
				
				home_page hmpage1 = new home_page();
				hmpage1.setVisible(false);
				adpage.setVisible(false);
				//Thread. sleep(10);
				hmpage1.setVisible(true);
			}
		});
		btnBack.setBounds(510, 245, 111, 41);
		add(btnBack);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Ebrima", Font.PLAIN, 28));
		lblAdminLogin.setBounds(413, 10, 163, 39);
		add(lblAdminLogin);
		
		password_txt = new JPasswordField();
		password_txt.setBounds(481, 168, 96, 19);
		add(password_txt);

	}
	
	
	
	
	private boolean isValid(String username, String password) {
	    // Replace this with your actual validation logic
	    return username.equals("kavindu") && password.equals("1234");
	}
}

