import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AccountManager extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountManager window = new AccountManager();
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
	public AccountManager() {
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
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnCreateAccount = new JButton("CREATE ACCOUNT");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAccount newAccount = new NewAccount();
				newAccount.getFrame().setVisible(true);;
		        frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCreateAccount, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnCreateAccount, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnCreateAccount, -183, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCreateAccount, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnCreateAccount);
		
		JButton btnDeleteAccount = new JButton("DELETE ACCOUNT");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAccount deleteAccount = new DeleteAccount();
				deleteAccount.getFrame().setVisible(true);;
		        frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDeleteAccount, 6, SpringLayout.SOUTH, btnCreateAccount);
		springLayout.putConstraint(SpringLayout.WEST, btnDeleteAccount, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDeleteAccount, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnDeleteAccount);
		
		JButton btnEditAccount = new JButton("EDIT ACCOUNT");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditAccount editAccount = new EditAccount();
				editAccount.getFrame().setVisible(true);;
		        frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnEditAccount, 183, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnEditAccount, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnEditAccount, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnDeleteAccount, -6, SpringLayout.NORTH, btnEditAccount);
		springLayout.putConstraint(SpringLayout.SOUTH, btnEditAccount, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnEditAccount);
	}

}
