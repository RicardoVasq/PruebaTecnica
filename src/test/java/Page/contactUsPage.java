package Page;

import Utilities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class contactUsPage extends BaseTest {

    // ✅ Localizadores
    private static By submitButton = By.xpath("//input[@name=\"submitMessage\"]");
    private static By errorMessage = By.xpath("//li[text()='Invalid email address.']");

    // ✅ Métodos de acción
    public static void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public static boolean isErrorMessageDisplayed() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed();
    }

}
