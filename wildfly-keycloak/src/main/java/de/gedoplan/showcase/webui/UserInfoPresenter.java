package de.gedoplan.showcase.webui;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.logging.Log;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Stream;

@RequestScoped
@Named
public class UserInfoPresenter {

  @Inject
  ExternalContext externalContext;

//  @Inject
  JsonWebToken jwt;

  @Inject
  Log logger;

  public String getRemoteUser() {
    String remoteUserFromExternalContext = externalContext.getRemoteUser();
    logger.debug("remoteUserFromExternalContext: " + remoteUserFromExternalContext);
    if (jwt != null && jwt.getClaimNames() != null) {
      jwt.getClaimNames().forEach(n -> logger.debug(String.format("JWT %s: %s", n, jwt.getClaim(n))));
    }
    return remoteUserFromExternalContext;
  }

  public List<String> getAllRoles() {
    return Stream.of("demoRole", "otherRole")
      .toList();
  }

  public List<String> getUserRoles() {
    return getAllRoles()
      .stream()
      .filter(n -> externalContext.isUserInRole(n))
      .toList();
  }

  public void logout() throws IOException {
    externalContext.redirect("/logout");
  }
}
