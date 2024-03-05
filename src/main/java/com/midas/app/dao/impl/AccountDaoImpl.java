package com.midas.app.dao.impl;

import com.midas.app.dao.AccountDao;
import com.midas.app.models.Account;
import com.midas.app.repositories.AccountRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDaoImpl implements AccountDao {

  @Autowired AccountRepository accountRepository;

  /**
   * Create a new account entity in database
   *
   * @param account to save
   * @return Account
   */
  @Override
  public Account save(Account account) {
    return accountRepository.save(account);
  }

  /**
   * Find existing account by id. Return null if not found
   *
   * @param id identifier to find account
   * @return Optional<Account>
   */
  @Override
  public Optional<Account> findById(String id) {
    return accountRepository.findById(UUID.fromString(id));
  }
}
