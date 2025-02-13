package Utilities;

import listeners.SuiteListeners;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


@Listeners({TestListener.class, SuiteListeners.class})
public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected SoftAssert softAssertl;
    protected final String smoke = "regression";
    protected final String regression = "regression";
    protected static WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void mastersetUp() {
        softAssertl = new SoftAssert();
        Logs.debug("Inicializamos el Webdriver");
        driver = new ChromeDriver();

        Logs.debug("Maximizamos pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando cookies");
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Logs.debug("asignamos el webdriver");
        new WebDriverProvider().set(driver);
    }

    @AfterMethod (alwaysRun = true)
    public void mastertearDown() {
        Logs.debug("Finalizamos el webDriver");
        driver.quit();
    }

    //Metodos - reutilizables
    public void redireccionContactus(){
        final var urlPrincipal = "https://teststore.automationtesting.co.uk/index.php";
        Logs.info("Ingresamos a la URL: %s", urlPrincipal);
        driver.get(urlPrincipal);

        Logs.info("Navegamos a la pagina de Contact us");
        driver.findElement(By.id("contact-link")).click();

        Logs.info("Validamos la redireccion hacia la pagina Contact US");
        final var CurrentUrl = driver.getCurrentUrl();
        Assert.assertEquals(CurrentUrl,
                "https://teststore.automationtesting.co.uk/index.php?controller=contact");

        Logs.info("Validamos que esten visibles los campos Subject, email, message");

    }

    public void Login(String correo,String contra){
        Logs.info("Ingresamos a la URL de home");
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        Logs.info("Navegamos hasta la pantalla de login");
        driver.findElement(By.cssSelector("div[class='user-info']")).click();

        Logs.info("Ingresamos el correo y la contraseña");
        driver.findElement(By.id("field-email")).sendKeys(correo);
        driver.findElement(By.id("field-password")).sendKeys(contra);

        Logs.info("Enviamos los datos de login");
        driver.findElement(By.id("submit-login")).click();
    }


}
