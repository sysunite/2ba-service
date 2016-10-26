package com.sysunite.uneto.controllers;

import com.sysunite.uneto.Service2ba;
import com.sysunite.uneto.model.Feature;
import com.sysunite.uneto.model.Product2ba;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Mohamad Alamili
 */
public class Controller2ba {
  
  public static String auth(Request req, Response res){
    String apiKey = req.queryParams("apiKey");

    if(apiKey == null){
      res.status(403);
      return "Unauthorized: apiKey not given";
    }
    else if(!apiKey.equals(Service2ba.credentials.get("api_key"))){
      res.status(403);
      return "Unauthorized: apiKey invalid";
    }
    else {
      return null;
    }
  }
  
  public static Route get = (Request req, Response res) -> {
    String productId = req.queryParams("productId");
    
    String error;
    if((error = auth(req, res)) != null){
      return error;
    }
    
    return new Service2ba().get2baProductJson(productId);
  };


  /**
   * Lists product id's based on MC code
   */
  public static Route list = (Request req, Response res) -> {
    String mc = req.queryParams("mc");
    
    String error;
    if((error = auth(req, res)) != null){
      return error;
    }
    
    return new Service2ba().get2baProducts(mc);
  };


  public static Route getProductsByModellingClass = (Request req, Response res) -> {
    return "not implemented";
  };


  public static Route getProductDiff = (Request req, Response res) -> {
    return "not implemented";
  };
  
  
  public static TemplateViewRoute examine = (req, res) -> {
    res.type("text/html");

    String productId = req.queryParams("productId");

    Map<String, Object> attributes = new HashMap<>();
    
    String error;
    if((error = auth(req, res)) != null){
      attributes.put("error", error);
      return new ModelAndView(attributes, "error.ftl");
    }

    Product2ba product = new Service2ba().get2baProduct(productId); 
    
    /// NUMERICAL
    List<Feature> numericalFeatures = new ArrayList<>(); 
      product.getFeatures().stream()
      .filter(feature -> feature.getType().equals("N"))
      .forEach(numericalFeatures::add);

    product.getModellingFeatures().stream()
      .filter(feature -> feature.getType().equals("N"))
      .forEach(numericalFeatures::add);

    
    /// TEXT
    List<Feature> textFeatures = product.getFeatures().stream()
      .filter(feature -> feature.getType().equals("A"))
      .collect(Collectors.toList());
    
    
    
    
    /// COORDINATES
    
    
    
    
    // LOGICAL
    
    
    
    /// NULL features
    List<Feature> nullFeatures = new ArrayList<>();
    numericalFeatures.stream().filter(feature -> feature.getNumericValue() == null).forEach(nullFeatures::add);
    
    attributes.put("product", product);
    attributes.put("numericalFeatures", numericalFeatures);
    
    return new ModelAndView(attributes, "examine.ftl");
  };


  public static TemplateViewRoute examineCsv = (req, res) -> {
    res.type("text/html");
    
    return new ModelAndView(null, "examineCsv.ftl");
  };
}