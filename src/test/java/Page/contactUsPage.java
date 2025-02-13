package Page;

import Utilities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class contactUsPage extends BaseTest {

    // Localizadores
    private static By submitButton = By.xpath("//input[@name=\"submitMessage\"]");
    private static By errorMessage = By.xpath("//li[text()='Invalid email address.']");
    private static By selectContact = By.id("id_contact");
    private static By campoEmail = By.id("email");
    private static By campoMessage = By.id("contactform-message");
    private static By mensajeExitoso = By.xpath("//li[text()='Your message has been successfully sent to our team.']");

    // Métodos de acción
    public static void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    //Identificamos elementos de la pagina
    public static boolean isErrorMessageDisplayed() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed();
    }

    public static void opcionContact(String opcion){
        WebElement select = driver.findElement(selectContact);
        Select dropdwon = new Select(select);
        dropdwon.selectByValue(opcion);
    }

    public static void ingresarEmail(String email){
        driver.findElement(campoEmail).sendKeys(email);
    }

    public static void ingresarMessage(String message){
        driver.findElement(campoMessage).sendKeys(message);
    }
    public static boolean mensajeEnvio() {
        return driver.findElement(mensajeExitoso).isDisplayed();}

}
