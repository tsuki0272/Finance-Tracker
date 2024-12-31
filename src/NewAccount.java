import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;

public class NewAccount extends JFrame{

	private JFrame frame;
	private JTextField textFieldAccountName;
	private JTextField textFieldAccountAmount;
	private String accountName;
	private double accountAmount;
	private String accountCurrency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount window = new NewAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewAccount() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(6, 6, 88, 35);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountManager accountManager = new AccountManager();
				accountManager.getFrame().setVisible(true);;
		        frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewAccountName = new JLabel("Account name:");
		lblNewAccountName.setBounds(16, 83, 115, 16);
		frame.getContentPane().add(lblNewAccountName);
		
		textFieldAccountName = new JTextField();
		textFieldAccountName.setBounds(16, 100, 180, 35);
		frame.getContentPane().add(textFieldAccountName);
		textFieldAccountName.setColumns(10);
		
		JLabel lblNewAccountAmount = new JLabel("Amount:");
		lblNewAccountAmount.setBounds(17, 159, 94, 16);
		frame.getContentPane().add(lblNewAccountAmount);
		
		textFieldAccountAmount = new JTextField();
		textFieldAccountAmount.setBounds(16, 176, 180, 35);
		textFieldAccountAmount.setColumns(10);
		frame.getContentPane().add(textFieldAccountAmount);
		
		JLabel lblNewAccountCurrency = new JLabel("Currency:");
		lblNewAccountCurrency.setBounds(263, 14, 115, 16);
		frame.getContentPane().add(lblNewAccountCurrency);
		
		JComboBox<Object> comboBoxCurrencies = new JComboBox<Object>(CurrencyDatabase.currencyArray());
		
		comboBoxCurrencies.setBounds(258, 32, 126, 32);
		frame.getContentPane().add(comboBoxCurrencies);
		
		JButton btnNewAccountCreate = new JButton("CREATE");
		btnNewAccountCreate.setBounds(350, 224, 94, 42);
		btnNewAccountCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 15)); 
		frame.getContentPane().add(btnNewAccountCreate);
		btnNewAccountCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				accountName = "" + textFieldAccountName.getText();
				String amount = "" + textFieldAccountAmount.getText();
				accountCurrency = comboBoxCurrencies.getSelectedItem().toString();
				boolean validName = isNameValid(accountName);
				boolean validAmount = isAmountValid(amount);
				
				
				if(!validName || !validAmount) {
					AccountError error = new AccountError();
					error.getFrame().setVisible(true);
				} else {
					accountAmount = Double.parseDouble(amount);
					MyJDBC.insert(accountName, accountAmount, accountCurrency);
					ActionSuccess success = new ActionSuccess();
					success.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
	}
	
	public static boolean isNameValid(String name) {
		boolean isValid = false;
		if(!name.equals("")) {
			int length = name.length();
			int letters = 0;
			
			for(int i = 0; i < length; i++) {
				if(Character.isLetter(name.charAt(i))) {
					letters++;
				}
			}
			if((length <= 15 && length >= 3) && (letters == length)) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	public static boolean isAmountValid(String amount) {
		boolean isValid = false;
		final char FLAG = '.';
		
		if(!amount.equals("")) {
			int length = amount.length();
			int digitCount = 0;
			
			for(int i = 0; i < length; i++) {
				if(Character.isDigit(amount.charAt(i))) {
					digitCount++;
				}
			}
			
			if(digitCount == length) {
				isValid = true;
			} else if(digitCount + 1 == length) {
				if(amount.indexOf(FLAG) != -1 && amount.indexOf(FLAG) != 0) {
					isValid = true;
				}
			}	
		}
		return isValid;
	}
}
