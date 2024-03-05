package com.midas.app.dao.impl;

import com.midas.app.dao.AccountDao;
import com.midas.app.models.Account;
import com.midas.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDaoImpl implements AccountDao {

  @Autowired AccountRepository accountRepository;

  @Override
  public Account save(Account account) {
    return accountRepository.save(account);
  }
}