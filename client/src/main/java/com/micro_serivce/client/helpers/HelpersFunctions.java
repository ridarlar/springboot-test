package com.micro_serivce.client.helpers;

public class HelpersFunctions {

    public HelpersFunctions() {
    }

    public boolean isInteger(String inputValue){
        try{
            Integer.parseInt(inputValue);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
