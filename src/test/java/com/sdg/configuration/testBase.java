package com.sdg.configuration;

import com.sdg.datasetup.testDataSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class testBase extends testEnvironment{
    public static WebDriver driver;
    public static String testMethodName;
    public static String descriptiveTestName;
    public static HashMap<String, String> testdata;
    @BeforeTest
    @Parameters({"browser","environment"})
    public void browserSetup(String browser, String environment) throws Exception {
                String testEnv=configEnv(environment);
                if(browser.equalsIgnoreCase("firefox")){
                    System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/geckodriver.exe");
                    driver=new FirefoxDriver();
                    driver.get(testEnv);
                }
                else if(browser.equalsIgnoreCase("chrome")){
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
                    driver=new ChromeDriver();
                    driver.get(testEnv);
                }
                else if(browser.equalsIgnoreCase("edge")){
                    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/IEDriverServer.exe");
                    driver=new InternetExplorerDriver();
                    driver.get(testEnv);
                }
                else {
                    throw new Exception("Invalid Browser! Please pass a valid browser value to browser parameter in testng.xml");
                }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterTest
    public void tearDown(){
                if(driver!=null){
                    driver.quit();
                }
                else{
                    Assert.assertNull(driver,"Previous test driver is not closed!");
                }
    }
    @BeforeMethod
    public void setup(Method method) {
        testMethodName = method.getName(); //This will be:verifySaveButtonEnabled
        descriptiveTestName = method.getAnnotation(Test.class).testName(); //This will be: 'Verify if the save button is enabled'
        testdata= testDataSetup.retrieveTestData(testBase.testMethodName);
    }
}
