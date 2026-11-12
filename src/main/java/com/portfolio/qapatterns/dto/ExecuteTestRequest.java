package com.portfolio.qapatterns.dto;
import jakarta.validation.constraints.NotNull;
public record ExecuteTestRequest(@NotNull Boolean simulatedSuccess) {}
