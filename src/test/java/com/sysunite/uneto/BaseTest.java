package com.sysunite.uneto;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * @author Mohamad Alamili
 */
public class BaseTest {
  
  static Application application;
  
  @BeforeClass
  public static void setup(){
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Application.PORT;
    
    if(application == null) {
      application = new Application();
    }
  }
}