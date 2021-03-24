package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
        // stateless false means not requiring username/pw
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/createnewuser")
                .permitAll()
                .antMatchers("/roles/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/products/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/user/{id}")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/user/name/{userName}")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/user/name/like/{userName}")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/user/{userId}")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/user")
                .hasAnyRole("ADMIN")
                .antMatchers("/carts/**")
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.logout().disable();


    }
}
