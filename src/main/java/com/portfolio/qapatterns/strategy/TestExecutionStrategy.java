package com.portfolio.qapatterns.strategy;
import com.portfolio.qapatterns.model.TestType;
public interface TestExecutionStrategy { TestType supports(); String execute(String title, boolean simulatedSuccess); }
