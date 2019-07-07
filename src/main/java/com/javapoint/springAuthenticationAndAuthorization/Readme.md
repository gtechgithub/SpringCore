# Secure Spring REST With Spring Security and OAuth2

we will use spring security for authentication and oauth2 for authentication to secure our
rest endpoints

* Configure Spring Security  + LDAP
* Create an Authorization Server.
* Create a Resource Server.
* Get an access token and a refresh token.
* Get a secured Resource using an access token.


 we are going to combine the Authorization Server and Resource Server in the same project. 
 **As a grant type, we will use a password** (we will use BCrypt to hash our passwords).


```
                                                       (1) POST /oauth/token 
Resource Owner --------------------->  Oauth2 Client  -------------------------->   Authorization Server 
                                        |
               <---------------------   |             <---------------------    
                                        |               (2) Access Token
                                        |
                                        |
                                        |
                                        |               (3) Get /secured/company ( API resource) 
                                          --------------------------------------------> Resource Server
                                              
                                              
                                         <---------------------------------------------
                                                                (4) Secured resource 
                                                                
```

## PasswordEncoders

Since we are going to use different encryptions for OAuth2 client and user,
we will define separate password encoders for encryption:

* OAuth2 client password – BCrypt (4 rounds)
* User password - BCrypt (8 rounds)


```

@Configuration
public class Encoders {
    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
```

## users details service

```

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username);
    }
}

```

## user repository

```
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.username = :username")
    User findByUsername(@Param("username") String username);
}
```

## Setup Spring Security

```

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Import(Encoders.class)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder userPasswordEncoder;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
    }
}

```


# OAuth2 Configuration

First of all, we have to implement the following components:

* Authorization Server
* Resource Server

**To configure and enable the OAuth 2.0 Authorization Server we have to use @EnableAuthorizationServer annotation.**

* Defined the TokenStore bean to let Spring know to use the database for token operations.
* Overrode the configure methods to use the custom UserDetailsService implementation,
  AuthenticationManager bean, and OAuth2 client’s password encoder.
* Defined handler bean for authentication issues.


```
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(ServerSecurityConfig.class)
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(oauthClientPasswordEncoder);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }
}
```

## Resource Server

Spring OAuth2 provides an authentication filter that handles protection.
The @EnableResourceServer annotation enables a Spring Security filter that authenticates requests via an incoming OAuth2 token.

The configure(HttpSecurity http) method configures the access rules and request matchers (path) for
protected resources using the HttpSecurity class. We secure the URL paths /secured/*. 
It’s worth noting that to invoke any POST method request, the ‘write’ scope is needed.


```
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/secured/**";
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers(SECURED_PATTERN).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .anyRequest().access(SECURED_READ_SCOPE);
    }
}
```

eg:
POST http://localhost:8080/oauth/token


HEADERS

grant_type: password
username: admin
password: admin1234
client_id: spring-security-oauth2-read-write-client

response


{
    "access_token": "e6631caa-bcf9-433c-8e54-3511fa55816d",
    "token_type": "bearer",
    "refresh_token": "015fb7cf-d09e-46ef-a686-54330229ba53",
    "expires_in": 9472,
    "scope": "read write"
}

* check endpoint is working fine:

  http://localhost:8080/secured/company/ \
  
  headers
  'authorization: Bearer e6631caa-bcf9-433c-8e54-3511fa55816d'
