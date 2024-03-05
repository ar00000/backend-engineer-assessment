package com.midas.app.workflows.impl;

import com.midas.app.activities.UpdateAccountActivity;
import com.midas.app.models.Account;
import com.midas.app.workflows.UpdateAccountWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import org.slf4j.Logger;

@WorkflowImpl(taskQueues = UpdateAccountWorkflow.QUEUE_NAME)
public class UpdateAccountWorkflowImpl implements UpdateAccountWorkflow {

  private Logger logger = Workflow.getLogger(this.getClass().getName());
  private final ActivityOptions accountActivityOptions =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build();

  private final UpdateAccountActivity accountActivity =
      Workflow.newActivityStub(UpdateAccountActivity.class, accountActivityOptions);

  /**
   * updateAccount updates an existing account in the system or provider.
   *
   * @param accountId
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account updateAccount(String accountId, Account details) {
    logger.debug("Starting update account activity...");
    return accountActivity.updateAccount(accountId, details);
  }
}
