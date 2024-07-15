import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebFormAutomationTest {


    WebDriver driver;

    @BeforeAll
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headed--");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @DisplayName("Check if webform input and Submission Workflow is Successful")
    @Test
    public void automateWebform() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");
        Utils.handleCookiesWindow(driver);

        List<WebElement> elements = driver.findElements(By.className("form-control"));
        elements.get(0).sendKeys("Shuhana Jahan");
        elements.get(1).sendKeys("01317735677");

        WebElement dateBox = driver.findElement(By.xpath("//input[@id='edit-date']"));

        //Fill date as dd/mm/yyyy as 09/25/2013

        dateBox.sendKeys("25/09/2013");

        dateBox.sendKeys(Keys.TAB);


        elements.get(3).sendKeys("suhanariya4@gmail.com");


        elements.get(4).sendKeys("Hello there! I wanna be a SQA Engineer.");
        Utils.scrollDown(driver,900);

        Thread.sleep(2000);

        String absolutePathPart =System.getProperty("user.dir");
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys( absolutePathPart + "/src/test/resources/r.pdf");


        Thread.sleep(3000);
        List <WebElement> checkBox =driver.findElements(By.xpath("//input[@id='edit-age']"));
        checkBox.get(0).click();

        boolean isCheckboxSelected = checkBox.get(0).isSelected();  // Check if the checkbox is selected

        if (!isCheckboxSelected) {
            // Handle the case where the checkbox is not selected
            System.out.println("Checkbox is not selected.");
        } else {
            // Handle the case where the checkbox is selected
            System.out.println("Checkbox is selected.");
        }

        // Retrieve the entered value from the name field
        WebElement nameField = driver.findElement(By.id("edit-name"));
        String enteredName = nameField.getAttribute("value");

        // Check if the name field is filled and checkbox is selected
        if (!enteredName.isEmpty() && isCheckboxSelected) {
            driver.findElement(By.id("edit-submit")).click();
            System.out.println("Form submitted successfully!");
        } else {
            System.out.println("Name field must be filled and checkbox must be selected to submit the form.");
        }

        Thread.sleep(2000);
        String submitActualText = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(submitActualText.contains("Thank you for your submission!"));

    }

        @AfterAll
        public void quitBrowser(){
            driver.quit();
        }


}
