package com.abelhzo.atm.utils;

import com.abelhzo.atm.dto.AccountHolder;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 21/09/2018 17:17:14
 * @file: Sessions.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class Sessions {
	
	public static AccountHolder accountHolder;

	public static AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public static void setAccountHolder(AccountHolder accountHolder) {
		Sessions.accountHolder = accountHolder;
	}

}
