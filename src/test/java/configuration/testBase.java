package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class testBase {
    public static WebDriver driver;
    @BeforeTest
    @Parameters("browser")
    public void browserSetup(String browser) throws Exception {
                if(browser.equalsIgnoreCase("firefox")){
                    System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/geckodriver.exe");
                    driver=new FirefoxDriver();
                }
                else if(browser.equalsIgnoreCase("chrome")){
                    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
                    driver=new ChromeDriver();
                }
                else if(browser.equalsIgnoreCase("edge")){
                    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/src/test/resources/drivers/IEDriverServer.exe");
                    driver=new InternetExplorerDriver();
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
}
