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

import javax.sql.DataSource;

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
    private DataSource dataSource;

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
        // as of now, only b-rubix services is the only client, so in-memory seems ok
        clients
                .jdbc(dataSource)
                .withClient("brubix")
                .secret("secret")
                // Not intended to support "implicit" grant type
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("read", "write", "trust")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .accessTokenValiditySeconds(tokenExpiration)
                .refreshTokenValiditySeconds(refreshTokenExpiration)
                .resourceIds(RESOURCE_ID);
    }
}
