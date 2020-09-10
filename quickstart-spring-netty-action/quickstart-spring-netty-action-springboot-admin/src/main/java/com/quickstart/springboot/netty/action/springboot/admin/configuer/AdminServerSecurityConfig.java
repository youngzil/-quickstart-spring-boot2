package com.quickstart.springboot.netty.action.springboot.admin.configuer;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import java.util.UUID;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The type Security secure config.
 *
 * @author dax
 * @since 2019 /10/19 23:33
 */
@Configuration
public class AdminServerSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;

    /**
     * Instantiates a new Security secure config.
     *
     * @param adminServer the admin server
     */
    public AdminServerSecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        final String adminServerContextPath = this.adminServer.getContextPath();
        successHandler.setDefaultTargetUrl(adminServerContextPath+"/");

        http.authorizeRequests()
            .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
            .hasRole("SBA_ADMIN")
            .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers(adminServerContextPath + "/assets/**").permitAll() // <1>
                .antMatchers(adminServerContextPath + "/login").permitAll()
                .anyRequest().authenticated() // <2>
                .and()
                .formLogin().loginPage(adminServerContextPath + "/login").successHandler(successHandler).and() // <3>
                .logout().logoutUrl(adminServerContextPath + "/logout").and()
                .httpBasic().and() // <4>
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // <5>
                .ignoringRequestMatchers(
                        new AntPathRequestMatcher(adminServerContextPath + "/instances", HttpMethod.POST.toString()),  // <6>
                        new AntPathRequestMatcher(adminServerContextPath + "/instances/*", HttpMethod.DELETE.toString()),  // <6>
                        new AntPathRequestMatcher(adminServerContextPath + "/actuator/**")  // <7>
                )
                .and()
                .rememberMe().key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600);

    }

}
