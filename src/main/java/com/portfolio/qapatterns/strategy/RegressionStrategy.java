package com.portfolio.qapatterns.strategy;
import com.portfolio.qapatterns.model.TestType;
import org.springframework.stereotype.Component;
@Component
public class RegressionStrategy implements TestExecutionStrategy {
 public TestType supports(){return TestType.REGRESSION;}
 public String execute(String title,boolean success){return "Regression check for '"+title+"': "+(success?"PASSED":"FAILED");}
}
