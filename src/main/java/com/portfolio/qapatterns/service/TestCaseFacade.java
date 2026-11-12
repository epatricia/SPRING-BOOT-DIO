package com.portfolio.qapatterns.service;
import com.portfolio.qapatterns.config.QaSettings;
import com.portfolio.qapatterns.dto.*;
import com.portfolio.qapatterns.exception.NotFoundException;
import com.portfolio.qapatterns.model.*;
import com.portfolio.qapatterns.repository.TestCaseRepository;
import com.portfolio.qapatterns.strategy.TestExecutionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
/** Facade: one simple API coordinating repository, strategies and configuration. */
@Service
public class TestCaseFacade {
 private final TestCaseRepository repository;
 private final Map<TestType,TestExecutionStrategy> strategies;
 private final QaSettings settings;
 public TestCaseFacade(TestCaseRepository repository,List<TestExecutionStrategy> implementations,QaSettings settings){
  this.repository=repository;this.settings=settings;
  this.strategies=implementations.stream().collect(Collectors.toUnmodifiableMap(TestExecutionStrategy::supports,s->s));
 }
 public TestCase create(CreateTestCaseRequest request){TestCase test=new TestCase();test.setTitle(request.title());test.setType(request.type());return repository.save(test);}
 public List<TestCase> list(){return repository.findAll();}
 public TestCase get(Long id){return repository.findById(id).orElseThrow(()->new NotFoundException("Test case not found: "+id));}
 @Transactional public TestCase execute(Long id,ExecuteTestRequest request){
  TestCase test=get(id);boolean success=request.simulatedSuccess();
  TestExecutionStrategy strategy=strategies.get(test.getType());
  if(strategy==null)throw new IllegalStateException("Unsupported test type: "+test.getType());
  test.setStatus(success?TestStatus.PASSED:TestStatus.FAILED);
  test.setResultMessage("["+settings.environment()+"] "+strategy.execute(test.getTitle(),success));
  return repository.save(test);
 }
 public void delete(Long id){get(id);repository.deleteById(id);}
}
