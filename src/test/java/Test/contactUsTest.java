package Test;
import Readers.JsonReader;
import Utilities.BaseTest;
import Utilities.Logs;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.contactUsPage;

public class contactUsTest extends BaseTest {


    @Test
    public void caso1ErrorMensajeContactaUs() {
        contactUsPage.redireccionContactus();

        contactUsPage.clickSubmitButton();

        Assert.assertTrue(contactUsPage.isErrorMessageDisplayed(),
                "El mensaje de error no se mostró.");
    }

    @Test
    public void caso2EnvioFormulario(){
        contactUsPage.redireccionContactus();
        Logs.info("Seleccionamos la opcion de Webmaster del campo Subject");
        contactUsPage.opcionContact(JsonReader.variableReader().getContactFormulario().getSubjectOpcion());

        Logs.info("Ingresamos un email de prueba");
        contactUsPage.ingresarEmail(JsonReader.variableReader().getContactFormulario().getEmailAddress());

        Logs.info("Digiamos el texto en el elemento Message");
        contactUsPage.ingresarMessage(JsonReader.variableReader().getContactFormulario().getMessage());

        Logs.info("Enviamos la informacion y validamos el envio exitoso");
        contactUsPage.clickSubmitButton();
        contactUsPage.mensajeEnvio();

    }
}
