package Automation;

import Utilities.BaseTest;
import Utilities.Logs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class caso3ProcesoCompra extends BaseTest {

    @Test
    public void Caso3Test() throws InterruptedException {
        Logs.info("Ingresamos credenciales invalidas");
        Login("false@gg.com", "noescorrecta");

        Logs.info("Validamos el mensaje de error");
        Assert.assertTrue(driver.findElement(By.xpath
                ("//li[text()='Authentication failed.']")).isDisplayed());

        Logs.info("Ingresamos con credenciales validas");
        Login("prueba@gg.com","ricardo");
        Assert.assertTrue(driver.findElement(By.xpath
                ("//span[text()='Ricardo Vasquez']")).isDisplayed());

        Logs.info("Navegamos hasta la seccion de Accessories");
        driver.findElement(By.id("category-6")).click();
        driver.findElement(By.cssSelector("button[data-toggle='dropdown']")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("a[class='select-list js-search-link']"));
        for (WebElement element : elements){
            final var opcion = element.getText();
            if(opcion.equals("Name, Z to A")){
                element.click();
                break;
            }
        }
        Logs.debug("Obtenemos la lista de productos");
        List<WebElement> productos = driver.findElements(By.cssSelector("article[class=\"product-miniature js-product-miniature\"]"));

        Logs.debug(" Crear un array de Strings para almacenar los textos de los productos");
        String[] productosArray = new String[productos.size()];

        Logs.debug("Llenar el array con los textos de los productos");
        for (int i = 0; i < productos.size(); i++) {
            productosArray[i] = productos.get(i).getText();
        }
        Arrays.sort(productosArray);

        Logs.debug("Capturamos el primer producto de la lista ordenada");
        final var ordenProductos = productosArray[0];

        Logs.debug("Separamos el nombre del producto del precio");
        String[] division = ordenProductos.split("\n");

        Logs.debug("Capturamos el nombre del primer producto ");
        final var primerProducto =  division[0];
        Logs.debug("Capturamos el precio del primer producto");
        final var  primerPrecio = (division[1]);


        for (int i = 0; i < 3; i++) {
            try {
                WebElement image = driver.findElement(By.xpath("//img[@alt='Brown bear cushion']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);
                image.click();
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Elemento no válido, reintentando...");
            }
        }


        Logs.debug("Comparamos precio y nombre del producto");
        final var titulo = driver.findElement(By.cssSelector("h1[class='h1']")).getText();
        final var upperCase = titulo.toUpperCase(Locale.ROOT);
        softAssertl.assertEquals(upperCase, primerProducto.toUpperCase());
        softAssertl.assertEquals(driver.findElement(By.cssSelector("span[class='current-price-value']")).getText(),
                primerPrecio);
        softAssertl.assertAll();

        for (int i = 1; i <= 4; i++) {
            driver.findElement(By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']")).click();
        }

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='qty']")).getAttribute("value"),"5");

        driver.findElement(By.cssSelector("button[data-button-action=\"add-to-cart\"]")).click();

        Logs.info("damos click al confirmar la compra ");
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("button[class=\"btn btn-secondary\"]")).click();

    }
}
