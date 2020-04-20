package com.mytask.transfermoney.auth0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("app").tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests().anyRequest()
//                .antMatchers(HttpMethod.OPTIONS, "/oauth/token")
                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/token", "/clients/create-client")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
