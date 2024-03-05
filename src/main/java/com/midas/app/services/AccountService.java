package com.midas.app.services;

import com.midas.app.models.Account;
import java.util.List;

public interface AccountService {
  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  Account createAccount(Account details);

  /**
   * updateAccount updates an existing account in the system or provider.
   *
   * @param accountId id of account to update
   * @param details is the details of the account to be updated.
   * @return Account
   */
  Account updateAccount(String accountId, Account details);

  /**
   * getAccounts returns a list of accounts.
   *
   * @return List<Account>
   */
  List<Account> getAccounts();
}
