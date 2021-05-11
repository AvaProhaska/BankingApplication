
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * A class that represents a bank account for the deposit and withdraw of amounts input by user.
 * @author prohaska

 */

public class BankAccount
/**
 * Store the state of the objects
 */
{
	private double balance;
	private String accountNumber;
	private String history;
	private boolean buttonDeposit, buttonWithdraw;

	/**
	 * Sets the initial  balance of the BankAccount to zero 
	 */
	public BankAccount() {
		balance = 0;
	}

	public BankAccount(double amount, String account) {
		balance = amount;
		accountNumber = account;
	}
	/**
	 * Adds the amount input by user to the BankAccount balance
	 * @param amount This is the current balance of the bank account 
	 */
	public void deposit(double amount) {

		double d = getBalance() + amount;
		setBalance(d);	
	}

	/**
	 * Subtracts the amount input by user from the BankAccount balance
	 * @param amount This is the current balance of the bank account
	 * @return The amount withdrawn cannot be greater than the balance of the BankAccount
	 */
	public boolean withdraw(double amount) {
		if(balance>=amount) {
			balance = balance - amount;
			return true;
		} else {
			return false;	
		}

	}
	/**
	 * Gets the current Balance 
	 * @return the  balance
	 */
	public double getBalance() 
	{
		return balance;

	}
	/**
	 * Sets the balance 
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
		System.out.println("Balance "+balance);
	}

	/**
	 * Stores the transaction history of deposits and withdraw's 
	 * @return history
	 */
	public String getHistory() {
		return history;

	}
	/**
	 * 	Gets the account number
	 * @return the account number
	 */

	public String getAccountNumber() {

		return accountNumber;
	}

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/**
			 * BankAccount is Displayed in Window
			 */
			public void run() {
				try {
					BankAccount window = new BankAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This application serves as the interactive application in which user can deposit and withdraw funds
	 * Create the application.
	 */
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		/**
		 * Implements ListModel
		 */
		DefaultListModel<String> listModel = new DefaultListModel<String>();


		/**
		 * Creates textField
		 */
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {

			/**
			 * Location for user to input amount 
			 */
			public void actionPerformed(ActionEvent e) {

			}
		});
		/**
		 * Creates ScrollPane
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 34, 229, 269);
		frame.getContentPane().add(scrollPane);


		/**
		 * Initializes List
		 */
		JList<String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		textField.setBounds(315, 83, 109, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);



		/**
		 * Creates Radio Button to deposit amount  
		 */
		JRadioButton rdbtnDeposit = new JRadioButton("Desposit");
		rdbtnDeposit.setActionCommand(textField.getText());
		rdbtnDeposit.addActionListener(new ActionListener() {

			/**
			 * The value input by user is set to be deposited 
			 */
			public void actionPerformed(ActionEvent e) {
				rdbtnDeposit.setSelected(true);
				buttonDeposit = true;
				buttonWithdraw = false;

			}
		});

		rdbtnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnDeposit.setBounds(315, 124, 114, 34);
		frame.getContentPane().add(rdbtnDeposit);

		/**
		 *Creates Radio Button to withdraw amount 
		 */

		JRadioButton rdbtnWithdrawl = new JRadioButton("Withdrawl");
		rdbtnWithdrawl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnWithdrawl.setActionCommand(textField.getText());

		rdbtnWithdrawl.addActionListener(new ActionListener() {
			/** 
			 * Value input by user is set to be withdrawn 
			 */

			public void actionPerformed(ActionEvent e) {
				rdbtnWithdrawl.setSelected(true);
				buttonDeposit = false;
				buttonWithdraw = true;

			}
		});
		rdbtnWithdrawl.setBounds(315, 161, 109, 23);
		frame.getContentPane().add(rdbtnWithdrawl);
		/**
		 * Create a Button group for deposit and withdraw radio button 
		 */

		ButtonGroup group = new ButtonGroup();

		group.add(rdbtnDeposit);
		group.add(rdbtnWithdrawl);

		/** 
		 * Create "Process" Button
		 */
		JButton btnProcess = new JButton("Process Amount");
		btnProcess.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnProcess.addActionListener(new ActionListener() {

			/**
			 * Displays amount withdrawn/deposited from BankAccount 
			 * Displays total balance
			 * Text Field and Radio button selection  is reset 
			 */
			public void actionPerformed(ActionEvent e) {
				String update = "";
				if(buttonDeposit) {
					update = "";
					double w=0;
					try {
						w = Double.parseDouble(textField.getText());
						deposit(w);
						update = "Deposit: "+w+" Balance: "+getBalance();
						System.out.println("Size of jlist: "+listModel.size());

						listModel.addElement(update);
						listModel.remove(listModel.size()-1);
						textField.setText("");
						group.clearSelection();


					} catch(Exception e1) {	 
					}

				}else if(buttonWithdraw) {
					double w =0;
					try {
						w= Double.parseDouble(textField.getText()); 
						if (withdraw(w)) {
							update = "Withdrawl: " + w + " Balance: " + getBalance();
						} else {
							update = "Withdrawl: Cannot withdraw more than balance";
						}
						listModel.addElement(update);
						listModel.remove(listModel.size()-1);
						textField.setText("");
						group.clearSelection();

					}
					catch(Exception e1) {

					}	 
				}
				listModel.addElement(update);

			}

		});
		btnProcess.setBounds(279, 191, 145, 28);
		frame.getContentPane().add(btnProcess);

		/** 
		 * Create "Balance" Button
		 */

		JButton btnBalance = new JButton("Balance");

		btnBalance.addActionListener(new ActionListener() {
			/**
			 * Prints Balance of BankAccount
			 */
			public void actionPerformed(ActionEvent e) {                 
				listModel.addElement("Balance: "+getBalance());
				String sum = String.valueOf(getBalance());
				textField_1.setText("$" + sum);
			}
		});
		btnBalance.setBounds(288, 328, 141, 40);
		frame.getContentPane().add(btnBalance);

		/**
		 * Creates "Clear Transaction Button" 
		 */
		JButton btnClear = new JButton("Clear Transactions");

		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.addActionListener(new ActionListener() {
			/**
			 * Clears list of Transactions
			 */

			public void actionPerformed(ActionEvent arg0) {
				listModel.addElement("Transaction cleared");
				listModel.removeAllElements();
				textField_1.setText("");

			}
		});
		btnClear.setBounds(24, 327, 212, 40);
		frame.getContentPane().add(btnClear);

		/**
		 * The title of the List 
		 */

		JLabel lblTransactionHistory = new JLabel("Transaction History");
		lblTransactionHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTransactionHistory.setBounds(64, 5, 172, 23);
		frame.getContentPane().add(lblTransactionHistory);

		/**
		 * Creates "Click to Access Account Button"
		 */

		JButton btnClickToAcess = new JButton("Click to Access Account");
		btnClickToAcess.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClickToAcess.addActionListener(new ActionListener() {

			/**
			 * Prints information of account holder
			 */
			public void actionPerformed(ActionEvent arg0) {
				listModel.addElement("Name of Account Holder: Prohaska, Ava");
				listModel.addElement("Account Number : 3397");
				listModel.addElement("Inital Balance: = $0.00");
			}
		});
		btnClickToAcess.setBounds(255, 27, 169, 34);
		frame.getContentPane().add(btnClickToAcess);

		/**
		 * Creates "Current Balance" Button
		 */
		JLabel lblCurrentBalance = new JLabel("Current Balance");
		lblCurrentBalance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCurrentBalance.setBounds(240, 272, 109, 40);
		frame.getContentPane().add(lblCurrentBalance);


		/**
		 * Creates a label called "Amount"
		 */
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmount.setBounds(255, 82, 61, 34);
		frame.getContentPane().add(lblAmount);
		lblAmount.setBounds(255, 82, 61, 34);
		frame.getContentPane().add(lblAmount);


		/**
		 * Creates TextField
		 */
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			/**
			 * Displays balance of BankAccount
			 */
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_1.setBounds(338, 283, 86, 20);
		frame.getContentPane().add(textField_1);
	}

}

