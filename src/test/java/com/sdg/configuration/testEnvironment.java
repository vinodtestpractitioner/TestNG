package com.sdg.configuration;

public class testEnvironment{

    public String testEnv;

    public String configEnv(String environment) throws Exception {
        if(environment.equalsIgnoreCase("UAT")){
            testEnv="http://www.google.com";
        }
        else if(environment.equalsIgnoreCase("SIT")){
            testEnv="http://www.facebook.com";
        }
        else if(environment.equalsIgnoreCase("DIT")){
            testEnv="http://www.linkedin.com";
        }
        else {
            throw new Exception("Invalid Test Environment! Please pass a valid test environment value to environment parameter in testng.xml");
        }
        return testEnv;
    }
}
