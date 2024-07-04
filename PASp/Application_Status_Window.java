package PASp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class Application_Status_Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id_txt;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application_Status_Window frame = new Application_Status_Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Application_Status_Window() {
		setTitle("Application Status");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kavin\\OneDrive\\Pictures\\OIG2.jpeg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Apllication Status");
		lblNewLabel.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblNewLabel.setBounds(316, 10, 111, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblApplicationId = new JLabel("Application id");
		lblApplicationId.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblApplicationId.setBounds(102, 58, 111, 26);
		contentPane.add(lblApplicationId);
		
		id_txt = new JTextField();
		id_txt.setBounds(190, 62, 96, 19);
		contentPane.add(id_txt);
		id_txt.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Application ID", "New column", "New column"
			}
		));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBackground(new Color(192, 192, 192));
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(79, 175, 587, 54);
		contentPane.add(table);
		
		

		

		
		JButton search_btn = new JButton("Search");
		search_btn.setFont(new Font("Ebrima", Font.PLAIN, 18));
		search_btn.setForeground(new Color(0, 0, 0));
		search_btn.setBackground(new Color(255, 255, 255));
		search_btn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = new DefaultTableModel();
		        try {
		            String searchText = id_txt.getText();
		            Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASdB", "root", "1234");
		            String sql = "SELECT * FROM Application_Status WHERE Application_ID = ?";
		            PreparedStatement pstmt = conn2.prepareStatement(sql);
		            pstmt.setString(1, searchText);
		            ResultSet rs = pstmt.executeQuery();
		            if (!rs.isBeforeFirst()) { 
		                id_txt.setText("");
		                JOptionPane.showMessageDialog(null, "No records found for the provided ID.");
		            }

		            
		            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
		            int columnCount = metaData.getColumnCount();

		         

		         
		         Vector<String> columnIdentifiers = new Vector<>();
		         for (int column = 1; column <= columnCount; column++) {
		             columnIdentifiers.add(metaData.getColumnLabel(column));
		         }
		         model.setColumnIdentifiers(columnIdentifiers);

		         
		         while (rs.next()) {
		             Vector<Object> row = new Vector<>();
		             for (int i = 0; i < columnCount; i++) {
		                 row.add(rs.getObject(i + 1));
		             }
		             model.addRow(row);
		         }

		         
		         table.setModel(model);

		         
		         
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "ID not found." + ex);
		            ex.printStackTrace();
		        }
		    }
		});


		search_btn.setBounds(190, 96, 97, 21);
		contentPane.add(search_btn);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblId.setBounds(116, 150, 111, 26);
		contentPane.add(lblId);
		
		JLabel lblApplicationId_1 = new JLabel("Application ID");
		lblApplicationId_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblApplicationId_1.setBounds(258, 150, 111, 26);
		contentPane.add(lblApplicationId_1);
		
		JLabel lblApplicationId_1_1 = new JLabel("Status");
		lblApplicationId_1_1.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblApplicationId_1_1.setBounds(381, 150, 111, 26);
		contentPane.add(lblApplicationId_1_1);
		
		JLabel lblApplicationId_1_2 = new JLabel("Appointment date");
		lblApplicationId_1_2.setFont(new Font("Ebrima", Font.PLAIN, 13));
		lblApplicationId_1_2.setBounds(524, 150, 111, 26);
		contentPane.add(lblApplicationId_1_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 207, 2, 2);
		contentPane.add(scrollPane);
		
		JButton search_btn_1 = new JButton("Home Page");
		search_btn_1.setFont(new Font("Ebrima", Font.PLAIN, 18));
		search_btn_1.setForeground(new Color(0, 0, 0));
		search_btn_1.setBackground(new Color(255, 255, 255));
		
		search_btn_1.addActionListener(new ActionListener() {
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
		search_btn_1.setBounds(297, 96, 142, 21);
		contentPane.add(search_btn_1);
	}
	
	
	 
    private void fetchAndDisplayData() {
    	
    }
}
