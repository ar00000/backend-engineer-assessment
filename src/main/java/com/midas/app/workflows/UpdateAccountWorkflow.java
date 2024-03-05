package com.midas.app.workflows;

import com.midas.app.models.Account;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface UpdateAccountWorkflow {

  String QUEUE_NAME = "update-account-workflow";

  /**
   * updateAccount updates an existing account in the system or provider.
   *
   * @param accountId id of account to update
   * @param details is the details of the account to be created.
   * @return Account
   */
  @WorkflowMethod
  Account updateAccount(String accountId, Account details);
}
