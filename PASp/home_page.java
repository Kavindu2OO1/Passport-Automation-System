package PASp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

public class home_page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		
		
		//end
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home_page frame = new home_page();
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
	public home_page() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kavin\\OneDrive\\Pictures\\OIG2.jpeg"));
		setTitle("Passport Automation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1037, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Passport automation  System");
		lblNewLabel.setFont(new Font("Ebrima", Font.PLAIN, 28));
		
		
		
		
		
		JButton btnNewButton = new JButton("Apply For Passport");
		btnNewButton.setFont(new Font("Ebrima", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				openPassportApplicationWindow();
			}
		});
		btnNewButton.setToolTipText("Application");
		
		JButton btnCheckPassportStatus = new JButton("Applications Status");
		btnCheckPassportStatus.setFont(new Font("Ebrima", Font.PLAIN, 18));
		btnCheckPassportStatus.setForeground(new Color(0, 0, 0));
		btnCheckPassportStatus.setBackground(new Color(255, 255, 255));
		btnCheckPassportStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Application_Status_Window().setVisible(true);
				
			}
		});
		btnCheckPassportStatus.setToolTipText("Application");
		
		JButton btnAdminLogin = new JButton("Admin LogIn");
		btnAdminLogin.setFont(new Font("Ebrima", Font.PLAIN, 18));
		btnAdminLogin.setForeground(new Color(0, 0, 0));
		btnAdminLogin.setBackground(new Color(255, 255, 255));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Remove the content pane of home_page
		        getContentPane().removeAll();

		        // Create an instance of admin_login panel
		        home_page adminLoginPanel2 = new home_page();
		        adminLoginPanel2.setSize(1920, 1080);
		        
		        admin_login adminLoginPanel = new admin_login();
		        adminLoginPanel.setSize(1000, 1000); // Set an appropriate size


		        // Set the layout of the content pane to null
		        getContentPane().setLayout(null);

		        // Add the admin_login panel to the frame
		        getContentPane().add(adminLoginPanel);

		        // Validate and repaint the frame to reflect the changes
		        validate();
		        repaint();
		        
	
			}
		});
		btnAdminLogin.setToolTipText("Application");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(370)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnAdminLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCheckPassportStatus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(287)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(322, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(134)
					.addComponent(btnNewButton)
					.addGap(33)
					.addComponent(btnCheckPassportStatus)
					.addGap(35)
					.addComponent(btnAdminLogin)
					.addContainerGap(262, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	
	// Method to open the passport application window
    private static void openPassportApplicationWindow() {
        // Create an instance of your existing passport application window
    	
        JFrame passportFrame = new passport_Application_window();
        JFrame Home = new home_page();
        passportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        passportFrame.setVisible(true);
        Home.setVisible(false);
    }
}
