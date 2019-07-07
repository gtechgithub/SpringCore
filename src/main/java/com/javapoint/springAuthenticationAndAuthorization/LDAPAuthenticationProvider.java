
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.InitialLdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticationProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * The LDAPAuthenticationProvider implements user LDAP-Authentication.
 *
public class LDAPAuthenticationProvider extends AbstractLdapAuthenticationProvider {

    
    private String domain1;
    private String url;
    private String[] ldapUrlArray = null;

    /**
     * @param domain1
     *
     * @param url
     *            an LDAP url (or multiple URLs)
     * @param keyStore
     */
    public LDAPAuthenticationProvider(String domain1, String url, String keyStore) {
       
       # ldapUrl=ldaps://199.92.133.86:636|ldaps://199.92.129.67:636|ldaps://199.92.131.142:636|ldaps://199.92.131.138:636

       iServeLogger.debug(keyStore);
        Assert.isTrue(StringUtils.hasText(url), "Url cannot be empty");
        this.domain1 = StringUtils.hasText(domain1) ? domain1.toUpperCase() : null;

        this.url = url;
        constructLDAPUrls();
    }


    @Override
    public Authentication authenticate(Authentication authentication) {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, messages.getMessage(
                "LdapAuthenticationProvider.onlySupports", "Only UsernamePasswordAuthenticationToken is supported"));
        final UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = userToken.getName();
        String passwordOrUserKey = (String) authentication.getCredentials();

       
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, passwordOrUserKey,
                AuthorityUtils.NO_AUTHORITIES);
        doAuthentication(userToken);

        return createSuccessfulAuthentication(userToken, userDetails);
    }

    @Override
    protected DirContextOperations doAuthentication(UsernamePasswordAuthenticationToken auth) {
        String username = auth.getName();
        String encrptPassword = (String) auth.getCredentials();

        DirContext ctx = null;

        try {
            String password = new String(Base64.getDecoder().decode(encrptPassword));
            ctx = bindAsUserInAlldomain(username, password);
        } catch (Exception e) {
            throw e;
        } finally {
            LdapUtils.closeContext(ctx);
        }

        return null;

    }

    @Override
    protected Collection<? extends GrantedAuthority> loadUserAuthorities(DirContextOperations arg0, String username,
            String password) {
        // Return null for authorities since we use Authorities from own
        // Database.
        return AuthorityUtils.NO_AUTHORITIES;
    }

  
    private String createBindPrincipal(String username, String domain) {
        return domain + "\\" + username;
    }

    /*
     * User id will be checked in 2 domains and the 2 different ldap urls
     * configured
     */
    /**
     * @param username
     * @param password
     * @return DirContext
     */
    public DirContext bindAsUserInAlldomain(String username, String password) {
        final String bindUrl1 = ldapUrlArray[0];
        final String bindPrincipal1 = createBindPrincipal(username, domain1);
        DirContext dirContext = null;
        Map<String, String> env = new HashMap<>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.OBJECT_FACTORIES, DefaultDirObjectFactory.class.getName());
        env.put(Context.SECURITY_PROTOCOL, "ssl");
        env.put(CONNECT_TIMEOUT, "10000");
        try {
            env.put(Context.SECURITY_PRINCIPAL, bindPrincipal1);
            env.put(Context.PROVIDER_URL, bindUrl1);
            dirContext = new InitialLdapContext(new Hashtable<String, String>(env), null);
        } catch (NamingException ex) {
          
        }

        return dirContext;
    }


}
