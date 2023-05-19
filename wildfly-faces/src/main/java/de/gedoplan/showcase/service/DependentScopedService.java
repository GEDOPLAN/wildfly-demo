package de.gedoplan.showcase.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.Dependent;

@Dependent
public class DependentScopedService {

  private static final Log LOG = LogFactory.getLog(DependentScopedService.class);

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
