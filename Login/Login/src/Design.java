import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Design extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Design frame = new Design();
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
	public Design() {
		setTitle("Log in");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(190, 190, 190));
		panel.setBounds(55, 11, 552, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(181, 131, 164, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(213, 30, 96, 96);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\sofiane f\\Desktop\\icons8-male-user-96.png"));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(181, 221, 164, 30);
		panel.add(lblNewLabel_1_1);
		
		userName = new JTextField();
		userName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(168, 180, 194, 30);
		panel.add(userName);
		userName.setColumns(10);
		
		passWord = new JPasswordField();
		passWord.setHorizontalAlignment(SwingConstants.CENTER);
		passWord.setBounds(168, 262, 194, 30);
		panel.add(passWord);
		
		JButton logBtn = new JButton("Sign in");
		logBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un = userName.getText().trim();
				@SuppressWarnings("deprecation")
				String pw = passWord.getText()	;
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
					// Construct the SQL query
					String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, un);
					stmt.setString(2, pw);

					// Execute the SQL query
					ResultSet rs = stmt.executeQuery();

					// Check if the username and password are valid
					if (rs.next()) {
					    // The user is authenticated, do something
						JOptionPane.showMessageDialog(null, "Succefully logged in ");
						//System.out.print("1");
						//JDialog d = new JDialog(panel, "dialog Box");
						 
			            // create a label
			            //JLabel l = new JLabel("this is a dialog box");
			 
			            //d.add(l);
			 
			            // setsize of dialog
			            //d.setSize(100, 100);
			 
			            // set visibility of dialog
			           // d.setVisible(true);
					} else {
					    
						// The username or password is invalid, show an error message
						System.out.print("2");
					}

					// Close the ResultSet, PreparedStatement, and Connection objects
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		logBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logBtn.setForeground(new Color(0, 0, 0));
		logBtn.setBounds(213, 329, 105, 30);
		panel.add(logBtn);
	}
}
