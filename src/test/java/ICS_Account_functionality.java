import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ICS_Account_functionality {
    public static void main(String[] args) throws InterruptedException {
        accountTest("desktop");
        accountTest("tablet");
        accountTest("mobile");
    }

    public static void accountTest(String size) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = driverSetup(size);

        Dimension desktop = new Dimension(1366, 766);
        Dimension tablet = new Dimension(766, 1024);
        Dimension mobile = new Dimension(400, 800);

        if (size.equals("desktop")) {
            driver.manage().window().setSize(desktop);
        } else if (size.equals("tablet")) {
            driver.manage().window().setSize(tablet);
        } else {
            driver.manage().window().setSize(mobile);
        }

        driver.get("https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/");

        //check if "Account" button from Homepage it's written correctly
        if (size.equals("desktop") || size.equals("tablet")) {
            String accountButton = driver.findElement(By.cssSelector("[class='TopSection_accountLabel__1nFim']")).getText();
            boolean condition1 = false;
            if (accountButton.equals("Account")) {
                condition1 = true;
            }
            Assert.assertTrue(condition1);
            System.out.println(accountButton);

            WebElement accountClickOnButton = driver.findElement(By.cssSelector("[class='TopSection_accountLabel__1nFim']"));
            accountClickOnButton.click();
            Thread.sleep(3000);

        } else {
            WebElement menuButton = driver.findElement(By.xpath("//*[@id=\"TopSection_main__2Uztx\"]/button"));
            menuButton.click();
            Thread.sleep(3000);

            String accountButtonM = driver.findElement(By.cssSelector("[class='TopSection_btnMenuAccount___JCyj']")).getText();
            boolean condition1 = false;
            if (accountButtonM.equals("Account")) {
                condition1 = true;
            }
            Assert.assertTrue(condition1);
            System.out.println(accountButtonM);

            WebElement accountClickOnButtonM = driver.findElement(By.cssSelector("[class='TopSection_btnMenuAccount___JCyj']"));
            accountClickOnButtonM.click();
            Thread.sleep(3000);

        }

        //check if 'Sign Up' and 'Log In' buttons are displayed
        String signUpButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/button[1]")).getText();
        boolean condition = false;
        if (signUpButton.equals("Sign Up"))
            condition = true;
        Assert.assertTrue(condition);
        System.out.println(signUpButton);

        //check the action OnClick for 'Sign Up' button
        WebElement signUp = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/button[1]"));
        signUp.click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("You signed up", alert.getText());
        alert.accept();

        String logInButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/button[2]")).getText();
        condition = false;
        if (logInButton.equals("Log In"))
            condition = true;
        Assert.assertTrue(condition);
        System.out.println(logInButton);

        //check the action OnClick for 'Log In' button
        WebElement logIn = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/button[2]"));
        logIn.click();
        Thread.sleep(3000);
        Assert.assertEquals("You logged in", alert.getText());
        alert.accept();

        System.out.println("Test passed for " + size);
    }

    private static WebDriver driverSetup(String size) {
        if (size.equals("desktop") || size.equals("tablet")) {
            WebDriver driver = new ChromeDriver();
            return driver;
        } else {
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", "iPhone X");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            WebDriver driver = new ChromeDriver(chromeOptions);
            return driver;
        }
    }
}