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
package org.cuacfm.members.model.accounttype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** The Class AccountType. */
@SuppressWarnings("serial")
@Entity
public class AccountType implements java.io.Serializable {

   /** The id. */
   @Id
   @GeneratedValue
   private Long id;

   /** The name. */
   @Column(unique = true)
   private String name;

   /** The organization. */
   private boolean organization;

   /** The description. */
   private String description;

   /** The discount in %. */
   private int discount;

   /** Instantiates a new account type. */
   protected AccountType() {
      // Default empty constructor.
   }

   /**
    * Instantiates a new account type.
    *
    * @param name
    *           the name
    * @param organization
    *           the organization
    * @param description
    *           the description
    * @param discount
    *           the discount
    */
   public AccountType(String name, boolean organization, String description, int discount) {
      super();
      this.name = name;
      this.organization = organization;
      this.description = description;
      this.discount = discount;
   }

   /**
    * Gets the id.
    *
    * @return the id
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
    * @param name
    *           the new name
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Checks if is organization.
    *
    * @return true, if is organization
    */
   public boolean isOrganization() {
      return organization;
   }

   /**
    * Sets the organization.
    *
    * @param organization
    *           the new organization
    */
   public void setOrganization(boolean organization) {
      this.organization = organization;
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
    * @param description
    *           the new description
    */
   public void setDescription(String description) {
      this.description = description;
   }

   /**
    * Gets the discount.
    *
    * @return the discount
    */
   public int getDiscount() {
      return discount;
   }

   /**
    * Sets the discount.
    *
    * @param discount
    *           the new discount
    */
   public void setDiscount(int discount) {
      this.discount = discount;
   }
}
