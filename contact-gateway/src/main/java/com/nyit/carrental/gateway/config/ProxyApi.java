package com.nyit.carrental.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ProxyApi {
 @Autowired
 ZuulProperties properties;
 @SuppressWarnings({ "rawtypes", "unchecked" })
@Primary
 @Bean
 public SwaggerResourcesProvider swaggerResourcesProvider() {
  return () -> {
   List resources = new ArrayList();
   properties.getRoutes().values().stream()
   .forEach(route -> resources.add(createResource(route.getServiceId(), route.getId(), "2.0")));
   return resources;
  };
 }
 private SwaggerResource createResource(String name, String location, String version) {
  SwaggerResource swaggerResource = new SwaggerResource();
  swaggerResource.setName(name);
  swaggerResource.setLocation("/api/vehicle/rental/" + location + "/v2/api-docs");
  swaggerResource.setSwaggerVersion(version);
  return swaggerResource;
 }
}
