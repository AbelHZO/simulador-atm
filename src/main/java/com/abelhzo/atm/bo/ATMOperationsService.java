package com.abelhzo.atm.bo;

import java.util.List;

import com.abelhzo.atm.dto.AccountHolder;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 17:54:07
 * @file: ATMOperationsService.java
 * @license: <i>GNU General Public License<i>
 *
 */
public interface ATMOperationsService {
	
	public boolean login(AccountHolder accountHolder); //ingresar
	
	public boolean validateBalance(AccountHolder accountHolder);  //validar que no supere los saldos.
	
	public String withdrawals(AccountHolder accountHolder); //retirar dinero
	
	public String deposit(AccountHolder accountHolder);  //depositar dinero
	
	public Boolean transfer(AccountHolder accountHolder, Long accountDestination);  //transferencia
	
	public String validateAccountDestiny(Long accountDestination);
	
	public AccountHolder queryAccountHolder(AccountHolder accountHolder);  //consulta de usuario
	
	public List<AccountHolder> queryAllAccountsHolder();  //consultar todos los usuarios
	
	public boolean addAccountHolder(AccountHolder accountHolder);  //agregar cuentahabiente
	
	public boolean modifyAccountHolder(AccountHolder accountHolder);  //modificar cuentahabiente

}
