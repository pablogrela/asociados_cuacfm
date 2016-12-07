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
package org.cuacfm.members.model.program;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.cuacfm.members.model.account.Account;

/** The Class Program. */
@Entity
public class Program implements Serializable {

	private static final long serialVersionUID = 1L;
	/** The id. */
	@Id
	@GeneratedValue
	private Long id;
	/** The name. */
	@Column(unique = true)
	private String name;

	/** The description. */
	private String description;

	/** The periodicity. */
	private Float periodicity;

	/** The duration. */
	private int duration;

	/** The accounts. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UserPrograms", joinColumns = { @JoinColumn(name = "programId") }, inverseJoinColumns = { @JoinColumn(name = "accountId") })
	private List<Account> accounts;

	/** The account payer. */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountPayer")
	private Account accountPayer;

	/** The active is a check if program if active*. */
	private boolean active;

	/** Instantiates a new program. */
	public Program() {
		super();
	}

	/**
	 * Instantiates a new program.
	 *
	 * @param name the name
	 * @param periodicity the periodicity
	 * @param description the description
	 * @param duration the duration
	 * @param accounts the accounts
	 */
	public Program(String name, Float periodicity, String description, int duration, List<Account> accounts) {
		super();
		this.name = name;
		this.periodicity = periodicity;
		this.description = description;
		this.duration = duration;
		this.accounts = accounts;
		this.active = false;
	}

	/**
	 * Get the id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the periodicity.
	 *
	 * @return the periodicity
	 */
	public Float getPeriodicity() {
		return periodicity;
	}

	/**
	 * Sets the periodicity.
	 *
	 * @param periodicity the new periodicity
	 */
	public void setPeriodicity(Float periodicity) {
		this.periodicity = periodicity;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Sets the accounts.
	 *
	 * @param accounts the new accounts
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Gets the account payer.
	 *
	 * @return the account payer
	 */
	public Account getAccountPayer() {
		return accountPayer;
	}

	/**
	 * Sets the account payer.
	 *
	 * @param accountPayer the new account payer
	 */
	public void setAccountPayer(Account accountPayer) {
		this.accountPayer = accountPayer;
	}

	@Override
	public String toString() {
		return "Program [id=" + id + ", name=" + name + ", description=" + description + ", periodicity=" + periodicity + ", duration=" + duration
				+ ", accounts=" + accounts + ", accountPayer=" + accountPayer + ", active=" + active + "]";
	}

}
