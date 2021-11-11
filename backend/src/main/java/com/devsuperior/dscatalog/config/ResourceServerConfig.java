package com.devsuperior.dscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer // ResourceServer is enable to use the functionality of OAuth2
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;
	
	
	// ROUTES
	
	private static final String[] PUBLIC = {"/oauth/token"}; // route to login, products or categories
	
	private static final String[] OPERATOR_OR_ADMIN = {"/products/**", "/categories/**"};	 //	hierarchical access of users and products or categories cadastre							
											
	private static final String[] ADMIN = {"/users/**"}; // only admin
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() // Permit all route PUBLIC dont required login
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() // Permit all route with method get and OPERATOR_OR_ADMIN
		.antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR","ADMIN") // Permit any access to OPERATOR_OR_ADMIN has "OPERATOR" or "ADMIN" (defined on data.sql)
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated(); // any other route require be authenticated and logged, independent of the perfil
	}

}
