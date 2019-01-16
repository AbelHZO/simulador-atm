package com.abelhzo.atm.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.abelhzo.atm.bo.ATMOperationsService;
import com.abelhzo.atm.dto.AccountHolder;
import com.abelhzo.atm.utils.Sessions;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 18:51:25
 * @file: ViewLogin.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewLogin extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7575053105029754894L;
	
	private JPanel panelForm, panelButtons;
	private JLabel labelIcon, labelAccount, labelNip;
	private JTextField fieldAccount;
	private JPasswordField fieldNip;
	private JButton buttonLogin, buttonCancel;
	
	//Service
	private ATMOperationsService aTMOperationsService;
	
	//Window Dad
	private ViewWindow viewWindow;
	
	public ViewLogin(ViewWindow viewWindow) {
		
		this.viewWindow = viewWindow;
		
		panelForm = new JPanel();
		panelButtons = new JPanel();
		labelIcon = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/padlock_96.png")));
		labelAccount = new JLabel("Numero de cuenta: ");
		labelNip = new JLabel("Ingrese NIP: ");
		fieldAccount = new JTextField(12);
		fieldNip = new JPasswordField(12);
		buttonLogin = new JButton("Entrar");
		buttonLogin.addActionListener(this);
		buttonCancel = new JButton("Cancelar");
		buttonCancel.addActionListener(this);
		
		fieldAccount.setBorder(BorderFactory.createCompoundBorder(fieldAccount.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldAccount.setFont(new Font("Arial", Font.BOLD, 15));
		fieldNip.setBorder(BorderFactory.createCompoundBorder(fieldNip.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldNip.setFont(new Font("Arial", Font.BOLD, 15));
		
		panelForm.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelForm.add(labelIcon, gbc);
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelForm.add(labelAccount, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelForm.add(fieldAccount, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelForm.add(labelNip, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		panelForm.add(fieldNip, gbc);
		
		panelButtons.add(buttonLogin);
		panelButtons.add(buttonCancel);
		
		setLayout(new BorderLayout());
		add(panelForm, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonLogin) {
			
			AccountHolder accountHolder = new AccountHolder();
			try {
				accountHolder.setAccount(Long.parseLong(fieldAccount.getText()));
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
				return;
			}
			
			accountHolder.setNip(String.valueOf(fieldNip.getPassword()));
			
			if(aTMOperationsService.login(accountHolder)) {
				Sessions.accountHolder = (AccountHolder) aTMOperationsService.queryAccountHolder(accountHolder).clone();
				clearFields();
				this.viewWindow.changeViewPanel("toViewWithdrawals");
			} else {
				JOptionPane.showMessageDialog(null, "Numero de cuenta o nip incorrectos.");
			}
			
		} else if(e.getSource() == buttonCancel) {
			clearFields();
		}
	}
	
	private void clearFields() {
		fieldAccount.setText("");
		fieldNip.setText("");
	}

	public void setaTMOperationsService(ATMOperationsService aTMOperationsService) {
		this.aTMOperationsService = aTMOperationsService;
	}

	public void setViewWindow(ViewWindow viewWindow) {
		this.viewWindow = viewWindow;
	}
	
}
