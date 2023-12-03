package com.company.solarwatch.exception;

public class CityNotFoundException extends RuntimeException{
     public CityNotFoundException(String city) {
          super("City not found in the Geo API: " + city);
     }
}
