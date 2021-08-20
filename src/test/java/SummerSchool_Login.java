import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SummerSchool_Login {
    public static void main(String[] args) throws InterruptedException {
        completeValidOrderWithFirstUser();
    }

    public static void completeValidOrderWithFirstUser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        /**System.setProperty("webdriver.chrome.driver", "path of driver");    --> daca nu va deschide chrome , bagati linia asta cu path-ul catre chromedriver.exe*/
        //login process
        driver.get("https://altex.ro/");
        WebElement backToSite = driver.findElement(By.cssSelector(".Promo2-headerLink"));
        backToSite.click();
        Thread.sleep(2000);
        WebElement loginButton = driver.findElement(By.xpath("//*[span=\"Contul meu\"]"));
        WebElement nameField = driver.findElement(By.name("email"));
        WebElement passField = driver.findElement(By.name("password"));
        loginButton.click();
        nameField.sendKeys("anitacochirlea@yahoo.com");
        passField.sendKeys("ParolaDeTest!");

        WebElement authButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div/div[2]/div[3]/div[1]/div/div[2]/div/form/div[3]/span/button/div/div"));
        authButton.click();
        Thread.sleep(5000);

        //check "comenzile mele" button from menu
        String actualOrders = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div/div[2]/div[3]/div[1]/div/div[2]/div/ul/li[4]/a")).getText();
        boolean condition1 = false;
        if(actualOrders.equals("Comenzile mele"))
        {
            condition1 = true;
        }
        Assert.assertTrue(condition1);
        System.out.println(actualOrders);

        //close menu
        WebElement menu = driver.findElement(By.cssSelector(".SessionContTrigger.text-usm"));
        menu.click();

        //search something
        WebElement searchBox = driver.findElement(By.cssSelector(".SearchInputComponent.w-full"));
        searchBox.click();
        searchBox.sendKeys("xbox");

        //click on the find button
        WebElement findButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div/div[2]/div[4]/form/div/div[2]/button/div/div"));
        Thread.sleep(2000);
        findButton.click();

        //add to cart
        Thread.sleep(4000);
        List<WebElement> addTo = driver.findElements(By.cssSelector(".border-2.rounded-r"));
        WebElement addToCartXbox = addTo.get(0);
        addToCartXbox.click();
        Thread.sleep(2000);

        //close add to cart
        WebElement closeButton = driver.findElement(By.cssSelector(".close"));
        closeButton.click();

        //see cart
        WebElement myCart = driver.findElement(By.cssSelector(".SessionCosTrigger.text-usm"));
        myCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/div[3]/a/div/div"));
        goToCart.click();
        Thread.sleep(2000);


        //empty my cart
        List<WebElement> emptyCart = driver.findElements(By.cssSelector(".border-2.rounded.border-primary"));
        WebElement emptyMyCart = emptyCart.get(0);
        emptyMyCart.click();
        Thread.sleep(2000);


        //goBack
        WebElement goBack = driver.findElement(By.xpath("//*[@id=\"checkout\"]/div/section/div/div/div/a"));
        goBack.click();
        Thread.sleep(2000);


        //logOut
        WebElement account = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div/div[2]/div[3]/div[1]/a"));
        account.click();
        WebElement logOut = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/div/div[2]/div[3]/div[1]/div/div[2]/div/ul/li[11]/a"));
        logOut.click();

        System.out.println("test passed");
        driver.quit();



    }
}
