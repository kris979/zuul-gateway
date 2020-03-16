package com.agisoft.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        AuthorizationFilter filter = new AuthorizationFilter(authenticationManager());

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/marklogic/**").authenticated()
            .antMatchers(HttpMethod.GET, "/graphql/graphiql/**").permitAll()
            .antMatchers(HttpMethod.GET, "/graphql/hello").permitAll()
            .antMatchers(HttpMethod.GET, "/graphql/**").authenticated()
            .antMatchers(HttpMethod.GET, "/**").permitAll()
            .and()
            .addFilter(filter);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
