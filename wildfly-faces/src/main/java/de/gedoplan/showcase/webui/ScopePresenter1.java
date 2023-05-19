package de.gedoplan.showcase.webui;

import de.gedoplan.showcase.service.ApplicationScopedService;
import de.gedoplan.showcase.service.DependentScopedService;
import de.gedoplan.showcase.service.RequestScopedService;
import de.gedoplan.showcase.service.SessionScopedService;
import de.gedoplan.showcase.service.ViewScopedService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ScopePresenter1 {
  @Inject
  RequestScopedService requestScopedService;

  @Inject
  SessionScopedService sessionScopedService;

  @Inject
  ApplicationScopedService applicationScopedService;

  @Inject
  ViewScopedService viewScopedService;

  @Inject
  DependentScopedService dependentScopedService;

  public String getScopeInfo() {
    return String.format(
        "Request %02d, Session %02d, Application %tT, View %02d, Dependent %02d", this.requestScopedService.getInstanceNumber(), this.sessionScopedService.getInstanceNumber(),
        this.applicationScopedService.getInstanceCreated(), this.viewScopedService.getInstanceNumber(), this.dependentScopedService.getInstanceNumber());
  }

}
