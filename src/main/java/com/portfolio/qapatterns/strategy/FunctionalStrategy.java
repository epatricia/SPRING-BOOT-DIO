package com.portfolio.qapatterns.strategy;
import com.portfolio.qapatterns.model.TestType;
import org.springframework.stereotype.Component;
@Component
public class FunctionalStrategy implements TestExecutionStrategy {
 public TestType supports(){return TestType.FUNCTIONAL;}
 public String execute(String title,boolean success){return "Functional check for '"+title+"': "+(success?"PASSED":"FAILED");}
}
