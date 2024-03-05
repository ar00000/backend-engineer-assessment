package com.midas.app.activities.impl;

import com.midas.app.activities.AccountActivity;
import com.midas.app.dao.AccountDao;
import com.midas.app.models.Account;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(taskQueues = "create-account-workflow")
public class AccountActivityImpl implements AccountActivity {

  @Autowired private AccountDao accountDao;

  /**
   * saveAccount saves an account in the data store.
   *
   * @param account is the account to be saved
   * @return Account
   */
  @Override
  public Account saveAccount(Account account) {

    Stripe.apiKey =
        "sk_test_51Oqc7RSF4rVhXIgFLytCOBoFg0bwfaG2BBLNuXiGoT8L5QkPY4Skp4iLFMSa7S5PxIrJmt4trRBK6x89jJvCVE9n008w9qzGfU";

    CustomerCreateParams params =
        CustomerCreateParams.builder()
            .setName(account.getFirstName() + " " + account.getLastName())
            .setEmail(account.getEmail())
            .build();
    Customer customer;
    try {
      customer = Customer.create(params);
    } catch (StripeException e) {
      throw new RuntimeException(e);
    }
    account.setProviderId(customer.getId());

    return accountDao.save(account);
  }

  /**
   * createPaymentAccount creates a payment account in the system or provider.
   *
   * @param account is the account to be created
   * @return Account
   */
  @Override
  public Account createPaymentAccount(Account account) {
    return null;
  }
}
