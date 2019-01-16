package com.abelhzo.atm.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.abelhzo.atm.bo.ATMOperationsService;
import com.abelhzo.atm.bo.ATMOperationsServiceImpl;
import com.abelhzo.atm.dto.Role;
import com.abelhzo.atm.utils.Sessions;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 18:52:16
 * @file: ViewWindow.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -420914928739750159L;
	
	//Menu
	private JMenuBar menuBar;
	private JMenu menuOperations, menuConfig, menuAdministrator, menuAbout;
	private ButtonGroup buttonGroupOperations;
	private JRadioButtonMenuItem radioMenuDrawals, radioMenuDeposit, radioMenuTransfer;
	private JMenuItem menuItemLogout, menuItemClose, menuItemQuery, menuItemChangeNip, menuItemUsuarios, menuItemInfo;
	
	//Services
	private ATMOperationsService aTMOperationsService = new ATMOperationsServiceImpl();
	
	//Views Paneles
	private ViewLogin viewLogin = new ViewLogin(this);
	private ViewWithdrawals viewWithdrawals = new ViewWithdrawals();
	private ViewDeposit viewDeposit = new ViewDeposit();
	private ViewTransfer viewTransfer = new ViewTransfer();
	private ViewAdministrator viewAdministrator = new ViewAdministrator();
	private ViewChangeNip viewChangeNip = new ViewChangeNip();
	private ViewAbout viewAbout;
	
	public ViewWindow() {
		setTitle("Simulador ATM @AbelHZO");
		viewLogin.setaTMOperationsService(aTMOperationsService);
		viewWithdrawals.setaTMOperationsService(aTMOperationsService);
		viewDeposit.setaTMOperationsService(aTMOperationsService);
		viewTransfer.setaTMOperationsService(aTMOperationsService);
		viewAdministrator.setaTMOperationsService(aTMOperationsService);
		viewChangeNip.setaTMOperationsService(aTMOperationsService);
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/monitor_16x16.png")).getImage());
		add(viewLogin);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	private void buildMenu() {
		menuBar =  new JMenuBar();
		
		String roles = "";
		for(Role role : Sessions.accountHolder.getRole()) {
			roles += role + " ";
		}
		
		menuOperations = new JMenu("Operaciones");
		menuOperations.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/harddisk_16x16.png")));
		buttonGroupOperations = new ButtonGroup();
		radioMenuDrawals = new JRadioButtonMenuItem("Retirar",  new ImageIcon(getClass().getClassLoader().getResource("images/coins_delete_16x16.png")));
		radioMenuDrawals.setSelected(true);
		radioMenuDeposit = new JRadioButtonMenuItem("Depositar", new ImageIcon(getClass().getClassLoader().getResource("images/coins_add_16x16.png")));
		radioMenuTransfer = new JRadioButtonMenuItem("Transferir", new ImageIcon(getClass().getClassLoader().getResource("images/arrow_switch_16x16.png")));
		radioMenuDrawals.addActionListener(this);
		radioMenuDeposit.addActionListener(this);
		radioMenuTransfer.addActionListener(this);
		buttonGroupOperations.add(radioMenuDrawals);
		buttonGroupOperations.add(radioMenuDeposit);
		buttonGroupOperations.add(radioMenuTransfer);
		menuItemLogout = new JMenuItem("Salir", new ImageIcon(getClass().getClassLoader().getResource("images/logout_16x16.png")));
		menuItemLogout.addActionListener(this);
		menuItemClose = new JMenuItem("Cerrar", new ImageIcon(getClass().getClassLoader().getResource("images/delete_16x16.png")));
		menuItemClose.addActionListener(this);
		
		menuConfig = new JMenu("Configuracion");
		menuConfig.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/settings_16x16.png")));
		if(roles.contains("ADMINISTRATOR")) menuItemQuery = new JMenuItem("Consulta", new ImageIcon(getClass().getClassLoader().getResource("images/folder_16x16.png")));
		menuItemChangeNip = new JMenuItem("Cambiar Nip", new ImageIcon(getClass().getClassLoader().getResource("images/key_16x16.png")));
		menuItemChangeNip.addActionListener(this);
		menuItemUsuarios = new JMenuItem("Usuarios", new ImageIcon(getClass().getClassLoader().getResource("images/user_16x16.png")));
		menuItemUsuarios.addActionListener(this);
		menuItemInfo = new JMenuItem("Info", new ImageIcon(getClass().getClassLoader().getResource("images/information_16x16.png")));
		menuItemInfo.addActionListener(this);
		
		if(roles.contains("ADMINISTRATOR")) {
			menuAdministrator = new JMenu("Administrador");
			menuAdministrator.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/globe_16x16.png")));
			menuAdministrator.add(menuItemUsuarios);
		}
		menuAbout = new JMenu("Acerca");
		menuAbout.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/help_16x16.png")));
		menuAbout.add(menuItemInfo);
		
		menuOperations.add(radioMenuDrawals);
		menuOperations.add(radioMenuDeposit);
		menuOperations.add(radioMenuTransfer);
		menuOperations.addSeparator();
		menuOperations.add(menuItemLogout);
		menuOperations.add(menuItemClose);
		
		if(roles.contains("ADMINISTRATOR")) menuConfig.add(menuItemQuery);
		menuConfig.add(menuItemChangeNip);
		
		menuBar.add(menuOperations);
		menuBar.add(menuConfig);
		if(roles.contains("ADMINISTRATOR")) menuBar.add(menuAdministrator);
		menuBar.add(menuAbout);
		
		setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuItemLogout) {
			changeViewPanel("toViewLogin");
			Sessions.accountHolder = null;
		} else if(e.getSource() == radioMenuDrawals) {
			changeViewPanel("toViewWithdrawals");
		} else if(e.getSource() == radioMenuDeposit) {
			changeViewPanel("toViewDeposit");
		} else if(e.getSource() == radioMenuTransfer) {
			changeViewPanel("toViewTransfer");
		} else if(e.getSource() == menuItemClose) {
			System.exit(0);
		} else if(e.getSource() == menuItemUsuarios) {
			changeViewPanel("toViewAdministrator");
		} else if(e.getSource() == menuItemChangeNip) {
			changeViewPanel("toViewChangeNip");
		} else if(e.getSource() == menuItemInfo) {
			changeViewPanel("toViewAbout");
		}
		
	}
	
	public void changeViewPanel(String view) {
		
		if(view.equals("toViewAbout")) {
			viewAbout = new ViewAbout(this, true);
			viewAbout.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			return;
		}

		getContentPane().removeAll();
		
		if(getJMenuBar() == null) buildMenu();
		
		if(view.equals("toViewLogin")) {
			add(viewLogin);
			setJMenuBar(null);
		} else if(view.equals("toViewWithdrawals")) {
			viewWithdrawals.setInfoLabels();
			add(viewWithdrawals);
		} else if(view.equals("toViewDeposit")) {
			viewDeposit.setInfoLabels();
			add(viewDeposit);
		} else if(view.equals("toViewTransfer")) {
			viewTransfer.setInfoLabels();
			add(viewTransfer);
		} else if(view.equals("toViewAdministrator")) {
			viewAdministrator.initTable();
			add(viewAdministrator);
		} else if(view.equals("toViewChangeNip")) {
			add(viewChangeNip);
		}
		
		pack();
		repaint();
		revalidate();
		
	}

}
