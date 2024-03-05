package com.midas.app.workflows.impl;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import com.midas.app.workflows.CreateAccountWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import org.slf4j.Logger;

@WorkflowImpl(taskQueues = "create-account-workflow")
public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private Logger logger = Workflow.getLogger(this.getClass().getName());
  private final ActivityOptions accountActivityOptions =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build();

  private final AccountActivity accountActivity =
      Workflow.newActivityStub(AccountActivity.class, accountActivityOptions);

  @Override
  public Account createAccount(Account details) {
    logger.debug("Starting save account activity...");
    Account account = accountActivity.saveAccount(details);
    return account;
  }
}
