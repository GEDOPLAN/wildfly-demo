package de.gedoplan.showcase.service;

import java.util.Properties;

import jakarta.ejb.Stateless;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Stateless
public class FrontendServiceBean {

  private static final Log LOGGER = LogFactory.getLog(FrontendServiceBean.class);

  public String callBackend() {
    Context context = null;
    try {
      // Anmeldung am JNDI
      Properties jndiProps = new Properties() {{
        put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        put(Context.PROVIDER_URL, "http+remoting://localhost:8080");
        put(Context.SECURITY_PRINCIPAL, "hugo");
        put(Context.SECURITY_CREDENTIALS, "hugo_123");
        put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        put("org.xnio.Options.SASL_DISALLOWED_MECHANISMS", "JBOSS-LOCAL-USER");
        put("org.xnio.Options.SASL_POLICY_NOANONYMOUS", "true");
        put("org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        put("org.xnio.Options.SSL_STARTTLS", "true");
      }};
      LOGGER.debug("jndiProps: "+ jndiProps);

      context = new InitialContext(jndiProps);
      LOGGER.debug("context: "+ context);

      // Lookup der Bean
      String lookupName = "/wildfly-remote-ejb-backend/BackendServiceBean!de.gedoplan.showcase.service.BackendService";
      LOGGER.debug("lookupname: "+ lookupName);

      BackendService backendService = (BackendService) context.lookup(lookupName);
      LOGGER.debug("backendService: "+ backendService);

      String hello = backendService.sayHello();
      LOGGER.info("hello: "+ hello);
      return hello;
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }
    finally {
      try {
        context.close();
      } catch (Throwable e) {
        // ignore
      }
    }
  }
}
