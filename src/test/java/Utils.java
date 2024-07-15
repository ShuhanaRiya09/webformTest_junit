import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {


    public static void scrollDown(WebDriver driver , int height){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");

    }

    public  static void handleCookiesWindow(WebDriver driver) {
        // Locate the accept cookies button directly
        WebElement acceptCookiesButton = driver.findElement(By.id("onetrust-accept-btn-handler")); // Replace with the actual ID or selector
        acceptCookiesButton.click();
        System.out.println("Cookies window accepted.");
    }
}
