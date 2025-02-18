package Page;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class accessoriesPage extends BaseTest {

    //Localizadores
    private static final By errorMensajeLogin = By.xpath("//li[text()='Authentication failed.']");
    private static final By linkLogin = By.cssSelector("div[class='user-info']");
    private static final By campoEmail = By.id("field-email");
    private static final By campoContra =  By.id("field-password");
    private static final By buttonLogin = By.id("submit-login");
    private static final By nombreUsuarioLogeado = By.xpath("//span[text()='Ricardo Vasquez']");
    private static final By ingresoAccesories = By.id("category-6");
    private static final By buttonOrden = By.cssSelector("button[data-toggle='dropdown']");
    private static final By opcionesOrden = By.cssSelector("a[class='select-list js-search-link']");
    private static final By primerProducto = By.cssSelector("article[class=\"product-miniature js-product-miniature\"]");
    private static final By buttonAnadirCantidadProducto = By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']");
    private static final By clickPrimerProducto = By.cssSelector("div[class='thumbnail-top']");
    private static final By buttonAddtoCard = By.cssSelector("button[data-button-action='add-to-cart']");
    private static final By titleProductos = By.cssSelector("h1[class='h1']");
    private static final By precioProdcuto = By.cssSelector("span[class='current-price-value']");
    private static final By modalCompras = By.id("blockcart-modal");
    private static final By precioTotalCompra = By.cssSelector("span[class='value']");

    //Metodos Accion
    public static boolean mensajeLogin(){
        return driver.findElement(errorMensajeLogin).isDisplayed();
    }
    public static boolean usuarioLogeado(){
        return driver.findElement(nombreUsuarioLogeado).isDisplayed();
    }
    public static void linkAccesories(){driver.findElement(ingresoAccesories).click();}
    public static void menuOrden(){ driver.findElement(buttonOrden).click();}
    public static void seleccionOrden(String orden) throws InterruptedException {
        List<WebElement> elements = driver.findElements(opcionesOrden);
        for (WebElement element : elements){
            final var opcion = element.getText();
            if(opcion.equals(orden)){
                Logs.debug("Orden es %s", element.getText());
                element.click();
                Thread.sleep(1000);
                break;
            }
        }
    }
    public static String[] infoPrimerProducto(){
        final var datosPrimerProducto = driver.findElement(primerProducto).getText();
        String[] infoProducto = datosPrimerProducto.split("\n");
        Logs.debug("Mostrar nombre del producto %s",infoProducto[0]);
        Logs.debug("Mostrar precio del producto %s",infoProducto[1]);
        return infoProducto;
    }
    public static void seleccionarPrimerProducto(){driver.findElement(clickPrimerProducto).click();}
    public static void aumentarCantidadProdcuto(int cantidad){
        for (int i = 1; i < cantidad ; i++) {
            driver.findElement(buttonAnadirCantidadProducto).click();
        }

    }
    public static void clickAddtoCard() throws InterruptedException {driver.findElement(buttonAddtoCard).click();
        Thread.sleep(1000);
    }
    public static void compararNombreProductoaComprar(String nombrePrimerProducto){
        Assert.assertEquals(nombrePrimerProducto.toUpperCase(), driver.findElement(titleProductos).getText());
    }
    public static void compararPrecioProductoaComprar(double precioPrimerProducto){
        Assert.assertEquals(precioPrimerProducto, obtenerPrecio(driver.findElement(precioProdcuto).getText()));
    }
    public static void setModalCompras(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalCompras));
    }
    public static void validarPreciofinalCompra(int cantidadProducto, double precioPRoduco, double Shipping ){
        final var finalPrecio = (cantidadProducto*precioPRoduco) + Shipping;
        setModalCompras();
        Logs.info("Precio final %s",obtenerPrecio(driver.findElement(precioTotalCompra).getText()));
        Assert.assertEquals(redondear(finalPrecio,1),obtenerPrecio(driver.findElement(precioTotalCompra).getText()));
    }



    //Metodos reutilizacion
    public static void Login(String correo,String contra){
        Logs.info("Ingresamos a la URL de home");
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        Logs.info("Navegamos hasta la pantalla de login");
        driver.findElement(linkLogin).click();

        Logs.info("Ingresamos el correo y la contraseña");
        driver.findElement(campoEmail).sendKeys(correo);
        driver.findElement(campoContra).sendKeys(contra);

        Logs.info("Enviamos los datos de login");
        driver.findElement(buttonLogin).click();
    }
    public static double obtenerPrecio(String precio){
        String doublePrecio = precio.replaceAll("[^\\d.]", "");
        return Double.parseDouble(doublePrecio);
    }
}

