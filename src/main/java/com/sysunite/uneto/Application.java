package com.sysunite.uneto;

import com.sysunite.uneto.controllers.ApplicationController;
import com.sysunite.uneto.controllers.Controller2ba;
import com.sysunite.uneto.util.CORS;
import com.sysunite.uneto.util.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;

import static spark.Spark.*;

/**
 * @author Mohamad Alamili
 */
public class Application {

  public static final int PORT    = 9349; 
  public static final String NAME = "2BA Service";
  static Logger logger = LoggerFactory.getLogger(Application.class);
  
  public Application() {
    // HTTP port to listen on
    port(PORT);

    // Enable CORS on all hosts
    CORS.enable();
    
    // Init
    Service2ba.init();

    // Wire routes
    get("/", ApplicationController.index);
    get("/application/version",    ApplicationController.version);
    get("/get",                    Controller2ba.get);
    get("/list",                   Controller2ba.list);
    get("/examine",                Controller2ba.examine, new FreeMarkerEngine());
    get("*",                       ApplicationController.notfound);

    // Wait for server to be initialized
    awaitInitialization();
    
    // Catch exceptions
    exception(Exception.class, (e, request, response) -> {
      e.printStackTrace();
      response.status(503);
      response.body("Server Error");
    });
    
    // Running
    final String VERSION = Props.getInstance().getProperty("application.version");
    logger.info(NAME + " " + VERSION  + " running on port " + PORT);
  }

  public static void main(String[] args) throws IOException {
    new Application();
  }
}