package com.portfolio.qapatterns.strategy;
import com.portfolio.qapatterns.model.TestType;
import org.springframework.stereotype.Component;
@Component
public class ExploratoryStrategy implements TestExecutionStrategy {
 public TestType supports(){return TestType.EXPLORATORY;}
 public String execute(String title,boolean success){return "Exploratory session for '"+title+"': "+(success?"PASSED":"FAILED");}
}
