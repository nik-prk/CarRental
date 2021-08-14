package com.nyit.carrental.usermanagement;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.nyit.carrental.usermanagement.service.CreateRolesForUser;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCaching
@EnableSwagger2
@EnableFeignClients
public class UsermanagementApplication implements CommandLineRunner{
	
	@Autowired
	CreateRolesForUser createRolesForUser;

	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Set<String> newRoles = new HashSet<>();
		newRoles.add("USER");
		newRoles.add("DRIVER");
		newRoles.add("ADMIN");
		createRolesForUser.executeUserService(newRoles);
	}
	
}

