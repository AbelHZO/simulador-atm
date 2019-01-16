package com.abelhzo.atm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 17:38:30
 * @file: AccountHolder.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class AccountHolder implements Cloneable {

	private Long account;
	private String name;
	private String lastName;
	private String nip;
	private Double balance;
	private List<Role> role;

	public AccountHolder() {
	}

	public AccountHolder(Long account, String name, String lastName, String nip, Double balance,
			List<Role> role) {
		super();
		this.account = account;
		this.name = name;
		this.lastName = lastName;
		this.nip = nip;
		this.balance = balance;
		this.role = role;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Role> getRole() {
		if(role == null)
			role = new ArrayList<>();
		
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println("No se puede duplicar");
		}
		return obj;
	}

}
