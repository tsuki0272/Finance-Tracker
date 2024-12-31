import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class ActionSuccess extends JFrame{

	private JFrame frame;
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActionSuccess window = new ActionSuccess();
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
	public ActionSuccess() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 200, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountManager accountManager = new AccountManager();
				accountManager.getFrame().setVisible(true);;
				frame.dispose();
			}
		});
		btnOK.setBounds(119, 84, 75, 35);
		frame.getContentPane().add(btnOK);
		
		JLabel lblSuccess = new JLabel("SUCCESS!");
		lblSuccess.setBounds(43, 35, 117, 35);
		lblSuccess.setFont(new Font("Lucida Grande", Font.PLAIN, 25)); 
		frame.getContentPane().add(lblSuccess);
	}
}
