import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ICS_Top_Selling_Products {
    public static void main(String[] args) throws InterruptedException {
        topSellingTest("desktop");
        topSellingTest("tablet");
        topSellingTest("mobile");
    }

    public static void topSellingTest(String size) throws InterruptedException {
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


        // Check name of Top Gelato
        WebElement titleGelato = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[1]/div/div[1]"));
        Assert.assertEquals("TOP SELLING ICE CREAM & GELATO", titleGelato.getText());


        // Check name of Top Donuts
        WebElement titleDonuts = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[3]/div[1]/div/div[1]"));
        Assert.assertEquals("TOP SELLING DONUTS", titleDonuts.getText());


        // Check numbers of gelato from section
        WebElement topGelatoSection = driver.findElement(By.className("ItemSection_section__3zVJh"));
        Thread.sleep(1000);
        List<WebElement> topGelatoList = topGelatoSection.findElements(By.className("ItemSection_topsubsection__3H8Ig"));
        Assert.assertEquals(4, topGelatoList.size());


        // CHECKING EVERY CARD from Top Gelato
        cardDataTest(topGelatoList.get(0),"https://imgur.com/MJxQcj3.png","Bittersweet Mint Gelato", "$ 3");
        cardDataTest(topGelatoList.get(1),"https://imgur.com/EIqLW0s.png","Watermelon Gelato", "$ 3");
        cardDataTest(topGelatoList.get(2),"https://imgur.com/RD6a8lc.png","Strawberry Gelato", "$ 3");
        cardDataTest(topGelatoList.get(3),"https://imgur.com/Fwhw0gN.png","Chocolate Gelato", "$ 3");


        // Check numbers of donuts from section
        WebElement topDonutsSection = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[3]/div[2]"));
        Thread.sleep(1000);
        List<WebElement> topDonutsList = topDonutsSection.findElements(By.className("ItemSection_topsubsection__3H8Ig"));
        Assert.assertEquals(4, topDonutsList.size());


        // CHECKING EVERY CARD from Top Donuts
        cardDataTest(topDonutsList.get(0),"https://imgur.com/maVjPNW.png","Chocolate Mix Donuts", "$ 2.5");
        cardDataTest(topDonutsList.get(1),"https://imgur.com/BiAdGs7.png","Chocolate Donuts", "$ 2.5");
        cardDataTest(topDonutsList.get(2),"https://imgur.com/OWZrVeo.png","Strawberry Donuts", "$ 2.5");
        cardDataTest(topDonutsList.get(3),"https://imgur.com/JYv1ij9.png","Ferrero Donuts", "$ 2.5");


        // Check "Explore all"
        WebElement titleExploreAllGelato;
        WebElement titleExploreAllDonuts;
        if(size.equals("desktop")) {
            // Gelato
            titleExploreAllGelato = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[2]/div[1]/div/div[2]/span[1]"));
            Assert.assertEquals("EXPLORE ALL", titleExploreAllGelato.getText());

            WebElement exploreAllGelato = driver.findElement(By.className("ItemSection_explore__3_C5a"));
            checkLink(exploreAllGelato, "https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/gelato");

            // Donuts
            titleExploreAllDonuts = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[3]/div[1]/div/div[2]/span[1]"));
            Assert.assertEquals("EXPLORE ALL", titleExploreAllDonuts.getText());

            WebElement exploreAllDonuts = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[3]/div[1]/div/div[2]"));
            checkLink(exploreAllDonuts, "https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/donuts");
        }
        else if (size.equals("tablet")){
            // Gelato
            titleExploreAllGelato = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div[3]/div[1]/div/div[2]/span[2]"));
            Assert.assertEquals("ALL", titleExploreAllGelato.getText());

            WebElement exploreAllGelato = driver.findElement(By.className("ItemSection_explore__3_C5a"));
            checkLink(exploreAllGelato, "https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/gelato");

            // Donuts
            titleExploreAllDonuts = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[3]/div[1]/div/div[2]/span[2]"));
            Assert.assertEquals("ALL", titleExploreAllDonuts.getText());

            WebElement exploreAllDonuts = driver.findElement(By.xpath("/html/body/div[1]/div/main/div[3]/div[1]/div/div[2]"));
            checkLink(exploreAllDonuts, "https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/donuts");
        }

        System.out.println("test passed");

        driver.quit();
    }


    private static void checkLink(WebElement card, String url) {
        WebElement exploreAllElement = card.findElement(By.cssSelector("a"));
        String href = exploreAllElement.getAttribute("href");
        Assert.assertEquals(url, href);
    }


    private static void cardDataTest(WebElement card, String url, String title, String price) {
        //checking column data
        WebElement topSellingImage = card.findElement(By.cssSelector("img"));
        String src = topSellingImage.getAttribute("src");
        Assert.assertEquals(url, src);


        WebElement cardTitle = card.findElement(By.className("ItemSection_toptitle__hT84x"));
        String cardTitleText = cardTitle.getText();
        Assert.assertEquals(title, cardTitleText);

        WebElement cardDescription = card.findElement(By.className("ItemSection_topdesc__1ecDS"));
        String cardDescriptionText = cardDescription.getText();
        Assert.assertEquals("Treat yourself to ice cream infused with smooth Bourbon sprinkled", cardDescriptionText);

        WebElement cardPrice = card.findElement(By.className("ItemSection_topprice__22gYO"));
        String cardPriceText = cardPrice.getText();
        Assert.assertEquals(price, cardPriceText);

        WebElement button = card.findElement(By.className("ItemSection_basketbutton__2RUER"));
        String buttonText = button.getText();
        Assert.assertEquals("Add to basket", buttonText);
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
