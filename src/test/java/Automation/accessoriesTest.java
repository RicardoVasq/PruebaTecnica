package Automation;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.accessoriesPage;

import java.time.Duration;

public class accessoriesTest extends BaseTest {
    @Test
    public void testCas3Compras() throws InterruptedException {
        Logs.info("Ingresamos credenciales invalidas");
        accessoriesPage.Login("false@gg.com", "noescorrecta");

        Logs.info("Validamos el mensaje de error");
        Assert.assertTrue(accessoriesPage.mensajeLogin());

        Logs.info("Ingresamos con credenciales validas");
        accessoriesPage.Login("prueba@gg.com","ricardo");
        Assert.assertTrue(accessoriesPage.usuarioLogeado());

        Logs.info("Navegamos hasta la seccion de Accessories");
        accessoriesPage.linkAccesories();

        Logs.info("Desplegamos menu de opciones de ordenamiento");
        accessoriesPage.menuOrden();

        Logs.info("Seleccionamos el orden de productos deseado");
        accessoriesPage.seleccionOrden("Name, A to Z");

        Logs.debug("Obtenemos los datos del producto");
        String[] infoproducto = accessoriesPage.infoPrimerProducto();
        final var nombrePrimerProducto = infoproducto[0];
        final var precioPrimerProducto = accessoriesPage.obtenerPrecio(infoproducto[1]);

        Logs.info("Damos click en producto del primero de la Lista");
        //Tenes que pensar como hacer click en el primer producto de una forma escalable
        accessoriesPage.seleccionarPrimerProducto(); //AUN ESTAMOS ACA
        //accessoriesPage.aumentarCantidadProdcuto(5); //no se le ha dado click aun al producto


    }
}
