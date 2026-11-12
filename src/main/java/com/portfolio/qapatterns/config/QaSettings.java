package com.portfolio.qapatterns.config;
/** Singleton: Spring creates one instance of this component by default. */
import org.springframework.stereotype.Component;
@Component
public class QaSettings { public String environment(){return "portfolio-demo";} }
