package com.services.account.exception;


public class ResourceNotFoundException extends RuntimeException{

   public ResourceNotFoundException(String resource, String attribute, String value){
    super(String.format("%s resource not found for %s : %s",resource,attribute,value));
   }

}
