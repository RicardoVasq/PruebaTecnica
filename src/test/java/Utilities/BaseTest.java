package Utilities;

import listeners.SuiteListeners;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected WebDriver driver;



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
}
