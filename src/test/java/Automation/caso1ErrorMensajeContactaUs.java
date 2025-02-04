package Automation;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class caso1ErrorMensajeContactaUs extends BaseTest {
    @Test
    public void Caso1Test() {
        //Invocamos metodo para validar existencia de Elementos y redireccion a la pagian Contactanos
        redireccionContactus();

        Logs.info("Validamos que al momento de enviar vacios los campos genere error");

        driver.findElement(By.xpath("//input[@name=\"submitMessage\"]")).click();
        Assert.assertTrue(driver.findElement(By.xpath
                ("//li[text()='Invalid email address.']")).isDisplayed());
    }
}
