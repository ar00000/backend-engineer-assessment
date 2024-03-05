package com.midas.app.activities;

import com.midas.app.models.Account;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface UpdateAccountActivity {

  /**
   * updateAccount updates an account in the data store.
   *
   * @param accountId id of account to update
   * @param account is the account to be saved
   * @return Account
   */
  @ActivityMethod
  Account updateAccount(String accountId, Account account);
}
