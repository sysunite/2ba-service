package com.sysunite.uneto.controllers;

import com.sysunite.uneto.Application;
import com.sysunite.uneto.util.Props;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Mohamad Alamili
 */
public class ApplicationController {
  
  public static Route index = (Request req, Response res) ->
    Application.NAME + " " + Props.getInstance().getProperty("application.version");  
    
  public static Route version = (Request req, Response res) -> 
    Props.getInstance().getProperty("application.version");

  public static Route notfound = (Request req, Response res) ->
    "not found";
}