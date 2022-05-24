package Code;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
//import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener{

	private JPanel generalPanel;
	private JPanel fieldsPanel;
	private JLabel loginLabel;
	private JLabel nameLabel;
	private JLabel passwdLabel;
	private JTextField nameField;
	private JPasswordField passwdField;
	private JButton loginButton;
	private JButton registerButton;
	private JCheckBox checkbox;
	
		
	public LoginPage()
	{
		generalPanel = new JPanel(null);
		fieldsPanel = new JPanel(new GridLayout(2,2));
		loginLabel = new JLabel("Login to your Database");
		nameLabel = new JLabel("Username:");
		passwdLabel = new JLabel("Password:");
		nameField = new JTextField(12);
		passwdField = new JPasswordField(12);
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		checkbox = new JCheckBox();
		checkbox.setBounds(260,15,50,50);
		
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		fieldsPanel.add(passwdLabel);
		fieldsPanel.add(passwdField);
		fieldsPanel.setBounds(15,7,240,45);

		//Adding items to the generalPanel.
		generalPanel.add(loginLabel);
		generalPanel.add(fieldsPanel);
		generalPanel.add(checkbox);
		generalPanel.add(loginButton);
		generalPanel.add(registerButton);
		registerButton.setBounds(50,70,85,25);
		loginButton.setBounds(145,70,85,25);
		
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);
		checkbox.addActionListener(this);
		
		this.setContentPane(generalPanel);
		this.setVisible(true);
		this.setTitle("Login");
		this.setSize(300, 150);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setIconImage(new ImageIcon(this.getClass().getResource("/images/start_image.png")).getImage());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(loginButton))
		{
			String username = nameField.getText();
			String password = toString(passwdField.getPassword());
			String.format("windows-1252", password);
			
			ArrayList<String> account = new ArrayList<>();
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("accounts.ser"));
				account = (ArrayList<String>)in.readObject();
				if(account.get(0) == username && account.get(1) == password)
				{
					JOptionPane.showMessageDialog(null, "Your connection has been established!");
					this.setVisible(false);
					new DataPage();
					in.close();
				}
				else
					JOptionPane.showMessageDialog(null, "Wrong credentials!");
				
			}catch(IOException exc1) {
				exc1.printStackTrace();
				JOptionPane.showMessageDialog(null, "There is no account in the system!");
			}catch(ClassNotFoundException exc2) {
				exc2.printStackTrace();
			}
		}
		
		if(e.getSource().equals(registerButton))
		{
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accounts.ser"));
				ArrayList<String> account = new ArrayList<String>();
				
				String password = toString(passwdField.getPassword());
				String.format("windows-1252", password);
				account.add(nameField.getText());
				account.add(password);
				
				out.writeObject(account);
				out.close();
				JOptionPane.showMessageDialog(null, "You have successfully registered to the system!");
				
				this.setVisible(false);
				new DataPage();
			}catch(IOException ecx) {
				ecx.printStackTrace();
			}
		}
		
		if(e.getSource().equals(checkbox))
		{
			if(checkbox.isSelected())
				passwdField.setEchoChar((char) 0);
			else
				passwdField.setEchoChar('*');
		}
		
	}
	
	//A method that converts an char type array into a String variable.
		public static String toString(char[] a)
	    {
	        String string = new String(a);
	        return string;
	    }
}
