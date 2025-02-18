package Test;

import Utilities.BaseTest;
import Utilities.Logs;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page.accessoriesPage;

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

        accessoriesPage.seleccionarPrimerProducto();
        //Hay que validar que sea el mismo producto de la lista
        Logs.info("Validamos Nombre del producto");
        accessoriesPage.compararNombreProductoaComprar(nombrePrimerProducto);
        Logs.info("Validamos Nombre del Precio");
        accessoriesPage.compararPrecioProductoaComprar(precioPrimerProducto);
        Logs.info("Aumentamos cantidad de proctos");
        accessoriesPage.aumentarCantidadProdcuto(5);
        Logs.info("Anadimos al carrito");
        accessoriesPage.clickAddtoCard();
        Logs.info("Validamos precio");
        accessoriesPage.validarPreciofinalCompra(5,precioPrimerProducto,7);
    }
}
