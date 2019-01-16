package com.abelhzo.atm.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.abelhzo.atm.bo.ATMOperationsService;
import com.abelhzo.atm.dto.AccountHolder;
import com.abelhzo.atm.dto.Role;
import com.abelhzo.atm.exceptions.ValidateFieldException;
import com.abelhzo.atm.utils.Formats;
import com.abelhzo.atm.utils.Sessions;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 21/09/2018 16:08:51
 * @file: ViewAdministrator.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewAdministrator extends JPanel implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2593247277132829437L;
	
	private JPanel panelName, panelComponents, panelForm, panelButtons ,panelTable;
	private JLabel labelAccountName ,labelAccount, labelName, labelLastName, labelNip, labelNipConfirm, labelRole, labelSearch;
	private JTextField fieldAccount, fieldName, fieldLastName, fieldSearch;
	private JPasswordField fieldNip, fieldNipConfirm;
	private JCheckBox checkAccounHolder, checkAdministrator;
	private JButton buttonSave, buttonModify, buttonClear, buttonSearch;
	private JTable table;
	private ModeloTabla modeloTabla;
	private JScrollPane jScroll;
	
	// Service
	private ATMOperationsService aTMOperationsService;
	
	public ViewAdministrator() {
		
		panelName = new JPanel();
		panelForm = new JPanel();
		panelButtons = new JPanel();
		panelTable = new JPanel();
		panelComponents = new JPanel();
		
		labelAccountName = new JLabel();
		labelAccountName.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 16));
		labelAccount = new JLabel("Numero de cuenta: ");
		labelName = new JLabel("Nombre: ");
		labelLastName = new JLabel("Apellido: ");
		labelNip = new JLabel("Nip: ");
		labelNipConfirm =  new JLabel("Confirma Nip: ");
		labelRole = new JLabel("Permisos: ");
		
		labelSearch = new JLabel("Buscar: ");
		
		fieldAccount = new JTextField(12);
		fieldAccount.setFont(new Font("Arial", Font.BOLD, 15));
		fieldAccount.setBorder(BorderFactory.createCompoundBorder(fieldAccount.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldAccount.addKeyListener(this);
		fieldName = new JTextField(12);
		fieldName.setFont(new Font("Arial", Font.BOLD, 15));
		fieldName.setBorder(BorderFactory.createCompoundBorder(fieldName.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldName.addKeyListener(this);
		fieldLastName = new JTextField(12);
		fieldLastName.setFont(new Font("Arial", Font.BOLD, 15));
		fieldLastName.setBorder(BorderFactory.createCompoundBorder(fieldLastName.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldLastName.addKeyListener(this);
		fieldNip = new JPasswordField(12);
		fieldNip.setFont(new Font("Arial", Font.BOLD, 15));
		fieldNip.setBorder(BorderFactory.createCompoundBorder(fieldNip.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldNip.addKeyListener(this);
		fieldNipConfirm = new JPasswordField(12);
		fieldNipConfirm.setFont(new Font("Arial", Font.BOLD, 15));
		fieldNipConfirm.setBorder(BorderFactory.createCompoundBorder(fieldNipConfirm.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
		fieldNipConfirm.addKeyListener(this);
		
		fieldSearch = new JTextField(14);
		fieldSearch.setFont(new Font("Arial", Font.ITALIC, 13));
		fieldSearch.setBorder(BorderFactory.createCompoundBorder(fieldSearch.getBorder(), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		fieldSearch.setToolTipText("Burcar por nombre.");
		fieldSearch.addKeyListener(this);
		
		checkAccounHolder = new JCheckBox("Cuenta Habiente");
		checkAdministrator = new JCheckBox("Administrador");
		
		buttonSave = new JButton("Guardar", new ImageIcon(getClass().getClassLoader().getResource("images/save_16x16.png")));
		buttonModify = new JButton("Modificar", new ImageIcon(getClass().getClassLoader().getResource("images/edit_16x16.png")));
		buttonClear = new JButton("Limpiar", new ImageIcon(getClass().getClassLoader().getResource("images/refresh_16x16.png")));
		buttonSearch = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/search_16x16.png")));
		
		buttonSave.addActionListener(this);
		buttonModify.addActionListener(this);
		buttonClear.addActionListener(this);
		buttonSearch.addActionListener(this);
		
		panelButtons.add(buttonSave);
		panelButtons.add(buttonModify);
		panelButtons.add(buttonClear);
		panelButtons.add(labelSearch);
		panelButtons.add(fieldSearch);
		panelButtons.add(buttonSearch);
		
		modeloTabla = new ModeloTabla();
		table = new JTable(modeloTabla);
		table.addMouseListener(this);
		table.addKeyListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(560, 150));
		
		jScroll = new JScrollPane();
		jScroll.setViewportView(table);
		
		panelName.add(labelAccountName);
		
		panelForm.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelForm.add(labelAccount, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelForm.add(fieldAccount, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelForm.add(labelName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelForm.add(fieldName, gbc);	
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelForm.add(labelLastName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelForm.add(fieldLastName, gbc);	
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelForm.add(labelNip, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelForm.add(fieldNip, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelForm.add(labelNipConfirm, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelForm.add(fieldNipConfirm, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelForm.add(labelRole, gbc);	
		gbc.gridx = 0;
		gbc.gridy = 6;
		panelForm.add(checkAdministrator, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		panelForm.add(checkAccounHolder, gbc);
		gbc.gridwidth = 5;
		gbc.gridx = 0;
		gbc.gridy = 7;
		panelForm.add(panelButtons, gbc);	
		
		panelTable.add(jScroll);
		
		panelComponents.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		panelComponents.add(panelForm, gbc1);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		panelComponents.add(panelTable, gbc1);
		
		setLayout(new BorderLayout());
		add(panelName, BorderLayout.NORTH);
		add(panelComponents, BorderLayout.CENTER);
		
	}
	
	public void setaTMOperationsService(ATMOperationsService aTMOperationsService) {
		this.aTMOperationsService = aTMOperationsService;
	}
	
	public void initTable() {
		AccountHolder accountHolder = aTMOperationsService.queryAccountHolder(Sessions.accountHolder);
		labelAccountName.setText("Administrador: " + accountHolder.getName() + " " + accountHolder.getLastName());
		modeloTabla.setAllAccountHolder(aTMOperationsService.queryAllAccountsHolder());
		clearFields();
	}
	
	private void clearFields() {
		fieldAccount.setBackground(Color.WHITE);
		fieldName.setBackground(Color.WHITE);
		fieldLastName.setBackground(Color.WHITE);
		fieldNip.setBackground(Color.WHITE);
		fieldNipConfirm.setBackground(Color.WHITE);
		fieldAccount.setEnabled(true);
		fieldAccount.setText("");
		fieldName.setText("");
		fieldLastName.setText("");
		fieldNip.setText("");
		fieldNipConfirm.setText("");
		checkAccounHolder.setSelected(false);
		checkAdministrator.setSelected(false);
		fieldSearch.setText("");
	}
	
	private void selectAccountHolder() {
		
		fieldAccount.setEnabled(false);
		
		int row = table.getSelectedRow();
		
		if(row == -1) return;
		
		AccountHolder accountHolder = new AccountHolder();
		accountHolder.setAccount(Long.parseLong(modeloTabla.getValueAt(row, 0).toString()));
		
		accountHolder = aTMOperationsService.queryAccountHolder(accountHolder);
		
		fieldAccount.setText(accountHolder.getAccount().toString());
		fieldName.setText(accountHolder.getName());
		fieldLastName.setText(accountHolder.getLastName());
		fieldNip.setText(accountHolder.getNip());
		fieldNipConfirm.setText(accountHolder.getNip());
		
		checkAdministrator.setSelected(false);
		checkAccounHolder.setSelected(false);
		for(Role role : accountHolder.getRole()) {
			switch(role.getNameRole()) {
				case "Administrator":
					checkAdministrator.setSelected(true);
					break;
				case "Accountholder":
					checkAccounHolder.setSelected(true);
					break;
			}
		}
		
	}
	
	private void validateFields(AccountHolder accountHolder) throws ValidateFieldException {
		
		String message = "";
		
		fieldAccount.setBackground(Color.WHITE);
		fieldName.setBackground(Color.WHITE);
		fieldLastName.setBackground(Color.WHITE);
		fieldNip.setBackground(Color.WHITE);
		fieldNipConfirm.setBackground(Color.WHITE);
		
		if(accountHolder.getAccount().toString().length() != 11 || !accountHolder.getAccount().toString().matches("[0-9]+")) {
			fieldAccount.setBackground(Color.RED);
			message = "El numero de cuenta debe de ser de once digitos.";
		} else if(accountHolder.getName().isEmpty() || !accountHolder.getName().toString().matches("[A-Za-z]+")) {
			fieldName.setBackground(Color.RED);
			message = "Campo nombre contiene numeros o esta vacio.";
		} else if(accountHolder.getLastName().isEmpty() || !accountHolder.getLastName().toString().matches("[A-Za-z]+") ) { 
			fieldLastName.setBackground(Color.RED);
			message = "Campo apellido contiene numeros o esta vacio.";
		} else if(accountHolder.getNip().isEmpty() || !accountHolder.getNip().toString().matches("[0-9]+") || accountHolder.getNip().toString().length() != 4) {
			fieldNip.setBackground(Color.RED);
			message = "Campo nip contiene letras o esta vacio. Debe ser de cuatro digitos.";
		} else if(!accountHolder.getNip().equals(String.valueOf(fieldNipConfirm.getPassword()).trim()) || String.valueOf(fieldNipConfirm.getPassword()).trim().length() != 4) {
			fieldNipConfirm.setBackground(Color.RED);
			message = "Su confirmación de password es incorrecta. Debe ser de cuatro digitos.";
		}
		
		if(!message.isEmpty()) {
			throw new ValidateFieldException(message);
		}
		
	}
	
	private AccountHolder setDataAccountHolder() {
		
		try {
			
			AccountHolder accountHolder = new AccountHolder();
			accountHolder.setAccount(Long.parseLong(fieldAccount.getText().trim()));
			accountHolder.setName(fieldName.getText().trim());
			accountHolder.setLastName(fieldLastName.getText().trim());
			accountHolder.setNip(String.valueOf(fieldNip.getPassword()).trim());
			accountHolder.setBalance(0.0);
			
			validateFields(accountHolder);
			
			if(checkAccounHolder.isSelected())
				accountHolder.getRole().add(Role.ACCOUNTHOLDER);
			if(checkAdministrator.isSelected())
				accountHolder.getRole().add(Role.ADMINISTRATOR);
			
			return accountHolder;
		
		} catch (NumberFormatException e) {
			fieldAccount.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
			return null;
		}  catch (ValidateFieldException ex1) {
			JOptionPane.showMessageDialog(null, ex1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
			
		
	}
	
	private void searchAccountHolder() {
		
		List<AccountHolder> filterListAccoutHolder = new ArrayList<>(); 
		for(AccountHolder accoutHolder : aTMOperationsService.queryAllAccountsHolder()) {
			if(accoutHolder.getName().toLowerCase().startsWith(fieldSearch.getText().trim().toLowerCase()) || 
			   accoutHolder.getLastName().toLowerCase().startsWith(fieldSearch.getText().trim().toLowerCase())) {
				filterListAccoutHolder.add(accoutHolder);
			}
		}
		
		modeloTabla.setAllAccountHolder(filterListAccoutHolder);
		
	}
	
	private void caretPositionFields() {
		
		if(fieldAccount.hasFocus()) 
			fieldAccount.setCaretPosition(fieldAccount.getText().length());
		else if(fieldName.hasFocus())
			fieldName.setCaretPosition(fieldName.getText().length());
		else if(fieldLastName.hasFocus())
			fieldLastName.setCaretPosition(fieldLastName.getText().length());
		else if(fieldNip.hasFocus())
			fieldNip.setCaretPosition(fieldNip.getPassword().length);
		else if(fieldNipConfirm.hasFocus())
			fieldNipConfirm.setCaretPosition(fieldNipConfirm.getPassword().length);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonSave) {
			
			AccountHolder accountHolder = setDataAccountHolder();
			
			if(accountHolder == null) return;
			
			if(aTMOperationsService.queryAccountHolder(accountHolder) != null) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(aTMOperationsService.addAccountHolder(accountHolder)) {
				JOptionPane.showMessageDialog(null, "El usuario se guardo correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
				initTable();
			} else {
				JOptionPane.showMessageDialog(null, "El usuario no se pudo guardar.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		} else if (e.getSource() == buttonModify) {
			
			AccountHolder accountHolder = setDataAccountHolder();
			
			if(accountHolder == null) return;
			
			if(aTMOperationsService.modifyAccountHolder(accountHolder)) {
				JOptionPane.showMessageDialog(null, "El usuario se modifico correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
				initTable();
			} else {
				JOptionPane.showMessageDialog(null, "El usuario no se pudo modificar.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (e.getSource() == buttonClear) {
			initTable();
		} else if(e.getSource() == buttonSearch) {
			searchAccountHolder();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		caretPositionFields();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(table.hasFocus())
			selectAccountHolder();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if(e.getSource() == table && table.hasFocus()) {
			selectAccountHolder();
		} else {
			caretPositionFields();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getSource() == fieldSearch) {
			searchAccountHolder();
		} else if(e.getSource() == table) {  //Cuando de enfoca la tabla con TAB
			selectAccountHolder();
		} else if(e.getSource() == fieldAccount) {
			
			if(!fieldAccount.getText().trim().isEmpty() && !fieldAccount.getText().trim().matches("[0-9]+")) {
				fieldAccount.setText(fieldAccount.getText().replace(String.valueOf(e.getKeyChar()), ""));
			}
			
			if(fieldAccount.getText().trim().length() > 11) {
				fieldAccount.setText(fieldAccount.getText().substring(0, 11));
			}
			
		} else if(e.getSource() == fieldName) {
			
			if(!fieldName.getText().trim().isEmpty() && !fieldName.getText().trim().matches("[A-Za-z]+")) {
				fieldName.setText(fieldName.getText().replace(String.valueOf(e.getKeyChar()), ""));
			}
			
		} else if(e.getSource() == fieldLastName) {
			
			if(!fieldLastName.getText().trim().isEmpty() && !fieldLastName.getText().trim().matches("[A-Za-z]+")) {
				fieldLastName.setText(fieldLastName.getText().replace(String.valueOf(e.getKeyChar()), ""));
			}
			
		} else if(e.getSource() == fieldNip) {
			
			if(!String.valueOf(fieldNip.getPassword()).trim().isEmpty() && 
			   !String.valueOf(fieldNip.getPassword()).trim().matches("[0-9]+")) {
				fieldNip.setText(String.valueOf(fieldNip.getPassword()).replace(String.valueOf(e.getKeyChar()), ""));
			}
			
			if(String.valueOf(fieldNip.getPassword()).trim().length() > 4) {
				fieldNip.setText(String.valueOf(fieldNip.getPassword()).substring(0, 4));
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

}

class ModeloTabla extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -24694694739612280L;
	
	private String[] columnName = {"No. Cuenta", "Nombre", "Apellido", "Saldo"};
	private List<AccountHolder> accounts = new ArrayList<>();
	
	public void setAllAccountHolder(List<AccountHolder> queryAllAccountsHolder) {
		this.accounts = queryAllAccountsHolder;
		fireTableDataChanged();
	}
	
	public String getColumnName(int columnIndex) {
		return columnName[columnIndex];
	}
	
	@Override
	public int getRowCount() {
		return accounts.size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(rowIndex == -1) return -1;
		
		AccountHolder accountHolder = accounts.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				return accountHolder.getAccount();
			case 1:
				return accountHolder.getName();
			case 2:
				return accountHolder.getLastName();
			case 3:
				return Formats.moneda(accountHolder.getBalance());
			default:
			break;
		}
		
		return -1;
	}
	
}
