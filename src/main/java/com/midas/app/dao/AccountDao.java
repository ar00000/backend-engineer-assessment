package com.midas.app.dao;

import com.midas.app.models.Account;
import java.util.Optional;

public interface AccountDao {

  /**
   * Create a new account entity in database
   *
   * @param account to save
   * @return Account
   */
  Account save(Account account);

  /**
   * Find existing account by id. Return null if not found
   *
   * @param id identifier to find account
   * @return Optional<Account>
   */
  Optional<Account> findById(String id);
}
