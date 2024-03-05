package com.midas.app.workflows.impl;

import com.midas.app.activities.CreateAccountActivity;
import com.midas.app.models.Account;
import com.midas.app.workflows.CreateAccountWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import org.slf4j.Logger;

@WorkflowImpl(taskQueues = CreateAccountWorkflow.QUEUE_NAME)
public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private Logger logger = Workflow.getLogger(this.getClass().getName());
  private final ActivityOptions accountActivityOptions =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build();

  private final CreateAccountActivity createAccountActivity =
      Workflow.newActivityStub(CreateAccountActivity.class, accountActivityOptions);

  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account createAccount(Account details) {
    logger.debug("Starting save account activity...");
    return createAccountActivity.saveAccount(details);
  }
}
