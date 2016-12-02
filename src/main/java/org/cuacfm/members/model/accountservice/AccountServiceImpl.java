/**
 * Copyright (C) 2015 Pablo Grela Palleiro (pablogp_9@hotmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cuacfm.members.model.accountservice;

import java.util.ArrayList;
import java.util.List;

import org.cuacfm.members.model.account.Account;
import org.cuacfm.members.model.account.AccountDTO;
import org.cuacfm.members.model.account.AccountRepository;
import org.cuacfm.members.model.bankaccount.BankAccount;
import org.cuacfm.members.model.bankaccount.BankAccountRepository;
import org.cuacfm.members.model.exceptions.ExistInscriptionsException;
import org.cuacfm.members.model.exceptions.UniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** The Class AccountService. */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	/** The account repository. */
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/** Instantiates a new account service. */
	public AccountServiceImpl() {
		// Default empty constructor.
	}

	/**
	 * Save, saves an account into database.
	 *
	 * @param account the account
	 * @return the account
	 * @throws UniqueException
	 */
	@Override
	public Account save(Account account) throws UniqueException {

		// It is verified that there is not exist dni
		if (accountRepository.findByDni(account.getDni()) != null) {
			throw new UniqueException("Dni", account.getDni());
		}

		// It is verified that there is not exist login
		if (accountRepository.findByLogin(account.getLogin()) != null) {
			throw new UniqueException("Login", account.getLogin());
		}

		// It is verified that there is not exist email
		if (accountRepository.findByEmail(account.getEmail()) != null) {
			throw new UniqueException("Email", account.getEmail());
		}

		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return accountRepository.save(account);
	}

	/**
	 * Update, updates an user registered into bd depending if he wants to update his password or not.
	 *
	 * @param account the account
	 * @param passwordUpdate the passwordUpdate
	 * @return the account
	 * @throws UniqueException
	 */
	@Override
	public Account update(Account account, boolean newPassword) throws UniqueException {

		// It is verified that there is not exist dni
		Account accountSearch = accountRepository.findByDni(account.getDni());
		if ((accountSearch != null) && (accountSearch.getId() != account.getId())) {
			throw new UniqueException("Dni", account.getDni());
		}

		// It is verified that there is not exist login
		accountSearch = accountRepository.findByLogin(account.getLogin());
		if ((accountSearch != null) && (accountSearch.getId() != account.getId())) {
			throw new UniqueException("Login", account.getLogin());
		}

		// It is verified that there is not exist email
		accountSearch = accountRepository.findByEmail(account.getEmail());
		if ((accountSearch != null) && (accountSearch.getId() != account.getId())) {
			throw new UniqueException("Email", account.getEmail());
		}

		if (newPassword) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
		}
		return accountRepository.update(account);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @throws ExistInscriptionsException the exist inscriptions exception
	 */
	@Override
	public void delete(Long id) {
		accountRepository.delete(id);
	}

	/**
	 * Subscribe Account.
	 *
	 * @param id the id
	 */
	@Override
	public void subscribe(Long id) {

		Account account = accountRepository.findById(id);
		account.setActive(true);
		accountRepository.update(account);
	}

	/**
	 * Unsubscribe Account.
	 *
	 * @param id the id
	 */
	@Override
	public void unsubscribe(Long id) {

		Account account = accountRepository.findById(id);
		account.setActive(false);
		accountRepository.update(account);
	}

	/**
	 * Find by dni.
	 *
	 * @param dni the dni
	 * @return the account
	 */
	@Override
	public Account findByDni(String dni) {
		return accountRepository.findByDni(dni);
	}

	/**
	 * Find by email returns user which has this email.
	 *
	 * @param email the email
	 * @return the account
	 */
	@Override
	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	/**
	 * Find by id returns user which has this identifier.
	 *
	 * @param id the id
	 * @return the account
	 */
	@Override
	public Account findByLogin(String login) {
		return accountRepository.findByLogin(login);
	}

	/**
	 * Find by id returns user which has this identifier.
	 *
	 * @param id the id
	 * @return the account
	 */
	@Override
	public Account findById(Long id) {
		return accountRepository.findById(id);
	}

	/**
	 * Match password check if password match with the user.
	 *
	 * @param account the account
	 * @param rawPassword the raw password
	 * @return true, if successful
	 */
	@Override
	public boolean matchPassword(Account account, String rawPassword) {
		return accountRepository.matchPassword(account, rawPassword);
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	@Override
	public List<Account> getUsers() {
		return accountRepository.getUsers();
	}

	@Override
	public List<Account> getUsersDirectDebit() {
		return accountRepository.getUsersDirectDebit();
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.getAccounts();
	}

	@Override
	public List<Account> getAccountsOrderByActive() {
		return accountRepository.getAccountsOrderByActive();
	}
	
	@Override
	public List<AccountDTO> getAccountsDTO() {

		List<Account> accounts = accountRepository.getAccountsOrderByActive();
		List<AccountDTO> accountsDTO = new ArrayList<>();
		for (Account account : accounts) {
			AccountDTO accountDTO = new AccountDTO(account.getId(), account.getLogin(), account.getDni(), account.getEmail(), account.getName(),
					account.getNickName(), account.getAddress(), account.isActive(), account.getRole(), account.getInstallments());

			if (account.getAccountType() != null) {
				accountDTO.setAccountType(account.getAccountType().getName());
			}
			if (account.getMethodPayment() != null) {
				accountDTO.setMethodPayment(account.getMethodPayment().getName());
			}
			accountsDTO.add(accountDTO);
		}

		return accountsDTO;
	}

	/**
	 * Gets the name users with role=ROLE_USER an active=true.
	 *
	 * @return the name users
	 */
	@Override
	public List<String> getUsernames() {
		return accountRepository.getUsernames();

	}

	/**
	 * Save bank account.
	 *
	 * @param bankAccount the bank account
	 * @return the bank account
	 */
	@Override
	public BankAccount saveBankAccount(BankAccount bankAccount) {
		int id = 0;
		if (bankAccount.getAccount().getBankAccounts() != null) {
			id = bankAccount.getAccount().getBankAccounts().size() + 1;
		}

		String mandate = bankAccount.getAccount().getId() + "_" + bankAccount.getAccount().getDni() + "_" + id;
		bankAccount.setMandate(mandate);
		return bankAccountRepository.save(bankAccount);
	}

	/**
	 * Active bank account by account id.
	 *
	 * @param accountId the account id
	 * @return the bank account
	 */
	@Override
	public BankAccount activeBankAccountByAccountId(Long accountId) {
		return bankAccountRepository.activeBankAccountByAccountId(accountId);
	}
}
