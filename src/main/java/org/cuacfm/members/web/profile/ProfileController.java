package org.cuacfm.members.web.profile;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.cuacfm.members.model.account.Account;
import org.cuacfm.members.model.accountservice.AccountService;
import org.cuacfm.members.model.accounttypeservice.AccountTypeService;
import org.cuacfm.members.model.bankaccount.BankAccount;
import org.cuacfm.members.model.exceptions.UniqueException;
import org.cuacfm.members.model.methodpaymentservice.MethodPaymentService;
import org.cuacfm.members.model.userservice.UserService;
import org.cuacfm.members.web.support.DisplayDate;
import org.cuacfm.members.web.support.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** The Class ProfileController. */
@Controller
public class ProfileController {

   /** The Constant PROFILE_VIEW_NAME. */
   private static final String PROFILE_VIEW_NAME = "profile/profile";

   /** The account service. */
   @Autowired
   private AccountService accountService;

   /** The account Type service. */
   @Autowired
   private AccountTypeService accountTypeService;

   /** The account Method Payment service. */
   @Autowired
   private MethodPaymentService methodPaymentService;

   /** The user service. */
   @Autowired
   private UserService userService;

   /** The Global variable account. */
   private Account account = null;

   /** The bank accounts. */
   private List<BankAccount> bankAccounts;
   
   /**
    * Instantiates a new Profile controller.
    */
   public ProfileController() {
      // Default empty constructor.
   }

   /**
    * Bank accounts.
    *
    * @return the list
    */
   @ModelAttribute("bankAccounts")
   public List<BankAccount> bankAccounts() {
      return bankAccounts;
   }
   
   /**
    * Creates the profile form.
    *
    * @param model
    *           the model
    * @param profileForm
    *           the profile form
    * @return the string
    */
   public String createProfileForm(Model model, ProfileForm profileForm) {
      profileForm.setName(account.getName());
      profileForm.setNickName(account.getNickName());
      profileForm.setDni(account.getDni());
      profileForm.setAddress(account.getAddress());
      profileForm.setCp(account.getCp());
      profileForm.setProvince(account.getProvince());
      profileForm.setCodeCountry(account.getCodeCountry());
      profileForm.setLogin(account.getLogin());
      profileForm.setEmail(account.getEmail());
      profileForm.setPhone(account.getPhone());
      profileForm.setMobile(account.getMobile());
      profileForm.setProgramName(account.getProgramName());
      profileForm.setStudent(account.isStudent());
      profileForm.setDateBirth(DisplayDate.dateToString(account.getDateBirth()));
      if (account.getAccountType() != null) {
         profileForm.setAccountTypeId(account.getAccountType().getId());
      }
      profileForm.setAccountTypes(accountTypeService.getAccountTypes());
      if (account.getMethodPayment() != null) {
         profileForm.setMethodPaymentId(account.getMethodPayment().getId());
      }
      profileForm.setMethodPayments(methodPaymentService.getMethodPayments());
      profileForm.setInstallments(account.getInstallments());
      model.addAttribute(profileForm);
      return PROFILE_VIEW_NAME;
   }

   /**
    * Profile.
    * 
    * @param model
    *           the model
    * @param principal
    *           the actual user
    * @return the string
    */
   @RequestMapping(value = "profile")
   public String profile(Model model, Principal principal) {

      account = accountService.findByLogin(principal.getName());
      bankAccounts = account.getBankAccounts();
      model.addAttribute("bankAccounts", bankAccounts);
      return createProfileForm(model, new ProfileForm());
   }

   /**
    * Profile.
    *
    * @param signupForm
    *           the signup form
    * @param errors
    *           the errors
    * @param ra
    *           the RedirectAttributes
    * @return the string
    */
   @RequestMapping(value = "profile", method = RequestMethod.POST)
   public String profile(@Valid @ModelAttribute ProfileForm profileForm, Errors errors,
         RedirectAttributes ra, Model model) {

      if (errors.hasErrors()) {
         return createProfileForm(model, profileForm);
      }

      // Password
      boolean modifyPassword = false;
      if (profileForm.isOnPassword()) {
         // check that the password and rePassword are the same
         String password = profileForm.getPassword();
         String rePassword = profileForm.getRePassword();
         if (!password.equals(rePassword)) {
            errors.rejectValue("password", "profile.passwordsDontMatch");
            errors.rejectValue("rePassword", "profile.passwordsDontMatch");
            return createProfileForm(model, profileForm);
         } else {
            account.setPassword(password);
            modifyPassword = true;
         }
      }

      // Login
      boolean modifyLogin = false;
      String login = profileForm.getLogin();
      if (profileForm.isOnLogin() && login != "") {
         account.setLogin(login);
         modifyLogin = true;
      }

      // Email
      String email = profileForm.getEmail();
      if (profileForm.isOnEmail() && email != "") {
         account.setEmail(profileForm.getEmail());
      }

      account.setName(profileForm.getName());
      account.setNickName(profileForm.getNickName());
      account.setDni(profileForm.getDni());
      account.setAddress(profileForm.getAddress());
      account.setCp(profileForm.getCp());
      account.setProvince(profileForm.getProvince());
      account.setCodeCountry(profileForm.getCodeCountry());
      account.setPhone(profileForm.getPhone());
      account.setMobile(profileForm.getMobile());
      account.setProgramName(profileForm.getProgramName());
      account.setStudent(profileForm.isStudent());
      account.setDateBirth(DisplayDate.stringToDate2(profileForm.getDateBirth()));

      // If correct
      try {
         accountService.update(account, modifyPassword);
      } catch (UniqueException e) {
         if (e.getAttribute() == "Dni") {
            errors.rejectValue("dni", "signup.existentDni", new Object[] { e.getAttribute() },
                  "dni");
         }
         if (e.getAttribute() == "Login") {
            errors.rejectValue("login", "profile.existentLogin", new Object[] { e.getAttribute() },
                  "login");
         }
         if (e.getAttribute() == "Email") {
            errors.rejectValue("email", "profile.existentEmail", new Object[] { e.getAttribute() },
                  "email");
         }
      }

      if (errors.hasErrors()) {
         return createProfileForm(model, profileForm);
      }

      if (modifyLogin) {
         userService.signin(account);
      }
      MessageHelper.addSuccessAttribute(ra, "profile.success");

      return "redirect:/profile";
   }

}
