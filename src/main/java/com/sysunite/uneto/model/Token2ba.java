package com.sysunite.uneto.model;

/**
 * @author Mohamad Alamili
 */
public class Token2ba {
  
  String access_token;

  public Token2ba(String access_token) {
    this.access_token = access_token;
  }

  public String getToken() {
    return access_token;
  }
}