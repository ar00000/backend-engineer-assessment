package com.midas.app.activities.impl;

import com.midas.app.activities.UpdateAccountActivity;
import com.midas.app.dao.AccountDao;
import com.midas.app.exceptions.ResourceNotFoundException;
import com.midas.app.models.Account;
import com.midas.app.workflows.UpdateAccountWorkflow;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerUpdateParams;
import io.temporal.spring.boot.ActivityImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(taskQueues = UpdateAccountWorkflow.QUEUE_NAME)
public class UpdateAccountActivityImpl implements UpdateAccountActivity {

  @Autowired private AccountDao accountDao;

  /**
   * updateAccount updates an account in the data store.
   *
   * @param accountId id of account to update
   * @param account is the account to be saved
   * @return Account
   */
  @Override
  public Account updateAccount(String accountId, Account account) {
    Optional<Account> accountOptional = accountDao.findById(accountId);
    if (accountOptional.isEmpty()) throw new ResourceNotFoundException("Account not found");
    Account savedAccount = accountOptional.get();

    Stripe.apiKey =
        "sk_test_51Oqc7RSF4rVhXIgFLytCOBoFg0bwfaG2BBLNuXiGoT8L5QkPY4Skp4iLFMSa7S5PxIrJmt4trRBK6x89jJvCVE9n008w9qzGfU";
    Customer resource = null;
    try {
      resource = Customer.retrieve(savedAccount.getProviderId());

      CustomerUpdateParams params =
          CustomerUpdateParams.builder()
              .putMetadata("name", account.getFirstName() + " " + account.getLastName())
              .putMetadata("email", account.getEmail())
              .build();
      Customer customer = resource.update(params);
    } catch (StripeException e) {
      throw new RuntimeException(e);
    }

    savedAccount.setEmail(account.getEmail());
    savedAccount.setFirstName(account.getFirstName());
    savedAccount.setLastName(account.getLastName());

    return accountDao.save(savedAccount);
  }
}
