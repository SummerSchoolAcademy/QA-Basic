import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ICS_MerchandiseSection {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/");
        WebDriverWait wait = new WebDriverWait(driver, 60);
        CheckTitle(driver, wait);
        CheckElements(driver, wait);

    }

    public static void CheckTitle(WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='merchandise']")));
        String actualTitle = driver.findElement(By.xpath("//*[text()='merchandise']")).getText();
        String expectedText = "MERCHANDISE";
        Assert.assertEquals("Merchandise title is not OK!", expectedText, actualTitle);
    }

    public static void CheckElements(WebDriver driver, WebDriverWait wait) throws InterruptedException {

        //Check product title and also check that the 2 products are on the page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Basic T Shirt']")));
        String actualTitleTshirt = driver.findElement(By.xpath("//*[text()='Basic T Shirt']")).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Simple Mug']")));
        String actualTitleMug = driver.findElement(By.xpath("//*[text()='Simple Mug']")).getText();

        String expectedTitleTshirt = "Basic T Shirt";
        String expectedTitleMug = "Simple Mug";

        Assert.assertEquals("Missing product or wrong title! (Basic T shirt)", expectedTitleTshirt, actualTitleTshirt);
        Assert.assertEquals("Missing product or wrong title! (Simple Mug)", expectedTitleMug, actualTitleMug);


        //Check Description of Merchandise products
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam']")));
        int checkDescription = driver.findElements(By.xpath("//*[text()='Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam']")).size();

        Assert.assertEquals("Missing description!", 2, checkDescription);


        //Check img src is correct and that both img are loaded.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Product picture' and @src='/T-Shirt.png']")));
        WebElement imageTshirt = driver.findElement(By.xpath("//img[@alt='Product picture' and @src='/T-Shirt.png']"));
        Boolean imageTshirtPresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageTshirt);
        String actualTshirtImg = driver.findElement(By.xpath("//img[@alt='Product picture' and @src='/T-Shirt.png']")).getAttribute("src");
        String expectedTshirtImg = "https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/T-Shirt.png";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Product picture' and @src='/Mug.png']")));
        WebElement imageMug = driver.findElement(By.xpath("//img[@alt='Product picture' and @src='/Mug.png']"));
        Boolean imageMugPresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageMug);
        String actualMugImg = driver.findElement(By.xpath("//img[@alt='Product picture' and @src='/Mug.png']")).getAttribute("src");
        String expectedMugImg = "https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/Mug.png";

        Assert.assertEquals("T shirt image not OK!", expectedTshirtImg, actualTshirtImg);
        Assert.assertTrue("T shirt image is missing", imageTshirtPresent);

        Assert.assertEquals("Simple Mug image is not ok!", expectedMugImg, actualMugImg);
        Assert.assertTrue("Simple Mug image is missing!", imageMugPresent);


        //Check products price
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='$  2.5']")));
        int productsNumber = driver.findElements(By.xpath("//*[text()='$  2.5']")).size();

        Assert.assertEquals("Missing product price!", 2, productsNumber);


        //Check Add to Basket Button Add to basket
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Add to basket']")));
        int addToBasketNumber = driver.findElements(By.xpath("//*[text()='Add to basket']")).size();

        Assert.assertEquals("Missing Add to Basket button", 10, addToBasketNumber);

        System.out.println("0 problems detected!");
    }
}
