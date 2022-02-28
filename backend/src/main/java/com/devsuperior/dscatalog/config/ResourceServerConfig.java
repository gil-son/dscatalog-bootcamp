package com.devsuperior.dscatalog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
	private Environment env; // Environment of execute from application. Can access many variables
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	
	// ROUTES
	
	// something/** = all after something is access
	
	private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"}; // route to login  / this path or everything after this path
	
	private static final String[] OPERATOR_OR_ADMIN = {"/products/**", "/categories/**"};	 //	hierarchical access of users and products or categories cadastre							
											
	private static final String[] ADMIN = {"/users/**"}; // only admin
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// Access H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")){// If actives profiles contains test profiles
			http.headers().frameOptions().disable(); // release the frames and include h2 frame
		}
		
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() // Permit all route PUBLIC dont required login (OAuth)
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll() // Permit all route with only method GET and OPERATOR_OR_ADMIN (this case verify the method). Obs.: the route ADMIN dont be limited, so can use all methods
		.antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR","ADMIN") // Permit this route (OPERATOR_OR_ADMIN) access to has "OPERATOR" or "ADMIN" (this case verify the role) Obs.: in the database (data.sql) requires the prefix ROLE_ 
		.antMatchers(ADMIN).hasRole("ADMIN") // the route ADMIN can access by role ADMIN
		.anyRequest().authenticated(); // any other route require be authenticated and logged, independent of the perfil
	}

}
