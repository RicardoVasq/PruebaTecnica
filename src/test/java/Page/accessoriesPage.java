package Page;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import java.util.List;

public class accessoriesPage extends BaseTest {

    //Localizadores
    private static By errorMensajeLogin = By.xpath("//li[text()='Authentication failed.']");
    private static By linkLogin = By.cssSelector("div[class='user-info']");
    private static By campoEmail = By.id("field-email");
    private static By campoContra =  By.id("field-password");
    private static By buttonLogin = By.id("submit-login");
    private static By nombreUsuarioLogeado = By.xpath("//span[text()='Ricardo Vasquez']");
    private static By ingresoAccesories = By.id("category-6");
    private static By buttonOrden = By.cssSelector("button[data-toggle='dropdown']");
    private static By opcionesOrden = By.cssSelector("a[class='select-list js-search-link']");
    private static By primerProducto = By.cssSelector("article[class=\"product-miniature js-product-miniature\"]");
    private static By buttonAnadirCantidadProducto = By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']");
    private static By clickPrimerProducto = By.cssSelector("div[class='product-description']");
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

