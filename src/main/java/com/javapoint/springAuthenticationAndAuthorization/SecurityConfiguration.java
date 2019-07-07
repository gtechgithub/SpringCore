**
 * The SecurityConfiguration provides the security configuration.
 *
 */
 
ldapUrl=ldaps://199.92.133.86:636|ldaps://199.92.129.67:636|ldaps://199.92.131.142:636|ldaps://1999.92.131.138:636
ldap.domain1=CITI
keyStore=keystores/server-sit.uat.dbs.com.jks
ldap.keypassword=
ldapSearchBase=DC=uat1bank,DC=CITI,DC=com
ldapDefaultGDPort = 9999


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${ldap.domain1}")
    private String domain1;

    @Value("${ldapUrl}")
    private String url;

    @Value("${keyStore}")
    private String keyStore;

    @Value("${ldap.keypassword}")
    private String keyPassword;

    @Value("${ldapSearchBase}")
    private String searchRoot;

    @Value("${jboss.home.dir}")
    private String userHome;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param auth
     *            - AuthenticationManagerBuilder
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ldapAuthenticationProvider());
    }

    /**
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return Oauth2ClientPasswordEncoder.getInstance();
    }

    /**
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider ldapAuthenticationProvider() {
        return new LDAPAuthenticationProvider(domain1, url, userHome + "/iservetw-config/" + keyStore);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/scripts/**/*.{js,html}").antMatchers("/bower_components/**")
                .antMatchers("/i18n/**").antMatchers("/assets/**").antMatchers("/swagger-ui/**").antMatchers("/login")
                .antMatchers("/test/**").antMatchers("/console/**").antMatchers("/api/tmac/tmacEndOfCall")
                .antMatchers("/bbsLogin").antMatchers("/bbs").antMatchers("/api/getAppVersion").antMatchers("/ml/**")
                .antMatchers("/api/download/otherServer").antMatchers("/api/casaOnboarding/pwebXML")
                .antMatchers("/api/ulstp/cashline/downloadUlstpPdfOnOtherServer")
                .antMatchers("/api/ulstp/cashline/pdfGeneration")
                .antMatchers("/api/ulstp/cashline/downloadSDBWEBUlstpPdfOnOtherServer")
                .antMatchers("/api/ulstp/cashline/outboundVerify").antMatchers("/api/ulstp/btil/pdfGeneration")
                .antMatchers("/api/ulstp/btil/downloadBTILPdfOnOtherServer")
                .antMatchers("/api/ulstp/cashline/checkSDBWEBUlstpPdfOnOtherServer").antMatchers("/api/postUILogs");
    }

    /**
     * @return SecurityEvaluationContextExtension
     */
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
