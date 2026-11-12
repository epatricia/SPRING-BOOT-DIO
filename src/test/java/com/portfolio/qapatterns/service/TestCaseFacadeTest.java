package com.portfolio.qapatterns.service;
import com.portfolio.qapatterns.config.QaSettings;
import com.portfolio.qapatterns.dto.*;
import com.portfolio.qapatterns.exception.NotFoundException;
import com.portfolio.qapatterns.model.*;
import com.portfolio.qapatterns.repository.TestCaseRepository;
import com.portfolio.qapatterns.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TestCaseFacadeTest {
 @Mock TestCaseRepository repository;
 TestCaseFacade facade;
 @BeforeEach void setup(){facade=new TestCaseFacade(repository,List.of(new FunctionalStrategy(),new RegressionStrategy(),new ExploratoryStrategy()),new QaSettings());}
 @Test void createStoresCase(){when(repository.save(any(TestCase.class))).thenAnswer(inv->inv.getArgument(0));TestCase result=facade.create(new CreateTestCaseRequest("Login",TestType.FUNCTIONAL));assertEquals("Login",result.getTitle());assertEquals(TestStatus.NOT_EXECUTED,result.getStatus());}
 @Test void executeUsesSelectedStrategy(){TestCase test=new TestCase();test.setTitle("Checkout");test.setType(TestType.REGRESSION);when(repository.findById(1L)).thenReturn(Optional.of(test));when(repository.save(test)).thenReturn(test);TestCase result=facade.execute(1L,new ExecuteTestRequest(true));assertEquals(TestStatus.PASSED,result.getStatus());assertTrue(result.getResultMessage().contains("Regression check"));}
 @Test void missingCaseReturnsNotFound(){when(repository.findById(99L)).thenReturn(Optional.empty());assertThrows(NotFoundException.class,()->facade.get(99L));}
}
