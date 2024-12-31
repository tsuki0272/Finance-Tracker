import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AccountError extends JFrame{

	private JFrame frame;
	public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    
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
					AccountError window = new AccountError();
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
	public AccountError() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 250, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textAreaError = new JTextArea();
		textAreaError.setLineWrap(true);
		textAreaError.setEditable(false);
		textAreaError.setBackground(Color.WHITE);
		textAreaError.setText("ERROR: Invalid name or amount.\n\nEnsure "
				+ "NAME meets the\nfollowing requirements:\n\n• Contains "
				+ "3-15 characters\n• Does not include numbers\n•Does not "
				+ "include special\n   characters\n\nEnsure the AMOUNT meets"
				+ " \nthe following requirements:\n\n• Only contains numbers\n• "
				+ "Is a non-negative number\n• Does not include commas");
		
		JScrollPane scroll = new JScrollPane (textAreaError,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(10, 10, 230, 210);
		//frame.add(scroll);
		
		frame.getContentPane().add(scroll);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOK.setBounds(167, 224, 75, 35);
		frame.getContentPane().add(btnOK);
	}
}
