package Automation;

import Utilities.BaseTest;
import Utilities.Logs;
import Utilities.WebDriverProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if(opcion.equals("Name, A to Z")){
                element.click();
                break;
            }
        }

        Logs.debug("Obtenemos el primero producto del listado, hacemos una espera que carge el ordenamiento seleccionado");
        Thread.sleep(2000);
        final var primerProducto = driver.findElement(By.cssSelector("h2[class='h3 product-title']")).getText().toUpperCase();
        Logs.debug("Capturamos el precio del primero producto en la lista");
        final var precioPrimeroProducto = driver.findElement(By.cssSelector("div[class='product-price-and-shipping']")).getText();

        Logs.info("Damos click en producto del primero de la Lista");
        driver.findElement(By.xpath("//img[@alt='Brown bear cushion']")).click();

        Logs.debug("Capturamos el titulo del producto seleccionnado y el precio");
        final var productoSeleccionado = driver.findElement(By.cssSelector("h1[class='h1']")).getText();
        final var precioProductoSelecionado = driver.findElement(By.cssSelector("div[class='current-price']")).getText();

        Logs.debug("Comparamos titulos y precios");
        softAssertl.assertEquals(productoSeleccionado,primerProducto);
        softAssertl.assertEquals(precioPrimeroProducto,precioProductoSelecionado);
        softAssertl.assertAll();

        Logs.info("Indicamos que vamos a adquirir 5 articulos");
        for (int i = 1; i < 5; i++) {
            driver.findElement(
                    By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']"))
                    .click();
        }


        Logs.debug("Hacemos click en añadir a Carrito");
        driver.findElement(By.cssSelector("button[class='btn btn-primary add-to-cart']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='cart-content']")));

        final var cantidadProducto = driver.findElement(By.cssSelector("span[class='product-quantity']")).getText();

        Logs.debug("la cantidad de prodcuto es %s", cantidadProducto);

        Logs.debug("Extraermos el precio total de la compra ");
        final var precioTotal = driver.findElement(By.cssSelector("p[class=\"product-total\"]")).getText();

        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(precioTotal);

        String precioCompra = "";
        if (matcher.find()) {
            precioCompra = matcher.group(); // Captura el número
        }

        Logs.debug("El precio total de la compra es %s", precioCompra);

        final var precioMostrado = Double.parseDouble(precioCompra);
        Logs.debug("Comparamos el total mostrado con la esperado");
        precioFinal(ExtraerNumeroDouble(cantidadProducto),ExtraerNumeroDouble(precioProductoSelecionado) , 7, precioMostrado);

    }
}
