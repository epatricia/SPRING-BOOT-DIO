package com.portfolio.qapatterns.controller;
import com.portfolio.qapatterns.dto.*;
import com.portfolio.qapatterns.model.TestCase;
import com.portfolio.qapatterns.service.TestCaseFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/test-cases")
public class TestCaseController {
 private final TestCaseFacade facade;
 public TestCaseController(TestCaseFacade facade){this.facade=facade;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public TestCase create(@Valid @RequestBody CreateTestCaseRequest request){return facade.create(request);}
 @GetMapping public List<TestCase> list(){return facade.list();}
 @GetMapping("/{id}") public TestCase get(@PathVariable Long id){return facade.get(id);}
 @PostMapping("/{id}/execute") public TestCase execute(@PathVariable Long id,@Valid @RequestBody ExecuteTestRequest request){return facade.execute(id,request);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){facade.delete(id);}
}
