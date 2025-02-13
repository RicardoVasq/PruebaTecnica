package Automation;
import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.contactUsPage;

public class contactUsTest extends BaseTest {


    @Test
    public void caso1ErrorMensajeContactaUs() {
        redireccionContactus();

        contactUsPage.clickSubmitButton();

        Assert.assertTrue(contactUsPage.isErrorMessageDisplayed(),
                "El mensaje de error no se mostró.");
    }

    @Test
    public void caso2EnvioFormulario(){
        redireccionContactus();
        Logs.info("Seleccionamos la opcion de Webmaster del campo Subject");
        contactUsPage.opcionContact("1");

        Logs.info("Ingresamos un email de prueba");
        contactUsPage.ingresarEmail("test12@prueba.com");

        Logs.info("Digiamos el texto en el elemento Message");
        contactUsPage.ingresarMessage("Linea de texto 1\nLinea de texto 2\nLinea de Texto 3");

        Logs.info("Enviamos la informacion y validamos el envio exitoso");
        contactUsPage.clickSubmitButton();
        contactUsPage.mensajeEnvio();

    }
}
