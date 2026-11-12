package com.portfolio.qapatterns.model;
import jakarta.persistence.*;
@Entity
@Table(name="test_cases")
public class TestCase {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private TestType type;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private TestStatus status = TestStatus.NOT_EXECUTED;
 private String resultMessage;
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
 public TestType getType(){return type;} public void setType(TestType type){this.type=type;}
 public TestStatus getStatus(){return status;} public void setStatus(TestStatus status){this.status=status;}
 public String getResultMessage(){return resultMessage;} public void setResultMessage(String resultMessage){this.resultMessage=resultMessage;}
}
