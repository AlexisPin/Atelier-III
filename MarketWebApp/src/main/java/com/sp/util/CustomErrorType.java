package com.sp.util;

public class CustomErrorType implements CustomType{
	 
    private String errorMessage;
 
    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}