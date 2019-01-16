package com.abelhzo.atm.bo;

import java.util.ArrayList;
import java.util.List;

import com.abelhzo.atm.datasource.DataSource;
import com.abelhzo.atm.dto.AccountHolder;
import com.abelhzo.atm.utils.Formats;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 17:59:55
 * @file: ATMOperationsServiceImpl.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class ATMOperationsServiceImpl implements ATMOperationsService {

	@Override
	public boolean login(AccountHolder accountHolder) {
		
		AccountHolder account = DataSource.bd.get(accountHolder.getAccount());
		
		if(account != null && account.getNip().equals(accountHolder.getNip())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public boolean validateBalance(AccountHolder accountHolder) {
		
		AccountHolder account = DataSource.bd.get(accountHolder.getAccount());
		
		return accountHolder.getBalance() <= account.getBalance();
	}

	@Override
	public String withdrawals(AccountHolder accountHolder) {
		
		AccountHolder account = DataSource.bd.get(accountHolder.getAccount());
		account.setBalance(account.getBalance() - accountHolder.getBalance());
		
		return "Su retiro de " + Formats.moneda(accountHolder.getBalance()) + " se realizo de manera exitosa.";
		
	}

	@Override
	public String deposit(AccountHolder accountHolder) {
		
		AccountHolder account = DataSource.bd.get(accountHolder.getAccount());
		account.setBalance(account.getBalance() + accountHolder.getBalance());
		
		return "Su deposito de " + Formats.moneda(accountHolder.getBalance()) + " se realizo de manera exitosa.";
	}

	@Override
	public Boolean transfer(AccountHolder accountHolder, Long accountDestination) {
		
		AccountHolder accountOrigin = DataSource.bd.get(accountHolder.getAccount());
		
		AccountHolder accountDestina = DataSource.bd.get(accountDestination);
		
		accountOrigin.setBalance(accountOrigin.getBalance() - accountHolder.getBalance());
		accountDestina.setBalance(accountDestina.getBalance() + accountHolder.getBalance());
		
		return true;
	}

	@Override
	public String validateAccountDestiny(Long accountDestination) {
		AccountHolder accountDestina = DataSource.bd.get(accountDestination);
		
		if(accountDestina != null) {
			return accountDestina.getName() + " " + accountDestina.getLastName();
		}
		
		return null;
	}
	
	@Override
	public AccountHolder queryAccountHolder(AccountHolder accountHolder) {
		return DataSource.bd.get(accountHolder.getAccount());
	}

	@Override
	public List<AccountHolder> queryAllAccountsHolder() {
		return new ArrayList<AccountHolder>(DataSource.bd.values());
	}

	@Override
	public boolean addAccountHolder(AccountHolder accountHolder) {
		DataSource.bd.put(accountHolder.getAccount(), accountHolder);
		return queryAccountHolder(accountHolder) != null;
	}

	@Override
	public boolean modifyAccountHolder(AccountHolder accountHolder) {
		
		AccountHolder account = DataSource.bd.get(accountHolder.getAccount());
		account.setName(accountHolder.getName());
		account.setLastName(accountHolder.getLastName());
		account.setNip(accountHolder.getNip());
		account.setRole(accountHolder.getRole());
		
		return queryAccountHolder(accountHolder) != null;
	}

}
