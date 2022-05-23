package Code;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener{

	private JPanel panel;
	private JLabel nameLabel;
	private JLabel passwdLabel;
	private JTextField nameField;
	private JPasswordField passwdField;
	private JButton loginButton;
	private JButton registerButton;
		
	public LoginPage()
	{
		panel = new JPanel();
		nameLabel = new JLabel("Username:");
		passwdLabel = new JLabel("Password:");
		nameField = new JTextField(12);
		passwdField = new JPasswordField(12);
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(passwdLabel);
		panel.add(passwdField);
		panel.add(registerButton);
		panel.add(loginButton);

		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
		
		this.setContentPane(panel);
		this.setVisible(true);
		this.setTitle("Login");
		this.setSize(300, 150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/start_image.png")).getImage());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(loginButton))
		{
			String username = nameField.getText();
			String password = toString(passwdField.getPassword());
			String.format("windows-1252", password);
			
			ArrayList<String> credentials = new ArrayList<>();
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("account.ser"));
				credentials = (ArrayList<String>)in.readObject();
				if(credentials.get(0) == username && credentials.get(1) == password)
				{
					JOptionPane.showMessageDialog(null, "Your connection has been established!");
				}
				in.close();
				
			}catch(IOException exc1) {
				exc1.printStackTrace();
			}catch(ClassNotFoundException exc2) {
				exc2.printStackTrace();
			}
		}
		
		if(e.getSource().equals(registerButton))
		{
			
		}
	}
	
	//A method that converts an char type array into a String variable.
		public static String toString(char[] a)
	    {
	        String string = new String(a);
	        return string;
	    }
}
