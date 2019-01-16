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
 * @created: 21/09/2018 15:46:43
 * @file: ViewWithdrawals.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewWithdrawals extends JPanel implements ActionListener, ChangeListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730106172463253253L;

	private JPanel panelBalance, panelForm, panelButtons;
	private JLabel labelWelcome, labelBalance;
	private ButtonGroup buttonGroup;
	private JRadioButton radioCombo, radioField;
	private JComboBox<String> comboBoxMoney;
	private String money[] = { "$100.00", "$200.00", "$500.00", "$1,000.00", "$2,000.00", "$5,000.00", "$8,000.00" };
	private JTextField fieldMoney;
	private JButton buttonDrawal, buttonCancel;

	// Service
	private ATMOperationsService aTMOperationsService;

	public ViewWithdrawals() {

		panelBalance = new JPanel();
		panelForm = new JPanel();
		panelButtons = new JPanel();

		buttonGroup = new ButtonGroup();

		labelWelcome = new JLabel("");
		labelWelcome.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 16));
		labelBalance = new JLabel("");
		labelBalance.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 16));

		radioCombo = new JRadioButton("Seleccione una cantidad a retirar: ");
		radioCombo.setSelected(true);
		radioCombo.addChangeListener(this);
		comboBoxMoney = new JComboBox<String>(money);
		comboBoxMoney.setFont(new Font("Arial", Font.BOLD, 15));
		radioField = new JRadioButton("Escriba una cantidad a retirar: ");
		radioField.addChangeListener(this);
		fieldMoney = new JTextField(12);
		fieldMoney.setEnabled(false);
		fieldMoney.addMouseListener(this);
		fieldMoney.addKeyListener(this);
		fieldMoney.setBorder(BorderFactory.createCompoundBorder(fieldMoney.getBorder(),
				BorderFactory.createEmptyBorder(3, 3, 3, 3)));
		fieldMoney.setFont(new Font("Arial", Font.BOLD, 15));
		fieldMoney.setHighlighter(null);

		buttonDrawal = new JButton("Retirar", new ImageIcon(getClass().getClassLoader().getResource("images/money_delete_16x16.png")));
		buttonDrawal.addActionListener(this);
		buttonCancel = new JButton("Cancelar", new ImageIcon(getClass().getClassLoader().getResource("images/cancel_16x16.png")));
		buttonCancel.addActionListener(this);

		buttonGroup.add(radioCombo);
		buttonGroup.add(radioField);

		panelBalance.setLayout(new GridLayout(2, 1));
		panelBalance.add(labelWelcome);
		panelBalance.add(labelBalance);

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

		panelButtons.add(buttonDrawal);
		panelButtons.add(buttonCancel);

		setLayout(new BorderLayout());
		add(panelBalance, BorderLayout.NORTH);
		add(panelForm, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);

	}

	private void clearFields() {
		radioCombo.setSelected(true);
		comboBoxMoney.setSelectedIndex(0);
		radioCombo.setSelected(true);
		fieldMoney.setText("");
		fieldMoney.setEnabled(false);
	}

	public void setaTMOperationsService(ATMOperationsService aTMOperationsService) {
		this.aTMOperationsService = aTMOperationsService;
	}

	public void setInfoLabels() {
		AccountHolder accountHolder = aTMOperationsService.queryAccountHolder(Sessions.accountHolder);
		labelWelcome.setText("Bienvenido: " + accountHolder.getName() + " " + accountHolder.getLastName());
		labelBalance.setText("Saldo: " + Formats.moneda(accountHolder.getBalance()));
		clearFields();
	}

	private void scarePositionTextFieldAmount() {
		if (!fieldMoney.getText().trim().isEmpty()) {
			fieldMoney.setCaretPosition(fieldMoney.getText().length() - 3);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonDrawal) {

			AccountHolder accountHolder = Sessions.accountHolder;

			if (radioCombo.isSelected()) {

				String quantity = comboBoxMoney.getSelectedItem().toString();
				quantity = quantity.substring(1, quantity.length());
				quantity = quantity.substring(0, quantity.length() - 3).replace(",", "");
				accountHolder.setBalance(Double.parseDouble(quantity));

			} else if (radioField.isSelected()) {

				try {

					if (fieldMoney.getText().trim().equals(""))
						throw new ValidateFieldException("Campo vacio.");

					String quantity = fieldMoney.getText().substring(1, fieldMoney.getText().length());
					quantity = quantity.substring(0, quantity.length() - 3).replace(",", "");

					accountHolder.setBalance(Double.parseDouble(quantity));

				} catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad valida.", "Error", JOptionPane.ERROR_MESSAGE);
					clearFields();
					return;
				} catch (ValidateFieldException ex1) {
					JOptionPane.showMessageDialog(null, ex1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					clearFields();
					return;
				} 

			}
			
			if(aTMOperationsService.validateBalance(accountHolder)) {

				int option =
				JOptionPane.showConfirmDialog(null, "¿Desea retirar la cantidad de " + Formats.moneda(accountHolder.getBalance()) +"?", "Confirmación de retiro", JOptionPane.YES_NO_OPTION);
				
				if(option == 0)
					JOptionPane.showMessageDialog(null, aTMOperationsService.withdrawals(accountHolder), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			
			} else {
				JOptionPane.showMessageDialog(null, "Su monto a retirar supera su saldo.", "Mensaje", JOptionPane.PLAIN_MESSAGE);
			}
			
			setInfoLabels();
			clearFields();
			
		} else if (e.getSource() == buttonCancel) {
			clearFields();
		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {

		if(fieldMoney.getText().length() >= 10) {
			fieldMoney.setText(fieldMoney.getText().substring(0, 10));
		} 
		
		if (!fieldMoney.getText().trim().isEmpty()) {

			if (fieldMoney.getText().length() >= 5) {
				// Cuando hay por lo menos un caracter ingresado, ya esta
				// formateado y ejecuta este bloque.
				String quantity = fieldMoney.getText().substring(1, fieldMoney.getText().length());
				quantity = quantity.substring(0, quantity.length() - 3).replace(",", "");
				try {
					fieldMoney.setText(Formats.moneda(Double.parseDouble(quantity)));
				} catch (NumberFormatException ex) {
					fieldMoney.setText(fieldMoney.getText().replace(String.valueOf(e.getKeyChar()), ""));
					if (fieldMoney.getText().trim().isEmpty())
						return;
				}

			} else {
				// Cuando es el primer caracter ingresado ejecuta este bloque.
				try {
					fieldMoney.setText(Formats.moneda(Double.parseDouble(fieldMoney.getText())));
				} catch (NumberFormatException ex) {
					fieldMoney.setText(fieldMoney.getText().replace(String.valueOf(e.getKeyChar()), ""));
					return;
				}
			}

			fieldMoney.setCaretPosition(fieldMoney.getText().length() - 3);
		}

	}

	public void mouseClicked(MouseEvent e) {
		scarePositionTextFieldAmount();
	}

	public void mousePressed(MouseEvent e) {
		scarePositionTextFieldAmount();
	}

	public void mouseReleased(MouseEvent e) {
		scarePositionTextFieldAmount();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void stateChanged(ChangeEvent e) {

		if (radioCombo.isSelected()) {
			comboBoxMoney.setEnabled(true);
			fieldMoney.setEnabled(false);
		} else if (radioField.isSelected()) {
			comboBoxMoney.setEnabled(false);
			fieldMoney.setEnabled(true);
		}

	}

}