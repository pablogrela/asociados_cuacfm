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
package org.cuacfm.members.web.program;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.cuacfm.members.model.account.Account;
import org.cuacfm.members.model.program.Program;
import org.cuacfm.members.model.program.ProgramCategory;
import org.cuacfm.members.model.program.ProgramLanguage;
import org.cuacfm.members.model.program.ProgramThematic;
import org.cuacfm.members.model.program.ProgramType;
import org.hibernate.validator.constraints.NotBlank;

/** The Class ProgramForm. */
public class ProgramForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String MAX_CHARACTERS = "{max.characters}";

	/** The name. */
	@NotBlank(message = ProgramForm.NOT_BLANK_MESSAGE)
	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String name;

	/** The description. */
	@NotBlank(message = ProgramForm.NOT_BLANK_MESSAGE)
	@Size(max = 5000, message = ProgramForm.MAX_CHARACTERS)
	private String description;

	/** The duration. */
	@NotNull
	@Min(0)
	private int duration;

	/** The periodicity. */
	@Max(7)
	@Min(0)
	private Float periodicity;

	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String email;

	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String twitter;

	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String facebook;

	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String podcast;

	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String web;

	private List<Account> accounts;

	private String login;

	private Account accountPayer;

	@Size(max = 50, message = ProgramForm.MAX_CHARACTERS)
	private String accountPayerName;

	private List<ProgramType> programTypes;
	@NotNull(message = ProgramForm.NOT_BLANK_MESSAGE)
	private Integer programTypeId;

	private List<ProgramThematic> programThematics;
	@NotNull(message = ProgramForm.NOT_BLANK_MESSAGE)
	private Integer programThematicId;

	private List<ProgramCategory> programCategories;
	@NotNull(message = ProgramForm.NOT_BLANK_MESSAGE)
	private Integer programCategoryId;

	private List<ProgramLanguage> programLanguages;
	@NotNull(message = ProgramForm.NOT_BLANK_MESSAGE)
	private Integer programLanguageId;

	/**
	 * Instantiates a new program form.
	 */
	public ProgramForm() {
		super();
	}

	/**
	 * Get the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 *
	 * @param name String, the new name
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
	 * Adds the account.
	 *
	 * @param account the account
	 */
	public void addAccount(Account account) {
		accounts.add(account);
	}

	/**
	 * Removes the account.
	 *
	 * @param id the id
	 */
	public void removeAccount(Long id) {
		Account accountToDelete = null;
		for (Account a : accounts) {
			if (a.getId() == id) {
				accountToDelete = a;
				break;
			}
		}
		accounts.remove(accountToDelete);
	}

	/**
	 * Get the description..
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description.
	 *
	 * @param description String, the new description
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
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
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

	/**
	 * Gets the account payer name.
	 *
	 * @return the account payer name
	 */
	public String getAccountPayerName() {
		return accountPayerName;
	}

	/**
	 * Sets the account payer name.
	 *
	 * @param accountPayerName the new account payer name
	 */
	public void setAccountPayerName(String accountPayerName) {
		this.accountPayerName = accountPayerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getPodcast() {
		return podcast;
	}

	public void setPodcast(String podcast) {
		this.podcast = podcast;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<ProgramType> getProgramTypes() {
		return programTypes;
	}

	public void setProgramTypes(List<ProgramType> programTypes) {
		this.programTypes = programTypes;
	}

	public List<ProgramThematic> getProgramThematics() {
		return programThematics;
	}

	public void setProgramThematics(List<ProgramThematic> programThematics) {
		this.programThematics = programThematics;
	}

	public List<ProgramLanguage> getProgramLanguages() {
		return programLanguages;
	}

	public void setProgramLanguages(List<ProgramLanguage> programLanguages) {
		this.programLanguages = programLanguages;
	}

	public Integer getProgramTypeId() {
		return programTypeId;
	}

	public void setProgramTypeId(Integer programTypeId) {
		this.programTypeId = programTypeId;
	}

	public Integer getProgramThematicId() {
		return programThematicId;
	}

	public void setProgramThematicId(Integer programThematicId) {
		this.programThematicId = programThematicId;
	}

	public List<ProgramCategory> getProgramCategories() {
		return programCategories;
	}

	public void setProgramCategories(List<ProgramCategory> programCategories) {
		this.programCategories = programCategories;
	}

	public Integer getProgramCategoryId() {
		return programCategoryId;
	}

	public void setProgramCategoryId(Integer programCategoryId) {
		this.programCategoryId = programCategoryId;
	}

	public Integer getProgramLanguageId() {
		return programLanguageId;
	}

	public void setProgramLanguageId(Integer programLanguageId) {
		this.programLanguageId = programLanguageId;
	}

	public ProgramType getProgramType() {
		for (ProgramType programType : programTypes) {
			if (programType.getId() == programTypeId) {
				return programType;
			}
		}
		return null;
	}

	public ProgramThematic getProgramThematic() {
		for (ProgramThematic programThematic : programThematics) {
			if (programThematic.getId() == programThematicId) {
				return programThematic;
			}
		}
		return null;
	}

	public ProgramCategory getProgramCategory() {
		for (ProgramCategory programCategory : programCategories) {
			if (programCategory.getId() == programCategoryId) {
				return programCategory;
			}
		}
		return null;
	}

	public ProgramLanguage getProgramLanguage() {
		for (ProgramLanguage programLanguage : programLanguages) {
			if (programLanguage.getId() == programLanguageId) {
				return programLanguage;
			}
		}
		return null;
	}

	/**
	 * Creates the program.
	 *
	 * @return the program
	 */
	public Program createProgram() {

		return new Program(name, description, periodicity, duration, accounts, accountPayer, getProgramType(), getProgramThematic(),
				getProgramCategory(), getProgramLanguage(), email, twitter, facebook, podcast, web);
	}

	/**
	 * Update program.
	 *
	 * @param program the program
	 * @return the program
	 */
	public Program updateProgram(Program program) {
		program.setName(getName());
		program.setDescription(getDescription());
		program.setPeriodicity(getPeriodicity());
		program.setDuration(getDuration());
		program.setAccountPayer(accountPayer);
		program.setAccounts(getAccounts());
		program.setProgramType(getProgramType());
		program.setProgramThematic(getProgramThematic());
		program.setProgramCategory(getProgramCategory());
		program.setProgramLanguage(getProgramLanguage());
		program.setEmail(email);
		program.setTwitter(twitter);
		program.setFacebook(facebook);
		program.setPodcast(podcast);
		program.setWeb(web);

		return program;
	}
}
