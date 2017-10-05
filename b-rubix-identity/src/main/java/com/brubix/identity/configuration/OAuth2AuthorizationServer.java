package com.brubix.identity.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import static com.brubix.identity.configuration.OAuth2ResourceServer.RESOURCE_ID;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Value("${brubix.oauth.token.timeout:1800}")
    private int tokenExpiration;

    @Value("${brubix.oauth.refresh-token.timeout:3600}")
    private int refreshTokenExpiration;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
        configurer
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .userApprovalHandler(userApprovalHandler);
    }

    // FIXME dynamically configuring clients
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                // as of now, only b-rubix services is the only client, so in-memory seems ok
                .inMemory()
                .withClient("brubix")
                .secret("secret")
                // Only password flow to be supported as of now,
                // other flows available ("authorization_code", "refresh_token", "implicit")
                .authorizedGrantTypes("password")
                .scopes("read", "write", "trust")
                .authorities("ROLE_CLIENT")
                .accessTokenValiditySeconds(tokenExpiration)
                .refreshTokenValiditySeconds(refreshTokenExpiration)
                .resourceIds(RESOURCE_ID);
    }
}
