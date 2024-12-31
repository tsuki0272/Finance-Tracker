import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class EditAccount {

	private JFrame frame;
	private JTextField txtEditAccountName;
	private JTextField txtEditAccountAmount;
	private static JComboBox<Object> comboBoxAccountList;
	private String oldName;
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
					EditAccount window = new EditAccount();
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
	public EditAccount() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountManager accountManager = new AccountManager();
				accountManager.getFrame().setVisible(true);;
		        frame.dispose();

			}
		});
		btnBack.setBounds(6, 6, 88, 35);
		frame.getContentPane().add(btnBack);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnBack);
		
		JLabel lblEditAccount = new JLabel("Select account to edit:");
		lblEditAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEditAccount.setBounds(14, 63, 193, 16);
		frame.getContentPane().add(lblEditAccount);
		
		comboBoxAccountList = new JComboBox<Object>(MyJDBC.getAllAccountsArray());
		comboBoxAccountList.setBounds(9, 85, 266, 27);
		frame.getContentPane().add(comboBoxAccountList);
		
		JLabel lblNewAccountName = new JLabel("New account name:");
		lblNewAccountName.setBounds(14, 124, 180, 16);
		frame.getContentPane().add(lblNewAccountName);
		
		txtEditAccountName = new JTextField();
		txtEditAccountName.setText(updateName());
		txtEditAccountName.setColumns(10);
		txtEditAccountName.setBounds(14, 141, 180, 35);
		frame.getContentPane().add(txtEditAccountName);
		
		JLabel lblNewAccountAmount = new JLabel("New amount:");
		lblNewAccountAmount.setBounds(15, 200, 179, 16);
		frame.getContentPane().add(lblNewAccountAmount);
		
		txtEditAccountAmount = new JTextField();
		txtEditAccountAmount.setText(MyJDBC.getAmount(updateName()));
		txtEditAccountAmount.setColumns(10);
		txtEditAccountAmount.setBounds(14, 217, 180, 35);
		frame.getContentPane().add(txtEditAccountAmount);
		
		JLabel lblNewAccountCurrency = new JLabel("Currency:");
		lblNewAccountCurrency.setBounds(306, 64, 115, 16);
		frame.getContentPane().add(lblNewAccountCurrency);
		
		JComboBox<Object> comboBoxCurrencies = new JComboBox<Object>(CurrencyDatabase.currencies());
		comboBoxCurrencies.setBounds(302, 82, 126, 32);
		comboBoxCurrencies.setSelectedIndex(matchingCurrency());
		frame.getContentPane().add(comboBoxCurrencies);
		
		
		JButton btnFinishEditAccount = new JButton("FINISH");
		btnFinishEditAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 15)); 
		btnFinishEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldName = updateName();
				accountName = "" + txtEditAccountName.getText();
				String amount = "" + txtEditAccountAmount.getText();
				accountCurrency = comboBoxCurrencies.getSelectedItem().toString();
				boolean validName = NewAccount.isNameValid(accountName);
				boolean validAmount = NewAccount.isAmountValid(amount);
				
				if(!validName || !validAmount) {
					AccountError error = new AccountError();
					error.getFrame().setVisible(true);
				} else {
					accountAmount = Double.parseDouble(amount);
					MyJDBC.update(oldName, accountName, accountAmount, accountCurrency);
					ActionSuccess success = new ActionSuccess();
					success.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
		btnFinishEditAccount.setBounds(348, 225, 96, 41);
		frame.getContentPane().add(btnFinishEditAccount);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEditAccountName.setText(updateName());
				txtEditAccountAmount.setText(MyJDBC.getAmount(updateName()));
				comboBoxCurrencies.setSelectedIndex(matchingCurrency());
			}
		});
		btnReset.setBounds(375, 189, 69, 35);
		frame.getContentPane().add(btnReset);
	}
	
	public static String updateName() {
		String selected = comboBoxAccountList.getSelectedItem().toString();
		String name = "";
		String[] names = MyJDBC.getAllNamesArray();
		for(String i : names) {
			if(selected.substring(0,i.length()).equals(i)) {
				name = i;
			}
		}
		return name;
	}
	
	public static int matchingCurrency() {
		int matching = -1;
		String[] currencies = CurrencyDatabase.currencies();
		String current = comboBoxAccountList.getSelectedItem().toString();
		int length = current.length();
		for(int i = 0; i < currencies.length; i++) {
			if(currencies[i].equals(current.substring(length - 3,length))) {
				matching = i;
			}
		}
		return matching;
	}
}
