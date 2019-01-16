package com.abelhzo.atm.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.abelhzo.atm.bo.ATMOperationsService;
import com.abelhzo.atm.dto.AccountHolder;
import com.abelhzo.atm.exceptions.ValidateFieldException;
import com.abelhzo.atm.utils.Formats;
import com.abelhzo.atm.utils.Sessions;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 21/09/2018 15:49:15
 * @file: ViewTransfer.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewTransfer extends JPanel implements ActionListener, ChangeListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6057459380601277643L;
	
	private JPanel panelBalance, panelFormAndValidate ,panelValidate, panelForm, panelButtons;
	private JLabel labelWelcome, labelBalance, labelAccountDestiny;
	private JTextField fieldAccountDestiny;
	private JButton buttonValidateAccount;
	private ButtonGroup buttonGroup;
	private JRadioButton radioCombo, radioField;
	private JComboBox<String> comboBoxMoney;
	private String money[] = { "$100.00", "$200.00", "$500.00", "$1,000.00", "$2,000.00", "$5,000.00", "$8,000.00" };
	private JTextField fieldMoney;
	private JButton buttonTransfer, buttonCancel;
	
	// Service
	private ATMOperationsService aTMOperationsService;
	
	public ViewTransfer() {
		
		panelBalance = new JPanel();
		panelFormAndValidate = new JPanel();
		panelValidate = new JPanel();
		panelForm = new JPanel();
		panelButtons = new JPanel();
		
		labelWelcome = new JLabel("Bienvenido:  ");
		labelWelcome.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 16));
		labelBalance = new JLabel("Saldo:  ");
		labelBalance.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 16));
		
		panelBalance.setLayout(new GridLayout(2, 1));
		panelBalance.add(labelWelcome);
		panelBalance.add(labelBalance);
		
		labelAccountDestiny = new JLabel("Ingrese la cuenta destino: ");
		fieldAccountDestiny = new JTextField(14);
		fieldAccountDestiny.setFont(new Font("Arial", Font.BOLD, 13));
		fieldAccountDestiny.setBorder(BorderFactory.createCompoundBorder(fieldAccountDestiny.getBorder(),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		fieldAccountDestiny.addKeyListener(this);
		buttonValidateAccount = new JButton("Validar");
		buttonValidateAccount.addActionListener(this);
		
		buttonGroup = new ButtonGroup();
		
		radioCombo = new JRadioButton("Seleccione una cantidad a transferir:");
		radioCombo.setSelected(true);
		radioCombo.setEnabled(false);
		radioCombo.addChangeListener(this);
		comboBoxMoney = new JComboBox<>(money);
		comboBoxMoney.setFont(new Font("Arial", Font.BOLD, 15));
		comboBoxMoney.setEnabled(false);
		radioField = new JRadioButton("Escriba una cantidad a transferir:");
		radioField.setEnabled(false);
		radioField.addChangeListener(this);
		fieldMoney = new JTextField(12);
		fieldMoney.setEnabled(false);
		fieldMoney.addMouseListener(this);
		fieldMoney.addKeyListener(this);
		fieldMoney.setBorder(BorderFactory.createCompoundBorder(fieldMoney.getBorder(),
				BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		fieldMoney.setFont(new Font("Arial", Font.BOLD, 15));
		fieldMoney.setHighlighter(null);
		
		buttonGroup.add(radioCombo);
		buttonGroup.add(radioField);
		
		panelValidate.setLayout(new GridBagLayout());
		GridBagConstraints gbc0 = new GridBagConstraints();
		gbc0.fill = GridBagConstraints.HORIZONTAL;
		gbc0.weightx = 3;
		gbc0.gridx = 0;
		gbc0.gridy = 0;
		panelValidate.add(labelAccountDestiny, gbc0);
		gbc0.fill = GridBagConstraints.HORIZONTAL;
		gbc0.weightx = 15;
		gbc0.gridx = 1;
		gbc0.gridy = 0;
		panelValidate.add(fieldAccountDestiny, gbc0);
		gbc0.fill = GridBagConstraints.HORIZONTAL;
		gbc0.weightx = 3;
		gbc0.gridx = 2;
		gbc0.gridy = 0;
		panelValidate.add(buttonValidateAccount, gbc0);
		
		panelForm.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelForm.add(radioCombo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 10;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelForm.add(comboBoxMoney, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelForm.add(radioCombo, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 10;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelForm.add(comboBoxMoney, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 3;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelForm.add(radioField, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 10;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelForm.add(fieldMoney, gbc);
		
		buttonTransfer = new JButton("Transferir", new ImageIcon(getClass().getClassLoader().getResource("images/arrow_switch_16x16.png")));
		buttonTransfer.addActionListener(this);
		buttonTransfer.setEnabled(false);
		buttonCancel = new JButton("Cancelar", new ImageIcon(getClass().getClassLoader().getResource("images/cancel_16x16.png")));
		buttonCancel.addActionListener(this);
		
		panelButtons.add(buttonTransfer);
		panelButtons.add(buttonCancel);
		
		panelFormAndValidate.setLayout(new GridLayout(2, 1));
		panelFormAndValidate.add(panelValidate);
		panelFormAndValidate.add(panelForm);
		
		setLayout(new BorderLayout());
		add(panelBalance, BorderLayout.NORTH);
		add(panelFormAndValidate, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);
		
	}
	
	private void clearFields(boolean clear) {
		if(clear) fieldAccountDestiny.setText("");
		radioCombo.setEnabled(false);
		radioCombo.setSelected(true);
		comboBoxMoney.setSelectedIndex(0);
		comboBoxMoney.setEnabled(false);
		radioField.setEnabled(false);
		fieldMoney.setEnabled(false);
		fieldMoney.setText("");
		buttonTransfer.setEnabled(false);
	}
	
	public void setaTMOperationsService(ATMOperationsService aTMOperationsService) {
		this.aTMOperationsService = aTMOperationsService;
	}
	
	public void setInfoLabels() {
		AccountHolder accountHolder = aTMOperationsService.queryAccountHolder(Sessions.accountHolder);
		labelWelcome.setText("Bienvenido: " + accountHolder.getName() + " " + accountHolder.getLastName());
		labelBalance.setText("Saldo: " + Formats.moneda(accountHolder.getBalance()));
		clearFields(true);
	}
	
	private void scarePositionTextFieldAmount() {
		if(!fieldMoney.getText().trim().isEmpty()) {
			fieldMoney.setCaretPosition(fieldMoney.getText().length() - 3);
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonValidateAccount) {
			try {
				String nameAcountDestiny = 
				aTMOperationsService.validateAccountDestiny(Long.parseLong(fieldAccountDestiny.getText()));
				
				if(fieldAccountDestiny.getText().trim().equals(""))
					throw new ValidateFieldException("Campo vacio.");
		
				
				if(nameAcountDestiny == null) 
					throw new ValidateFieldException("Cuenta invalida.");
					
				JOptionPane.showMessageDialog(null, "Cuenta a nombre de " + nameAcountDestiny, "Cuenta valida", JOptionPane.INFORMATION_MESSAGE);
				
				radioCombo.setEnabled(true);
				radioCombo.setSelected(true);
				comboBoxMoney.setEnabled(true);
				radioField.setEnabled(true);
				buttonTransfer.setEnabled(true);
				fieldMoney.setText("");
				fieldMoney.setEnabled(false);
				
			} catch(NumberFormatException ex) {
				clearFields(true);
				JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (ValidateFieldException ex1) {
				JOptionPane.showMessageDialog(null, ex1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		
		} else if(e.getSource() == buttonTransfer) {
			
			AccountHolder accountHolder = Sessions.accountHolder;
			
			if(radioCombo.isSelected()) {
				
				String quantity = comboBoxMoney.getSelectedItem().toString();
				quantity = quantity.substring(1, quantity.length());
				quantity = quantity.substring(0, quantity.length() - 3).replace(",", "");
				accountHolder.setBalance(Double.parseDouble(quantity));
				
			} else if(radioField.isSelected()) {
				
				try {
					
					if(fieldMoney.getText().trim().equals("")) 
						throw new ValidateFieldException("Campo vacio.");
					
					String quantity = fieldMoney.getText().substring(1, fieldMoney.getText().length());
					quantity = quantity.substring(0, quantity.length() - 3).replace(",", "");
					
					accountHolder.setBalance(Double.parseDouble(quantity));
					
				} catch(NumberFormatException | StringIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida.", "Error", JOptionPane.ERROR_MESSAGE);
					fieldMoney.setText("");
					return;
				} catch (ValidateFieldException ex1) {
					JOptionPane.showMessageDialog(null, ex1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
			
			if(aTMOperationsService.validateBalance(accountHolder)) {
			
				int option =
				JOptionPane.showConfirmDialog(null, "¿Desea transferir la cantidad de " + Formats.moneda(accountHolder.getBalance()) +"?", "Confirmación de transferencia", JOptionPane.YES_NO_OPTION);
				
				if(option == 0) {
					if(aTMOperationsService.transfer(accountHolder, Long.parseLong(fieldAccountDestiny.getText()))) {
						JOptionPane.showMessageDialog(null, "Su tranferencia de " + Formats.moneda(accountHolder.getBalance()) + " fue realizada con exito.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						setInfoLabels();
						clearFields(true);
					}
				}
			
			} else {
				JOptionPane.showMessageDialog(null, "No pudo realizarse la tranferencia: Su saldo es menor a la del monto a tranferir.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				fieldMoney.setText("");
			}
			
		} else if(e.getSource() == buttonCancel) {
			clearFields(true);
		} 
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		scarePositionTextFieldAmount();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		scarePositionTextFieldAmount();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		scarePositionTextFieldAmount();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getSource() == fieldMoney) {
			
			if(fieldMoney.getText().length() >= 15) {
				fieldMoney.setText(fieldMoney.getText().substring(0, 15));
			} 
		
			if(!fieldMoney.getText().trim().isEmpty()) {
				
				if(fieldMoney.getText().length() >= 5) { 
					//Cuando hay por lo menos un caracter ingresado, ya esta formateado y ejecuta este bloque.
					String quantity = fieldMoney.getText().substring(1, fieldMoney.getText().length());
					quantity = quantity.substring(0, quantity.length() - 3).replace(",", "");
					try {
						fieldMoney.setText(Formats.moneda(Double.parseDouble(quantity)));
					} catch(NumberFormatException ex) {
						fieldMoney.setText(fieldMoney.getText().replace(String.valueOf(e.getKeyChar()), ""));
						if(fieldMoney.getText().trim().isEmpty()) return;
					}
					
				} else { 
					//Cuando es el primer caracter ingresado ejecuta este bloque.
					try {
						fieldMoney.setText(Formats.moneda(Double.parseDouble(fieldMoney.getText())));
					} catch(NumberFormatException ex) {
						fieldMoney.setText(fieldMoney.getText().replace(String.valueOf(e.getKeyChar()), ""));
						return;
					}
				}
				
				fieldMoney.setCaretPosition(fieldMoney.getText().length() - 3);
			}
		
		} else if(e.getSource() == fieldAccountDestiny) {
			clearFields(false);
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(radioCombo.isEnabled() && radioCombo.isSelected()) {
			comboBoxMoney.setEnabled(true);
			fieldMoney.setEnabled(false);
		} else if(radioField .isEnabled() && radioField.isSelected()) {
			comboBoxMoney.setEnabled(false);
			fieldMoney.setEnabled(true);
		}
		
	}


}
