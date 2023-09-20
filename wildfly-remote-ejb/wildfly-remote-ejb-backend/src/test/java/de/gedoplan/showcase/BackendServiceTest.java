package de.gedoplan.showcase;

import de.gedoplan.showcase.service.BackendService;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

public class BackendServiceTest {

  private static final Logger LOGGER = Logger.getLogger(BackendServiceTest.class);

  @Test
  void testSayHello() {
    Context context = null;
    try {
      // Anmeldung am JNDI
      Properties jndiProps = new Properties() {{
        put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        put(Context.SECURITY_PRINCIPAL, "hugo");
        put(Context.SECURITY_CREDENTIALS, "hugo_123");
        put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        put("org.xnio.Options.SASL_DISALLOWED_MECHANISMS", "JBOSS-LOCAL-USER");
        put("org.xnio.Options.SASL_POLICY_NOANONYMOUS", "true");
        put("org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        put("org.xnio.Options.SSL_STARTTLS", "true");
      }};
      LOGGER.debugf("jndiProps: %s", jndiProps);

      context = new InitialContext(jndiProps);
      LOGGER.debugf("context: %s", context);

      // Lookup der Bean
      String lookupName = "/wildfly-remote-ejb-backend/BackendServiceBean!de.gedoplan.showcase.service.BackendService";
      LOGGER.debugf("lookupname: %s", lookupName);

      BackendService backendService = (BackendService) context.lookup(lookupName);
      LOGGER.debugf("backendService: %s", backendService);

      String hello = backendService.sayHello();
      LOGGER.infof("hello: %s", hello);
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }

  }
}
