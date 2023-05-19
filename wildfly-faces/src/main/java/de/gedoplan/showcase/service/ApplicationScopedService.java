package de.gedoplan.showcase.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationScopedService {

  private Date instanceCreated;

  private static final Log LOG = LogFactory.getLog(ApplicationScopedService.class);

  public Date getInstanceCreated() {
    return this.instanceCreated;
  }

  @PostConstruct
  public void create() {
    this.instanceCreated = new Date();

    LOG.debug("Created " + this.instanceCreated);
  }

  @PreDestroy
  public void destroy() {
    LOG.debug("Destroy " + this.instanceCreated);
  }
}
