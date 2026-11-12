package com.portfolio.qapatterns.repository;
import com.portfolio.qapatterns.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TestCaseRepository extends JpaRepository<TestCase,Long> {}
