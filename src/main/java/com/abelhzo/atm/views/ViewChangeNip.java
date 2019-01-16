package com.abelhzo.atm.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.abelhzo.atm.bo.ATMOperationsService;
import com.abelhzo.atm.dto.AccountHolder;
import com.abelhzo.atm.utils.Sessions;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 08/11/2018 18:27:34
 * @file: ViewChangeNip.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewChangeNip extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8283116289703476447L;
	
	private JPanel panelTitle, panelFields, panelButtons;
	private JLabel labelTitle, labelNip, labelNewNip, labelNipConfirm;
	private JPasswordField fieldNip, fieldNewNip, fieldNipConfirm;
	private JButton buttonSave, buttonCancel;
	
	// Service
	private ATMOperationsService aTMOperationsService;
	
	public ViewChangeNip() {
		
		panelTitle = new JPanel();
		panelFields = new JPanel();
		panelButtons = new JPanel();
		
		labelTitle = new JLabel();
		labelTitle.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 16));
		labelNip = new JLabel("NIP: ");
		labelNewNip = new JLabel("Nuevo NIP: ");
		labelNipConfirm = new JLabel("Confirmación NIP: ");
		fieldNip = new JPasswordField(12);
		fieldNip.setFont(new Font("Arial", Font.BOLD, 15));
		fieldNip.setBorder(BorderFactory.createCompoundBorder(fieldNip.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldNip.addKeyListener(this);
		fieldNewNip = new JPasswordField(12);
		fieldNewNip.setFont(new Font("Arial", Font.BOLD, 15));
		fieldNewNip.setBorder(BorderFactory.createCompoundBorder(fieldNewNip.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldNewNip.addKeyListener(this);
		fieldNipConfirm = new JPasswordField(12);
		fieldNipConfirm.setFont(new Font("Arial", Font.BOLD, 15));
		fieldNipConfirm.setBorder(BorderFactory.createCompoundBorder(fieldNipConfirm.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldNipConfirm.addKeyListener(this);
		
		buttonSave = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource("images/save_16x16.png")));
		buttonSave.addActionListener(this);
		buttonCancel = new JButton("Cancelar", new ImageIcon(getClass().getClassLoader().getResource("images/cancel_16x16.png")));
		buttonCancel.addActionListener(this);
		
		panelTitle.add(labelTitle);
		
		panelFields.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFields.add(labelNip, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelFields.add(fieldNip, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelFields.add(labelNewNip, gbc);	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelFields.add(fieldNewNip, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelFields.add(labelNipConfirm, gbc);	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelFields.add(fieldNipConfirm, gbc);			
		
		panelButtons.add(buttonSave);
		panelButtons.add(buttonCancel);
		
		setLayout(new BorderLayout());
		add(panelTitle, BorderLayout.NORTH);
		add(panelFields, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);
		
	}
	
	public void setaTMOperationsService(ATMOperationsService aTMOperationsService) {
		this.aTMOperationsService = aTMOperationsService;
	}
	
	public void setInfoLabels() {
		AccountHolder accountHolder = aTMOperationsService.queryAccountHolder(Sessions.accountHolder);
		labelTitle.setText("Cambio de Nip: " + accountHolder.getName() + " " + accountHolder.getLastName());
		clearFields();
	}
	
	private String validateFields() {
		
		String message = "";
		
		if(String.valueOf(fieldNip.getPassword()).trim().isEmpty()) {
			return "Campo \"NIP\" no debe estar vacio.";
		} else if(String.valueOf(fieldNewNip.getPassword()).trim().length() != 4 || !String.valueOf(fieldNewNip.getPassword()).trim().matches("[0-9]+")) {
			message = "Campo \"Nuevo NIP\" debe ser cuatro digitos numericos.";
		} else if(String.valueOf(fieldNipConfirm.getPassword()).trim().length() != 4 || !String.valueOf(fieldNipConfirm.getPassword()).trim().matches("[0-9]+")) {		
			message = "Campo \"Confirmación NIP\" debe ser cuatro digitos numericos.";
		}
		
		return message;
	}
	
	private void clearFields() {
		fieldNip.setText("");
		fieldNewNip.setText("");
		fieldNipConfirm.setText("");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getSource() == fieldNip) {
			
			if(!String.valueOf(fieldNip.getPassword()).trim().isEmpty() && 
			   !String.valueOf(fieldNip.getPassword()).trim().matches("[0-9]+")) {
				fieldNip.setText(String.valueOf(fieldNip.getPassword()).replace(String.valueOf(e.getKeyChar()), ""));
			}
			
			if(String.valueOf(fieldNip.getPassword()).trim().length() > 4) {
				fieldNip.setText(String.valueOf(fieldNip.getPassword()).substring(0, 4));
			}
			
		} else if(e.getSource() == fieldNewNip) {
			
			if(!String.valueOf(fieldNewNip.getPassword()).trim().isEmpty() && 
			   !String.valueOf(fieldNewNip.getPassword()).trim().matches("[0-9]+")) {
				fieldNewNip.setText(String.valueOf(fieldNewNip.getPassword()).replace(String.valueOf(e.getKeyChar()), ""));
			}
					
			if(String.valueOf(fieldNewNip.getPassword()).trim().length() > 4) {
				fieldNewNip.setText(String.valueOf(fieldNewNip.getPassword()).substring(0, 4));
			}
			
		} else if(e.getSource() == fieldNipConfirm) {
			
			if(!String.valueOf(fieldNipConfirm.getPassword()).trim().isEmpty() && 
			   !String.valueOf(fieldNipConfirm.getPassword()).trim().matches("[0-9]+")) {
				fieldNipConfirm.setText(String.valueOf(fieldNipConfirm.getPassword()).replace(String.valueOf(e.getKeyChar()), ""));
			}
					
			if(String.valueOf(fieldNipConfirm.getPassword()).trim().length() > 4) {
				fieldNipConfirm.setText(String.valueOf(fieldNipConfirm.getPassword()).substring(0, 4));
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonSave) {
			
			String nip = String.valueOf(fieldNip.getPassword()).trim();
			String newNip = String.valueOf(fieldNewNip.getPassword()).trim();
			String nipConfirm = String.valueOf(fieldNipConfirm.getPassword()).trim();
			
			AccountHolder accountHolder = aTMOperationsService.queryAccountHolder(Sessions.accountHolder);
			
			String message = validateFields();
			if(!message.isEmpty()) {
				JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(!accountHolder.getNip().equals(nip)) {
				JOptionPane.showMessageDialog(this,"Nip Incorrecto", "Incorrecto", JOptionPane.ERROR_MESSAGE);
				fieldNip.setText("");
				return;
			}
			
			if(newNip.isEmpty() || nipConfirm.isEmpty() || !newNip.equals(nipConfirm)) {
				JOptionPane.showMessageDialog(this,"Confirmacion de Nip incorrecta", "Incorrecto", JOptionPane.ERROR_MESSAGE);
				fieldNipConfirm.setText("");
				return;
			}
			
			accountHolder.setNip(nipConfirm);
			if(aTMOperationsService.modifyAccountHolder(accountHolder)) {
				JOptionPane.showMessageDialog(this,"El nip se modifico exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
				clearFields();
			}
				
		} else if(e.getSource() == buttonCancel) {
			clearFields();
		}
		
	}

}
