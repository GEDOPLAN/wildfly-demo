package de.gedoplan.showcase.service;

import jakarta.ejb.Remote;

@Remote
public interface BackendService {
  public String sayHello();  
}
