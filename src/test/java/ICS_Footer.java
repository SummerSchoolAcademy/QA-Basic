import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ICS_Footer {
    public static void main(String[] args) throws InterruptedException {
        footerTest("desktop");
        footerTest("tablet");
        footerTest("mobile");
    }

    public static void footerTest(String size) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = driverSetup(size);

        Dimension desktop = new Dimension(1366, 766);
        Dimension tablet = new Dimension(766, 1024);
        Dimension mobile = new Dimension(400, 800);
        if (size.equals("desktop")){
            driver.manage().window().setSize(desktop);
        }else if (size.equals("tablet")){
            driver.manage().window().setSize(tablet);
        }else{
            driver.manage().window().setSize(mobile);
        }

        driver.get("https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/");

        //fetching footer element
        WebElement footer = driver.findElement(By.className("footer"));

        //first footer
        WebElement footer1 = footer.findElement(By.cssSelector("[class^='footer-']"));

        //fetching the column title elements into a list
        List<WebElement> columnList = footer1.findElements(By.cssSelector("h4"));

        //checking the number of columns
        Assert.assertEquals(4, columnList.size());

        //checking column data
        if (size.equals("desktop")){
            columnDataTest(columnList, size);
        }else if (size.equals("tablet")){
            columnDataTest(columnList, size);
        }else{
            columnDataTest(columnList, size);
        }

        //fetching the row sub-elements into a list
        List<WebElement> rowList = footer1.findElements(By.cssSelector("a"));

        //checking row data
        if (size.equals("desktop")){
            rowListTest(rowList, size, footer1);
        }else if (size.equals("tablet")){
            rowListTest(rowList, size, footer1);
        }else{
            rowListTest(rowList, size, footer1);
        }

        //second footer
        WebElement footer2 = footer.findElement(By.className("footer1"));

        WebElement title = footer2.findElement(By.cssSelector("h1"));
        String titleText = title.getText();

        Assert.assertEquals("@2021 Gelato & Donuts.", titleText);


        //newsletter text field element
        WebElement newsletterTextField = footer1.findElement(By.cssSelector("[id$='-input']"));

        //newsletter button element
        WebElement newsletterButton = footer1.findElement(By.cssSelector("[id$='-button']"));

        //checking email validation - good email
        newsletterTextField.click();
        newsletterTextField.sendKeys("test@test");

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(newsletterButton));

        Assert.assertNull(newsletterButton.getAttribute("disabled"));
        newsletterButton.click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals("A confirmation email will be sent to the provided email address", alert.getText());
        alert.accept();


        //checking email validation - bad email
        newsletterTextField.click();
        newsletterTextField.sendKeys("test");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id$='-button']")));

        Assert.assertNotNull(newsletterButton.getAttribute("disabled"));

        System.out.println("test passed");

        driver.quit();
    }

    private static void columnDataTest(List<WebElement> columnList, String size) {
        //checking column data
        if (size.equals("desktop")){
            Assert.assertEquals("USEFUL INFO", columnList.get(0).getText());
            Assert.assertEquals("YOU CAN FIND US IN", columnList.get(1).getText());
            Assert.assertEquals("ABOUT GELATO & DONUTS", columnList.get(2).getText());
            Assert.assertEquals("NEWSLETTER", columnList.get(3).getText());
        }else{
            Assert.assertEquals("NEWSLETTER", columnList.get(0).getText());
            Assert.assertEquals("USEFUL INFO", columnList.get(1).getText());
            Assert.assertEquals("YOU CAN FIND US IN", columnList.get(2).getText());
            Assert.assertEquals("ABOUT GELATO & DONUTS", columnList.get(3).getText());
        }
    }

    private static void rowListTest(List<WebElement> rowList, String size, WebElement footer) throws InterruptedException {
        if (size.equals("mobile")){
            //expand button
            List<WebElement> expander = footer.findElements(By.cssSelector("[id^='imag']"));

            //dropdown content box
            List<WebElement> dropdownBox = footer.findElements(By.cssSelector("[id^='dropdown']"));

            List <WebElement> dropdownBoxItems1 = dropdownBox.get(0).findElements(By.cssSelector("[class='col']"));
            List <WebElement> dropdownBoxItems2 = dropdownBox.get(1).findElements(By.cssSelector("[class='col']"));
            List <WebElement> dropdownBoxItems3 = dropdownBox.get(2).findElements(By.cssSelector("[class='col']"));

            Assert.assertEquals(4, dropdownBoxItems1.size());
            Assert.assertEquals(4, dropdownBoxItems2.size());
            Assert.assertEquals(5, dropdownBoxItems3.size());


            //first dropdown box
            //checking to see if the dropdown opens
            expander.get(0).click();
            expander.get(0).click();
            Assert.assertEquals("block", dropdownBox.get(0).getCssValue("display"));

            //checking values inside dropdown box
            Assert.assertEquals("Privacy Policy", dropdownBoxItems1.get(0).getText());
            Assert.assertEquals("Terms & Conditions", dropdownBoxItems1.get(1).getText());
            Assert.assertEquals("Cookie Policy", dropdownBoxItems1.get(2).getText());
            Assert.assertEquals("FAQ", dropdownBoxItems1.get(3).getText());

            //checking to see if the dropdown closes
            expander.get(0).click();
            Assert.assertEquals("none", dropdownBox.get(0).getCssValue("display"));


            //2nd dropdown box
            //checking to see if the dropdown opens
            expander.get(1).click();
            expander.get(1).click();
            Assert.assertEquals("block", dropdownBox.get(1).getCssValue("display"));

            //checking values inside dropdown box
            Assert.assertEquals("London, UK", dropdownBoxItems2.get(0).getText());
            Assert.assertEquals("Bucharest, RO", dropdownBoxItems2.get(1).getText());
            Assert.assertEquals("Paris, FR", dropdownBoxItems2.get(2).getText());
            Assert.assertEquals("Sofia, BG", dropdownBoxItems2.get(3).getText());

            //checking to see if the dropdown closes
            expander.get(1).click();
            Assert.assertEquals("none", dropdownBox.get(1).getCssValue("display"));


            //3rd dropdown box
            //checking to see if the dropdown opens
            expander.get(2).click();
            expander.get(2).click();
            Assert.assertEquals("block", dropdownBox.get(2).getCssValue("display"));

            //checking values inside dropdown box
            Assert.assertEquals("About us", dropdownBoxItems3.get(0).getText());
            Assert.assertEquals("Store locator", dropdownBoxItems3.get(1).getText());
            Assert.assertEquals("Franchise", dropdownBoxItems3.get(2).getText());
            Assert.assertEquals("Careers", dropdownBoxItems3.get(3).getText());
            Assert.assertEquals("Contact us", dropdownBoxItems3.get(4).getText());

            //checking to see if the dropdown closes
            expander.get(2).click();
            Assert.assertEquals("none", dropdownBox.get(2).getCssValue("display"));

        }else{
            //checking the number of row elements
            Assert.assertEquals(13, rowList.size());
            //checking row data
            Assert.assertEquals("Privacy Policy", rowList.get(0).getText());
            Assert.assertEquals("London, UK", rowList.get(1).getText());
            Assert.assertEquals("About Us", rowList.get(2).getText());
            Assert.assertEquals("Terms & Conditions", rowList.get(3).getText());
            Assert.assertEquals("Bucharest, RO", rowList.get(4).getText());
            Assert.assertEquals("Store locator", rowList.get(5).getText());
            Assert.assertEquals("Cookie Policy", rowList.get(6).getText());
            Assert.assertEquals("Paris, FR", rowList.get(7).getText());
            Assert.assertEquals("Franchise", rowList.get(8).getText());
            Assert.assertEquals("FAQ", rowList.get(9).getText());
            Assert.assertEquals("Sofia, BG", rowList.get(10).getText());
            Assert.assertEquals("Careers", rowList.get(11).getText());
            Assert.assertEquals("Contact Us", rowList.get(12).getText());
        }
    }

    private static WebDriver driverSetup(String size){
        if (size.equals("desktop") || size.equals("tablet")){
            WebDriver driver = new ChromeDriver();
            return driver;
        }else {
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", "iPhone X");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            WebDriver driver = new ChromeDriver(chromeOptions);
            return driver;
        }
    }
}
