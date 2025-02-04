package Automation;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class caso2EnvioFormularioContactUs extends BaseTest {
    @Test
    public void Caso2Test() {
        redireccionContactus();
        Logs.info("Seleccionamos la opcion de Webmaster del campo Subject");

        final var selectSubject = driver.findElement(By.id("id_contact"));
        final var select = new Select(selectSubject);
        select.selectByValue("1");

        Logs.info("Ingresamos un email de prueba");
        driver.findElement(By.id("email")).sendKeys("test12@prueba.com");

        Logs.info("Digiamos el texto en el elemento Message");
        driver.findElement(By.id("contactform-message")).
                sendKeys("Linea de texto 1\nLinea de texto 2\nLinea de Texto 3");

        Logs.info("Enviamos la informacion y validamos el envio exitoso");
        driver.findElement(By.xpath("//input[@name=\"submitMessage\"]")).click();
        Assert.assertTrue(driver.findElement(By.xpath
                ("//li[text()='Your message has been successfully sent to our team.']")).isDisplayed());
    }
}
