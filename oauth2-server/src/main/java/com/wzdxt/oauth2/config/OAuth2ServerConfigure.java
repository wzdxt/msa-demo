package com.wzdxt.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by wzdxt on 2018/6/23.
 */
@Configuration
public class OAuth2ServerConfigure {
    private static final String DEMO_RESOURCE_ID = "user";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .anonymous()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/user/**").permitAll();
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

//        @Autowired
//        AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            //配置两个客户端,一个用于password认证一个用于client认证
            clients.inMemory().withClient("client_1")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("demo")
                    .authorities("client")
                    .secret("123456")
//                    .and().withClient("client_2")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("select")
//                    .authorities("client")
//                    .secret("123456")
                    .and().withClient("msa-demo")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("demo")
                    .authorities("client")
                    .secret("123456")
                    .redirectUris("http://localhost:8080/oauth-callback")
            ;
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(new InMemoryTokenStore());
//                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }

    }

    @Configuration
    @EnableWebSecurity
    public static class SecurityConfigure extends WebSecurityConfigurerAdapter {

        @Bean
        @Override
        protected UserDetailsService userDetailsService() {
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
            manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
            return manager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers().anyRequest()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/oauth/authorize").authenticated()
                    .and().httpBasic().realmName("OAuth2 Server")
                    .and().csrf().disable();
        }

        @Bean
        public AuthenticationManager getAuthenticationManager() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return new PasswordEncoder() {
                public String encode(CharSequence rawPassword) {
                    return rawPassword.toString();
                }

                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return encodedPassword.equals(rawPassword);
                }
            };
        }

    }
}
