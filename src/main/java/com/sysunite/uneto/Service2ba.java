package com.sysunite.uneto;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sysunite.uneto.exception.BadCredentialsException;
import com.sysunite.uneto.model.Product2ba;
import com.sysunite.uneto.model.Token2ba;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohamad Alamili
 */
public class Service2ba {

  private static final String AUTH_URL = "https://authorize.2ba.nl/OAuth/Token"; 
  private static final String PROD_URL = "https://api.2ba.nl/1/json/Product/DetailsRTById?id="; 
  private static final String LIST_URL = "https://api.beta.2ba.nl/1/json/UOB/ModellingProducts?modellingClass="; 
  
  public static Map<String, Object> credentials = new HashMap<>();

  public Service2ba() {
    credentials.put("grant_type", "password");
  }

  public static void init(){
    credentials.put("client_id",     System.getenv("CLIENT_ID"));
    credentials.put("client_secret", System.getenv("CLIENT_SECRET"));
    credentials.put("username",      System.getenv("USERNAME"));
    credentials.put("password",      System.getenv("PASSWORD"));
    credentials.put("api_key",       System.getenv("API_KEY"));
    
    assertSet(new String[]{"CLIENT_ID", "CLIENT_SECRET", "USERNAME", "PASSWORD"});
  }
  
  private static void assertSet(String... variables){
    for(String variable : variables) {
      if (System.getenv(variable) == null) {
        System.out.print("Environment variable '" + variable + "' is not set");
        System.exit(0);
      }
    }
  }
  
  private String getAccessToken() throws UnirestException {
    HttpResponse<String> response = Unirest
      .post(AUTH_URL)
      .fields(credentials)
      .asString();

    if(response.getStatus() != 200){
      throw new BadCredentialsException();
    }

    return new Gson().fromJson(response.getBody(), Token2ba.class).getToken();
  }
  
  private String getReal2baProduct(String productId) throws UnirestException {
    String token = getAccessToken();

    HttpResponse<String> response = Unirest
      .get(PROD_URL + productId)
      .header("Authorization", "Bearer " + token)
      .asString();

    return response.getBody();
  }
  
  private String getMocked2baProduct(String productId) {
    try {
      File f = new File(Resources.getResource("mocks/" + productId + ".json").getPath());
      return FileUtils.readFileToString(f, "UTF-8");
    } catch (Exception e){
      return null;
    }
  }

  /**
   * Try a mocked version first, otherwise return the real one
   * @param productId
   * @return
   * @throws UnirestException
   */
  public String get2baProductJson(String productId) throws UnirestException {
    String mocked = getMocked2baProduct(productId);
    if(mocked != null){
      return mocked;
    }
    else {
      return getReal2baProduct(productId);
    }
  }

  /**
   * Get marshalled Product2ba
   * @param productId
   * @return
   * @throws UnirestException
   */
  public Product2ba get2baProduct(String productId) throws UnirestException {
    return new Gson().fromJson(get2baProductJson(productId), Product2ba.class);
  }

  public String get2baProducts(String mc) throws UnirestException {
    String token = getAccessToken();

    HttpResponse<String> response = Unirest
      .get(LIST_URL + mc)
      .header("Authorization", "Bearer " + token)
      .asString();

    return response.getBody();
  }
}