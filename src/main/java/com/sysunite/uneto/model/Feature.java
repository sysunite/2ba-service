package com.sysunite.uneto.model;

/**
 * @author Mohamad Alamili
 */
public class Feature {
  
  public String Description, DimensionalDrawingCode, UnitShortNotation, CoordinateX, CoordinateY, 
    CoordinateZ, FeatureID, LogicalValue, Portcode, Type, ValueDescription, ValueID, NumericValue;
  
  public String getDescription() {
    return Description;
  }

  public String getDimensionalDrawingCode() {
    return DimensionalDrawingCode;
  }

  public String getUnitShortNotation() {
    return UnitShortNotation;
  }

  public String getNumericValue() {
    return NumericValue;
  }

  public String getCoordinateX() {
    return CoordinateX;
  }

  public String getCoordinateY() {
    return CoordinateY;
  }

  public String getCoordinateZ() {
    return CoordinateZ;
  }

  public String getFeatureID() {
    return FeatureID;
  }

  public String getLogicalValue() {
    return LogicalValue;
  }

  public String getPortcode() {
    return Portcode;
  }

  public String getType() {
    return Type;
  }

  public String getValueDescription() {
    return ValueDescription;
  }

  public String getValueID() {
    return ValueID;
  }
}