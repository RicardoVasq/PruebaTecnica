package Page;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


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
    public static boolean isErrorMessageDisplayed() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed();
    }
    public static void opcionContact(int opcion){

        WebElement select = driver.findElement(selectContact);
        Select dropdwon = new Select(select);
        dropdwon.selectByValue(Integer.toString(opcion));
    }
    public static void ingresarEmail(String email){
        driver.findElement(campoEmail).sendKeys(email);
    }
    public static void ingresarMessage(String message){
        driver.findElement(campoMessage).sendKeys(message);
    }
    public static boolean mensajeEnvio() {
        return driver.findElement(mensajeExitoso).isDisplayed();}

    //Metodos - reutilizables
    public static void redireccionContactus(){
        final var urlPrincipal = "https://teststore.automationtesting.co.uk/index.php";
        Logs.info("Ingresamos a la URL: %s", urlPrincipal);
        driver.get(urlPrincipal);

        Logs.info("Navegamos a la pagina de Contact us");
        driver.findElement(By.id("contact-link")).click();

        Logs.info("Validamos la redireccion hacia la pagina Contact US");
        final var CurrentUrl = driver.getCurrentUrl();
        Assert.assertEquals(CurrentUrl,
                "https://teststore.automationtesting.co.uk/index.php?controller=contact");

        Logs.info("Validamos que esten visibles los campos Subject, email, message");

    }

}
