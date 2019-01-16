package com.abelhzo.atm.dto;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 21/09/2018 16:02:57
 * @file: Role.java
 * @license: <i>GNU General Public License<i>
 *
 */
public enum Role {

	ADMINISTRATOR("001", "Administrator"), ACCOUNTHOLDER("002", "Accountholder"), NONE("003", "None");

	private final String idRole;
	private final String nameRole;

	private Role(String idRole, String nameRole) {
		this.idRole = idRole;
		this.nameRole = nameRole;
	}

	public String getIdRole() {
		return idRole;
	}

	public String getNameRole() {
		return nameRole;
	}

}
