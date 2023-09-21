package de.gedoplan.showcase.api;

import de.gedoplan.showcase.service.FrontendServiceBean;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("frontend")
public class FrontendResource {

  @Inject
  FrontendServiceBean frontendServiceBean;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return frontendServiceBean.callBackend();
  }
}
