package de.gedoplan.showcase.service;

import jakarta.ejb.Stateless;

@Stateless
public class BackendServiceBean implements BackendService {
  public String sayHello() {
    return "Hello from backend!";
  }  
}
