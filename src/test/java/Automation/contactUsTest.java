package Automation;
import Utilities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.contactUsPage;

public class contactUsTest extends BaseTest {


    @Test
    public void validarMensajeErrorEnContacto() {
        redireccionContactus();
        //contactUsPage = new ContactUsPage(driver);
        contactUsPage.clickSubmitButton();

        Assert.assertTrue(contactUsPage.isErrorMessageDisplayed(),
                "El mensaje de error no se mostró.");
    }
}
