package tests;

import configuration.testBase;
import org.testng.annotations.Test;

public class googleTest extends testBase {
    @Test
    public void method1(){
        driver.get("https://www.google.com");
    }
}
