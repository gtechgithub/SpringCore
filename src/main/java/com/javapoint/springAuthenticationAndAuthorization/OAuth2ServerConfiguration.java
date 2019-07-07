@Configuration
public class OAuth2ServerConfiguration {

    OAuth2ServerConfiguration() {
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private Http401UnauthorizedEntryPoint authenticationEntryPoint;

        @Autowired
        private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout().logoutUrl(FuncMapping.LOGOUT)
                    .logoutSuccessHandler(ajaxLogoutSuccessHandler).and().csrf()
                    .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable().headers()
                    .frameOptions().disable().and().authorizeRequests().antMatchers("api/acontrol/authenticate")
                    .permitAll().antMatchers("/api/register").permitAll().antMatchers("/websocket/**").permitAll()
                    .antMatchers("/websocket/tracker").hasAuthority(Constants.ADMIN)
                     .antMatchers(FuncMapping.ROOT + "/**").authenticated().antMatchers("/protected/**").authenticated();
            // @formatter:on
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
            implements EnvironmentAware {

        private static final String ENV_OAUTH = "authentication.oauth.";

        private static final String PROP_CLIENTID = "clientid";

        private static final String PROP_SECRET = "secret";

        private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";
        @Autowired
        private Environment environment;

        @Autowired
        private DataSource dataSource;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        /**
         * @return TokenStore
         */
        @Bean
        @Qualifier("jdbcTokeStore")
        public TokenStore tokenStore() {
            JdbcTokenStore tokenStore = new JdbcTokenStore(dataSource);
            tokenStore.setAuthenticationKeyGenerator(new AuthenticationKeyGeneratorMultiple());
            return tokenStore;
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

            endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);

        }

        /**
         * @return OauthTokenService
         */
        @Bean
        public OauthTokenService tokenServices() {
            OauthTokenService tokenServices = new OauthTokenService(tokenStore());
            tokenServices.setSupportRefreshToken(false);
            return tokenServices;
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients.inMemory().withClient(environment.getProperty(ENV_OAUTH + PROP_CLIENTID)).scopes("read", "write")
                    .authorizedGrantTypes("password").secret(environment.getProperty(ENV_OAUTH + PROP_SECRET))
                    .accessTokenValiditySeconds(
                            environment.getProperty(ENV_OAUTH + PROP_TOKEN_VALIDITY_SECONDS, Integer.class))
                    .and().withClient("BBS").authorizedGrantTypes("password").scopes("read", "write").secret("secret")
                    .accessTokenValiditySeconds(
                            environment.getProperty(ENV_OAUTH + PROP_TOKEN_VALIDITY_SECONDS, Integer.class));

            // @formatter:on

        }

        @Override
        public void setEnvironment(Environment environment) {
            // Implemented to comply with the overridden rules
        }

    }
}
