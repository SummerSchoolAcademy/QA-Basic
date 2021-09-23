
import io.github.bonigarcia.wdm.WebDriverManager;
        import org.junit.Assert;
        import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


public class ICS_Donuts_Special {
    public static void main(String[] args) throws InterruptedException {
        donutsPlpTest("desktop");
        donutsPlpTest("tablet");
        donutsPlpTest("mobile");
    }

    public static void donutsPlpTest(String size) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = driverSetup(size);

        Dimension desktop = new Dimension(1366, 768);
        Dimension tablet = new Dimension(768, 1024);
        Dimension mobile = new Dimension(400, 800);
        if (size.equals("desktop")){
            driver.manage().window().setSize(desktop);
        }else if (size.equals("tablet")){
            driver.manage().window().setSize(tablet);
        }else{
            driver.manage().window().setSize(mobile);
        }

        driver.get("https://icecreamshop-frontend-rkbemzbp2q-ez.a.run.app/donutsForSpecialNeeds");

        //fetching list web elements titles

        List<WebElement> titlesList = driver.findElements(By.className("ItemSection_title___WbS6"));

        //fetching top selling title element
        WebElement topSellingTitle = titlesList.get(0);
        String topSellingTitleText = topSellingTitle.getText();
        Assert.assertEquals("TOP SELLING DONUTS FOR SPECIAL NEEDS", topSellingTitleText);


        //fetching the top selling donuts cards into a list
        WebElement topSellingSection = driver.findElement(By.className("ItemSection_section__3zVJh"));

        Thread.sleep(1000);
        List<WebElement> topSellingList = topSellingSection.findElements(By.className("ItemSection_topsubsection__3H8Ig"));

        //checking the number of top selling donuts cards
        Assert.assertEquals(4, topSellingList.size());

        //CHECKING EVERY CARD (top selling)
        cardDataTest(topSellingList.get(0),"https://imgur.com/maVjPNW.png","Chocolate Mix Donuts");
        cardDataTest(topSellingList.get(1),"https://imgur.com/BiAdGs7.png","Chocolate Donuts");
        cardDataTest(topSellingList.get(2),"https://imgur.com/OWZrVeo.png","Strawberry Donuts");
        cardDataTest(topSellingList.get(3),"https://imgur.com/JYv1ij9.png","Ferrero Donuts");

        //fetching all donuts title element
        WebElement allDonutsTitle = titlesList.get(1);
        String allDonutsTitleText = allDonutsTitle.getText();
        Assert.assertEquals("ALL DONUTS FOR SPECIAL NEEDS PRODUCTS", allDonutsTitleText);


        //fetching all donuts cards into a list
        WebElement allDonutsSection = driver.findElement(By.className("ItemPagination_section__1kEMC"));

        List<WebElement> allDonutsList = allDonutsSection.findElements(By.className("ItemPagination_topsubsection__6Eg5P"));

        //checking the number of all donuts cards
            boolean max = allDonutsList.size() <= 12;
            Assert.assertTrue(max);

        //CHECKING EVERY CARD (all donuts)
            cardDataTest(allDonutsList.get(0), "https://imgur.com/maVjPNW.png", "Chocolate Mix Donuts");
            cardDataTest(allDonutsList.get(1), "https://imgur.com/OWZrVeo.png", "Strawberry Donuts");
            cardDataTest(allDonutsList.get(2), "https://imgur.com/Oq0ZpWd.png", "Paradise Donuts");
            cardDataTest(allDonutsList.get(3), "https://imgur.com/QUzgKXU.png", "Marshmallow Donuts");

        //fetching pagination buttons
        //PREVIOUS
        WebElement previousButton = driver.findElement(By.className("ItemPagination_previous__26OEw"));
        String previousButtonText = previousButton.getText();
        Assert.assertEquals("Previous", previousButtonText);

        //PAGE NUMBER
        WebElement pageNumberButton = driver.findElement(By.className("ItemPagination_pagenumber__3UEre"));
        String pageNumberButtonText = pageNumberButton.getText();
        Assert.assertEquals("1", pageNumberButtonText);

        //NEXT
        WebElement nextButton = driver.findElement(By.className("ItemPagination_next__28Tg1"));
        String nextButtonText = nextButton.getText();
        Assert.assertEquals("Next", nextButtonText);

        System.out.println("test passed");

        driver.quit();
    }

    private static void cardDataTest(WebElement card, String url, String title) {
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
        Assert.assertEquals("$ 2.5", cardPriceText);

        WebElement button = card.findElement(By.className("ItemSection_basketbutton__2RUER"));
        String buttonText = button.getText();
        Assert.assertEquals("Add to basket", buttonText);
    }

    private static WebDriver driverSetup(String size){
        if (size.equals("desktop") || size.equals("tablet")){
            return new ChromeDriver();
        }else {
            Map<String, String> mobileEmulation = new HashMap<String, String>();
            mobileEmulation.put("deviceName", "iPhone X");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            return new ChromeDriver(chromeOptions);
        }
    }
}
