package com.nyit.carrental.carmanagement.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.nyit.carrental.carmanagement.controller"))              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(metaData());                                           
	    }
	 
	 @SuppressWarnings("deprecation")
	private ApiInfo metaData() {
	        ApiInfo apiInfo = new ApiInfo(
	        		"Vehicle Management", 
	      	       	"Vehicle Management System consist of creating vehicles, rent vehicles ",
	                "1.0",
	                "Terms of service",
	                "yadpunia10@gmail.com",
	                "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0");
	        return apiInfo;
	    }
}
