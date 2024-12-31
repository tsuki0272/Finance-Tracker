import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class DeleteAccount {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAccount window = new DeleteAccount();
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
	public DeleteAccount() {
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
		
		JComboBox<Object> comboBoxAccountList = new JComboBox<Object>(MyJDBC.getAllAccountsArray());
		comboBoxAccountList.setBounds(90, 124, 266, 27);
		frame.getContentPane().add(comboBoxAccountList);
		
		JLabel lblDeleteAccount = new JLabel("Select account to delete:");
		lblDeleteAccount.setBounds(128, 102, 193, 16);
		lblDeleteAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 15)); 
		frame.getContentPane().add(lblDeleteAccount);
		
		JRadioButton rdbtnConfirmDelete = new JRadioButton("I confirm (this action is permanent)");
		rdbtnConfirmDelete.setBounds(6, 240, 309, 23);
		frame.getContentPane().add(rdbtnConfirmDelete);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Lucida Grande", Font.PLAIN, 15)); 
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnConfirmDelete.isSelected()) {
					String selected = comboBoxAccountList.getSelectedItem().toString();
					String[] names = MyJDBC.getAllNamesArray();
					for(String i : names) {
						if(selected.substring(0,i.length()).equals(i)) {
							MyJDBC.delete(i);
							ActionSuccess success = new ActionSuccess();
							success.getFrame().setVisible(true);
							frame.dispose();
						}
					}
				} else {
					rdbtnConfirmDelete.setForeground(Color.red);
				}
			}
		});
		btnDelete.setBounds(341, 222, 103, 44);
		frame.getContentPane().add(btnDelete);	
	}
}
