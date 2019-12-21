package com.sdg.tests;
import com.sdg.configuration.testBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class loginTests extends testBase {
    @Test
    public void method1(){
        System.out.println(testdata.get("URL"));
        System.out.println("Hello!");
    }
    @Test(enabled = false)
    public void method2(){
        String expected="Google";
        String actual=driver.getTitle();
        Assert.assertEquals(actual,expected);
    }
}
