package com.sysunite.uneto.model;

import java.util.List;

/**
 * @author Mohamad Alamili
 */
public class Product2ba {

  public String Brand, Description, GTIN, Id, ManufacturerGLN, ManufacturerName, 
    Model, ModellingClass, ProductClassID, Productcode;
  
  public List<Feature> Features, ModellingFeatures;

  public String getBrand() {
    return Brand;
  }

  public String getDescription() {
    return Description;
  }

  public String getGTIN() {
    return GTIN;
  }

  public String getId() {
    return Id;
  }

  public String getManufacturerGLN() {
    return ManufacturerGLN;
  }

  public String getManufacturerName() {
    return ManufacturerName;
  }

  public String getModel() {
    return Model;
  }

  public String getModellingClass() {
    return ModellingClass;
  }

  public String getProductClassID() {
    return ProductClassID;
  }

  public String getProductcode() {
    return Productcode;
  }

  public List<Feature> getFeatures() {
    return Features;
  }

  public List<Feature> getModellingFeatures() {
    return ModellingFeatures;
  }
}