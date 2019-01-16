package com.abelhzo.atm.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.abelhzo.atm.dto.AccountHolder;
import com.abelhzo.atm.dto.Role;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 17:39:26
 * @file: DataSource.java
 * @license: <i>GNU General Public License<i>
 *
 */
@SuppressWarnings("serial")
public class DataSource {
	
	public static Map<Long, AccountHolder> bd = new HashMap<Long, AccountHolder>();
	
	static {
		bd.put(50047634111L, new AccountHolder(50047634111L, "Erich", "Fromm", "4682", 300000.00, new ArrayList<Role>(){{add(Role.ADMINISTRATOR); add(Role.ACCOUNTHOLDER);}}));
		bd.put(50095826544L, new AccountHolder(50095826544L, "Immanuel", "Kant", "1236", 100000.00, new ArrayList<Role>(){{add(Role.ACCOUNTHOLDER);}}));
		bd.put(50021255632L, new AccountHolder(50021255632L, "Karl", "Marx", "9885", 200000.00, new ArrayList<Role>(){{add(Role.ACCOUNTHOLDER);}}));
		bd.put(50011256695L, new AccountHolder(50011256695L, "Wilhelm", "Hegel", "5214", 150000.00, new ArrayList<Role>(){{add(Role.ACCOUNTHOLDER);}}));
		bd.put(50012876933L, new AccountHolder(50012876933L, "Friedrich", "Nietzsche", "9714", 90000.00, new ArrayList<Role>(){{add(Role.ACCOUNTHOLDER);}}));
		bd.put(50076462036L, new AccountHolder(50076462036L, "Martin", "Heidegger", "9512", 400000.00, new ArrayList<Role>(){{add(Role.ADMINISTRATOR);}}));
		bd.put(50096260035L, new AccountHolder(50096260035L, "JeanPaul", "Sartre", "7526", 25000.00, new ArrayList<Role>(){{add(Role.NONE);}}));
	}

}
