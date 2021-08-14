package com.nyit.carrental.gateway.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.nyit.carrental.gateway.service.GetUserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
	    return new Base64CustomEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
	    return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
	            "Unauthorized");
	}

	@Bean
	public UserDetailsService mongoUserDetails() {
	    return new GetUserDetails();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/swagger-resources")
				.antMatchers("/swagger-resources/**")
				.antMatchers("/swagger-ui.html");
	}
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable().csrf().disable().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests()
        .antMatchers("/api/usermanagement/registerUser").permitAll()
        .antMatchers("/api/usermanagement/user/account/**").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/api/usermanagement/updateUser").hasAnyAuthority("USER","ADMIN","DRIVER")
        .antMatchers("/api/usermanagement/getAllUsers").hasAnyAuthority("ADMIN","USER")
        .antMatchers("/api/usermanagement/deleteUser/*").hasAuthority("ADMIN")
        .antMatchers("/api/usermanagement/createRole").hasAuthority("ADMIN")
        .antMatchers("/api/usermanagement/getAllRoles").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/createVehicleType").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/getAllVehicleTypes").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/api/vehiclemanagement/updateVehicleType").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/deleteVehicleType/*").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/createVehicle").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/getAllVehicles").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/api/vehiclemanagement/updateVehicle").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/deleteVehicle/*").hasAuthority("ADMIN")
        .antMatchers("/api/vehiclemanagement/rentVehicle").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/api/vehiclemanagement/endRental/*").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/api/wallet/updateAmount").hasAuthority("ADMIN")
        .antMatchers("/api/wallet/findWallet/*").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/api/filemanagement/*").hasAnyAuthority("USER","ADMIN","DRIVER")
        .anyRequest().authenticated()
        .and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
        .and().apply(new JwtConfigurer(jwtTokenProvider));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }
}