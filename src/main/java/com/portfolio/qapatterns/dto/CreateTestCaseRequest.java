package com.portfolio.qapatterns.dto;
import com.portfolio.qapatterns.model.TestType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreateTestCaseRequest(@NotBlank String title, @NotNull TestType type) {}
