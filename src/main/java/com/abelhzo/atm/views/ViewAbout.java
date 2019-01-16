package com.abelhzo.atm.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 08/11/2018 23:17:22
 * @file: ViewAbout.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ViewAbout extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1003582893103441756L;
	
	private JPanel panelCenter, panelSouth;
	private JLabel labelInfo,labelImg;
	private JButton buttonAccept;
	
	public ViewAbout(JFrame frame, boolean modal) {
		super(frame, modal);
		setTitle("Acerca...");
		
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		
		labelInfo = new JLabel("<html><div style='text-align: center; color: rgb(100, 0, 0);'>Sistema simulado de cajero automatico (ATM)<br><i>AbelHZO ® 2018</i></div></html>");
		labelInfo.setFont(new Font("Monospace", Font.BOLD + Font.ITALIC, 14));
		labelInfo.setForeground(Color.BLACK);
		Image img = new ImageIcon(getClass().getClassLoader().getResource("images/atm.png")).getImage();
		ImageIcon imageScale = new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		labelImg = new JLabel();
		labelImg.setIcon(imageScale);
		buttonAccept = new JButton("<html><i style='color: rgb(200, 30, 60);'>Aceptar</i></html>");
		buttonAccept.addActionListener(this);
		
		panelCenter.add(labelImg);
		panelSouth.add(buttonAccept);
		
		setLayout(new BorderLayout());
		
		add(labelInfo, BorderLayout.NORTH);
		add(panelCenter, BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
