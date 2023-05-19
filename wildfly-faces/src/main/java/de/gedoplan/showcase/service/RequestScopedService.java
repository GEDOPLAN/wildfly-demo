package de.gedoplan.showcase.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopedService {
  
  private static final Log LOG = LogFactory.getLog(RequestScopedService.class);

  private static int nextNumber = 1;

  private int instanceNumber;

  public int getInstanceNumber() {
    return this.instanceNumber;
  }

  @PostConstruct
  public void create() {
    this.instanceNumber = nextNumber;
    ++nextNumber;

    LOG.debug("Created " + this.instanceNumber);
  }

  @PreDestroy
  public void destroy() {
    LOG.debug("Destroy " + this.instanceNumber);
  }

}
